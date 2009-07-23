package org.exquisitus.client.mvc;

/**
 * The Login Dialog view Panel allow the user to register or to login into the 
 * ExquisitusJ2EE application
 * 
 * @author muzero
 * 
 * */

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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogView extends View {

	private static final int YWINLOGIN = 280;
	private static final int XWINLOGIN = 640;

	private Dialog loginDialog = null;

	private FormData formData = new FormData("-20");

	private Button btnRegister = null;
	private Button btnLogin = null;

	private TextField<String> loginFirstName;
	private TextField<String> loginPass;
	
	private TextField<String> firstNameReg;
	private TextField<String> passReg;
	private TextField<String> pass2Reg;
	private TextField<String> emailReg;

	public LoginDialogView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		super.initialize();

		loginDialog = new Dialog();
		loginDialog.setBodyBorder(true);
		loginDialog.setHeading("Exquisitus J2EE Login Dialog");
		loginDialog.setWidth(XWINLOGIN);
		loginDialog.setHeight(YWINLOGIN);
		loginDialog.setButtons(Dialog.CANCEL);
		loginDialog.setHideOnButtonClick(true);
		loginDialog.setShadow(true);
		loginDialog.setResizable(false);

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

				final String user = loginFirstName.getValue();
				final String password = loginPass.getValue();

				if (user == null || password == null) {
					LoginDialogView.dialogUtilShow("Insert Username or Password!", "LOGIN ERROR", true, true);
					return;
				}

				// LoginServiceAsync login = (LoginServiceAsync)
				// Registry.get(LoginDialogController.LOGINMOCKSERVICE);
				// login.login(user, password,RemoteServiceFacade.getInstance().getMockAsyncCallback());

				LoginServiceAsync login = (LoginServiceAsync) Registry
						.get(LoginDialogController.LOGINMOCKSERVICE);
				login.login(user, password, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						LoginDialogView.dialogUtilShow("Error... " + caught.getMessage() + "", "LOGIN ERROR", true, true);
					}

					@Override
					public void onSuccess(String username) {

						if (username != null) {

							loginDialog.hide();

							AppView aView = Registry.get(AppView.MAINAPPVIEWPORT);
							aView.setApplicationTitle(" - " + username);

							LoginDialogView.dialogUtilShow("Welcome Back " + username, "LOGIN SUCCESFULL", true, false);
							refresh();
						}
						else
							LoginDialogView.dialogUtilShow("Sorry, The user " + user + " doesn't exist", "LOGIN ERROR", true, true);						
							}
				});
			}
		});

		btnRegister.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final String usernameRegistration = firstNameReg.getValue();
				final String password             = passReg.getValue();
				final String password2            = pass2Reg.getValue();
				final String email                = emailReg.getValue();
				
				if (usernameRegistration == null || password == null || password2 == null || email == null) {
					LoginDialogView.dialogUtilShow("Error, missing registration form values", "ERROR", true, true);	
					return;
				}
				
				if (!password.equals(password2))
				{
					LoginDialogView.dialogUtilShow("Passwords are not the same!", "PASSWORD ERROR", true, true);	
					return;
				}
				
				if (password.length() < 5) {
					LoginDialogView.dialogUtilShow("Password too short: minimun 5 character", "PASSWORD ERROR", true, true);	
					return;		
				}
				
				if (!(email.contains("@") && email.contains("."))) { // ugly, use regexp
					LoginDialogView.dialogUtilShow("Email format not correct", "EMAIL ERROR", true, true);	
					return;		
				}
				
				LoginServiceAsync login = (LoginServiceAsync) Registry
				.get(LoginDialogController.LOGINMOCKSERVICE);
				
				login.register(usernameRegistration, password, email, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						LoginDialogView.dialogUtilShow("Error... " + caught.getMessage() + "", "REGISTRATION ERROR", true, true);
					}

					@Override
					public void onSuccess(String username) {

							loginDialog.hide();

							AppView aView = Registry.get(AppView.MAINAPPVIEWPORT); // TODO dispatch an Event to MainView instead of Registry
							aView.setApplicationTitle(" - " + username);

							LoginDialogView.dialogUtilShow("Thanks for your registration, " + username + ". An email will be sent to you to confirm registration.", "REGISTRATION SUCCESFULL", true, false);
							refresh();
					}

				});
			}

		});

	}
	
	private void refresh()  {
		loginFirstName.setValue(""); loginPass.setValue("");
		
		firstNameReg.setValue(""); passReg.setValue("");
		pass2Reg.setValue(""); emailReg.setValue("");
	}
	
	public static void dialogUtilShow(String msg, String title, boolean isModal, boolean isError) {
		MessageBox mb = new MessageBox();
		mb.setMessage(msg);
		mb.setTitle(title);
		mb.setModal(isModal);
		mb.setIcon(isError ? MessageBox.ERROR : MessageBox.INFO);
		mb.show();
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

		fieldSet.add(btnLogin, formData);
		// formPanel.addButton(btnLogin);

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

		firstNameReg = new TextField<String>();
		firstNameReg.setFieldLabel("Name");
		// firstName.setAllowBlank(false);

		fieldSet.add(firstNameReg, formData);

		passReg = new TextField<String>();
		passReg.setFieldLabel("Password");
		passReg.setPassword(true);
		fieldSet.add(passReg, formData);

		pass2Reg = new TextField<String>();
		pass2Reg.setFieldLabel("Repeat Password");
		pass2Reg.setPassword(true);
		fieldSet.add(pass2Reg, formData);

		emailReg = new TextField<String>();
		emailReg.setFieldLabel("email");
		emailReg.setPassword(false);
		fieldSet.add(emailReg, formData);

		fieldSet.add(btnRegister, formData);

		formPanel.add(fieldSet);
		formPanel.setWidth(310);
		return formPanel;
	}

	@Override
	protected void handleEvent(AppEvent event) {

		if (event.getType() == ApplicationEvents.ShowLoginEvent) {
			loginDialog.show();
		}
	}

}
