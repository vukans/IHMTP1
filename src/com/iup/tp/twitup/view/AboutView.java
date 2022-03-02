package com.iup.tp.twitup.view;

import javax.swing.*;

public class AboutView extends ViewBase {

	public AboutView() {
		super();

		ImageIcon noopy = new ImageIcon("src/resources/images/noopy.png");
		JLabel text = new JLabel("IL S'APPELLE NOOPY !", noopy, JLabel.CENTER);

		add(text);
	}
}
