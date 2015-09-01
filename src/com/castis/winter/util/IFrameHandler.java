package com.castis.winter.util;

import java.awt.Graphics;

import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundConfiguration;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScreen;
import org.havi.ui.HStillImageBackgroundConfiguration;

import com.castis.winter.Winter.ColorType;
import com.castis.winter.Winter.EventType;
import com.castis.winter.Winter.FontType;
import com.castis.winter.event.KeyEvent;
import com.castis.winter.navigator.NavigatorController;
import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorView;
import com.castis.winter.window.WComposite;

/**
 * Appl. IFrame의 Handler.
 * 
 */
public class IFrameHandler extends NavigatorView implements ResourceClient {
    public static final String FILE_NAME = "id_vod";
    
    public IFrameHandler(WComposite parent, NavigatorModel model) {
	super(parent, model);
	vodIframe = getIframeFlieFromFlash(FILE_NAME);
    }

    private HBackgroundDevice backdevice;

    private HStillImageBackgroundConfiguration backconfig;

    private HBackgroundImage vodIframe = null;


  

    /**
     * IFrameHandler instance를 리턴한다.
     * 
     * @return instance
     */
    /*
     * public static IFrameHandler getInstance() { return instance; }
     */
    /**
     * IFrame을 구동하기 위해 HStillImageBackgroundConfiguration 생성한다.
     * 
     */
    private void init() {
	if (backconfig == null) {
	    HScreen screen = HScreen.getDefaultHScreen();
	    backdevice = screen.getDefaultHBackgroundDevice();
	    HBackgroundConfigTemplate backgroundConfigurationTemplate = new HBackgroundConfigTemplate();
	    backgroundConfigurationTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
	    HBackgroundConfiguration backconfig = backdevice.getBestConfiguration(backgroundConfigurationTemplate);

	    Logger.I(this, "init():" + backconfig.toString() + " -x- " + backdevice.toString() + " -x- " + screen.toString());

	    if (backdevice.reserveDevice(this)) {
		try {
		    Logger.I(this, "init():setting backconfig");
		    backdevice.setBackgroundConfiguration(backconfig);
		} catch (Exception e) {
		    Logger.F(this, "SetIFrame:init(): " + e);
		    e.printStackTrace();
		    backdevice.releaseDevice();
		    return;
		}
		if (backconfig instanceof HStillImageBackgroundConfiguration) {
		    this.backconfig = (HStillImageBackgroundConfiguration) backconfig;
		    Logger.I(this, "init():we got the configs and devices, returning successfully");
		} else {
		    Logger.I(this, "init():backconfig not a HStillImageBackgroundConfiguration, releasing backdevice");
		    backdevice.releaseDevice();
		    return;
		}
	    }
	}
    }

    /**
     * IFrame을 화면에 display한다.
     * 
     * @param iframeType
     *            IFrame Type
     */

    public void loading() {
	if (backconfig == null)
	    init();

	try {
	    if (backconfig != null) {
		if (vodIframe == null)
		    vodIframe = getIframeFlieFromFlash(FILE_NAME);
		backconfig.displayImage(vodIframe);
		Logger.I(this, "show():displaying HBackgroundImage:Loading");
	    } else {
		Logger.F(this, "show():No stillimagebackgroundconfiguration");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	loadingText();
    }
    
    public void end() {
	if (backconfig == null)
	    init();
	
	try {
	    if(backconfig != null){
		if (vodIframe == null)
		    vodIframe = getIframeFlieFromFlash(FILE_NAME);
		backconfig.displayImage(vodIframe);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Logger.I(this, "show():displaying HBackgroundImage:Loading");
	endingText();
    }

    private void loadingText(){
//	IFrameModel iFrameModel = (IFrameModel)model;
//	iFrameModel.setTitle("VOD로딩중");
//	iFrameModel.setSubTitle("VOD Loading ...");
//	iFrameModel.setDetailText("헬로TV VOD를 불러오고 있습니다");
//	iFrameModel.setSubDetailText("잠시만 기다려 주세요");
    }
    
    private void endingText(){
//	IFrameModel iFrameModel = (IFrameModel)model;
//	iFrameModel.setTitle("VOD재생종료");
//	iFrameModel.setSubTitle("Thank you for Watching");
//	iFrameModel.setDetailText("최신개봉영화, 놓친 TV방송을 가장 빠르게!");
//	iFrameModel.setSubDetailText("감동과 즐거움이 가득한 헬로TV");
    }
    public void black() {
	
	if (backconfig == null)
	    init();
	try {
	    backconfig.setColor(ColorType.C0_0_0);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Logger.I(this, "show():displaying HBackgroundImage:Black");
    }

   

    /**
     * IFrameHandler를 close한다. PC가 아닐 경우 동작하며, resource를 반환한다.
     */
    public void close() {
	if (backdevice != null) {
	    Logger.I(this, "close():releasing backdevice");
	    backdevice.releaseDevice();
	    backdevice = null;
	    backconfig = null;
	}
    }

    private HBackgroundImage getIframeFlieFromFlash(String fileName) {
	Logger.I(this, "Loading I-Frame");
	HBackgroundImage iframe = null;

	String path = "";//STBInfoAgent.getInstance().getRootDirectory() + "/iframe"; // PATH
	// fileName = "id_vod"; // 구매목록 파일명

	Logger.I(this, "Path : " + path);
	iframe = new HBackgroundImage(path + "/" + fileName);

	return iframe;
    }

    public void notifyRelease(ResourceProxy proxy) {
	Logger.I(this, "NotifyRelease");
	close();
    }

    public void release(ResourceProxy proxy) {
	Logger.I(this, "Release");
	close();

    }

    public boolean requestRelease(ResourceProxy proxy, Object arg1) {
	Logger.I(this, "RequestRelease");
	return true;
    }

    public void initialize() {
	if (controller == null) {
	    controller = new NavigatorController(this) {
		public void keyReleased(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {
		    sendFakeEvent();
		    return;
		}
	    };
	}
	this.addKeyListener(controller);

    
	
    }
    protected void sendFakeEvent() {
	sendActionEvent(EventType.Fake);
    }
    public void paint(Graphics g){
	drawPaint(g);
    }

    public void drawPaint(Graphics g) {
	g.setFont(FontType.F40P);
	g.setColor(ColorType.C240_240_240);
	//IFrameModel iFrameModel = (IFrameModel)model;
	//g.drawString(iFrameModel.getTitle(), 186, 114);
	
	g.setFont(FontType.F16P);
	//g.drawString(iFrameModel.getSubTitle(), 188, 142);
	
	g.setColor(ColorType.C200_200_200);
	g.setFont(FontType.F20P);
	//g.drawString(iFrameModel.getDetailText(), 60, 216);
	
	g.setFont(FontType.F15P);
	//g.drawString(iFrameModel.getSubDetailText(), 60, 241);
    }

}