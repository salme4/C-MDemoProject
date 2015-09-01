package com.castis.winter.window;


public class WComposite extends Window {

    private static final long serialVersionUID = 718352282697314243L;

    WComposite() {
    }

    public WComposite(WComposite composite) {
	super(composite);
	screen.addWComposite(this);
    }

}
