package com.castis.winter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class Winter {

    public static interface KeyType {
	/**
	 * The null event type (value is 0).
	 */
	public static final int None = 0;
	/**
	 * The key down event type (value is 1).
	 */
	public static final int KeyDown = 1;
	/**
	 * The key up event type (value is 2).
	 */
	public static final int KeyUp = 2;
    }

    public static final Rectangle Resoultion720x480 = new Rectangle(0, 0, 720, 480);

    public static interface PurchaseType {
	public static final String Free = "무료쿠폰사용";
	public static final String Normal = "일반결제";
	public static final String Coin = "TV코인결제";
    }

    public static interface FontType {
	static String fontName = "tvTvGothic95";
	public final static Font F13P = new Font(fontName, Font.PLAIN, 13);
	public final static Font F14P = new Font(fontName, Font.PLAIN, 14);
	public final static Font F15P = new Font(fontName, Font.PLAIN, 15);
	public final static Font F16P = new Font(fontName, Font.PLAIN, 16);
	public final static Font F17P = new Font(fontName, Font.PLAIN, 17);
	public final static Font F18P = new Font(fontName, Font.PLAIN, 18);
	public final static Font F19P = new Font(fontName, Font.PLAIN, 19);
	public final static Font F20P = new Font(fontName, Font.PLAIN, 20);
	public final static Font F21P = new Font(fontName, Font.PLAIN, 21);
	public final static Font F22P = new Font(fontName, Font.PLAIN, 22);
	public final static Font F24P = new Font(fontName, Font.PLAIN, 24);
	public final static Font F26P = new Font(fontName, Font.PLAIN, 26);
	public final static Font F27P = new Font(fontName, Font.PLAIN, 27);
	public final static Font F40P = new Font(fontName, Font.PLAIN, 40);
    }

    public static interface ColorType {
	public static Color C192_220_0 = new Color(192, 220, 0);
	public static Color C0_0_0 = new Color(0, 0, 0);
	public static Color C185_96_0 = new Color(185, 96, 0);
	public static Color C230_126_9 = new Color(230, 126, 9);
	public static Color C64_64_57 = new Color(64, 64, 57);
	public static Color C173_170_142 = new Color(173, 170, 142);
	public static Color C42_43_34 = new Color(42, 43, 34);
	public static Color C139_160_0 = new Color(139, 160, 0);
	public static Color C102_102_83 = new Color(102, 102, 83);
	public static Color C255_255_255 = new Color(255, 255, 255);
	public static Color C66_66_58 = new Color(66, 66, 58);
	public static Color C240_240_240 = new Color(240, 240, 240);
	public static Color C147_150_119 = new Color(147, 150, 119);
	public static Color C204_210_163 = new Color(204, 210, 163);
	public static Color C233_240_189 = new Color(233, 240, 189);
	public static Color C245_245_245 = new Color(245, 245, 245);
	public static Color C120_135_0 = new Color(120, 135, 0);
	public static Color C50_50_50 = new Color(50, 50, 50);
	public static Color C82_82_76 = new Color(82, 82, 76);
	public static Color C250_250_250 = new Color(250, 250, 250);
	public static Color C18_18_16 = new Color(18, 18, 16);
	public static Color C46_46_37 = new Color(46, 46, 37);
	public static Color C181_183_159 = new Color(181, 183, 159);
	public static Color C137_139_114 = new Color(137, 139, 114);
	public static Color C143_169_0 = new Color(143, 169, 0);
	public static Color C60_60_55 = new Color(60, 60, 55);
	public static Color C52_52_46 = new Color(52, 52, 46);
	public static Color C60_60_50 = new Color(60, 60, 50);
	public static Color C98_98_80 = new Color(98, 98, 80);
	public static Color C173_178_139 = new Color(173, 178, 139);
	public static Color C0_0_0_93 = new Color(0, 0, 0, (int) (255 * 0.93));
	public static Color C20_20_20 = new Color(20, 20, 20);
	public static Color C200_120_0 = new Color(200, 120, 0);
	public static Color C27_27_24 = new Color(27, 27, 24);
	public static Color C0_0_0_90 = new Color(0, 0, 0, (int) (255 * 0.9));
	public static Color C39_39_34 = new Color(39, 39, 34);
	public static Color C60_60_52 = new Color(60, 60, 52);
	public static Color C205_205_195 = new Color(205, 205, 195);
	public static Color C102_112_23 = new Color(102, 112, 23);
	public static Color C172_174_152 = new Color(172, 174, 152);
	public static Color C220_221_214 = new Color(220, 221, 214);
	public static Color C1_0_0_50 = new Color(1, 0, 0, (int) (255 * 0.50));
	public static Color C255_198_0 = new Color(255, 198, 0);
	public static Color C200_200_200 = new Color(200, 200, 200);
	public static Color c_147_169_0 = new Color(147, 169, 0);
	public static Color c_172_174_152 = new Color(172, 174, 152);
    }

    public static interface DataSize {
	public static int Menu = 7;
	public static int ContentsList = 10;
	public static int SeriesList = 7;
	public static String EventList = "5";
	public static String RegisterList = "4";
	public static int CouponList = 4;
    }

    public static interface SortType {
	public static final String NameAscending = "nameAscend";
	public static final String NameDescending = "nameDescend";
	public static final String LicenseDescending = "licenseDescend";
	public static final String None = "notSet";
	public static final String LicenseAscending = "licenseAscend";
	public static final String HitCountDescending = "hitCountDescend";
	public static final String ReviewRatingDescending = "reviewRatingDescend";
	public static final String EventCreationTimeDescending = "creationTimeDescend";
	public static final String EventCreationTimeAscending = "creationTimeAscend";
	public static final String EventStatus = "eventStatus";
	public static final String WinnerWinTimeAscending = "winTimeAscend";
	public static final String SeriesSequenceAscending = "seriesSeqAsc";
	public static final String SeriesSequenceDescending = "seriesSeqDesc";
    }

    public static class ServiceType {
	public static final int NewVodMenu = 0;
	public static final int VodMenu = 1;
	public static final int HelpMenu = 2;
	public static final int MyContentsMenu = 3;
	/**
	 * <br>
	 * showVODChannelContent <br>
	 * showVODChannelPackage
	 */
	public static final int VodChannel = 4;
	public static final int VodLink = 5;
	public static final int VodSearch = 6;
    }

    public static interface EventType {
	public static final int Exit = 10;
	public static final int Enter = 12;
	public static final int Back = 13;
	public static final int FocusMoveNext = 14;
	public static final int NumberFull = 15;
	public static final int CompositeChange = 16;
	public static final int PageChange = 17;
	public static final int FocusChange = 18;
	public static final int FocusInputBox = 19;
	public static final int Fake = 20;
	public static final int ColorYellow = 21;
	public static final int ColorGreen = 22;
	public static final int ColorRed = 23;
	public static final int ColorBlue = 24;
	public static final int Right = 25;
	public static final int Left = 26;
	public static final int CouponShop = 27;
	public static final int AllCompositeHide = 28;
    }

    public static interface CustomEventType {
	public static final String Asset = "asset";
	public static final String Category = "category";
    }

    public static interface ExplorerType {
	public static final int CategoryList = 12;
	public static final int ContentsList = 13;
	public static final int SeriesContentsList = 17;
	public static final int DetailInfo = 14;
	public static final int MyContents = 15;
	public static final int Play = 16;
	public static final int GOD = 16;
    }

    public static interface MyContentType {
	public static final int HotEvent = 2;
	public static final int TVCoin = 3;
	public static final int AreaChannel = 5;
	public static final int SeenList = 6;
	public static final int PurchasedList = 7;
	public static final int MyCoupon = 8;
    }

    public static interface ListViewType {
	public static final int Normal = 0;
	public static final int Chart = 1;
	public static final int Poster = 2;
    }

    public static interface DetailViewType {
	public static final int Detail = 0;
	public static final int Purchase = 1;
	public static final int SvodDetail = 2;
	public static final int SvodPurchase = 3;
    }

    public static interface EventEnrollStatusType {
	public static final String Total = "99";
	public static final String Join = "10";
    }

    public static interface EventStatusType {
	public final static String NotYetStarted = "00";
	public final static String InTheProgress = "10";
	public final static String EndProgress = "11";
	public final static String VoteCompleted = "20";
    }

    public static interface BannerLinkType {
	public final static String Asset = "asset";
	public final static String Package = "VODPackage";
	public final static String Product = "product";
	public final static String Category = "category";
	public final static String External = "externalSource";
    }

    public static interface PlayType {
	public static final int Play = 1;
	public static final int PreviewPlay = 2;
    }

    public static interface PlaySpeedType {
	public static final int Pause = 0;
	public static final int Play = 1;
	public static final int RW2 = -2;
	public static final int RW4 = -4;
	public static final int RW8 = -8;
	public static final int RW16 = -16;
	public static final int RW32 = -32;
	public static final int RW64 = -64;
	public static final int FF2 = 2;
	public static final int FF4 = 4;
	public static final int FF8 = 8;
	public static final int FF16 = 16;
	public static final int FF32 = 32;
	public static final int FF64 = 64;
    }

    public class PurchasedInfoType {
	public static final int Normal = 0;
	public static final int Event = 1;
	public static final int HotIssue = 2;
    }

    public static class GatheringType {
	public static final int Update = 1;
	public static final int Init = 0;

    }

    public static class DepthType {
	public static final int Main = 0;
	public static final int Sub = 1;
	public static final int Third = 2;

    }

    public static class PlayStopTpye {
	public static final int Back = 0;
	public static final int Exit = 1;
    }

    public static class RemoteControllerType {
	public static final int TbroadCustomCode = 92930048;
    }

    public static class MenuType {
	public static final int NEWESTVODMENU = 0;
	public static final int VODMENU = 1;
    }

    public static class CategoryDescriptionType {
	    public static final String CouponShop = "coupon";
    }

    public static class CASType {
	public static final int CJHV_CAS = 160890880;
	public static final int DC_CAS = 152109057;
	public static final int YH_CAS = 152174594;
    }

    public static class ProductType {
	public static final int SVOD = 0;
	public static final int Package = 1;
	public static final int RVOD = 2;
	public static final int FOD = 3;

	public static String getType(int productType) {
	    switch (productType) {
	    case SVOD:
		return "Svod";
	    case Package:
		return "Package";
	    case RVOD:
		return "RVOD";
	    case FOD:
		return "FOD";
	    }
	    return "NOT_TYPE";
	}
    }
}
