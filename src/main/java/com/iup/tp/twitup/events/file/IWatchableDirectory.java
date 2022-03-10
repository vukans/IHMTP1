package com.iup.tp.twitup.events.file;

/**
 * Interface de l'entité responsable de la surveillance d'un répertoire.
 *
 * @author S.Lucas
 */
public interface IWatchableDirectory {

	/**
	 * Initialisation de la surveillance du répertoire. <br/>
	 * <i> Les observeurs sont premièrement avertis du contenu initial du
	 * répertoire, puis avertis des modifications (ajout/suppression)</i>
	 */
	void initWatching();

	/**
	 * Arret de la surveillance du répertoire.
	 */
	void stopWatching();

	/**
	 * Changement du répertoire de surveillance. <br/>
	 * Les fichiers présents seront considérés comme 'supprimés' donc les
	 * observateurs seront notifiés comme tel.<br/>
	 * Un appel à la méthode {@link #initWatching()} est nécessaire pour
	 * relancer la surveillance.
	 *
	 * @param directoryPath , nouveau répertoire à surveiller.
	 */
	void changeDirectory(String directoryPath);

	/**
	 * Ajout un observateur qui sera notifié des changements dans le répertoire
	 * surveillé.
	 *
	 * @param observer observer
	 */
	void addObserver(IWatchableDirectoryObserver observer);

	/**
	 * Supprime un observateur de la liste (il ne sera plus notifiés des
	 * changements).
	 *
	 * @param observer observer
	 */
	void removeObserver(IWatchableDirectoryObserver observer);

}
