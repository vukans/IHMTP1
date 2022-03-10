package com.iup.tp.twitup.component;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.interfaces.follows.IFollowObserver;
import com.iup.tp.twitup.model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilComponent extends JPanel {

	private final List<IFollowObserver> followObservers;

	public ProfilComponent(UserModel profilConnected, UserModel profilSeeked) {
		super();

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.red));

		followObservers = new ArrayList<>();

		User userConnected = profilConnected.getUser();
		User userSeeked = profilSeeked.getUser();

		ImageIcon profilImage = new ImageIcon(new ImageIcon(userSeeked.getAvatarPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel profilLabel = new JLabel("@" + userSeeked.getUserTag() + " " + userSeeked.getName() + " ah, beau nements ! : " + userSeeked.getFollows().size(), profilImage, SwingConstants.CENTER);
		add(profilLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		if (profilConnected.getUser() != null) {
			JButton sub = new JButton("sub");
			sub.addActionListener(e -> doSubscribe(profilConnected, profilSeeked));

			JButton unsub = new JButton("unsub");
			unsub.addActionListener(e -> doUnsubscribe(profilConnected, profilSeeked));

			if (userConnected.getFollows().stream().filter(res -> res.equals(userSeeked.getUserTag())).findFirst().orElse(null) == null) {
				add(sub, new GridBagConstraints(1, 0, 1, 1, 1, 1,
						GridBagConstraints.CENTER,
						GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5),
						0, 0));
			} else {
				add(unsub, new GridBagConstraints(2, 0, 1, 1, 1, 1,
						GridBagConstraints.CENTER,
						GridBagConstraints.NONE,
						new Insets(5, 5, 5, 5),
						0, 0));
			}
		}
	}

	private void doSubscribe(UserModel profilConnected, UserModel profilSeeked) {
		followObservers.forEach(res -> res.notifyDoSubscribe(profilConnected, profilSeeked));
	}

	private void doUnsubscribe(UserModel profilConnected, UserModel profilSeeked) {
		followObservers.forEach(res -> res.notifyDoUnsubscribe(profilConnected, profilSeeked));
	}

	public void addFollowObserver(IFollowObserver followController) {
		followObservers.add(followController);
	}

	public void removeFollowObserver(IFollowObserver followObserver) {
		followObservers.remove(followObserver);
	}
}
