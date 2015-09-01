package com.castis.winter.util;

import java.awt.FontMetrics;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Appl. 전체에 사용 되는 String Util Class.</br> 용도에 따라 변수명을 재정의하거나 추가하여 사용.</br> Static 멤버 변수들로 구성되어 직접 사용이 가능하다.
 */
public class StringUtil {
    /**
     * 널문자를 ""로 변환
     * 
     * @param str
     *            변환대상 문자열
     * @return 변환된 문자열
     */
    public static String nullToBlank(String str) {
	String result = str;
	if (result == null) {
	    result = "";
	}
	return result;
    }

    /**
     * 널문자이면 해당 문자열로 변환
     * 
     * @param str
     *            문자열
     * @param conv
     *            변환문자열
     * @return 변환문자열
     */
    public static String nullToString(String str, String conv) {
	String result = str;
	if (result == null) {
	    result = conv;
	}
	return result;
    }

    /**
     * 문자열을 원하는 크기로 잘라낸다.
     * 
     * @param str
     *            문자열
     * @param len
     *            자르기시작할 위치
     * @param attachment
     *            잘라낸이후 붙일 문자 열(예 ...)
     * @return 잘라낸 문자열
     */
    public static String sliceString(String str, int len, String attachment) {
	if (str == null || str.length() < 1)
	    return "";
	if (str.length() > len) {
	    return str.substring(0, len) + attachment;
	}
	return str;
    }

    /**
     * 문자열을 원하는 크기(byte 단위)로 잘라낸다. String str :문자열 int len : 자르기시작할 위치 int attachment : 잘라낸이후 붙일 문자 열(예 ...)
     */
    public static String trimString(String str, int len, String attachment) {
	StringBuffer temp = new StringBuffer();
	temp.delete(0, temp.length());
	int j, k;
	if (str == null || str.length() < 1)
	    return "";
	if (str.getBytes().length > len + 1) {
	    for (j = 0, k = 0; j < len; j++, k++) {
		if (str.substring(j, j + 1).getBytes().length == 1) {
		    temp.append(str.substring(j, j + 1));
		} else if (str.substring(j, j + 1).getBytes().length == 2) {
		    temp.append(str.substring(j, j + 1));
		    k++;
		} else if (str.substring(j, j + 1).getBytes().length == 3) {
		    temp.append(str.substring(j, j + 1));
		    k++;
		    k++;
		}
		if (k > len)
		    break;
	    }
	    str = temp.append(attachment).toString();
	}
	// null처리
	temp = null;
	return str;
    }

    /**
     * 2byte 문자의 갯수를 가져온다. String str :문자열 int len : 자르기시작할 위치 int attachment : 잘라낸이후 붙일 문자 열(예 ...)
     */
    public static int get2ByteStringCount(String str) {
	int j, k;
	if (str == null || str.length() < 1)
	    return 0;
	for (j = 0, k = 0; j < str.length(); j++, k++) {
	    if (str.substring(j, j + 1).getBytes().length == 1) {
	    } else if (str.substring(j, j + 1).getBytes().length == 2) {
		k++;
	    }
	}
	return k;
    }

    /**
     * 문자열을 원하는 크기로 잘라내어 String 배열에 담아준다. String str :문자열 int arraySize : 배열사이즈 int splitSize : 잘라낼 사이즈
     */
    public static String[] trimString(String str, int arraySize, int splitSize) {
	String[] result = new String[arraySize];
	if (str == null || str.length() < 1)
	    return result;
	int j = 0, k = 0;
	for (int i = 0; i < result.length; i++) {
	    k = (i + 1) * splitSize;
	    if (str.length() > k && str.length() > j) {
		result[i] = str.substring(j, k);
	    } else if (str.length() < k && str.length() > j) {
		result[i] = str.substring(j);
	    } else {
		result[i] = "";
	    }
	    j = k;
	}
	return result;
    }

    /**
     * 문자열을 원하는 크기부터 원하는 크기로 (byte 단위)로 잘라낸다. String str : 문자열 int start : 자르기시작할 위치 int end : 자르기시작할 위치 int attachment : 잘라낸이후 붙일 문자 열(예 ...)
     */
    public static String trimString(String str, int start, int end, String attachment) {
	StringBuffer temp = new StringBuffer();
	temp.delete(0, temp.length());
	int j, k;
	if (str == null || str.length() < 1)
	    return "";
	if (str.getBytes().length > end + 1) {
	    for (j = 0, k = 0; k < end; j++, k++) {
		if (str.substring(j, j + 1).getBytes().length == 1) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		} else if (str.substring(j, j + 1).getBytes().length == 2) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		    k++;
		} else if (str.substring(j, j + 1).getBytes().length == 3) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		    k++;
		    k++;
		}
		if (k > end)
		    break;
	    }
	    str = temp.append(attachment).toString();
	} else if (str.getBytes().length > start + 1) {
	    for (j = 0, k = 0; k < str.getBytes().length; j++, k++) {
		if (str.substring(j, j + 1).getBytes().length == 1) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		} else if (str.substring(j, j + 1).getBytes().length == 2) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		    k++;
		} else if (str.substring(j, j + 1).getBytes().length == 3) {
		    if (k > start)
			temp.append(str.substring(j, j + 1));
		    k++;
		    k++;
		}
		if (k > end)
		    break;
	    }
	    str = temp.append(attachment).toString();
	} else {
	    str = "";
	}
	// null처리
	temp = null;
	return str;
    }

    /**
     * 넘겨진 배열형태의 데이터를 주어진문자로 연결한 스트링 리턴
     * 
     * @param sep
     *            구분자
     * @param obj
     *            배열형태의 데이터
     * @return String
     */
    public static String implode(String sep, String[] obj) {
	StringBuffer temp = new StringBuffer(100);
	int i = 0;
	while (i < obj.length) {
	    if (temp.length() > 0)
		temp.append(sep);
	    temp.append(obj[i]);
	    i++;
	}
	return temp.toString();
    }

    /**
     * 넘겨진 배열형태의 데이터를 주어진문자로 연결한 스트링 리턴
     * 
     * @param sep
     *            구분자
     * @param obj
     *            배열형태의 데이터
     * @return String
     */
    public static String implode(String sep, Collection obj) {
	StringBuffer temp = new StringBuffer(100);
	Iterator itr = obj.iterator();
	while (itr.hasNext()) {
	    String objstr = (String) itr.next();
	    if (temp.length() > 0)
		temp.append(sep);
	    temp.append(objstr);
	}
	return temp.toString();
    }

    /**
     * 해당문자열을 주어진문자를 기준으로 나누어 배열로 리턴
     * 
     * @param sep
     *            구분자
     * @param str
     *            해당문자열
     * @return Collection
     */
    public static Collection explode(String sep, String str) {
	Collection retCol = null;
	if (str != null) {
	    StringTokenizer strtoken = new StringTokenizer((String) str, (String) sep);
	    if (strtoken.countTokens() > 0) {
		retCol = new ArrayList();
		while (strtoken.hasMoreTokens()) {
		    retCol.add(strtoken.nextToken());
		}
	    }
	}
	return retCol;
    }

    /**
     * Input String을 주어진문자를 기준으로 나누어 배열로 리턴
     * 
     * @param sep
     *            구분자
     * @param str
     *            Input String
     * @return 변환 String[]
     */
    public static String[] split(String str, String sep) {
	String[] tempString = null;
	if (str != null) {
	    StringTokenizer strToken = new StringTokenizer(str, sep);
	    tempString = new String[strToken.countTokens()];
	    for (int i = 0; i < tempString.length; i++)
		tempString[i] = strToken.nextToken();
	} else {
	    tempString = new String[0];
	    tempString[0] = "";
	}
	return tempString;
    }

    /**
     * Input String을 주어진문자를 기준으로 나누어 ArrayList로 리턴
     * 
     * @param sep
     *            구분자
     * @param str
     *            Input String
     * @return 변환 ArrayList
     */
    public static ArrayList splitArrayList(String str, String sep) {
	// String[] tempString = null;
	ArrayList arrayList = new ArrayList();
	if (str != null) {
	    StringTokenizer strToken = new StringTokenizer(str, sep);
	    // tempString = new String[strToken.countTokens()];
	    int size = strToken.countTokens();
	    for (int i = 0; i < size; i++)
		arrayList.add(strToken.nextToken());
	} else {
	}
	return arrayList;
    }

    /**
     * 문자열에서 해당문자를 원하는 문자로 변경한다.
     * 
     * @param org
     *            : 문자열
     * @param var
     *            : 변환할 문자열
     * @param tgt
     *            : 변환될 문자열
     * @return : 변환된 문자열
     */
    public static String replaceString(String org, String var, String tgt) {
	StringBuffer temp = new StringBuffer(1024);
	int end = 0;
	int begin = 0;
	while (true) {
	    end = org.indexOf(var, begin);
	    if (end == -1) {
		end = org.length();
		temp.append(org.substring(begin, end));
		break;
	    }
	    temp.append(org.substring(begin, end)).append(tgt);
	    begin = end + var.length();
	}
	return temp.toString();
    }

    /**
     * ASKII 문자셋을 KSC 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String ascii2ksc(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    // JS String Object를 전달할 때 Unicode로 전달함.
	    // 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로
	    // java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
	    // 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
	    byte[] b = str.getBytes("8859_1");
	    rtnStr = new String(b, "KSC5601");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * KSC 문자셋을ASKII 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String ksc2ascii(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    byte[] b = str.getBytes("KSC5601");
	    rtnStr = new String(b, "8859_1");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * ASKII 문자셋을 EUC_KR 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String ascii2euckr(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    // Communicator는 JS String Object를 전달할 때 Unicode로 전달함.
	    // 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로
	    // java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
	    // 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
	    byte[] b = str.getBytes("8859_1");
	    rtnStr = new String(b, "EUC_KR");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * EUC_KR 문자셋을 ASKII 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String euckr2ascii(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    byte[] b = str.getBytes("EUC_KR");
	    rtnStr = new String(b, "8859_1");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * ASKII 문자셋을 EUC_JP 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String ascii2eucjp(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    // Communicator는 JS String Object를 전달할 때 Unicode로 전달함.
	    // 그러나 문자열을 8859_1로 해석하여 Unicode로 변환하므로
	    // java에서 String으로 처리할 때는 그 반대 과정을 거쳐 char set를
	    // 변환해야 한다. 즉, 8859_1 byte array로 읽어 KSC5601로 변환한다.
	    byte[] b = str.getBytes("8859_1");
	    rtnStr = new String(b, "EUC_JP");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * EUC_JP 문자셋을 ASKII 문자셋으로 변환
     * 
     * @param str
     *            원본 스트링
     * @return 변환 스트링
     */
    public static String eucjp2ascii(String str) {
	String rtnStr = null;
	if (str == null)
	    return "";
	try {
	    byte[] b = str.getBytes("EUC_JP");
	    rtnStr = new String(b, "8859_1");
	} catch (UnsupportedEncodingException e) {
	    rtnStr = str;
	}
	return rtnStr;
    }

    /**
     * Input String이 한글인지를 판단한다.
     * 
     * @param str
     *            Input String
     * @return true 한글,</br> false 한글이 아님
     */
    public static boolean isHangul(String str) {
	char ch;
	for (int i = 0; i < str.length(); i++) {
	    ch = str.charAt(i);
	    if (ch == '\r' || ch == '\n' || ch == '\t') {
		continue;
	    }
	    if ((int) ch < 0x20 || (int) ch > 0x7f) {
		return true;
	    }
	}
	return false;
    }

    /**
     * 문자열을 일정 사이즈로 Parsing한다.
     * 
     * @param title
     *            원본 스트링
     * @param size
     *            Parsing 사이즈
     * @param fontMetrics
     *            Graphics Class fontMetrics
     * @return Parsing된 문자열
     */
    private static String getParseString(String title, int size, FontMetrics fontMetrics) {
	if (fontMetrics.stringWidth(title) > size) {
	    return getParseString(title.substring(0, title.length() - 1), size, fontMetrics);
	} else {
	    return title;
	}
    }

    /**
     * 문자열을 일정 사이즈로 Parsing 후 접미사를 붙여 리턴한다.
     * 
     * @param title
     *            원본 스트링
     * @param attachment
     *            접미사
     * @param size
     *            Parsing 사이즈
     * @param attachmentSize
     *            접미사 사이즈
     * @param fontMetrics
     *            Graphics Class fontMetrics
     * @return Parsing된 문자열
     */
    public static String getParseString(String title, String attachment, int size, int attachmentSize, FontMetrics fontMetrics) {
	if (fontMetrics.stringWidth(title) > size) {
	    title = getParseString(title.substring(0, title.length() - 1), size, fontMetrics);
	    title = title.substring(0, title.length() - attachmentSize) + attachment;
	}
	return title;
    }

    /**
     * 문자열을 원하는 너비와 array 사이즈로 Parsing하여 리턴한다.
     * 
     * @param str
     *            원본 스트링(ex. 줄거리)
     * @param width
     *            너비
     * @param arraySize
     *            리턴할 array 사이즈
     * @param fontMetrics
     *            Graphics Class fontMetrics
     * @return String[]
     */
    public static String[] getFitStrings(String str, int width, int arraySize, FontMetrics fontMetrics) {
	return getFitStrings(str, width, arraySize, "..", fontMetrics);
    }

    public static String[] getFitStrings(String str, int width, int arraySize, String attachment, FontMetrics fontMetrics) {
	String[] returnStr = new String[arraySize];
	for (int i = 0; i < arraySize; i++)
	    returnStr[i] = "";
	if (str == null || str.length() < 1)// || context == null)
	{
	    return returnStr;
	}
	int limit = str.length();// length
	int strIndex = 0;
	int currentStrLength = 0;
	for (int i = 0; i < arraySize; i++) {
	    str = str.substring(strIndex);
	    limit = str.length();
	    strIndex = 0;
	    currentStrLength = 0;
	    while ((currentStrLength < width) && (strIndex < limit)) {
		strIndex = strIndex + 1;
		if (str.length() < strIndex) {
		    returnStr[i - 1] = str.substring(0, returnStr[i - 1].length() - 1) + attachment;
		    return returnStr;
		}
		currentStrLength = fontMetrics.stringWidth(str.substring(0, strIndex));
	    }
	    returnStr[i] = str.substring(0, strIndex);
	    if (i == arraySize - 1 && str.length() > returnStr[i].length())
		returnStr[i] = str.substring(0, returnStr[i].length() - 1) + attachment;
	}
	return returnStr;
    }

    // =====================
    public static ArrayList getFitStrings(String str, int width, FontMetrics fontMetrics) {
	ArrayList arrayList = new ArrayList();
	if (str == null || str.length() < 1)// || context == null)
	{
	    return arrayList;
	}
	int limit = str.length();// length
	int strIndex = 0;
	int currentStrLength = 0;
	int currentIndex = 0;
	while (strIndex < limit) {
	    str = str.substring(strIndex);
	    limit = str.length();
	    strIndex = 0;
	    currentStrLength = 0;
	    while ((currentStrLength < width) && (strIndex < limit)) {
		strIndex = strIndex + 1;
		if (str.length() < strIndex) {
		    arrayList.add(currentIndex - 1, str.substring(0, arrayList.get(currentIndex - 1).toString().length() - 1));
		    return arrayList;
		}
		currentStrLength = fontMetrics.stringWidth(str.substring(0, strIndex));
	    }
	    arrayList.add(currentIndex, str.substring(0, strIndex));
	    currentIndex++;
	}
	return arrayList;
    }

    /**
     * Input String이 숫자 인지를 판단한다.
     * 
     * @param str
     *            Input String
     * @return true 한글,</br> false 한글이 아님
     */
    public static boolean numberCheck(String str) {
	char c = ' ';
	if (str.equalsIgnoreCase(""))
	    return false;
	for (int i = 0; i < str.length(); i++) {
	    c = str.charAt(i);
	    if (c < 48 || c > 59) {
		return false;
	    }
	}
	return true;
    }

    /**
     * 
     * @param str
     *            문자열
     * @param sep
     *            Split 문자
     * @param size
     *            넓이
     * @param fontMetrics
     * @return 문자배열
     */
    public static String[] splitFit(String str, String sep, int size, FontMetrics fontMetrics) {
	String[] tempString = null;
	String[] returnString = null;
	ArrayList arrayString = new ArrayList();
	if (str != null) {
	    tempString = split(str, sep);
	    for (int i = 0; i < tempString.length; i++) {
		String temp_str[] = getParseString2(tempString[i], size, fontMetrics);
		for (int j = 0; j < temp_str.length; j++) {
		    arrayString.add(temp_str[j]);
		}
	    }
	    returnString = new String[arrayString.size()];
	    for (int i = 0; i < arrayString.size(); i++) {
		returnString[i] = (String) arrayString.get(i);
	    }
	} else {
	    returnString = new String[0];
	    returnString[0] = "";
	}
	return returnString;
    }

    public static String[] splitFit(String str, String sep, int size, int arrSize, FontMetrics fontMetrics) {
	String[] strArray = splitFit(str, sep, size, fontMetrics);
	String[] desArray = null;
	if (strArray.length > arrSize) {
	    desArray = new String[arrSize];
	    System.arraycopy(strArray, 0, desArray, 0, arrSize);
	} else {
	    desArray = new String[strArray.length];
	    System.arraycopy(strArray, 0, desArray, 0, strArray.length);
	}
	return desArray;
    }

    private static String[] getParseString2(String title, int size, FontMetrics fontMetrics) {
	String[] return_title = null;
	if (fontMetrics.stringWidth(title) > size) {
	    ArrayList array_title = new ArrayList();
	    String temp_str = "";
	    for (int i = 0; i < title.length(); i++) {
		temp_str += title.substring(i, i + 1);
		if (fontMetrics.stringWidth(temp_str) > size) {
		    array_title.add(temp_str.substring(0, temp_str.length() - 1));
		    temp_str = temp_str.substring(temp_str.length() - 1, temp_str.length());
		}
	    }
	    array_title.add(temp_str);
	    return_title = new String[array_title.size()];
	    for (int i = 0; i < array_title.size(); i++) {
		return_title[i] = (String) array_title.get(i);
	    }
	} else {
	    return_title = new String[1];
	    return_title[0] = title;
	}
	return return_title;
    }

    public static boolean isDigit(String digit) {
	try {
	    Integer.parseInt(digit);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }
}
