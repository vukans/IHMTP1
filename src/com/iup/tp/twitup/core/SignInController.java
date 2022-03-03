package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.ISignInController;
import com.iup.tp.twitup.datamodel.ISignedInObserver;
import com.iup.tp.twitup.datamodel.User;

import java.util.ArrayList;
import java.util.List;

public class SignInController implements ISignInController {

	private final List<ISignedInObserver> signedInObservers;

	private final IDatabase database;

	public SignInController(IDatabase database) {
		signedInObservers = new ArrayList<>();

		this.database = database;
	}

	@Override
	public void notifyLogin(String tag, String password) {
		if (tag == null || password == null) {
			signedInObservers.forEach(ISignedInObserver::notifyWrongInputs);
		} else {
			User connectedUser = database.getUsers().stream().filter(user -> user.getUserTag().equals(tag)).findFirst().orElse(null);
			if (connectedUser != null && connectedUser.getUserPassword().equals(password)) {
				signedInObservers.forEach(res -> res.notifySuccess(connectedUser));
			} else {
				signedInObservers.forEach(res -> res.notifyError(tag));
			}
		}
	}

	public void addSignedInObserver(ISignedInObserver signedInObserver) {
		signedInObservers.add(signedInObserver);
	}
}
