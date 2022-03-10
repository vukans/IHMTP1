package com.iup.tp.twitup.interfaces.twits;

import com.iup.tp.twitup.exception.DirectoryException;
import com.iup.tp.twitup.model.UserModel;

public interface ITwitsObserver {

	void notifyDoSendTwit(UserModel profilConnected, String text) throws DirectoryException;

	void notifyDoGetTwits();
}
