package com.iup.tp.twitup.view;

import com.iup.tp.twitup.model.UserModel;

import javax.swing.*;
import java.awt.*;

public class MyProfilView extends ViewBase {

	public MyProfilView(UserModel connectedUserModel) {
		super();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Profille");
		title.setFont(title.getFont().deriveFont(48.0F));
		add(title, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		ImageIcon avatarImage = new ImageIcon(new ImageIcon(connectedUserModel.getUser().getAvatarPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel avatar = new JLabel("", avatarImage, SwingConstants.CENTER);
		add(avatar, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		JLabel userTagAndUserName = new JLabel("@" + connectedUserModel.getUser().getUserTag() + " - " + connectedUserModel.getUser().getName(), SwingConstants.CENTER);
		userTagAndUserName.setFont(title.getFont().deriveFont(18.0F));
		add(userTagAndUserName, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));
	}
}
