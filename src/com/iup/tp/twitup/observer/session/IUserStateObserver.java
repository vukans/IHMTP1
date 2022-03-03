package com.iup.tp.twitup.observer.session;

public interface IUserStateObserver {

	void notifyIsConnected();

	void notifyIsDisconnected();
}
