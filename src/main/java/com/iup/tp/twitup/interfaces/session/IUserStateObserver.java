package com.iup.tp.twitup.interfaces.session;

public interface IUserStateObserver {

	void notifyUserConnected();

	void notifyUserDisconnected();
}
