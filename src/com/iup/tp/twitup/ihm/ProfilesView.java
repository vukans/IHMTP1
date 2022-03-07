package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.component.ProfilComponent;
import com.iup.tp.twitup.datamodel.ConnectedUserModel;
import com.iup.tp.twitup.datamodel.ProfilesModel;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.datamodel.ProfilModel;
import com.iup.tp.twitup.observer.profiles.IGetProfilesObserver;
import com.iup.tp.twitup.observer.profiles.IProfilesObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilesView extends ViewBase implements IGetProfilesObserver {

	private final List<IProfilesObserver> profilesObservers;

	private final JTextField champs;
	private final JPanel profiltruc;

	public ProfilesView() {
		super();

		profilesObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JPanel recherche = new JPanel();
		recherche.setLayout(new GridBagLayout());

		champs = new JTextField(20);
		recherche.add(champs, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		JButton click = new JButton("va chercher médor");
		click.addActionListener((e) -> doGetProfil(champs.getText()));
		recherche.add(click, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		JButton all = new JButton("Attrapez les tous ! CBO !");
		all.addActionListener((e) -> doGetProfiles());
		recherche.add(all, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		add(recherche, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		profiltruc = new JPanel();
		profiltruc.setLayout(new GridBagLayout());

		JScrollPane scroller = new JScrollPane(profiltruc);
		scroller.getVerticalScrollBar().setUnitIncrement(30);

		add(scroller, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));
	}

	public void doGetProfiles() {
		profilesObservers.forEach(IProfilesObserver::notifyGetProfiles);
	}

	public void doGetProfil(String tague) {
		profilesObservers.forEach(res -> res.notifyGetProfile(tague));
	}

	@Override
	public void notifyGetProfile(ConnectedUserModel connectedUserModel, ProfilModel userModel) {
		profiltruc.removeAll();
		ProfilComponent profilComponent = new ProfilComponent(connectedUserModel, userModel);
		profiltruc.add(profilComponent, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0),
				0, 0));
		revalidate();
		repaint();
	}

	@Override
	public void notifyGotProfiles(ConnectedUserModel connectedUserModel, ProfilesModel profilesModel) {
		int x = 0;
		profiltruc.removeAll();
		for (User user : profilesModel.getProfiles()) {
			ProfilModel userModel = new ProfilModel(user);
			ProfilComponent profilComponent = new ProfilComponent(connectedUserModel, userModel);
			profiltruc.add(profilComponent, new GridBagConstraints(0, x++, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));
		}
		revalidate();
		repaint();
	}

	public void addProfilesObserver(IProfilesObserver profilesObserver) {
		profilesObservers.add(profilesObserver);
	}
}
