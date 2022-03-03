package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.SignInController;
import com.iup.tp.twitup.datamodel.ISignInController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignInView extends ViewBase {

	private List<ISignInController> signInControllers;

	private JTextField username;
	private JPasswordField password;

	public SignInView() {
		super();

		signInControllers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel usernameText = new JLabel("Username");
		username = new JTextField(50);

		JLabel passwordText = new JLabel("Mot de passe");
		password = new JPasswordField(50);

		JButton connect = new JButton("Se connecter");
		connect.addActionListener((e) -> doLogin(username.getText(), String.valueOf(password.getPassword())));

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

	private void doLogin(String username, String password) {
		signInControllers.forEach(res -> res.notifyLogin(username, password));
	}

	private void doCancel() {
		signInControllers.forEach(ISignInController::notifyCancel);
	}

	public void addController(SignInController signInController) {
		signInControllers.add(signInController);
	}
}
