package com.castis.winter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import com.castis.winter.Winter.ProductType;

public class PurchasedHistory {
    String[] purchasedData;
    final static int MAXCOUNT = 100;

    public String[] getPurchasedData() {
	return purchasedData;
    }
    private static final PurchasedHistory instance = new PurchasedHistory();

    public static PurchasedHistory getInstance() {
	return instance;
    }

    private PurchasedHistory() {
    }

    /**
     * 읽어올 때 지난 1일 이전의 데이터는 삭제해야한다.
     * 
     * @throws IOException
     */
    // BufferedReader reader = new BufferedReader(new InputStreamReader(new
    // FileInputStream(filepath),"UTF8"));
    public String[] readFromFile() throws IOException {
	String[] data = new String[MAXCOUNT];
	String filePath = "cvod/purchased.history";
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(/*STBInfoAgent.getInstance().getRootDirectory()*/
		File.separator + filePath), "UTF-8"));
	int count = 0;
	while (count < MAXCOUNT) {
	    String buffer;
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -60);
	    if ((buffer = bufferedReader.readLine()) == null) {
		break;
	    }
	    PurchaseData purchasedData = parsingLowData(buffer);
	    if (cal.getTime().getTime() > purchasedData.getPurchasedDate().getTime()
		    || (purchasedData.getProductType().equalsIgnoreCase(ProductType.getType(ProductType.RVOD))
			    || purchasedData.getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD)) || purchasedData.getProductType()
			    .equalsIgnoreCase(ProductType.getType(ProductType.Package)))) {
		// break;
	    } else {
		data[count] = buffer;
		count++;
	    }
	}
	purchasedData = new String[count];
	System.arraycopy(data, 0, purchasedData, 0, count);
	// fileReader.close();
	bufferedReader.close();
	return purchasedData;
    }

    public void deletePurchaseData(int index) throws IOException {
	if (purchasedData == null) {
	    Logger.I(this, "purchasedData is NULL");
	    return;
	}
	int count = purchasedData.length;
	Logger.I(this, "purchaseData length = " + purchasedData.length);
	String[] purchaseDataBuffer = new String[count - 1];
	int dataIndex = 0;
	int deletePosition = count - 1 - index;
	for (int i = 0; i < count; i++) {
	    if (i != deletePosition) {
		purchaseDataBuffer[dataIndex] = purchasedData[i];
		Logger.I(this, "purchaseData = " + purchaseDataBuffer[dataIndex]);
		dataIndex++;
	    }
	}
	purchasedData = new String[purchaseDataBuffer.length];
	purchasedData = purchaseDataBuffer;
	writePurchasedDataToFile(purchasedData);
    }

    /**
     * RAW 데이터 규격 (cvod/purchased.history) 상품명#구매일(long값)#금액#쿠폰사용여부(boolean)#상품타입(ISU,COUPON,SVOD,SERIES.,RVOD) String productName; Date purchasedDate; int price; boolean isUsingCoupon; String productType;
     * 
     * @param contentsName
     * @param date
     * @param price
     * @param isCoupon
     * @param isMonth
     * @throws IOException
     */
    public void makePurchasedData(String contentsName, long purchasedDate, int price, boolean isUsingCoupon, String productType) throws IOException {
	String data = null;
	StringBuffer sb = new StringBuffer();
	sb.append(contentsName);
	sb.append("#");
	sb.append(purchasedDate);
	sb.append("#");
	sb.append(price);
	sb.append("#");
	sb.append(isUsingCoupon);
	sb.append("#");
	sb.append(productType);
	data = sb.toString();
	Logger.I(this, "Make PurchaseData : " + data);
	writePurchasedData(data);
    }

    public void writePurchasedDataToFile(String data[]) throws IOException {
	Logger.I(this, "writePurchasedDataToFile");
	String rootDirectory = System.getProperties().getProperty("com.alticast.persistent.root");
	String filePath = "cvod/purchased.history";
	if (rootDirectory == null) {
	    rootDirectory = "";
	}
	FileOutputStream fos = new FileOutputStream(rootDirectory + File.separator + filePath);
	OutputStreamWriter streamWriter = new OutputStreamWriter(fos, "UTF-8");
	int length = data.length;
	for (int i = 0; i < length; i++) {
	    streamWriter.write(data[i] + "\n");
	}
	streamWriter.close();
    }

    public PurchaseData[] getPurchaseDataList() {
	try {
	    return getPurchaseDataList(readFromFile());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public PurchaseData[] getPurchaseDataList(String[] lowDatas) {
	if (lowDatas != null) {
	    int count = lowDatas.length;
	    PurchaseData[] purchaseData = new PurchaseData[count];
	    int index = count - 1;
	    for (int i = 0; i < count; i++) {
		purchaseData[index] = parsingLowData(lowDatas[i]);
		index--;
	    }
	    return purchaseData;
	} else {
	    return null;
	}
    }

    public PurchaseData parsingLowData(String lowData) {
	PurchaseData purchasedData = new PurchaseData();
	StringTokenizer strToken = new StringTokenizer(lowData, "#");
	purchasedData.setProductName(strToken.nextToken());
	purchasedData.setPurchasedDate(new Date(Long.parseLong(strToken.nextToken())));
	purchasedData.setPrice(Integer.parseInt(strToken.nextToken()));
	purchasedData.setUsingCoupon(strToken.nextToken().equalsIgnoreCase("true") ? true : false);
	purchasedData.setProductType(strToken.nextToken());
	return purchasedData;
    }

    public void writePurchasedData(String data) {
	String[] tempArray = new String[1];
	if (this.purchasedData == null) {
	    try {
		readFromFile();
	    } catch (IOException e) {
	    }
	}
	int length;
	if (this.purchasedData == null) {
	    length = 0;
	} else {
	    length = purchasedData.length;
	}
	if (length == MAXCOUNT) {
	    tempArray = new String[MAXCOUNT];
	    System.arraycopy(purchasedData, 1, tempArray, 0, MAXCOUNT - 1);
	    tempArray[MAXCOUNT - 1] = data;
	} else if (length == 0) {
	    tempArray[0] = data;
	} else {
	    tempArray = new String[length + 1];
	    System.arraycopy(purchasedData, 0, tempArray, 0, length);
	    tempArray[length] = data;
	}
	try {
	    writePurchasedDataToFile(tempArray);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	purchasedData = null;
    }
    private Vector data;
    private int[] filePurchaseData;

//    public Vector sortingData(CustomPurchaseLog[] logs, PurchaseData[] purchaseData) {
//	data = new Vector();
//	int startIndex = 0;
//	if (logs == null) {
//	    if (purchaseData != null) {
//		filePurchaseData = new int[purchaseData.length];
//		for (int i = 0; i < purchaseData.length; i++) {
//		    data.add(purchaseData[i]);
//		    filePurchaseData[i] = data.size() - 1;
//		}
//	    }
//	} else if (purchaseData != null) {
//	    filePurchaseData = new int[purchaseData.length];
//	    for (int i = 0; i < logs.length; i++) {
//		if (startIndex < purchaseData.length) {
//		    String purchaseTime = logs[i].getPurchasedTime();
//		    for (int j = startIndex; j < purchaseData.length; j++) {
//			if ((DateUtil.getDate(purchaseTime)).getTime() > purchaseData[j].getPurchasedDate().getTime()) {
//			    data.add(logs[i]);
//			    break;
//			} else {
//			    data.add(purchaseData[j]);
//			    filePurchaseData[j] = data.size() - 1;
//			    startIndex++;
//			}
//			if (j == purchaseData.length - 1) {
//			    data.add(logs[i]);
//			}
//		    }
//		} else {
//		    data.add(logs[i]);
//		}
//	    }
//	    for (int i = startIndex; i < purchaseData.length; i++) {
//		data.add(purchaseData[i]);
//		filePurchaseData[i] = data.size() - 1;
//	    }
//	} else {
//	    for (int i = 0; i < logs.length; i++) {
//		data.add(logs[i]);
//	    }
//	}
//	return data;
//    }
}
