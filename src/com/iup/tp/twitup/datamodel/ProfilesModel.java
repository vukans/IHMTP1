package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.List;

public class ProfilesModel {

	private List<User> profiles;

	public ProfilesModel() {
		this.profiles = new ArrayList<>();
	}

	public List<User> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<User> profiles) {
		this.profiles = profiles;
	}
}
