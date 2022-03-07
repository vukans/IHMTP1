package com.iup.tp.twitup.observer.session;

public interface ISignedUpObserver {

	void notifyUserCreated(String tag);

	void notifyUserAlreadyExists(String tag);

	void notifyWrongInputs();
}
