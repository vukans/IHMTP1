package com.iup.tp.twitup.interfaces.follows;

import com.iup.tp.twitup.model.UserModel;

public interface IFollowObserver {

	void notifyDoSubscribe(UserModel profilConnected, UserModel profilSeeked);

	void notifyDoUnsubscribe(UserModel profilConnected, UserModel profilSeeked);
}
