package com.iup.tp.twitup.interfaces.session;

public interface ISignedInObserver {

	void notifySignIn();

	void notifyTagNotFound(String tag);

	void notifyWrongInputs();
}
