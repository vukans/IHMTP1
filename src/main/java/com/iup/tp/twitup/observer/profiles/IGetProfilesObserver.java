package com.iup.tp.twitup.observer.profiles;

import com.iup.tp.twitup.datamodel.ConnectedUserModel;
import com.iup.tp.twitup.datamodel.ProfilModel;
import com.iup.tp.twitup.datamodel.ProfilesModel;

public interface IGetProfilesObserver {

	void notifyGetProfile(ConnectedUserModel connectedUserModel, ProfilModel userModel);

	void notifyGotProfiles(ConnectedUserModel connectedUserModel, ProfilesModel profilesModel);
}
