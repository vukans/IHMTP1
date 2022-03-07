package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.*;
import com.iup.tp.twitup.observer.profiles.IGetProfilesObserver;
import com.iup.tp.twitup.observer.profiles.IProfilesObserver;

import java.util.ArrayList;
import java.util.List;

public class ProfilesViewer implements IProfilesObserver {

	private final List<IGetProfilesObserver> getProfilesObservers;

	private final EntityManager entityManager;
	private final IDatabase database;
	private final ConnectedUserModel connectedUser;

	public ProfilesViewer(EntityManager entityManager, IDatabase database, ConnectedUserModel connectedUser) {
		this.entityManager = entityManager;
		this.database = database;
		this.connectedUser = connectedUser;

		getProfilesObservers = new ArrayList<>();
	}

	@Override
	public void notifyGetProfile(String tague) {
		User user = database.getUsers().stream().filter(res -> res.getUserTag().equals(tague)).findFirst().orElse(null);
		ProfilModel userModel = new ProfilModel();
		userModel.setUser(user);
		if (user != null) {
			getProfilesObservers.forEach(res -> res.notifyGetProfile(connectedUser, userModel));
		} else {
			notifyGetProfiles();
		}
	}

	@Override
	public void notifyGetProfiles() {
		ProfilesModel profilesModel = new ProfilesModel();
		profilesModel.setProfiles(new ArrayList<>(database.getUsers()));
		getProfilesObservers.forEach(res -> res.notifyGotProfiles(connectedUser, profilesModel));
	}

	public void addGetProfilesObserver(IGetProfilesObserver getProfilesObserver) {
		getProfilesObservers.add(getProfilesObserver);
	}
}
