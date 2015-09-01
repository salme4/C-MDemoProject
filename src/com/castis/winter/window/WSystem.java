package com.castis.winter.window;

import com.castis.winter.util.Logger;

public class WSystem extends WComposite {

    private static final long serialVersionUID = 8472445998235151906L;

    WSystem() {
    }

    public WSystem(Screen screen) {
	super();
	this.screen = screen;
    }

    public void openWComposite(WComposite composite) {
	Logger.I(this, "openWComposite : " + composite.toString());
	composite.setVisible(true);
	screen.getScreenContainer(composite).setVisible(true);
    }

    public void open() {
	screen.setVisible(true);
    }

    public void closeWComposite(WComposite composite) {
	Logger.I(this, "closeWComposite : " + composite.toString());
	composite.setVisible(false);
	screen.getScreenContainer(composite).setVisible(false);
    }

    public void close() {
	screen.setVisible(false);
    }

    void closeWComponents() {

    }
    
    public void clear() {
	screen.clear();
    }
}
