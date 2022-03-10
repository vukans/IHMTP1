package com.iup.tp.twitup.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Classe utilitaire pour la gestion des fichiers.
 *
 * @author S.Lucas
 */
public class FilesUtils {

	private FilesUtils() {

	}

	/**
	 * Déplacement du fichier source vers le fichier destination.
	 *
	 * @param sourceFileName , Chemin du fichier source
	 * @param destFileName   , Chemin du fichier de destination
	 * @return un booléen indiquant si le déplacement s'est déroulé avec succès.
	 */
	public static boolean moveFile(File sourceFileName, String destFileName) {
		boolean isOk;

		// Copie du fichier
		isOk = copyFile(sourceFileName.getAbsolutePath(), destFileName);

		// Si c'est bon, suppression de la source
		if (isOk) {
			sourceFileName.delete();
		}

		return isOk;
	}

	/**
	 * Déplacement du fichier source vers le fichier destination.
	 *
	 * @param sourceFileName , Chemin du fichier source
	 * @param destFileName   , Chemin du fichier de destination
	 * @return un booléen indiquant si la copie s'est déroulée avec succ�s.
	 */
	public static boolean copyFile(String sourceFileName, String destFileName) {
		boolean isOk = false;

		try (FileInputStream fis = new FileInputStream(sourceFileName);
		     FileChannel in = fis.getChannel();
		     FileOutputStream fos = new FileOutputStream(destFileName);
		     FileChannel out = fos.getChannel()) {

			in.transferTo(0, in.size(), out);
			isOk = true;

		} catch (Throwable t) {
			System.err.println("Erreur lors de la copie du fichier '" + sourceFileName + "'");
			t.printStackTrace();
		}

		return isOk;
	}
}
