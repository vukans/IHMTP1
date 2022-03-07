package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.component.ProfilComponent;
import com.iup.tp.twitup.observer.profiles.IGetProfilesObserver;
import com.iup.tp.twitup.observer.profiles.IProfilesObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilesView extends ViewBase implements IGetProfilesObserver {

	private final List<IProfilesObserver> profilesObservers;

	public ProfilesView() {
		super();

		profilesObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JScrollPane scroller = new JScrollPane(this);

		JButton profiles = new JButton("Afficher profiles");
		profiles.addActionListener((e) -> doGetProfiles());

		add(profiles, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}

	private void doGetProfiles() {
		profilesObservers.forEach(IProfilesObserver::notifyGetProfiles);
	}

	@Override
	public void notifyGotProfiles(String tague, String name, int follows, String avatar) {
		add(new ProfilComponent(tague, name, follows, avatar));
	}

	public void addProfilesObserver(IProfilesObserver profilesObserver) {
		profilesObservers.add(profilesObserver);
	}
}
