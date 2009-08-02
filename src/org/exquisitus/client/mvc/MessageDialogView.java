package org.exquisitus.client.mvc;

import java.util.ArrayList;
import java.util.List;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class MessageDialogView extends View {
	
	private static final int YWINLOGIN = 480;
	private static final int XWINLOGIN = 360;

	private FormData formData = new FormData("-20");  
	
	private Dialog msgDialog = null;

	public MessageDialogView(Controller controller) {
		super(controller);
		
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	
		msgDialog = new Dialog();
		msgDialog.setBodyBorder(true);
		msgDialog.setHeading("Exquisitus J2EE Message Dialog");
		msgDialog.setWidth(XWINLOGIN);
		msgDialog.setHeight(YWINLOGIN);
		msgDialog.setButtons(Dialog.CANCEL);
		msgDialog.setHideOnButtonClick(true);
		msgDialog.setShadow(true);
		msgDialog.setResizable(false);
		msgDialog.setLayout(new FitLayout());
		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setStyleAttribute("margin", "10px");
		fieldSet.setHeading("Send Message");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		fieldSet.setLayout(layout);  
		
		TextField<String> userName = new TextField<String>();  
		userName.setFieldLabel("UserName");  
		userName.setAllowBlank(false);  
		fieldSet.add(userName, formData);  
		
		List<BaseModelData> stocks = new ArrayList<BaseModelData>(); 
		BaseModelData bmd = new BaseModelData();
		bmd.set("name", "Only a issue...");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Doubt/Question");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Bug Report");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Suggestion!");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Request");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "I want argue!");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Off Topic");
		stocks.add(bmd);
		
		bmd = new BaseModelData();
		bmd.set("name", "Other...");
		stocks.add(bmd);
		
		ListStore<ModelData> store = new ListStore<ModelData>();  
		store.add(stocks);  
		   
		ComboBox<ModelData> combo = new ComboBox<ModelData>();  
		combo.setFieldLabel("Type");  
		combo.setDisplayField("name"); 
		combo.setStore(store); 
		combo.setValue(stocks.get(0));
		fieldSet.add(combo, formData);  
		
		TextField<String> txtTitle = new TextField<String>();  
		txtTitle.setFieldLabel("Title");  
		txtTitle.setAllowBlank(false);  
		fieldSet.add(txtTitle, formData);  
		
		RadioGroup radioGroup = new RadioGroup("test");  
		radioGroup.setFieldLabel("Humor");  
		
		Radio radio = new Radio();
		radio.setName("radio");  
		radio.setBoxLabel(":-D");  
		radioGroup.add(radio);    
		
		radio = new Radio();
		radio.setName("radio");  
		radio.setBoxLabel(":-)");  
		radioGroup.add(radio);    
		
		radio = new Radio();
		radio.setName("radio");  
		radio.setBoxLabel(":-P");  
		radio.setValue(true);
		radioGroup.add(radio);    
		
		radio = new Radio();
		radio.setName("radio");  
		radio.setBoxLabel(":-(");  
		radioGroup.add(radio);    
		
		/*
		radio = new Radio();
		radio.setName("radio");  
		radio.setBoxLabel(">:-@");  
		radioGroup.add(radio);    
		*/
		fieldSet.add(radioGroup, formData);  
		
		TextArea description = new TextArea();  
		description.setPreventScrollbars(true);  
		description.setFieldLabel("Message");
		description.setHeight("85%");
		fieldSet.add(description, formData);
		
		msgDialog.add(fieldSet);
		
		msgDialog.setButtonAlign(HorizontalAlignment.CENTER);  
		msgDialog.addButton(new Button("Send"));    
		msgDialog.hide();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowMessageDialogEvent) {
			msgDialog.show();
		}
	}

}
