package com.iup.tp.twitup.observer.session;

import com.iup.tp.twitup.datamodel.User;

public interface ILoggedInObserver {

	void notifyLoggedIn(User user);
}
