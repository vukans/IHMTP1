package com.iup.tp.twitup.view;

import com.iup.tp.twitup.interfaces.navigation.INavigationObserver;
import com.iup.tp.twitup.interfaces.session.ISessionObserver;
import com.iup.tp.twitup.interfaces.session.ISignedInObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignInView extends ViewBase implements ISignedInObserver {

	private final List<INavigationObserver> navigationObservers;
	private final List<ISessionObserver> sessionObservers;

	private final JTextField tagField;
	private final JPasswordField passwordField;
	private final JLabel infos;

	public SignInView() {
		super();

		navigationObservers = new ArrayList<>();
		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Coneksession");
		title.setFont(title.getFont().deriveFont(48.0F));
		add(title, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		ImageIcon tagImage = new ImageIcon(ClassLoader.getSystemResource("images/tague.png"));
		JLabel tagLabel = new JLabel("Tague :", tagImage, SwingConstants.CENTER);
		tagLabel.setFont(title.getFont().deriveFont(14.0F));
		add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		tagField = new JTextField(20);
		add(tagField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		ImageIcon passwordImage = new ImageIcon(ClassLoader.getSystemResource("images/depechemodepasse.png"));
		JLabel passwordLabel = new JLabel("Depeche Mode-passe :", passwordImage, SwingConstants.CENTER);
		passwordLabel.setFont(title.getFont().deriveFont(14.0F));
		add(passwordLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		passwordField = new JPasswordField(20);
		add(passwordField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		JButton signIn = new JButton("Se coneckthé");
		signIn.addActionListener(e -> doLogIn(tagField.getText(), String.valueOf(passwordField.getPassword())));
		add(signIn, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		JButton cancel = new JButton("NON.");
		cancel.addActionListener(e -> doCancel());
		add(cancel, new GridBagConstraints(1, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		infos = new JLabel();
		add(infos, new GridBagConstraints(0, 4, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void doLogIn(String tag, String password) {
		sessionObservers.forEach(res -> res.notifyDoLogIn(tag, password));
	}

	private void doCancel() {
		navigationObservers.forEach(INavigationObserver::doSetSignUpView);
	}

	@Override
	public void notifySignIn() {
		navigationObservers.forEach(INavigationObserver::doSetWelcomeView);
	}

	@Override
	public void notifyTagNotFound(String tag) {
		infos.setText("G pa trouvé le CBO @" + tag + " dans le poulélé !");
		infos.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/plant.png")));
	}

	@Override
	public void notifyWrongInputs() {
		infos.setText("Les champs ne sont pas correctement remplis, ils sont tout crlus !");
		infos.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/raw.png")));
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	public void removeNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.remove(navigationObserver);
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}

	public void removeSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.remove(sessionObserver);
	}
}
