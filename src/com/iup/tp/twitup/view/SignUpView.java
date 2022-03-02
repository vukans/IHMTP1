package com.iup.tp.twitup.view;

import com.iup.tp.twitup.model.IDatabaseObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase {

	private List<IDatabaseObserver> observers;

	private JTextField username;
	private JPasswordField password;

	public SignUpView() {
		super();

		observers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel usernameText = new JLabel("Username");
		username = new JTextField(50);

		JLabel passwordText = new JLabel("Mot de passe");
		password = new JPasswordField(50);

		JButton connect = new JButton("Créer un compte");
		connect.addActionListener((e) -> doCreate());

	}

	private void doCreate() {

	}

	public void addObserver(IDatabaseObserver observer) {
		observers.add(observer);
	}
}
