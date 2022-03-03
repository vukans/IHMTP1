package com.iup.tp.twitup.observer.session;

public interface ISessionObserver {

	void notifySignIn(String tag, String password);

	void notifySignUp(String tag, String username, String password);

	void notifyLogOut();

	void addSignedInObserver(ISignedInObserver signedInObserver);

	void addSignedUpObserver(ISignedUpObserver signedUpObserver);

	void addLoggedInObservers(ILoggedInObserver loggedInObserver);

	void addLoggedOutObserver(ILoggedOutObserver loggedOutObserver);

	void addUserStateObserver(IUserStateObserver userStateObserver);
}
