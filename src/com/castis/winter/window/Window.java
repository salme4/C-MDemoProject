package com.castis.winter.window;

import com.castis.winter.Winter.KeyType;
import com.castis.winter.event.KeyListener;


public abstract class Window extends WComponent {

    private static final long serialVersionUID = 1526841794413079712L;

    WComposite parent;
    boolean enabled = false;
    Window() {
    }

    public Window(WComposite parent) {
	super(parent);
	this.parent = parent;
	createWComponent();
    }
    
    void createWComponent(){
	register();
//	setVisible(true);
//	setBounds(0, 0, 720, 480);
    }
    
    void register(){
	screen.addWindow(this);
    }
    
    public boolean getEnabled ()     
    {
    	return enabled;
    }
    
    public void setEnabled(boolean enabled) {
    	this.enabled = enabled;
    }
    
    public void setVisible (boolean visible) {
    	super.setVisible(visible);
    }
    
    public void addKeyListener(KeyListener listener){
    	TypedListener typedListener = new TypedListener(listener);
    	addListener(KeyType.KeyUp, typedListener);
    	addListener(KeyType.KeyDown, typedListener);
    }
}
