package com.preguicoso.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class UserDropdownMenu extends UIObject {

	private static UserDropdownMenuUiBinder uiBinder = GWT
			.create(UserDropdownMenuUiBinder.class);

	interface UserDropdownMenuUiBinder extends
			UiBinder<Element, UserDropdownMenu> {
	}

	@UiField
	SpanElement name;

	public UserDropdownMenu(String firstName) {
		setElement(uiBinder.createAndBindUi(this));
		name.setInnerText(firstName);
	}

}
