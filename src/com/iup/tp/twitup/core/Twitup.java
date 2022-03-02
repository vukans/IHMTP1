package com.iup.tp.twitup.core;

import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.INavigationObserver;
import com.iup.tp.twitup.view.*;
import com.iup.tp.twitup.view.mock.TwitupMock;

import javax.swing.*;
import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitup implements INavigationObserver {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 */
	public Twitup() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}

		// Initialisation de l'IHM
		this.initGui();

		// Initialisation du répertoire d'échange
		this.initDirectory();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ignored) {

		}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		mMainView = new TwitupView();
		mMainView.getContentPane().add(new WelcomeView(mExchangeDirectoryPath));
		mMainView.addNavigationObserver(this);
		mMainView.showGUI();
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		String folder = this.mMainView.askDirectory();
		this.initDirectory(folder);
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
		System.out.println(mExchangeDirectoryPath);
	}

	public void show() {
		// ... setVisible?
	}

	@Override
	public void loadWelcomeView() {
		loadView(new WelcomeView(mExchangeDirectoryPath));
	}

	@Override
	public void loadAboutView() {
		loadView(new AboutView());
	}

	@Override
	public void loadLoginView() {
		SignInController loginObserver = new SignInController();
		SignInView loginView = new SignInView();
		loginView.addController(loginObserver);
		loadView(loginView);
	}

	@Override
	public void loadSignUp() {
		SignUpController signUpController = new SignUpController(mEntityManager, mDatabase);
		SignUpView signUpView = new SignUpView();
		signUpView.addController(signUpController);
		loadView(signUpView);
	}

	private void loadView(ViewBase viewBase) {
		mMainView.getContentPane().removeAll();
		mMainView.getContentPane().add(viewBase);
		mMainView.revalidate();
		mMainView.repaint();
	}
}
