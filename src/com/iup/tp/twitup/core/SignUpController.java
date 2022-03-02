package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.ISignUpController;
import com.iup.tp.twitup.datamodel.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SignUpController implements ISignUpController {

	private EntityManager entityManager;
	private IDatabase database;

	public SignUpController(EntityManager entityManager, IDatabase database) {
		this.entityManager = entityManager;
		this.database = database;
	}

	@Override
	public boolean notifyRegisterUser(String username, String password) {
		boolean bool = false;
		UUID uuid = UUID.randomUUID();
		User user = new User(uuid, "@" + username, password, username, new HashSet<>(), "");
		Set<User> users = database.getUsers().stream().filter(res -> res.getName().equals(username)).collect(Collectors.toSet());
		if (users.isEmpty()) {
			database.addUser(user);
			entityManager.sendUser(user);
			bool = true;
		}
		return bool;
	}
}
