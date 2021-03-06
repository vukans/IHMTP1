package com.iup.tp.twitup.core;

import com.iup.tp.twitup.controller.FollowController;
import com.iup.tp.twitup.controller.ProfilsController;
import com.iup.tp.twitup.controller.SessionController;
import com.iup.tp.twitup.controller.TwitsController;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.interfaces.navigation.INavigationObserver;
import com.iup.tp.twitup.interfaces.session.ILoggedInObserver;
import com.iup.tp.twitup.interfaces.session.ILoggedOutObserver;
import com.iup.tp.twitup.model.UserModel;
import com.iup.tp.twitup.view.*;
import com.iup.tp.twitup.view.mock.TwitupMock;

import javax.swing.*;
import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class Twitup implements INavigationObserver, ILoggedInObserver, ILoggedOutObserver {
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
	protected TwitupMainView mMainView;

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
	 * Utilisateur connecté.
	 */
	protected User connectedUser;

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
			// Ignored.
		}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		mMainView = new TwitupMainView();
		mMainView.getContentPane().add(new WelcomeView());
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
		this.initDirectory(this.mMainView.askDirectory());
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
	 * @param directoryPath directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// ... setVisible?
	}

	@Override
	public void doSetWelcomeView() {
		loadView(new WelcomeView());
	}

	@Override
	public void doSetAboutView() {
		loadView(new AboutView());
	}

	@Override
	public void doSetMyProfilView() {

		UserModel connectedUserModel = new UserModel(connectedUser);

		MyProfilView profilView = new MyProfilView(connectedUserModel);

		loadView(profilView);
	}

	@Override
	public void doSetSignInView() {
		SessionController sessionController = new SessionController(mEntityManager, mDatabase);

		SignInView signInView = new SignInView();

		signInView.addSessionObserver(sessionController);
		signInView.addNavigationObserver(this);

		sessionController.addSignedInObserver(signInView);
		sessionController.addLoggedInObserver(this);
		sessionController.addUserStateObserver(mMainView);

		loadView(signInView);
	}

	@Override
	public void doSetSignUpView() {
		SessionController sessionController = new SessionController(mEntityManager, mDatabase);

		SignUpView signUpView = new SignUpView();

		signUpView.addSessionObserver(sessionController);

		sessionController.addSignedUpObserver(signUpView);
		sessionController.addUserStateObserver(mMainView);

		loadView(signUpView);
	}

	@Override
	public void doSetLogOutView() {
		SessionController sessionController = new SessionController(mEntityManager, mDatabase);

		LogOutView disconnectView = new LogOutView();

		disconnectView.addNavigationObserver(this);
		disconnectView.addSessionObserver(sessionController);

		sessionController.addLoggedOutObserver(this);
		sessionController.addUserStateObserver(mMainView);

		loadView(disconnectView);
	}

	@Override
	public void doSetProfilesView() {
		FollowController followController = new FollowController(mEntityManager, mDatabase);

		UserModel connectedUserModel = new UserModel(connectedUser);

		ProfilsController profilesController = new ProfilsController(mDatabase, connectedUserModel);

		ProfilesView profilesView = new ProfilesView();

		profilesView.addProfilesObserver(profilesController);
		profilesView.addFollowObserver(followController);

		profilesController.addGotProfilsObserver(profilesView);

		followController.addFollowActionObserver(profilesView);

		loadView(profilesView);
	}

	public void doSetTwitsView() {
		TwitsController twitsController = new TwitsController(mEntityManager, mDatabase);

		UserModel connectedUserModel = new UserModel(connectedUser);

		TwitsView twitsView = new TwitsView(connectedUserModel);

		twitsView.addTwitsObserver(twitsController);

		twitsController.addIGotTwitsObserver(twitsView);

		mDatabase.addIDatabaseTwitsObserver(twitsController);

		loadView(twitsView);
	}

	private void loadView(ViewBase viewBase) {
		mMainView.getContentPane().removeAll();
		mMainView.getContentPane().add(viewBase);
		mMainView.revalidate();
		mMainView.repaint();
	}

	@Override
	public void notifyLoggedIn(UserModel userModel) {
		connectedUser = userModel.getUser();
	}

	@Override
	public void notifyLoggedOut() {
		connectedUser = null;
	}

	@Override
	public void doExitApplication() {
		System.exit(0);
	}
}
