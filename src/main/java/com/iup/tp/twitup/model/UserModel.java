package com.iup.tp.twitup.model;

import com.iup.tp.twitup.datamodel.User;

public class UserModel {

	private User user;

	public UserModel() {
		// Constructeur vide
	}

	public UserModel(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
