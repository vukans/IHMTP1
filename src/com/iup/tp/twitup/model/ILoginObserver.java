package com.iup.tp.twitup.model;

public interface ILoginObserver {

	void notifyLoginAttempt(String username, String password);

	void notifyCancelAction();
}
