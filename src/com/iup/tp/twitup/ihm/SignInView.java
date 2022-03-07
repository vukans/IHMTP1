package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.navigation.INavigationObserver;
import com.iup.tp.twitup.observer.session.ISessionObserver;
import com.iup.tp.twitup.observer.session.ISignedInObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignInView extends ViewBase implements ISignedInObserver {

	private final List<INavigationObserver> navigationObservers;
	private final List<ISessionObserver> sessionObservers;

	private final JTextField tague;
	private final JPasswordField depecheModePasse;
	private final JLabel infos;

	public SignInView() {
		super();

		navigationObservers = new ArrayList<>();
		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Coneksession");
		title.setFont(title.getFont().deriveFont(48.0F));

		ImageIcon tagueImg = new ImageIcon("src/resources/images/tague.png");
		JLabel tagueText = new JLabel("Tague :", tagueImg, JLabel.CENTER);
		tagueText.setFont(title.getFont().deriveFont(14.0F));
		tague = new JTextField(20);

		ImageIcon depecheModePasseImg = new ImageIcon("src/resources/images/depechemodepasse.png");
		JLabel depecheModePasseText = new JLabel("Depeche Mode-passe :", depecheModePasseImg, JLabel.CENTER);
		depecheModePasseText.setFont(title.getFont().deriveFont(14.0F));
		depecheModePasse = new JPasswordField(20);

		JButton signIn = new JButton("Se coneckthé");
		signIn.addActionListener((e) -> doLogin(tague.getText(), String.valueOf(depecheModePasse.getPassword())));

		JButton cancel = new JButton("NON.");
		cancel.addActionListener((e) -> doCancel());

		infos = new JLabel();

		add(title, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(tagueText, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tague, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(depecheModePasseText, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(depecheModePasse, new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(signIn, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(cancel, new GridBagConstraints(1, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(infos, new GridBagConstraints(0, 4, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void doLogin(String tag, String password) {
		sessionObservers.forEach(res -> res.notifySignIn(tag, password));
	}

	private void doCancel() {
		navigationObservers.forEach(INavigationObserver::loadSignUpView);
	}

	@Override
	public void notifySuccess() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	@Override
	public void notifyError(String tag) {
		infos.setText("G pa trouvé le CBO @" + tag + " dans le poulélé !");
		infos.setIcon(new ImageIcon("src/resources/images/plant.png"));
	}

	@Override
	public void notifyWrongInputs() {
		infos.setText("Les champs ne sont pas correctement remplis, ils sont tout crlus !");
		infos.setIcon(new ImageIcon("src/resources/images/raw.png"));
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}
}
