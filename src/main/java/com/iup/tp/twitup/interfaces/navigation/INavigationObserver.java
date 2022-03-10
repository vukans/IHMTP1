package com.iup.tp.twitup.interfaces.navigation;

public interface INavigationObserver {

	void doSetWelcomeView();

	void doSetAboutView();

	void doSetSignInView();

	void doSetSignUpView();

	void doSetLogOutView();

	void doSetMyProfilView();

	void doExitApplication();

	void doSetProfilesView();

	void doSetTwitsView();
}
