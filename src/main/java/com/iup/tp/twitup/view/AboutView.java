package com.iup.tp.twitup.view;

import javax.swing.*;
import java.awt.*;

public class AboutView extends ViewBase {

	public AboutView() {
		super();

		setLayout(new GridBagLayout());

		ImageIcon noopy = new ImageIcon(ClassLoader.getSystemResource("images/noopy.png"));
		JLabel text = new JLabel("IL S'APPELLE NOOPY !", noopy, SwingConstants.CENTER);

		add(text, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));
	}
}
