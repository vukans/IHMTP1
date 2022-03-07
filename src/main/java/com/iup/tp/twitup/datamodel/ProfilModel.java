package com.iup.tp.twitup.datamodel;

public class ProfilModel {

	private User user;

	public ProfilModel() {
	}

	public ProfilModel(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
