package com.iup.tp.twitup.view;

import javax.swing.*;

public class WelcomeView extends ViewBase {

	public WelcomeView(String folder) {
		super();

		ImageIcon frenchFries = new ImageIcon("src/resources/images/frites.png");
		JLabel text = new JLabel("Dossier de communication : " + folder, frenchFries, JLabel.CENTER);

		add(text);
	}
}
