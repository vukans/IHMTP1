package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.session.IUserStateObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame implements IUserStateObserver {

	private final List<INavigationObserver> navigationObservers;

	private final JMenuBar bar;

	private final JMenu firstMenu;

	private final JMenuItem welcome;
	private final JMenuItem about;
	private final JMenuItem profil;
	private final JMenuItem signIn;
	private final JMenuItem signUp;
	private final JMenuItem logOut;
	private final JMenuItem exit;

	private final JMenu secondMenu;

	private final JMenuItem profiles;

	public TwitupMainView() {
		super("Le twitter du McDonald's du patelin");

		navigationObservers = new ArrayList<>();

		setIconImage(new ImageIcon("src/resources/images/mcdo.png").getImage());

		bar = new JMenuBar();

		firstMenu = new JMenu("Menu MAXI BestOf");

		welcome = new JMenuItem("BigMac"); // Welcome
		welcome.setIcon(new ImageIcon("src/resources/images/bigmac.png"));
		welcome.addActionListener((e) -> notifyWelcomeViewObservers());

		about = new JMenuItem("CauetBurger"); // About
		about.setIcon(new ImageIcon("src/resources/images/cauet.png"));
		about.addActionListener((e) -> notifyAboutViewObservers());

		profil = new JMenuItem("Tenders"); // Profil
		profil.setIcon(new ImageIcon("src/resources/images/tenders.png"));
		profil.addActionListener((e) -> notifyProfilObservers());
		profil.setVisible(false);

		signIn = new JMenuItem("CBO"); // Connexion
		signIn.setIcon(new ImageIcon("src/resources/images/cbo.png"));
		signIn.addActionListener((e) -> notifySignInObservers());

		signUp = new JMenuItem("Whooper"); // Inscription
		signUp.setIcon(new ImageIcon("src/resources/images/whooper.png"));
		signUp.addActionListener((e) -> notifySignUpObservers());

		logOut = new JMenuItem("McChicken"); // Déconnexion
		logOut.setIcon(new ImageIcon("src/resources/images/mcchicken.png"));
		logOut.addActionListener((e) -> notifyLogOutObservers());
		logOut.setVisible(false);

		exit = new JMenuItem("ChickenDips"); // Quitter
		exit.setIcon(new ImageIcon("src/resources/images/dips.png"));
		exit.addActionListener((e) -> notifyExitObservers());

		firstMenu.add(welcome);
		firstMenu.add(about);
		firstMenu.add(profil);
		firstMenu.add(signIn);
		firstMenu.add(signUp);
		firstMenu.add(logOut);
		firstMenu.add(exit);

		bar.add(firstMenu);

		secondMenu = new JMenu("Menu BestOf");

		profiles = new JMenuItem("Bucket");
		profiles.setIcon(new ImageIcon("src/resources/images/bucket.png"));
		profiles.addActionListener((e) -> notifyProfilesViewObservers());

		secondMenu.add(profiles);

		bar.add(secondMenu);

		setJMenuBar(bar);

		setSize(950, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showGUI() {
		setVisible(true);
	}

	public String askDirectory() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("PRend dosser");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = chooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			return chooser.getSelectedFile().toString();
		} else {
			notifyExitObservers();
			return null;
		}
	}

	private void notifyWelcomeViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	private void notifyAboutViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadAboutView);
	}

	private void notifySignInObservers() {
		navigationObservers.forEach(INavigationObserver::loadSignInView);
	}

	private void notifyLogOutObservers() {
		navigationObservers.forEach(INavigationObserver::loadDisconnectView);
	}

	private void notifySignUpObservers() {
		navigationObservers.forEach(INavigationObserver::loadSignUpView);
	}

	private void notifyProfilObservers() {
		navigationObservers.forEach(INavigationObserver::loadProfilView);
	}

	private void notifyExitObservers() {
		navigationObservers.forEach(INavigationObserver::exit);
	}

	private void notifyProfilesViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadProfilesView);
	}

	@Override
	public void notifyIsConnected() {
		profil.setVisible(true);
		signIn.setVisible(false);
		signUp.setVisible(false);
		logOut.setVisible(true);
	}

	@Override
	public void notifyIsDisconnected() {
		profil.setVisible(false);
		signIn.setVisible(true);
		signUp.setVisible(true);
		logOut.setVisible(false);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}
}
