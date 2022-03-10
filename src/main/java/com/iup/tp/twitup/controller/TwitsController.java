package com.iup.tp.twitup.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseTwitsObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.exception.DirectoryException;
import com.iup.tp.twitup.interfaces.twits.ITwitsActionObserver;
import com.iup.tp.twitup.interfaces.twits.ITwitsObserver;
import com.iup.tp.twitup.model.TwitsModel;
import com.iup.tp.twitup.model.UserModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TwitsController implements ITwitsObserver, IDatabaseTwitsObserver {

	private final List<ITwitsActionObserver> gotTwitsObservers;

	private final EntityManager entityManager;
	private final IDatabase database;

	public TwitsController(EntityManager entityManager, IDatabase database) {
		this.entityManager = entityManager;
		this.database = database;

		gotTwitsObservers = new ArrayList<>();
	}

	private void sendTwitsFromDatabase() {
		TwitsModel twitsModel = new TwitsModel();
		twitsModel.setTwits(database.getTwits().stream().sorted(Comparator.comparingLong(Twit::getEmissionDate).reversed()).collect(Collectors.toList()));
		gotTwitsObservers.forEach(res -> res.notifyGotTwits(twitsModel));
	}

	@Override
	public void notifyDoSendTwit(UserModel connectedUserModel, String text) throws DirectoryException {
		if (text == null || text.isEmpty()) {
			gotTwitsObservers.forEach(ITwitsActionObserver::notifyEmptyTwit);
		} else if (text.length() > 250) {
			gotTwitsObservers.forEach(ITwitsActionObserver::notifyTextTooLong);
		} else {
			Twit twit = new Twit(connectedUserModel.getUser(), text);
			entityManager.sendTwit(twit);
		}
	}

	@Override
	public void notifyDoGetTwits() {
		sendTwitsFromDatabase();
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		sendTwitsFromDatabase();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// Not used
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// Not used
	}

	public void addIGotTwitsObserver(ITwitsActionObserver gotTwitsObserver) {
		gotTwitsObservers.add(gotTwitsObserver);
	}

	public void removeIGotTwitsObserver(ITwitsActionObserver gotTwitsObserver) {
		gotTwitsObservers.remove(gotTwitsObserver);
	}
}
