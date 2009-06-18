package org.exquisitus.client.subview;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("serial")
public class PanelData extends BaseModelData {

	private static final String NAME = "NAME";
	private static final String ICON = "ICON";
	private static final String VIEW = "VIEW";
	
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
