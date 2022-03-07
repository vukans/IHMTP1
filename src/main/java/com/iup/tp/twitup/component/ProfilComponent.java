package com.iup.tp.twitup.component;

import com.iup.tp.twitup.datamodel.ConnectedUserModel;
import com.iup.tp.twitup.datamodel.ProfilModel;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class ProfilComponent extends JPanel {

	public ProfilComponent(ConnectedUserModel profilConnected, ProfilModel profilSeeked) {
		super();

		setLayout(new GridBagLayout());

		User userConnected = profilConnected.getUser();
		User userSeeked = profilSeeked.getUser();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(userSeeked.getAvatarPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel content = new JLabel("@" + userSeeked.getUserTag() + " " + userSeeked.getName() + " followers : " + userSeeked.getFollows().size(), imageIcon, JLabel.CENTER);

		if (profilConnected.getUser() != null) {
			if (userConnected.getFollows().stream().filter(res -> res.equals(userSeeked.getUserTag())).findFirst().orElse(null) == null) {
				JButton sub = new JButton("sub");
				add(sub, new GridBagConstraints(1, 0, 1, 1, 1, 1,
						GridBagConstraints.CENTER,
						GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5),
						0, 0));
			} else {
				JButton unsub = new JButton("unsub");
				add(unsub, new GridBagConstraints(2, 0, 1, 1, 1, 1,
						GridBagConstraints.CENTER,
						GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5),
						0, 0));
			}
		}

		setBorder(BorderFactory.createLineBorder(Color.red));

		add(content, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}
}
