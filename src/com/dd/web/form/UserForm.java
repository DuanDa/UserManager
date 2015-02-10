package com.dd.web.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	private String username;

	public String getUsername() {
		System.out.println("---> get username");
		return username;
	}

	public void setUsername(String username) {
		System.out.println("---> set username");
		this.username = username;
	}
}
