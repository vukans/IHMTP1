package com.iup.tp.twitup.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.interfaces.follows.IFollowActionObserver;
import com.iup.tp.twitup.interfaces.follows.IFollowObserver;
import com.iup.tp.twitup.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class FollowController implements IFollowObserver {

	private final EntityManager entityManager;
	private final IDatabase database;

	private final List<IFollowActionObserver> followActionObservers;

	public FollowController(EntityManager entityManager, IDatabase database) {
		this.entityManager = entityManager;
		this.database = database;

		followActionObservers = new ArrayList<>();
	}

	public void notifyDoSubscribe(UserModel profilConnected, UserModel profilSeeked) {
		User userConnected = profilConnected.getUser();
		User userSeeked = profilSeeked.getUser();

		userConnected.addFollowing(userSeeked.getUserTag());

		database.modifiyUser(userConnected);

		entityManager.sendUser(userConnected);

		followActionObservers.forEach(IFollowActionObserver::notifySubscribed);
	}

	public void notifyDoUnsubscribe(UserModel profilConnected, UserModel profilSeeked) {
		User userConnected = profilConnected.getUser();
		User userSeeked = profilSeeked.getUser();

		userConnected.removeFollowing(userSeeked.getUserTag());

		database.modifiyUser(userConnected);

		entityManager.sendUser(userConnected);

		followActionObservers.forEach(IFollowActionObserver::notifyUnsubcribed);
	}

	public void addFollowActionObserver(IFollowActionObserver followActionObserver) {
		followActionObservers.add(followActionObserver);
	}

	public void removeFollowActionObserver(IFollowActionObserver followActionObserver) {
		followActionObservers.remove(followActionObserver);
	}
}
