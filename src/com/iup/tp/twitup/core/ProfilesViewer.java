package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.observer.profiles.IGetProfilesObserver;
import com.iup.tp.twitup.observer.profiles.IProfilesObserver;

import java.util.ArrayList;
import java.util.List;

public class ProfilesViewer implements IProfilesObserver {

	private final List<IGetProfilesObserver> getProfilesObservers;

	private final EntityManager entityManager;
	private final IDatabase database;

	public ProfilesViewer(EntityManager entityManager, IDatabase database) {
		this.entityManager = entityManager;
		this.database = database;

		getProfilesObservers = new ArrayList<>();
	}

	@Override
	public void notifyGetProfiles() {
		database.getUsers().forEach(user -> {
			getProfilesObservers.forEach(res -> res.notifyGotProfiles(
					user.getUserTag(),
					user.getName(),
					user.getFollows().size(),
					user.getAvatarPath()));
		});
	}

	public void addGetProfilesObserver(IGetProfilesObserver getProfilesObserver) {
		getProfilesObservers.add(getProfilesObserver);
	}
}
