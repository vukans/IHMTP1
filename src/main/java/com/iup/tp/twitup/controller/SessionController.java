package com.iup.tp.twitup.controller;

import com.iup.tp.twitup.common.FilesUtils;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.interfaces.session.*;
import com.iup.tp.twitup.model.UserModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class SessionController implements ISessionObserver {

	private final EntityManager entityManager;
	private final IDatabase database;

	private final List<ISignedInObserver> signedInObservers;
	private final List<ISignedUpObserver> signedUpObservers;
	private final List<ILoggedInObserver> loggedInObservers;
	private final List<ILoggedOutObserver> loggedOutObservers;
	private final List<IUserStateObserver> userStateObservers;

	public SessionController(EntityManager entityManager, IDatabase database) {
		this.entityManager = entityManager;
		this.database = database;

		signedInObservers = new ArrayList<>();
		signedUpObservers = new ArrayList<>();
		loggedInObservers = new ArrayList<>();
		loggedOutObservers = new ArrayList<>();
		userStateObservers = new ArrayList<>();
	}

	public void notifyDoLogIn(String tag, String password) {
		if (tag == null || tag.isEmpty() || password == null || password.isEmpty()) {
			signedInObservers.forEach(ISignedInObserver::notifyWrongInputs);
		} else {
			User connectedUser = database.getUsers().stream().filter(user -> user.getUserTag().equals(tag)).findFirst().orElse(null);
			if (connectedUser != null && connectedUser.getUserPassword().equals(password)) {
				signedInObservers.forEach(ISignedInObserver::notifySignIn);
				loggedInObservers.forEach(res -> res.notifyLoggedIn(new UserModel(connectedUser)));
				userStateObservers.forEach(IUserStateObserver::notifyUserConnected);
			} else {
				signedInObservers.forEach(res -> res.notifyTagNotFound(tag));
			}
		}
	}

	public void notifyDoSignUp(String tag, String userName, String password, File avatar) {
		if (tag == null || tag.length() < 4 ||
				userName == null || userName.length() < 4 ||
				password == null || password.length() < 6 ||
				avatar == null) {
			signedUpObservers.forEach(ISignedUpObserver::notifyWrongInputs);
		} else {
			UUID uuid = UUID.randomUUID();

			User user = new User(uuid, tag, password, userName, new HashSet<>(), this.entityManager.mDirectoryPath + "\\" + avatar.getName());

			boolean notAlreadyUsed = database.getUsers().stream().filter(res -> res.getUserTag().equals(tag)).findFirst().orElse(null) == null;

			if (notAlreadyUsed) {
				entityManager.sendUser(user);
				File file = new File(String.format("%s\\%s", entityManager.mDirectoryPath, avatar.getName()));
				FilesUtils.copyFile(avatar.getAbsolutePath(), file.getAbsolutePath());
				signedUpObservers.forEach(res -> res.notifySignedUp(user.getUserTag()));
			} else {
				signedUpObservers.forEach(res -> res.notifyUserAlreadyExists(user.getUserTag()));
			}
		}
	}

	public void notifyDoLogOut() {
		loggedOutObservers.forEach(ILoggedOutObserver::notifyLoggedOut);
		userStateObservers.forEach(IUserStateObserver::notifyUserDisconnected);
	}

	public void addSignedInObserver(ISignedInObserver signedInObserver) {
		signedInObservers.add(signedInObserver);
	}

	public void removeSignedInObserver(ISignedInObserver signedInObserver) {
		signedInObservers.remove(signedInObserver);
	}

	public void addSignedUpObserver(ISignedUpObserver signedUpObserver) {
		signedUpObservers.add(signedUpObserver);
	}

	public void removeSignedUpObserver(ISignedUpObserver signedUpObserver) {
		signedUpObservers.remove(signedUpObserver);
	}

	public void addLoggedInObserver(ILoggedInObserver loggedInObserver) {
		loggedInObservers.add(loggedInObserver);
	}

	public void removeLoggedInObserver(ILoggedInObserver loggedInObserver) {
		loggedInObservers.remove(loggedInObserver);
	}

	public void addLoggedOutObserver(ILoggedOutObserver logoutController) {
		loggedOutObservers.add(logoutController);
	}

	public void removeLoggedOutObserver(ILoggedOutObserver logoutController) {
		loggedOutObservers.remove(logoutController);
	}

	public void addUserStateObserver(IUserStateObserver userStateObserver) {
		userStateObservers.add(userStateObserver);
	}

	public void removeUserStateObserver(IUserStateObserver userStateObserver) {
		userStateObservers.remove(userStateObserver);
	}

}
