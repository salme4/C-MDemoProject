package com.castis.winter.window;

import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;

import com.castis.winter.Winter;


public class HSceneScreen extends WScreen implements UserEventListener{

	public HSceneScreen() {
		init();
	}
	
	public void init() {
		EventManager.getInstance().addUserEventListener(this, null);
		HSceneTemplate hSceneTemplate = new HSceneTemplate();
		hSceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
				new HScreenDimension(1, 1), HSceneTemplate.REQUIRED);
		hSceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
				new HScreenPoint(0, 0), HSceneTemplate.REQUIRED);
		rootContainer = HSceneFactory.getInstance().getBestScene(hSceneTemplate);
		rootContainer.setBounds(Winter.Resoultion720x480);
	}

	/**
	 * @param key  
	 */
	public void addWindow(Window window ,String key) {
		this.window = window;
		this.rootContainer.add(this.window);
	}

	public void setVisible(boolean visibled) {
		this.rootContainer.setVisible(visibled);
	}

	public void userEventReceived(UserEvent event) {
		handleEvent(event);
	}

}
