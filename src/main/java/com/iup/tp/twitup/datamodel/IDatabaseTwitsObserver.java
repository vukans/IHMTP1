package com.iup.tp.twitup.datamodel;

public interface IDatabaseTwitsObserver {

	/**
	 * Notification lorsqu'un Twit est ajouté en base de données.
	 *
	 * @param addedTwit addedTwit
	 */
	void notifyTwitAdded(Twit addedTwit);

	/**
	 * Notification lorsqu'un Twit est supprimé de la base de données.
	 *
	 * @param deletedTwit deletedTwit
	 */
	void notifyTwitDeleted(Twit deletedTwit);

	/**
	 * Notification lorsqu'un Twit est modifié en base de données.
	 *
	 * @param modifiedTwit modifiedTwit
	 */
	void notifyTwitModified(Twit modifiedTwit);
}
