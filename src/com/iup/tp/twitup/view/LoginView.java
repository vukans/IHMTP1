package com.iup.tp.twitup.view;

import com.iup.tp.twitup.model.ILoginObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoginView extends ViewBase {

	private List<ILoginObserver> observers;

	private JTextField username;
	private JPasswordField password;

	public LoginView(ILoginObserver loginObserver) {
		super();

		observers = new ArrayList<>();
		observers.add(loginObserver);

		setLayout(new GridBagLayout());

		JLabel usernameText = new JLabel("Username");
		username = new JTextField(50);

		JLabel passwordText = new JLabel("Mot de passe");
		password = new JPasswordField(50);

		JButton connect = new JButton("Se connecter");
		connect.addActionListener((e) -> doLogin());

		JButton cancel = new JButton("NON.");
		cancel.addActionListener((e) -> doCancel());


		add(usernameText, new GridBagConstraints(0, 0, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(username, new GridBagConstraints(0, 10, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(passwordText, new GridBagConstraints(0, 20, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(password, new GridBagConstraints(0, 30, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(connect, new GridBagConstraints(0, 40, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(cancel, new GridBagConstraints(0, 50, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}

	private void doLogin() {
		observers.forEach(res -> res.notifyLoginAttempt(username.getText(), String.valueOf(password.getPassword())));
	}

	private void doCancel() {
		observers.forEach(ILoginObserver::notifyCancelAction);
	}
}
