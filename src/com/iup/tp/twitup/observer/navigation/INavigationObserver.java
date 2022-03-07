package com.iup.tp.twitup.observer.navigation;

public interface INavigationObserver {

	void loadWelcomeView();

	void loadAboutView();

	void loadSignInView();

	void loadSignUpView();

	void loadDisconnectView();

	void loadProfilView();

	void exit();

	void loadProfilesView();
}
