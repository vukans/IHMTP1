package com.iup.tp.twitup.datamodel;

public interface INavigationObserver {

	void loadWelcomeView();

	void loadAboutView();

	void loadSignInView();

	void loadSignUp();

	void exit();
}
