package com.iup.tp.twitup.datamodel;

public interface IDatabaseUsersObserver {

	/**
	 * Notification lorsqu'un utilisateur est ajouté en base de données.
	 *
	 * @param addedUser addedUser
	 */
	void notifyUserAdded(User addedUser);

	/**
	 * Notification lorsqu'un utilisateur est supprimé de la base de données.
	 *
	 * @param deletedUser deletedUser
	 */
	void notifyUserDeleted(User deletedUser);

	/**
	 * Notification lorsqu'un utilisateur est modifié en base de données.
	 *
	 * @param modifiedUser modifiedUser
	 */
	void notifyUserModified(User modifiedUser);
}
