package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabaseTwitsObserver;
import com.iup.tp.twitup.datamodel.IDatabaseUsersObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class DatabaseObserver implements IDatabaseTwitsObserver, IDatabaseUsersObserver {

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// Not used
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// Not used
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// Not used
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// Not used
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// Not used
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// Not used
	}
}
