package br.com.mr.baseapp.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.gargoylesoftware.htmlunit.AlertHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FormUser extends Window {
	private TextField<String> fieldName;
	private TextField<String> fieldPass;
	private Button okButton;
	private Button cancelButton;

	public FormUser() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new FormLayout());
		add(getFieldName(), new FormData("100%"));
		add(getFieldPass(), new FormData("100%"));
		addButton(getCancelButton());
		addButton(getOkButton());
	}

	private TextField<String> getFieldName() {
		if (fieldName == null) {
			fieldName = new TextField<String>();
			fieldName.setFieldLabel("Nome");
			fieldName.setStyleName("serverResponseLabelError");

		}
		return fieldName;
	}

	private TextField<String> getFieldPass() {
		if (fieldPass == null) {
			fieldPass = new TextField<String>();
			fieldPass.setPassword(true);
			fieldPass.setFieldLabel("Senha");
		}
		return fieldPass;
	}

	UserServiceAsync serviceAsync = GWT.create(UserService.class);

	private Button getOkButton() {
		if (okButton == null) {
			okButton = new Button("Ok");
			okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					save(fieldName.getValue(), fieldPass.getValue());
				}
			});
		}
		;
		return okButton;
	}

	protected void save(String name, String pass) {
		serviceAsync.save(name, pass, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean saved) {
				if (saved) {
					clearState();
					Info.display("Info", "Usuário salvo com sucesso!!!");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Erro", "Erro ao salvar usuário!!");
			}
		});

	}

	private Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
		}
		return cancelButton;
	}
}
