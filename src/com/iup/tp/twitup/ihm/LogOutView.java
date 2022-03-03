package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.session.ISessionObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogOutView extends ViewBase {

	private final List<INavigationObserver> navigationObservers;
	private final List<ISessionObserver> sessionObservers;

	public LogOutView() {
		super();

		navigationObservers = new ArrayList<>();
		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Dé&Coneksession");
		title.setFont(title.getFont().deriveFont(48.0F));

		JButton logOut = new JButton("Se Dé&co");
		logOut.addActionListener((e) -> doLogOut());

		add(title, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(logOut, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void doLogOut() {
		sessionObservers.forEach(ISessionObserver::notifyLogOut);
		navigationObservers.forEach(INavigationObserver::loadSignInView);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}
}
