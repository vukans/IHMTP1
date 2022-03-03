package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.INavigationObserver;
import com.iup.tp.twitup.datamodel.ISignInController;
import com.iup.tp.twitup.datamodel.ISignedInObserver;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignInView extends ViewBase implements ISignedInObserver {

	private final List<INavigationObserver> navigationObservers;
	private final List<ISignInController> signInControllers;

	private final JTextField tag;
	private final JPasswordField password;
	private final JLabel info;

	public SignInView() {
		super();

		navigationObservers = new ArrayList<>();
		signInControllers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Coneksession");
		title.setFont(title.getFont().deriveFont(32.0F));

		ImageIcon tague = new ImageIcon("src/resources/images/tag.png");
		JLabel tagText = new JLabel("Tague", tague, JLabel.CENTER);
		tag = new JTextField(50);

		ImageIcon mode = new ImageIcon("src/resources/images/mode.png");
		JLabel passwordText = new JLabel("Depeche Mode passe", mode, JLabel.CENTER);
		password = new JPasswordField(50);

		JButton connect = new JButton("Se connecter");
		connect.addActionListener((e) -> doLogin(tag.getText(), String.valueOf(password.getPassword())));

		JButton cancel = new JButton("NON.");
		cancel.addActionListener((e) -> doCancel());

		add(title, new GridBagConstraints(0, 0, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tagText, new GridBagConstraints(0, 10, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tag, new GridBagConstraints(0, 20, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(passwordText, new GridBagConstraints(0, 30, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(password, new GridBagConstraints(0, 40, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(connect, new GridBagConstraints(0, 50, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(cancel, new GridBagConstraints(0, 60, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		info = new JLabel();

		add(info, new GridBagConstraints(0, 70, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}

	private void doLogin(String username, String password) {
		signInControllers.forEach(res -> res.notifyLogin(username, password));
	}

	private void doCancel() {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	public void addSIgnInController(ISignInController signInController) {
		signInControllers.add(signInController);
	}

	public void addNavigationObserver(INavigationObserver navigationObserver) {
		navigationObservers.add(navigationObserver);
	}

	@Override
	public void notifySuccess(User user) {
		navigationObservers.forEach(INavigationObserver::loadWelcomeView);
	}

	@Override
	public void notifyError(String tag) {
		info.setText("G pa trouvé le CBO " + tag + " dans le poulélé !");
		info.setIcon(new ImageIcon("src/resources/images/plant.png"));
	}

	@Override
	public void notifyWrongInputs() {
		info.setText("Les champs (du poulélé) ne sont pas correctement remplis, IL EST TOUT CRLU ! ;)");
		info.setIcon(new ImageIcon("src/resources/images/chickenWrongInputs.png"));
	}
}
