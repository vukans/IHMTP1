package com.iup.tp.twitup.model;

import com.iup.tp.twitup.datamodel.User;

import java.util.ArrayList;
import java.util.List;

public class UsersModel {

	private List<User> profiles;

	public UsersModel() {
		this.profiles = new ArrayList<>();
	}

	public List<User> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<User> profiles) {
		this.profiles = profiles;
	}
}
