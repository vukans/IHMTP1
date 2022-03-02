package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.ISignInController;

public class SignInController implements ISignInController {

	public SignInController() {

	}

	@Override
	public void notifyLogin(String username, String password) {
		System.out.println(username + " " + password);
	}

	@Override
	public void notifyCancel() {

	}
}
