package com.iup.tp.twitup.controller;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.interfaces.profiles.IProfilActionObserver;
import com.iup.tp.twitup.interfaces.profiles.IProfilsObserver;
import com.iup.tp.twitup.model.UserModel;
import com.iup.tp.twitup.model.UsersModel;

import java.util.ArrayList;
import java.util.List;

public class ProfilsController implements IProfilsObserver {

	private final IDatabase database;
	private final UserModel profilConnected;

	private final List<IProfilActionObserver> gotProfilsObservers;

	public ProfilsController(IDatabase database, UserModel profilConnected) {
		this.database = database;
		this.profilConnected = profilConnected;

		gotProfilsObservers = new ArrayList<>();
	}

	@Override
	public void notifyDoSearchOne(String tag) {
		User user = database.getUsers().stream().filter(res -> res.getUserTag().equals(tag)).findFirst().orElse(null);
		UserModel userModel = new UserModel();
		userModel.setUser(user);
		if (user != null) {
			gotProfilsObservers.forEach(res -> res.notifyGotOneProfil(profilConnected, userModel));
		} else {
			notifyDoSearchAll();
		}
	}

	@Override
	public void notifyDoSearchAll() {
		UsersModel profilesModel = new UsersModel();
		profilesModel.setProfiles(new ArrayList<>(database.getUsers()));
		gotProfilsObservers.forEach(res -> res.notifyGotAllProfils(profilConnected, profilesModel));
	}

	public void addGotProfilsObserver(IProfilActionObserver gotProfilsObserver) {
		gotProfilsObservers.add(gotProfilsObserver);
	}

	public void removeGotProfilsObserver(IProfilActionObserver gotProfilsObserver) {
		gotProfilsObservers.remove(gotProfilsObserver);
	}
}
