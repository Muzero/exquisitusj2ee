package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogView extends View {

	private Dialog loginDialog = null;

	private FormData formData = new FormData("-20");

	public LoginDialogView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		super.initialize();

		loginDialog = new Dialog();
		loginDialog.setBodyBorder(true);
		loginDialog.setHeading("Login Dialog");
		loginDialog.setWidth(640);
		loginDialog.setHeight(280);
		loginDialog.setButtons(Dialog.CANCEL);
		loginDialog.setHideOnButtonClick(true);

		HorizontalPanel hp = new HorizontalPanel();

		hp.add(createLoginFormPanel());
		hp.add(createRegisterFormPanel());

		loginDialog.add(hp);
		loginDialog.setButtonAlign(HorizontalAlignment.RIGHT);

		loginDialog.hide();
	}

	private Widget createLoginFormPanel() {

		FormPanel formPanel = new FormPanel();
		formPanel.setFrame(false);
		formPanel.setHeaderVisible(false);
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("If you are already registered...");
		fieldSet.setHeight(170);
		
		FormLayout formLayout = new FormLayout();
		fieldSet.setLayout(formLayout);

		TextField<String> firstName = new TextField<String>();
		firstName.setFieldLabel("Name");
//		firstName.setAllowBlank(false);

		fieldSet.add(firstName, formData);

		TextField<String> pass = new TextField<String>();
		pass.setFieldLabel("Password");
		pass.setPassword(true);
		fieldSet.add(pass, formData);

		Button btnLogin = new Button("LOGIN");
		
		fieldSet.add(btnLogin,formData);
		//formPanel.addButton(btnLogin);
		
		formPanel.add(fieldSet);
		formPanel.setWidth(310);
		return formPanel;
	}
	
	private Widget createRegisterFormPanel() {

		FormPanel formPanel = new FormPanel();
		formPanel.setFrame(false);
		formPanel.setHeaderVisible(false);
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("Otherwise If you want to register...");
		fieldSet.setHeight(170);
		
		FormLayout formLayout = new FormLayout();
		
		fieldSet.setLayout(formLayout);

		TextField<String> firstName = new TextField<String>();
		firstName.setFieldLabel("Name");
		//firstName.setAllowBlank(false);

		fieldSet.add(firstName, formData);

		TextField<String> pass = new TextField<String>();
		pass.setFieldLabel("Password");
		pass.setPassword(true);
		fieldSet.add(pass, formData);

		TextField<String> pass2 = new TextField<String>();
		pass2.setFieldLabel("Repeat Password");
		pass2.setPassword(true);
		fieldSet.add(pass2, formData);
		
		TextField<String> email = new TextField<String>();
		email.setFieldLabel("email");
		email.setPassword(true);
		fieldSet.add(email, formData);

		Button btnLogin = new Button("REGISTER");
		btnLogin.setWidth(120);
		fieldSet.add(btnLogin,formData);

		formPanel.add(fieldSet);
		formPanel.setWidth(310);
		return formPanel;
	}

	@Override
	protected void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.ShowLoginEvent) {
			GWT.log("YEAH", null);
			loginDialog.show();
		}
	}

}
