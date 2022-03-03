package com.iup.tp.twitup.datamodel;

public interface ISignedUpObserver {

	void notifyUserCreated(User user);

	void notifyUserAlreadyExists(User user);

	void notifyWrongInputs();
}
