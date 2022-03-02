package com.iup.tp.twitup.view;

import com.iup.tp.twitup.core.LoginObserver;
import com.iup.tp.twitup.core.Twitup;

import javax.swing.*;

/**
 * Classe de la vue principale de l'application.
 */
public class MainView extends JFrame {

	private String folder = "";

	private ViewBase currentView;

	public MainView() {
		super("Le twitter du McDonald's du patelin");

		setIconImage(new ImageIcon("src/resources/images/mcdo.png").getImage());

		JMenuBar bar = new JMenuBar();

		JMenu menu = new JMenu("Menu best of :");

		JMenuItem dips = new JMenuItem("Dips");
		dips.setIcon(new ImageIcon("src/resources/images/dips.png"));
		dips.addActionListener((e) -> loadView(new WelcomeView(folder)));

		JMenuItem bigMac = new JMenuItem("Big Mac");
		bigMac.setIcon(new ImageIcon("src/resources/images/big mac.png"));
		bigMac.addActionListener((e) -> loadView(new AboutView()));

		JMenuItem cbo = new JMenuItem("CBO");
		cbo.setIcon(new ImageIcon("src/resources/images/cbo.png"));
		cbo.addActionListener((e) -> loadView(new LoginView(new LoginObserver())));

		JMenuItem mcChicken = new JMenuItem("Mc Chicken");
		mcChicken.setIcon(new ImageIcon("src/resources/images/mc chicken.png"));
		mcChicken.addActionListener((e) -> exitApplication());

		menu.add(dips);
		menu.add(bigMac);
		menu.add(cbo);
		menu.add(mcChicken);

		bar.add(menu);

		setJMenuBar(bar);

		currentView = new WelcomeView(folder);
		getContentPane().add(currentView);

		setSize(606, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showGUI() {
		setVisible(true);
	}

	private void loadView(ViewBase viewBase) {
		getContentPane().removeAll();
		currentView = viewBase;
		getContentPane().add(currentView);
		revalidate();
		repaint();
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
		folder = filePath;
		return filePath;
	}

	private void exitApplication() {
		System.exit(0);
	}

	public ViewBase getCurrentView() {
		return currentView;
	}
}
