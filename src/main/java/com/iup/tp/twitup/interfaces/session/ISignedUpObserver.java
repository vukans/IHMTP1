package com.iup.tp.twitup.interfaces.session;

public interface ISignedUpObserver {

	void notifySignedUp(String tag);

	void notifyUserAlreadyExists(String tag);

	void notifyWrongInputs();
}
