package com.iup.tp.twitup.datamodel;

public class ConnectedUserModel {

	private User user;

	public ConnectedUserModel(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
