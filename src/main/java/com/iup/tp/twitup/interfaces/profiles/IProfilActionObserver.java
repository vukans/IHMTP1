package com.iup.tp.twitup.interfaces.profiles;

import com.iup.tp.twitup.model.UserModel;
import com.iup.tp.twitup.model.UsersModel;

public interface IProfilActionObserver {

	void notifyGotOneProfil(UserModel connectedUserModel, UserModel profilSeeked);

	void notifyGotAllProfils(UserModel connectedUserModel, UsersModel profilsSeeked);
}
