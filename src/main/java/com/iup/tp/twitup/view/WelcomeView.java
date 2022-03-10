package com.iup.tp.twitup.view;

import javax.swing.*;
import java.awt.*;

public class WelcomeView extends ViewBase {

	public WelcomeView() {
		super();

		setLayout(new GridBagLayout());

		ImageIcon frenchFries = new ImageIcon(ClassLoader.getSystemResource("images/frites.png"));
		JLabel text = new JLabel("Bien voeux nues jeune CBO !", frenchFries, SwingConstants.CENTER);

		add(text, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));
	}
}
