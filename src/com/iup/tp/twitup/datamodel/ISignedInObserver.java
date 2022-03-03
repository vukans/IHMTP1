package com.iup.tp.twitup.datamodel;

public interface ISignedInObserver {

	void notifySuccess(User user);

	void notifyError(String tag);

	void notifyWrongInputs();
}
