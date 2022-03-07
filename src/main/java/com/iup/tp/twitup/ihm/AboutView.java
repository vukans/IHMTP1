package com.iup.tp.twitup.ihm;

import javax.swing.*;

public class AboutView extends ViewBase {

	public AboutView() {
		super();

		ImageIcon noopy = new ImageIcon(ClassLoader.getSystemResource("images/images/noopy.png"));
		JLabel text = new JLabel("IL S'APPELLE NOOPY !", noopy, JLabel.CENTER);

		add(text);
	}
}
