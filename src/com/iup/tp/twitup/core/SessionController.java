package com.iup.tp.twitup.core;

import com.iup.tp.twitup.common.FilesUtils;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.session.*;

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

	public void notifySignIn(String tag, String password) {
		if (tag == null || tag.isEmpty() || password == null || password.isEmpty()) {
			signedInObservers.forEach(ISignedInObserver::notifyWrongInputs);
		} else {
			User connectedUser = database.getUsers().stream().filter(user -> user.getUserTag().equals(tag)).findFirst().orElse(null);
			if (connectedUser != null && connectedUser.getUserPassword().equals(password)) {
				signedInObservers.forEach(ISignedInObserver::notifySuccess);
				loggedInObservers.forEach(res -> res.notifyLoggedIn(connectedUser));
				userStateObservers.forEach(IUserStateObserver::notifyIsConnected);
			} else {
				signedInObservers.forEach(res -> res.notifyError(tag));
			}
		}
	}

	public void notifySignUp(String tag, String username, String password, File avatar) {
		if (tag == null || tag.length() < 4 ||
				username == null || username.length() < 4 ||
				password == null || password.length() < 6 ||
				avatar == null) {
			signedUpObservers.forEach(ISignedUpObserver::notifyWrongInputs);
		} else {
			UUID uuid = UUID.randomUUID();

			User user = new User(uuid, tag, password, username, new HashSet<>(), entityManager.mDirectoryPath + "\\" + avatar.getName());

			boolean notAlreadyUsed = database.getUsers().stream().filter(res -> res.getUserTag().equals(tag)).findFirst().orElse(null) == null;

			if (notAlreadyUsed) {
				entityManager.sendUser(user);
				File file = new File(entityManager.mDirectoryPath + "\\" + avatar.getName());
				FilesUtils.copyFile(avatar.getAbsolutePath(), file.getAbsolutePath());
				signedUpObservers.forEach(res -> res.notifyUserCreated(user.getUserTag()));
			} else {
				signedUpObservers.forEach(res -> res.notifyUserAlreadyExists(user.getUserTag()));
			}
		}
	}

	public void notifyLogOut() {
		loggedOutObservers.forEach(ILoggedOutObserver::notifyLoggedOut);
		userStateObservers.forEach(IUserStateObserver::notifyIsDisconnected);
	}

	public void addSignedInObserver(ISignedInObserver signedInObserver) {
		signedInObservers.add(signedInObserver);
	}

	public void addSignedUpObserver(ISignedUpObserver signedUpObserver) {
		signedUpObservers.add(signedUpObserver);
	}

	public void addLoggedInObservers(ILoggedInObserver loggedInObserver) {
		loggedInObservers.add(loggedInObserver);
	}

	public void addLoggedOutObserver(ILoggedOutObserver logoutController) {
		loggedOutObservers.add(logoutController);
	}

	public void addUserStateObserver(IUserStateObserver userStateObserver) {
		userStateObservers.add(userStateObserver);
	}
}
