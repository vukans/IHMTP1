package com.iup.tp.twitup.view;

import com.iup.tp.twitup.component.ProfilComponent;
import com.iup.tp.twitup.model.UserModel;
import com.iup.tp.twitup.model.UsersModel;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.interfaces.follows.IFollowActionObserver;
import com.iup.tp.twitup.interfaces.follows.IFollowObserver;
import com.iup.tp.twitup.interfaces.profiles.IProfilActionObserver;
import com.iup.tp.twitup.interfaces.profiles.IProfilsObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilesView extends ViewBase implements IProfilActionObserver, IFollowActionObserver {

	private final List<IProfilsObserver> profilsObservers;
	private final List<IFollowObserver> followObservers;

	private final JTextField searchField;
	private final JPanel profilsContainer;

	public ProfilesView() {
		super();

		profilsObservers = new ArrayList<>();
		followObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JPanel searchContainer = new JPanel();
		searchContainer.setLayout(new GridBagLayout());
		add(searchContainer, new GridBagConstraints(0, 0, 1, 1, 1, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		searchField = new JTextField(20);
		searchContainer.add(searchField, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		JButton searchOne = new JButton("va chercher médor");
		searchOne.addActionListener(e -> doSearchOne(searchField.getText()));
		searchContainer.add(searchOne, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		JButton searchAll = new JButton("Attrapez les tous ! CBO !");
		searchAll.addActionListener(e -> doSearchAll());
		searchContainer.add(searchAll, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		profilsContainer = new JPanel();
		profilsContainer.setLayout(new GridBagLayout());

		JScrollPane scroller = new JScrollPane(profilsContainer);
		scroller.getVerticalScrollBar().setUnitIncrement(30);
		add(scroller, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));
	}

	private void doSearchOne(String tag) {
		profilsObservers.forEach(res -> res.notifyDoSearchOne(tag));
	}

	private void doSearchAll() {
		profilsObservers.forEach(IProfilsObserver::notifyDoSearchAll);
	}

	private void refresh() {
		revalidate();
		repaint();
	}

	@Override
	public void notifyGotOneProfil(UserModel connectedUserModel, UserModel profilSeeked) {
		profilsContainer.removeAll();
		ProfilComponent profilComponent = new ProfilComponent(connectedUserModel, profilSeeked);
		followObservers.forEach(profilComponent::addFollowObserver);
		profilsContainer.add(profilComponent, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0),
				0, 0));
		refresh();
	}

	@Override
	public void notifyGotAllProfils(UserModel connectedUserModel, UsersModel profilsSeeked) {
		int x = 0;
		profilsContainer.removeAll();
		for (User user : profilsSeeked.getProfiles()) {
			UserModel userModel = new UserModel(user);
			ProfilComponent profilComponent = new ProfilComponent(connectedUserModel, userModel);
			followObservers.forEach(profilComponent::addFollowObserver);
			profilsContainer.add(profilComponent, new GridBagConstraints(0, x++, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));
		}
		refresh();
	}

	@Override
	public void notifySubscribed() {
		doSearchAll();
	}

	@Override
	public void notifyUnsubcribed() {
		doSearchAll();
	}

	public void addProfilesObserver(IProfilsObserver profilsObserver) {
		profilsObservers.add(profilsObserver);
	}

	public void removeProfilesObserver(IProfilsObserver profilsObserver) {
		profilsObservers.remove(profilsObserver);
	}

	public void addFollowObserver(IFollowObserver followObserver) {
		followObservers.add(followObserver);
	}

	public void removeFollowObserver(IFollowObserver followObserver) {
		followObservers.remove(followObserver);
	}
}
