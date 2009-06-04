package org.exquisitus.client.mvc;

import org.exquisitus.client.ApplicationEvents;
import org.exquisitus.client.services.LoginServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogView extends View {

	private Dialog loginDialog = null;

	private FormData formData = new FormData("-20");

	private Button btnRegister = null;
	private Button btnLogin = null;

	private TextField<String> loginFirstName;

	private TextField<String> loginPass;

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

		btnRegister = new Button("REGISTER");
		btnLogin = new Button("LOGIN");
		
		HorizontalPanel hp = new HorizontalPanel();

		hp.add(createLoginFormPanel());
		hp.add(createRegisterFormPanel());

		loginDialog.add(hp);
		loginDialog.setButtonAlign(HorizontalAlignment.RIGHT);

		setBtnListeners();
		
		loginDialog.hide();
	}

	private void setBtnListeners() {
		
		btnLogin.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				
				String user = loginFirstName.getValue();
				String password = loginPass.getValue();
				
				LoginServiceAsync login = Registry.get(LoginDialogController.LOGINMOCKSERVICE);
				login.login(user, password, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						MessageBox mb = new MessageBox();
						mb.setMessage("Error... " + caught.getMessage());
						mb.setTitle("ERROR");
						mb.setModal(true);
						mb.setIcon(MessageBox.ERROR);
						mb.show();
					}

					@Override
					public void onSuccess(String result) {
					
						MessageBox mb = new MessageBox();
						mb.setMessage("Autenticated!... " +  result);
						mb.show();
					}
					
				});

			}
			
		});
		
		btnRegister.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
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

		loginFirstName = new TextField<String>();
		loginFirstName.setFieldLabel("Name");

		fieldSet.add(loginFirstName, formData);

		loginPass = new TextField<String>();
		loginPass.setFieldLabel("Password");
		loginPass.setPassword(true);
		fieldSet.add(loginPass, formData);

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

		fieldSet.add(btnRegister,formData);

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
