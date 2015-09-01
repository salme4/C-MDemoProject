
package com.castis.winter.window;

import java.util.EventListener;

import com.castis.winter.Winter.EventType;
import com.castis.winter.Winter.KeyType;
import com.castis.winter.event.ActionListener;
import com.castis.winter.event.Event;
import com.castis.winter.event.KeyEvent;
import com.castis.winter.event.KeyListener;
import com.castis.winter.event.Listener;
import com.castis.winter.event.TransitExplorerEvent;
import com.castis.winter.event.TransitExplorerListener;

public class TypedListener implements Listener {
	protected EventListener eventListener;
	
	public TypedListener ( EventListener listener ) {
		eventListener = listener;
	}
	
	public EventListener getEventListener () {
		return eventListener;
	}
	
	public void handleEvent ( Event e ) {
		switch ( e.type ) {
			case KeyType.KeyUp: {
				KeyEvent event = new KeyEvent(e);
				((KeyListener) eventListener).keyPressed(event);
				break;
			}
			case KeyType.KeyDown: {
				KeyEvent event = new KeyEvent(e);
				((KeyListener) eventListener).keyPressed(event);
				break;
			}
			case EventType.Back:
			case EventType.ColorGreen:
			case EventType.Enter:
			case EventType.AllCompositeHide:
			case EventType.CouponShop:
			case EventType.FocusMoveNext:
			case EventType.NumberFull:
			case EventType.PageChange:
			case EventType.FocusChange:
			case EventType.Exit:
			case EventType.Left:
			case EventType.Right:
			case EventType.ColorYellow:
			case EventType.ColorRed:
			case EventType.ColorBlue:
			case EventType.FocusInputBox: {
				((ActionListener) eventListener).occurredEvent(e);
				break;
			}
			case EventType.CompositeChange: {
				if ( e instanceof TransitExplorerEvent )
					((TransitExplorerListener) eventListener).transitExplorer((TransitExplorerEvent) e);
				break;
			}
			default:
			break;
		}
	}
}
