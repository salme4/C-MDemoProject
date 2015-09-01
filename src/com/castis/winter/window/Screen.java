package com.castis.winter.window;

import java.awt.Container;

import org.dvb.event.UserEvent;

import com.castis.winter.event.Event;


public interface Screen {

    /**
     * Initializes any internal resources needed 
     * by the device.
     * 
     */
    void init();
    
    void addWindow(Window window);
    
    void addWComposite(WComposite composite);
    
    boolean handleEvent(UserEvent event);
    
    void setVisible(boolean visibled);
    
    boolean runDeferredEvents();
    
    void postEvent (Event event);
    
    public Container getScreenContainer(WComposite composite);

    void clear();
    
    public void dispose();
}
