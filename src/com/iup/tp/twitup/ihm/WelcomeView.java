package com.iup.tp.twitup.ihm;

import javax.swing.*;

public class WelcomeView extends ViewBase {

	public WelcomeView() {
		super();

		ImageIcon frenchFries = new ImageIcon("src/resources/images/frites.png");
		JLabel text = new JLabel("Bienvenue !", frenchFries, JLabel.CENTER);

		add(text);
	}
}
