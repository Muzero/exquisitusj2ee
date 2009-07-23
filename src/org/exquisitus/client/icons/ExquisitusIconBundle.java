package org.exquisitus.client.icons;

/**
 * ExquisitusIconBundle store the icon and image collection that will be
 * processed by ImageBundle and GWT generators to be taken from server with only
 * one request altogether
 * 
 * @author muzero
 */

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface ExquisitusIconBundle extends ImageBundle {
	
	@Resource("hehe_48.png")
	AbstractImagePrototype hehe48();

	@Resource("hehe_64.png")
	AbstractImagePrototype hehe64();

	@Resource("add32.gif")
	AbstractImagePrototype add32();
}
