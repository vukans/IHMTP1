package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class ProfilView extends ViewBase {

	public ProfilView(User user) {
		super();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Profille");
		title.setFont(title.getFont().deriveFont(48.0F));

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(user.getAvatarPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel avatar = new JLabel("", imageIcon, JLabel.CENTER);

		JLabel nameText = new JLabel("@" + user.getUserTag() + " - " + user.getName(), JLabel.CENTER);
		nameText.setFont(title.getFont().deriveFont(18.0F));

		add(title, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(avatar, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(nameText, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));
	}
}
