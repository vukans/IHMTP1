package com.iup.tp.twitup.common;

import java.util.UUID;

/**
 * Classe de contantes de l'appli.
 * 
 * @author S.Lucas
 */
public interface Constants {
	/**
	 * Extension des fichiers XML des User
	 */
	String USER_FILE_EXTENSION = "usr";

	/**
	 * Extension des fichiers XML des Twit
	 */
	String TWIT_FILE_EXTENSION = "twt";

	/**
	 * Extension des fichiers XML des DB utilisateur
	 */
	String DB_FILE_EXTENSION = "db";

	/**
	 * Répertoire des fichiers temporaires du système.
	 */
	String SYSTEM_TMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * Séparateur de fichier du système.
	 */
	String SYSTEM_FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * Caractère pour délimiter les tags référencant des utilisateurs.
	 */
	String USER_TAG_DELIMITER = "@";

	/**
	 * Caractère pour délimiter les tags référencant des mots-clés.
	 */
	String WORD_TAG_DELIMITER = "#";

	/**
	 * Identifiant de l'utilisateur inconnu.
	 */
	UUID UNKNONWN_USER_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

	/**
	 * Fichier de configuration de l'application.
	 */
	String CONFIGURATION_FILE = "src/resources/configuration.properties";

	/**
	 * Clé de configuration pour la sauvegarde du répertoire d'échange.
	 */
	String CONFIGURATION_KEY_EXCHANGE_DIRECTORY = "EXCHANGE_DIRECTORY";

	/**
	 * Clé de configuration pour l'UI
	 */
	String CONFIGURATION_KEY_UI_CLASS_NAME = "UI_CLASS_NAME";

	/**
	 * Clé de configuration pour le mode bouchoné
	 */
	String CONFIGURATION_KEY_MOCK_ENABLED = "MOCK_ENABLED";
}
