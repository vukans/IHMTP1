package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.SignUpController;
import com.iup.tp.twitup.datamodel.ISignedUpObserver;
import com.iup.tp.twitup.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase implements ISignedUpObserver {

	private final List<SignUpController> signUpControllers;

	private final JTextField tag;
	private final JTextField username;
	private final JPasswordField password;
	private final JLabel info;

	public SignUpView() {
		super();

		signUpControllers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Einscryptsion");
		title.setFont(title.getFont().deriveFont(32.0F));

		ImageIcon tague = new ImageIcon("src/resources/images/tag.png");
		JLabel tagText = new JLabel("Tague", tague, JLabel.CENTER);
		tag = new JTextField(50);

		ImageIcon nem = new ImageIcon("src/resources/images/nem.png");
		JLabel usernameText = new JLabel("User nem", nem, JLabel.CENTER);
		username = new JTextField(50);

		ImageIcon mode = new ImageIcon("src/resources/images/mode.png");
		JLabel passwordText = new JLabel("Depeche Mode passe", mode, JLabel.CENTER);
		password = new JPasswordField(50);

		JButton create = new JButton("Injectaient dans le poulélé");
		create.addActionListener((e) -> doRegisterUser(tag.getText(), username.getText(), String.valueOf(password.getPassword())));

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

		add(usernameText, new GridBagConstraints(0, 30, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(username, new GridBagConstraints(0, 40, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(passwordText, new GridBagConstraints(0, 50, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(password, new GridBagConstraints(0, 60, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(create, new GridBagConstraints(0, 70, 1, 6, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		info = new JLabel();

		add(info, new GridBagConstraints(0, 80, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}

	private void doRegisterUser(String tag, String username, String password) {
		signUpControllers.forEach(res -> res.notifyRegisterUser(tag, username, password));
	}

	@Override
	public void notifyUserCreated(User user) {
		info.setText("Le CBO est dans le poulélé, je répète, le cbo est dans le POULéLé !");
		info.setIcon(new ImageIcon("src/resources/images/chickenCreated.png"));
	}

	@Override
	public void notifyUserAlreadyExists(User user) {
		info.setText("L'utilisateur " + user.getUserTag() + " est déjà dans le POUéLéLé ! IL é TOU CUIT !");
		info.setIcon(new ImageIcon("src/resources/images/chickenAlreadyExists.png"));
	}

	@Override
	public void notifyWrongInputs() {
		info.setText("Les champs (du poulélé) ne sont pas correctement remplis, IL EST TOUT CRLU ! ;)");
		info.setIcon(new ImageIcon("src/resources/images/chickenWrongInputs.png"));
	}

	public void addController(SignUpController signUpController) {
		signUpControllers.add(signUpController);
	}
}
