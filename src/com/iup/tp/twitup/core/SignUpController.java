package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.ISignUpController;
import com.iup.tp.twitup.datamodel.ISignedUpObserver;
import com.iup.tp.twitup.datamodel.User;

import java.util.*;
import java.util.stream.Collectors;

public class SignUpController implements ISignUpController {

	private final List<ISignedUpObserver> signedUpObservers;

	private final EntityManager entityManager;
	private final IDatabase database;

	public SignUpController(EntityManager entityManager, IDatabase database) {
		signedUpObservers = new ArrayList<>();

		this.entityManager = entityManager;
		this.database = database;
	}

	@Override
	public void notifyRegisterUser(String tag, String username, String password) {
		if (tag == null || tag.length() < 4 ||
				username == null || password == null ||
				username.length() < 4 || password.length() < 6) {
			signedUpObservers.forEach(ISignedUpObserver::notifyWrongInputs);
		} else {
			UUID uuid = UUID.randomUUID();
			User user = new User(uuid, tag, password, username, new HashSet<>(), "");
			Set<User> users = database.getUsers().stream().filter(res -> res.getUserTag().equals(tag)).collect(Collectors.toSet());

			if (users.isEmpty()) {
				entityManager.sendUser(user);
				signedUpObservers.forEach(res -> res.notifyUserCreated(user));
			} else {
				signedUpObservers.forEach(res -> res.notifyUserAlreadyExists(user));
			}
		}
	}

	public void addSignedUpObserver(ISignedUpObserver signedUpObserver) {
		signedUpObservers.add(signedUpObserver);
	}
}

