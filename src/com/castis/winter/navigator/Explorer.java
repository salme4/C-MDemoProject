package com.castis.winter.navigator;

import com.castis.winter.window.WComposite;

public abstract class Explorer extends WComposite {

    public Explorer(WComposite parent) {
	super(parent);
    }

    public abstract void active();

    public abstract void update();

    /**
     * 자신을 포함하여, Composite 내의 모든 View의 setEnabled(false)
     */
    public abstract void deActive();

    /**
     * 자신을 포함하여, Composite 내의 모든 View의 setVisible(false)
     */
    public abstract void hideCom();

    public abstract void backUp();

    public abstract void restore();

}
