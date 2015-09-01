//package com.castis.winter.util;
//
//import java.awt.Image;
//import java.text.NumberFormat;
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Date;
//
//import org.json.me.JSONArray;
//import org.json.me.JSONException;
//
//import com.castis.repo.CouponAgent;
//import com.castis.repo.ProductAgent;
//import com.castis.repo.model.CustomCategory;
//import com.castis.repo.model.CustomContent;
//import com.castis.repo.model.CustomCoupon;
//import com.castis.repo.model.CustomProduct;
//import com.castis.repo.model.Label;
//import com.castis.winter.Winter.ProductType;
//import com.castis.winter.window.ImageResource;
//
//public class UIHelper {
//    public static Label[] setButtonData(CustomContent content) {
//	Label[] buttonData;
//	if (UIHelper.isPurchased(content.getProducts())) {
//	    if (content.hasPreview()) {
//		buttonData = Label.PVPLCL;
//	    } else {
//		buttonData = Label.PLCL;
//	    }
//	} else {
//	    if (ProductAgent.getInstance().getUsingProduct() != null
//		    && ProductAgent.getInstance().getUsingProduct().getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD))) {
//		if (CouponAgent.getInstance().getCategoryCoupon(content.getCategoryID(), CouponAgent.freeCoupon) != null) {
//		    if (content.hasPreview())
//			buttonData = Label.PVCJCL;
//		    else
//			buttonData = Label.CJCL;
//		} else {
//		    if (content.hasPreview())
//			buttonData = Label.PVJOCL;
//		    else
//			buttonData = Label.JOCL;
//		}
//	    } else {
//		CustomCoupon assetCoupon = CouponAgent.getInstance().getAssetCoupon(content.getAssetID());
//		if (assetCoupon != null && assetCoupon.getCouponType().equalsIgnoreCase(CouponAgent.freeCoupon)) {
//		    if (content.hasPreview()) {
//			buttonData = Label.PVCPCL;
//		    } else {
//			buttonData = Label.CPCL;
//		    }
//		} else {
//		    if (content.hasPreview()) {
//			buttonData = Label.PVPUCL;
//		    } else {
//			buttonData = Label.PUCL;
//		    }
//		}
//	    }
//	}
//	return buttonData;
//    }
//
//    public static Label[] getPurcasheListLabel(CustomContent content, CustomProduct product) {
//	if (isPurchased(product)) {
//	    return Label.PLDEDLCL;
//	} else {
//	    if (CouponAgent.getInstance().haveAssetCoupon(content.getAssetID())
//		    && CouponAgent.getInstance().getAssetCoupon(content.getAssetID()).getCouponType().equalsIgnoreCase(CouponAgent.freeCoupon)) {
//		return Label.USDEDLCL;
//	    } else if (product.getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD)))
//		return Label.JODEDLCL;
//	    else {
//		return Label.PUDEDLCL;
//	    }
//	}
//    }
//
//    public static Label[] getLabel(CustomContent content, CustomProduct product) {
//	if (content.getProducts().length > 1) {
//	    return getLabelForMultiAsset(content);
//	}
//	if (isPurchased(product)) {
//	    if (content.hasPreview()) {
//		return Label.PLPVDECL;
//	    } else {
//		return Label.PLDECL;
//	    }
//	} else {
//	    if (content.hasPreview()) {
//		if (CouponAgent.getInstance().haveAssetCoupon(content.getAssetID())
//			&& CouponAgent.getInstance().getAssetCoupon(content.getAssetID()).getCouponType().equalsIgnoreCase(CouponAgent.freeCoupon)) {
//		    return Label.USPRDECL;
//		} else if (product.getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD)))
//		    return Label.JODECL;
//		else {
//		    return Label.PUPVDECL;
//		}
//	    } else {
//		if (CouponAgent.getInstance().haveAssetCoupon(content.getAssetID())
//			&& CouponAgent.getInstance().getAssetCoupon(content.getAssetID()).getCouponType().equalsIgnoreCase(CouponAgent.freeCoupon)) {
//		    return Label.USDECL;
//		} else if (product.getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD)))
//		    return Label.JODECL;
//		else {
//		    return Label.PUDECL;
//		}
//	    }
//
//	}
//    }
//
//    static CustomContent[] convertJSONArray(JSONArray array) throws JSONException {
//	int size = array.length();
//	CustomContent[] promotionalContentGroupList = new CustomContent[size];
//	for (int i = 0; i < promotionalContentGroupList.length; i++) {
//	    CustomContent promotionalContentGroup = new CustomContent();
//	    promotionalContentGroup.setAsset(array.getJSONObject(i));
//	    promotionalContentGroupList[i] = promotionalContentGroup;
//	}
//	return promotionalContentGroupList;
//    }
//
//    public static Label[] getLabelForMultiAsset(CustomContent content) {
//	// 월정액 구매가 된 경우
//	if (!isProductType(content.getProducts(), ProductType.getType(ProductType.SVOD))) {
//	    if (isPurchased(content.getProducts())) {
//		if (content.hasPreview()) {
//		    return Label.PLPVDECL;
//		} else {
//		    return Label.PLDECL;
//		}
//	    } else {
//		if (content.hasPreview()) {
//		    return Label.PUPVDECL;
//		} else {
//		    return Label.PUDECL;
//		}
//	    }
//	} else if (isContentsPurchased(content.getProducts(), ProductType.getType(ProductType.SVOD))) {
//	    return Label.PLDECL;
//	}
//	// 월정액 구매가 안된 경우
//	else {
//	    if (isContentsPurchased(content.getProducts(), ProductType.getType(ProductType.RVOD))) {
//		return Label.PLFPDECL;
//	    } else {
//		return Label.PUFPDECL;
//	    }
//	}
//    }
//
//    public static int getRVODPrice(CustomProduct[] products) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().equalsIgnoreCase(ProductType.getType(ProductType.RVOD)))
//		return products[i].getPrice();
//	}
//	return -1;
//    }
//
//    public static int getPrice(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().equalsIgnoreCase(productType))
//		return products[i].getPrice();
//	}
//	return -1;
//    }
//
//    public static boolean isPurchased(CustomProduct product) {
//	if (product.getPrice() <= 0)
//	    return true;
//	else if (!product.getPurchaseTime().trim().equals("")) {
//	    return true;
//	} else
//	    return false;
//    }
//
//    public static boolean isContentsPurchased(CustomProduct[] products, String keyWord) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(keyWord)) {
//		if (products[i].getPrice() == 0)
//		    return true;
//		if (!products[i].getPurchaseTime().trim().equals(""))
//		    return true;
//	    }
//	}
//	return false;
//    }
//
//    public static String convertNumberFormat(int number) {
//	NumberFormat nf = NumberFormat.getNumberInstance();
//	return nf.format(number);
//    }
//
//    public static String convertPageFormat(String str) {
//	if (str == null)
//	    return null;
//	if (str.length() >= 2)
//	    return str;
//	else {
//	    return "" + str;
//	}
//    }
//
//    public static String convertPageNumberFormat(int page) {
//	String str = "";
//	if (page < 10) {
//	    str = "0" + Integer.toString(page);
//	} else {
//	    str = Integer.toString(page);
//	}
//	return str;
//    }
//
//    public static String remainingTime(CustomProduct[] products, String productType) {
//	return remainingTime(getPurchasedTime(products, productType), getViewablePeriod(products, productType));
//    }
//
//    public static String remainingTime(CustomProduct[] content) {
//	CustomProduct product = null;
//	for (int i = 0; i < content.length; i++) {
//	    if (content[i].getProductType().equalsIgnoreCase(ProductType.getType(ProductType.SVOD)) && !content[i].getPurchaseTime().equals(""))
//		return "  월정액 이용중 입니다";
//	    else if (content[i].getProductType().equalsIgnoreCase(ProductType.getType(ProductType.Package)) && !content[i].getPurchaseTime().equals(""))
//		product = content[i];
//	}
//	if (product == null)
//	    product = content[0];
//	return remainingTime(product.getPurchaseTime(), product.getViewablePeriod(), "M월 d일 HH시 mm분까지 시청 가능합니다");
//    }
//
//    /**
//     * "M월 d일 HH시 mm분까지 시청 가능합니다"
//     */
//    public static String remainingTime(String purchasedTime, String viewableTime) {
//	return remainingTime(purchasedTime, viewableTime, "M월 d일 HH시 mm분까지 시청 가능");
//    }
//
//    static String ONE_YEAR = "0001-00-00 00:00:00";
//
//    private static String remainingTime(String purchasedTime, String viewableTime, String pattern) {
//	if (purchasedTime == null || viewableTime == null || purchasedTime.equalsIgnoreCase("") || viewableTime.equalsIgnoreCase("")) {
//	    return "";
//	} else {
//	    Date purchaseDate = null;
//	    CalendarUtil purchasedDay = null;
//	    try {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(DateUtil.getDate(purchasedTime, "yyyy-MM-dd HH:mm:ss"));
//		if (DateUtil.is365days(viewableTime))
//		    purchaseDate = DateUtil.getDate(ONE_YEAR, "yyyy-MM-dd HH:mm:ss");
//		else
//		    purchaseDate = DateUtil.getDate(viewableTime, "yyyy-MM-dd HH:mm:ss");
//		purchasedDay = new CalendarUtil(purchaseDate);
//		purchasedDay.moveTime(cal);
//		if (purchasedDay.getTimeMillis() >= 0) {
//		    return DateUtil.getDateString(purchasedDay.getTime(), pattern);
//		} else {
//		    return "";
//		}
//	    } catch (ParseException e) {
//		e.printStackTrace();
//		return "";
//	    }
//	}
//    }
//
//    public static boolean isPurchased(CustomProduct[] products) {
//	for (int i = 0; i < products.length; i++) {
//	    if (isPurchased(products[i])) {
//		Logger.I("", "UIHelper isPurchased  = true");
//		return true;
//	    }
//	}
//	Logger.I("", "UIHelper isPurchased  = false");
//	return false;
//    }
//
//    public static String getViewablePeriod(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		return products[i].getViewablePeriod();
//	}
//	return "-1";
//    }
//
//    public static boolean isProductType(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		return true;
//	}
//	return false;
//    }
//
//    public static boolean isProductType(CustomProduct products, String productType) {
//	if (products.getProductType().trim().equalsIgnoreCase(productType))
//	    return true;
//	return false;
//    }
//
//    public static String getPurchasedTime(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		return products[i].getPurchaseTime();
//	}
//	return "-1";
//    }
//
//    public static String getProductId(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		return products[i].getProductId();
//	}
//	return "-1";
//    }
//
//    public static String getGoodId(CustomProduct[] products, String productType) {
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		return products[i].getGoodId();
//	}
//	return "-1";
//    }
//
//    public static CustomProduct getProduct(CustomProduct[] products, String productType) {
//	CustomProduct product = null;
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		product = products[i];
//	}
//	return product;
//    }
//
//    public static boolean isProduct(CustomProduct[] products, String productType) {
//	CustomProduct product = null;
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType))
//		product = products[i];
//	}
//	return (product != null);
//    }
//
//    public static int getProductTypeIndex(CustomProduct[] products, String productType) {
//	int index = -1;
//	for (int i = 0; i < products.length; i++) {
//	    if (products[i].getProductType().trim().equalsIgnoreCase(productType)) {
//		index = i;
//		break;
//	    }
//	}
//	return index;
//    }
//
//    public static Image getRatingForDetail(String rating) {
//	if (rating != null)
//	    if (rating.equalsIgnoreCase("all") || rating.equalsIgnoreCase("00"))
//		return ImageResource.getImage("pop_icon_age_all.png");
//	    else if (rating.indexOf("19") >= 0)
//		return ImageResource.getImage("pop_icon_age19.png");
//	    else if (rating.indexOf("15") >= 0)
//		return ImageResource.getImage("pop_icon_age15.png");
//	    else if (rating.indexOf("12") >= 0)
//		return ImageResource.getImage("pop_icon_age12.png");
//	    else if (rating.indexOf("7") >= 0)
//		return ImageResource.getImage("pop_icon_age7.png");
//	return null;
//    }
//
//    public static Image getRatingForList(String rating) {
//	if (rating.equalsIgnoreCase("all") || rating.equalsIgnoreCase("00"))
//	    return ImageResource.getImage("icon_miniepg_age_all.png");
//	else if (rating.indexOf("19") >= 0)
//	    return ImageResource.getImage("icon_miniepg_age_19.png");
//	else if (rating.indexOf("15") >= 0)
//	    return ImageResource.getImage("icon_miniepg_age_15.png");
//	else if (rating.indexOf("12") >= 0)
//	    return ImageResource.getImage("icon_miniepg_age_12.png");
//	else if (rating.indexOf("7") >= 0)
//	    return ImageResource.getImage("icon_miniepg_age_7.png");
//	return null;
//    }
//
//    public static boolean checkRating(String rating, String comRating) {
//	if (rating.equalsIgnoreCase(comRating))
//	    return true;
//	else
//	    return false;
//    }
//
//    public static Image getContentListIcon1(CustomContent content, boolean isFocus) {
//	String iconName = "";
//	if (UIHelper.checkRating(content.getRating(), "19")) {
//	    iconName = "icon_listview_age19";
//	} else if (UIHelper.isProductType(content.getProducts(), ProductType.getType(ProductType.RVOD))) {
//	    iconName = "icon_list_charge";
//	} else
//	    return null;
//	if (isFocus) {
//	    iconName = iconName + "_focus.png";
//	} else {
//	    iconName = iconName + ".png";
//	}
//	return ImageResource.getImage(iconName);
//    }
//
//    public static Image getContentListIcon2(CustomContent content, boolean isFocus) {
//	String iconName = "";
//	if (CouponAgent.getInstance().haveAssetCoupon(content.getAssetID())) {
//	    iconName = "icon_listview_havecoupon";
//	} else if (content.isThreeDimIndicator()) {
//	    iconName = "icon_listview_3d";
//	} else if (content.isHot() && content.isNew()) {
//	    iconName = "icon_listview_event";
//	} else if (content.isHot()) {
//	    iconName = "icon_listview_hot";
//	} else if (content.isNew()) {
//	    iconName = "icon_listview_new";
//	} else
//	    return null;
//	if (isFocus) {
//	    iconName = iconName + "_focus.png";
//	} else {
//	    iconName = iconName + ".png";
//	}
//	return ImageResource.getImage(iconName);
//    }
//
//    public static Image getCategoryIcon(CustomCategory category, boolean isFocus) {
//	String presentationType = category.getPresentationType();
//	String iconName = "";
//	if (presentationType.equalsIgnoreCase("adult")) {
//	    iconName = "icon_listview_age19";
//	} else if (presentationType.equalsIgnoreCase("month")) {
//	    iconName = "icon_listview_monthlypay";
//	} else if (presentationType.equalsIgnoreCase("hot")) {
//	    iconName = "icon_listview_hot";
//	} else if (presentationType.equalsIgnoreCase("new")) {
//	    iconName = "icon_listview_new";
//	} else if (presentationType.equalsIgnoreCase("3d")) {
//	    iconName = "icon_listview_3d";
//	} else if (presentationType.equalsIgnoreCase("best")) {
//	    iconName = "icon_listview_best_01";
//	} else if (presentationType.equalsIgnoreCase("event")) {
//	    iconName = "icon_listview_event";
//	} else if (presentationType.equalsIgnoreCase("favor")) {
//	    iconName = "icon_listview_popularity";
//	} else if (presentationType.equalsIgnoreCase("live")) {
//	    iconName = "icon_listview_realtime";
//	} else if (presentationType.equalsIgnoreCase("special")) {
//	    iconName = "icon_listview_special";
//	} else if (presentationType.equalsIgnoreCase("rec")) {
//	    iconName = "icon_listview_recommendation";
//	} else if (presentationType.equalsIgnoreCase("no")) {
//	    iconName = "icon_listview_neverregrets";
//	} else if (presentationType.equalsIgnoreCase("free")) {
//	    iconName = "icon_listview_freecharge";
//	}
//	if (isFocus) {
//	    iconName = iconName + "_focus.png";
//	} else {
//	    iconName = iconName + ".png";
//	}
//	return ImageResource.getImage(iconName);
//    }
//}
