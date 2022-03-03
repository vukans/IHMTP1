package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.observer.session.ISessionObserver;
import com.iup.tp.twitup.observer.session.ISignedUpObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase implements ISignedUpObserver {

	private final List<ISessionObserver> sessionObservers;

	private final JTextField tague;
	private final JTextField usernem;
	private final JPasswordField depecheModePasse;
	private final JLabel infos;

	public SignUpView() {
		super();

		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Einscryptsion");
		title.setFont(title.getFont().deriveFont(48.0F));

		ImageIcon tagueImg = new ImageIcon("src/resources/images/tague.png");
		JLabel tagueText = new JLabel("Tague :", tagueImg, JLabel.CENTER);
		tagueText.setFont(title.getFont().deriveFont(14.0F));
		tague = new JTextField(20);

		ImageIcon usernemImg = new ImageIcon("src/resources/images/usernem.png");
		JLabel usernemText = new JLabel("User-nem :", usernemImg, JLabel.CENTER);
		usernemText.setFont(title.getFont().deriveFont(14.0F));
		usernem = new JTextField(20);

		ImageIcon depecheModePasseImg = new ImageIcon("src/resources/images/depechemodepasse.png");
		JLabel depecheModePasseText = new JLabel("Depeche Mode-passe :", depecheModePasseImg, JLabel.CENTER);
		depecheModePasseText.setFont(title.getFont().deriveFont(14.0F));
		depecheModePasse = new JPasswordField(20);

		JButton create = new JButton("S'inscrire dans le poulélé");
		create.addActionListener((e) -> doRegisterUser(tague.getText(), usernem.getText(), String.valueOf(depecheModePasse.getPassword())));

		JButton avatar = new JButton("Sélectionner un Bonhomme Bleu");

		infos = new JLabel();

		add(title, new GridBagConstraints(0, 0, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(25, 25, 25, 25),
				0, 0));

		add(tagueText, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(tague, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(usernemText, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(usernem, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(depecheModePasseText, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(depecheModePasse, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(avatar, new GridBagConstraints(0, 4, 1, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(create, new GridBagConstraints(0, 5, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		add(infos, new GridBagConstraints(0, 6, 2, 1, 0, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void doRegisterUser(String tag, String username, String password) {
		sessionObservers.forEach(res -> res.notifySignUp(tag, username, password));
	}

	@Override
	public void notifyUserCreated(String tag) {
		infos.setText("Le CBO @" + tag + " a été ajouté dans le poulélé, je répète, le CBO est dans le POULéLé !");
		infos.setIcon(new ImageIcon("src/resources/images/chicken.gif"));
	}

	@Override
	public void notifyUserAlreadyExists(String tag) {
		infos.setText("Le CBO @" + tag + " est déjà dans le POULéLé ! T'es cuits !");
		infos.setIcon(new ImageIcon("src/resources/images/roast.png"));
	}

	@Override
	public void notifyWrongInputs() {
		infos.setText("Les champs ne sont pas correctement remplis, ils sont tout crlus !");
		infos.setIcon(new ImageIcon("src/resources/images/raw.png"));
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}
}
