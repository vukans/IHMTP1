package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.SignUpController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase {

	private List<SignUpController> signUpControllers;

	private JTextField username;
	private JPasswordField password;

	public SignUpView() {
		super();

		signUpControllers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel usernameText = new JLabel("Username");
		username = new JTextField(50);

		JLabel passwordText = new JLabel("Mot de passe");
		password = new JPasswordField(50);

		JButton create = new JButton("Créer un compte");
		create.addActionListener((e) -> doRegisterUser(username.getText(), String.valueOf(password.getPassword())));

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

		add(create, new GridBagConstraints(0, 40, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}

	private void doRegisterUser(String username, String password) {
		signUpControllers.forEach(res -> res.notifyRegisterUser(username, password));
	}

	public void addController(SignUpController signUpController) {
		signUpControllers.add(signUpController);
	}
}
