package com.iup.tp.twitup.view;

import com.iup.tp.twitup.datamodel.INavigationObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupView extends JFrame {

	private List<INavigationObserver> navigationObservers;

	public TwitupView() {
		super("Le twitter du McDonald's du patelin");

		navigationObservers = new ArrayList<>();

		setIconImage(new ImageIcon("src/resources/images/mcdo.png").getImage());

		JMenuBar bar = new JMenuBar();

		JMenu menu = new JMenu("Menu best of :");

		JMenuItem dips = new JMenuItem("Dips");
		dips.setIcon(new ImageIcon("src/resources/images/dips.png"));
		dips.addActionListener((e) -> notifyWelcomeViewObservers());

		JMenuItem bigMac = new JMenuItem("Big Mac");
		bigMac.setIcon(new ImageIcon("src/resources/images/big mac.png"));
		bigMac.addActionListener((e) -> notifyAboutViewObservers());

		JMenuItem cbo = new JMenuItem("CBO");
		cbo.setIcon(new ImageIcon("src/resources/images/cbo.png"));
		cbo.addActionListener((e) -> notifySignInObservers());

		JMenuItem whooper = new JMenuItem("Whooper");
		whooper.setIcon(new ImageIcon("src/resources/images/whooper.png"));
		whooper.addActionListener((e) -> notifySignUpObservers());

		JMenuItem mcChicken = new JMenuItem("Mc Chicken");
		mcChicken.setIcon(new ImageIcon("src/resources/images/mc chicken.png"));
		mcChicken.addActionListener((e) -> exitApplication());

		menu.add(dips);
		menu.add(bigMac);
		menu.add(cbo);
		menu.add(whooper);
		menu.add(mcChicken);

		bar.add(menu);

		setJMenuBar(bar);

		setSize(606, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showGUI() {
		setVisible(true);
	}

	public String askDirectory() {
		String filePath = "";
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("PRend dosser");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = chooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			filePath = chooser.getSelectedFile().toString();
		} else {
			exitApplication();
		}
		return filePath;
	}

	private void notifyWelcomeViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	private void notifyAboutViewObservers() {
		navigationObservers.forEach(INavigationObserver::loadAboutView);
	}

	private void notifySignInObservers() {
		navigationObservers.forEach(INavigationObserver::loadLoginView);
	}

	private void notifySignUpObservers() {
		navigationObservers.forEach(INavigationObserver::loadSignUp);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	private void exitApplication() {
		System.exit(0);
	}
}
