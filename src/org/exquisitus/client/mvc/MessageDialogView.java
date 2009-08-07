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
	
	private TextField<String> userName = null;
	private TextField<String> email    = null;
	private TextField<String> txtTitle = null;  
	private TextArea messageBody = null;  
	
	private ComboBox<ModelData> msgTypeCombo = null;
	
	private Button btnSend = new Button("Send");
	private RadioGroup radioGroup;
	
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
		
		userName = new TextField<String>();
		userName.setFieldLabel("UserName");  
		userName.setAllowBlank(false);  
		fieldSet.add(userName, formData);  
		
		email = new TextField<String>();
		email.setFieldLabel("Email");  
		email.setAllowBlank(false);  
		fieldSet.add(email, formData);  
		
		createMsgTypeCombo(fieldSet);  
		
		txtTitle = new TextField<String>();
		txtTitle.setFieldLabel("Title");  
		txtTitle.setAllowBlank(false);  
		fieldSet.add(txtTitle, formData);  
		
		createMsgHumorRadio(fieldSet);  
		
		messageBody = new TextArea();  
		messageBody.setPreventScrollbars(true);  
		messageBody.setFieldLabel("Message");
		messageBody.setHeight("235px");
		fieldSet.add(messageBody, formData);
		
		msgDialog.add(fieldSet);
		
		msgDialog.setButtonAlign(HorizontalAlignment.CENTER);  
		msgDialog.addButton(btnSend);    
		msgDialog.hide();
	}

	private void createMsgHumorRadio(FieldSet fieldSet) {
		
		radioGroup = new RadioGroup("radiohumor");  
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
	}

	private void createMsgTypeCombo(FieldSet fieldSet) {
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
		   
		msgTypeCombo = new ComboBox<ModelData>();  
		msgTypeCombo.setFieldLabel("Type");  
		msgTypeCombo.setDisplayField("name"); 
		msgTypeCombo.setStore(store); 
		msgTypeCombo.setValue(stocks.get(0));
		fieldSet.add(msgTypeCombo, formData);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == ApplicationEvents.ShowMessageDialogEvent) {
			msgDialog.show();
		}
		
		if (event.getType() == ApplicationEvents.HideMessageDialogEvent) {
			msgDialog.hide();
			resetDialog();
		}
	}

	public Button getBtnSend() {
		return btnSend;
	}
	
	public TextField<String> getUserName() {
		return userName;
	}
	
	public TextField<String> getEmail() {
		return email;
	}
	
	public ComboBox<ModelData> getMsgTypeCombo() {
		return msgTypeCombo;
	}
	
	public TextField<String> getTxtTitle() {
		return txtTitle;
	}
	
	public TextArea getMessageBody() {
		return messageBody;
	}

	public String getHumor() {		
		return radioGroup.getMessageTarget();
	}
	
	private void resetDialog() {
		userName.reset();
		email.reset();
		txtTitle.reset();
		messageBody.reset();
		msgTypeCombo.reset();
		radioGroup.reset();
	}
}







