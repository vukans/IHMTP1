package com.iup.tp.twitup.interfaces.session;

import java.io.File;

public interface ISessionObserver {

	void notifyDoLogIn(String tag, String password);

	void notifyDoSignUp(String tag, String userName, String password, File avatar);

	void notifyDoLogOut();
}
