package com.iup.tp.twitup.view;

import com.iup.tp.twitup.interfaces.navigation.INavigationObserver;
import com.iup.tp.twitup.interfaces.session.IUserStateObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame implements IUserStateObserver {

	private final List<INavigationObserver> navigationObservers;

	private final JMenuItem profil;
	private final JMenuItem signIn;
	private final JMenuItem signUp;
	private final JMenuItem logOut;

	public TwitupMainView() {
		super("Le twitter du McDonald's du patelin");

		setSize(950, 600);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/mcdo.png")).getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		navigationObservers = new ArrayList<>();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuMaxiBestOf = new JMenu("Menu MAXI BestOf");
		menuBar.add(menuMaxiBestOf);

		JMenuItem welcome = new JMenuItem("BigMac"); // Welcome
		welcome.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/bigmac.png")));
		welcome.addActionListener(e -> setWelcomeView());
		menuMaxiBestOf.add(welcome);

		JMenuItem about = new JMenuItem("CauetBurger"); // About
		about.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cauet.png")));
		about.addActionListener(e -> setAboutView());
		menuMaxiBestOf.add(about);

		profil = new JMenuItem("Tenders"); // Profil
		profil.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/tenders.png")));
		profil.addActionListener(e -> setMyProfilView());
		profil.setVisible(false);
		menuMaxiBestOf.add(profil);

		signIn = new JMenuItem("CBO"); // Connexion
		signIn.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cbo.png")));
		signIn.addActionListener(e -> setSignInView());
		menuMaxiBestOf.add(signIn);

		signUp = new JMenuItem("Whooper"); // Inscription
		signUp.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/whooper.png")));
		signUp.addActionListener(e -> setSignUpView());
		menuMaxiBestOf.add(signUp);

		logOut = new JMenuItem("McChicken"); // Déconnexion
		logOut.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/mcchicken.png")));
		logOut.addActionListener(e -> setLogOutView());
		logOut.setVisible(false);
		menuMaxiBestOf.add(logOut);

		JMenuItem close = new JMenuItem("ChickenDips"); // Quitter
		close.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/dips.png")));
		close.addActionListener(e -> doExitApplication());
		menuMaxiBestOf.add(close);

		JMenu menuBestOf = new JMenu("Menu BestOf");
		menuBar.add(menuBestOf);

		JMenuItem profiles = new JMenuItem("Bucket");
		profiles.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/bucket.png")));
		profiles.addActionListener(e -> setProfilesView());
		menuBestOf.add(profiles);

		JMenuItem twits = new JMenuItem("HappyMeal");
		twits.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/meal.png")));
		twits.addActionListener(e -> setTwitsView());
		menuBestOf.add(twits);
	}

	public void showGUI() {
		setVisible(true);
	}

	public String askDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("PRend dosser");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().toString();
		} else {
			doExitApplication();
			return null;
		}
	}

	private void setWelcomeView() {
		navigationObservers.forEach(INavigationObserver::doSetWelcomeView);
	}

	private void setAboutView() {
		navigationObservers.forEach(INavigationObserver::doSetAboutView);
	}

	private void setSignInView() {
		navigationObservers.forEach(INavigationObserver::doSetSignInView);
	}

	private void setSignUpView() {
		navigationObservers.forEach(INavigationObserver::doSetSignUpView);
	}

	private void setLogOutView() {
		navigationObservers.forEach(INavigationObserver::doSetLogOutView);
	}

	private void setMyProfilView() {
		navigationObservers.forEach(INavigationObserver::doSetMyProfilView);
	}

	private void setProfilesView() {
		navigationObservers.forEach(INavigationObserver::doSetProfilesView);
	}

	private void setTwitsView() {
		navigationObservers.forEach(INavigationObserver::doSetTwitsView);
	}

	private void doExitApplication() {
		navigationObservers.forEach(INavigationObserver::doExitApplication);
	}

	@Override
	public void notifyUserConnected() {
		profil.setVisible(true);
		signIn.setVisible(false);
		signUp.setVisible(false);
		logOut.setVisible(true);
	}

	@Override
	public void notifyUserDisconnected() {
		profil.setVisible(false);
		signIn.setVisible(true);
		signUp.setVisible(true);
		logOut.setVisible(false);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	public void removeNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.remove(navigationObserver);
	}
}
