package com.iup.tp.twitup.interfaces.session;

import com.iup.tp.twitup.model.UserModel;

public interface ILoggedInObserver {

	void notifyLoggedIn(UserModel profilConnected);
}
