package com.iup.tp.twitup.observer.session;

public interface ISignedInObserver {

	void notifySuccess();

	void notifyError(String tag);

	void notifyWrongInputs();
}
