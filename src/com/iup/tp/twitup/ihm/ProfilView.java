package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class ProfilView extends ViewBase {

	public ProfilView(User user) {
		super();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Profill");
		title.setFont(title.getFont().deriveFont(48.0F));

		JLabel avatar = new JLabel();

		JLabel nameText = new JLabel("@" + user.getUserTag() + " - " + user.getName(), JLabel.CENTER);
		nameText.setFont(title.getFont().deriveFont(18.0F));

		add(title, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(nameText, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(15, 15, 15, 15),
				0, 0));
	}
}
