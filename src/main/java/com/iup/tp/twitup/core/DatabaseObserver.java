package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class DatabaseObserver implements IDatabaseObserver {

	@Override
	public void notifyTwitAdded(Twit addedTwit) {

	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {

	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {

	}

	@Override
	public void notifyUserAdded(User addedUser) {

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {

	}

	@Override
	public void notifyUserModified(User modifiedUser) {

	}
}
