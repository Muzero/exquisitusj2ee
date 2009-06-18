package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("serial")
public class PanelData extends BaseModelData {

	public static final String NAME = "name";
	public static final String ICON = "icon";
	public static final String VIEW = "view";
	
	public String getName() {
		return get(NAME);
	}
	public void setName(String name) {
		set(NAME, name);
	}
	public String getIcon() {
		return get(NAME);
	}
	public void setIcon(String icon) {
		set(ICON, icon);
	}
	public String getView() {
		return get(VIEW);
	}
	public void setView(String view) {
		set(VIEW, view);
	}
	
	public PanelData() {
	}
	
	public PanelData(String name, String icon, String view) {
		setName(name);
		setIcon(name);
		setView(view);
	}
}
