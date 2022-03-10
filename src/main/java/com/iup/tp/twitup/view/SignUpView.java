package com.iup.tp.twitup.view;

import com.iup.tp.twitup.interfaces.session.ISessionObserver;
import com.iup.tp.twitup.interfaces.session.ISignedUpObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SignUpView extends ViewBase implements ISignedUpObserver {

	private final List<ISessionObserver> sessionObservers;

	private File avatarFile;

	private final JTextField tagField;
	private final JTextField userNameField;
	private final JPasswordField passwordField;
	private final JLabel infos;

	public SignUpView() {
		super();

		sessionObservers = new ArrayList<>();

		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Einscryptsion");
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

		ImageIcon userNameImage = new ImageIcon(ClassLoader.getSystemResource("images/usernem.png"));
		JLabel userNameLabel = new JLabel("User-nem :", userNameImage, SwingConstants.CENTER);
		userNameLabel.setFont(title.getFont().deriveFont(14.0F));
		add(userNameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		userNameField = new JTextField(20);
		add(userNameField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		ImageIcon passwordImage = new ImageIcon(ClassLoader.getSystemResource("images/depechemodepasse.png"));
		JLabel passwordLabel = new JLabel("Depeche Mode-passe :", passwordImage, SwingConstants.CENTER);
		passwordLabel.setFont(title.getFont().deriveFont(14.0F));
		add(passwordLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		passwordField = new JPasswordField(20);
		add(passwordField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		JButton avatarChooser = new JButton("Sélectionner un Bonhomme Bleu");
		avatarChooser.addActionListener(e -> openFileChooser());
		add(avatarChooser, new GridBagConstraints(0, 4, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		JButton signUp = new JButton("S'inscrire dans le poulélé");
		signUp.addActionListener(e -> doSignUp(tagField.getText(), userNameField.getText(), String.valueOf(passwordField.getPassword()), avatarFile));
		add(signUp, new GridBagConstraints(0, 5, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		infos = new JLabel();
		add(infos, new GridBagConstraints(0, 6, 2, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(15, 15, 15, 15),
				0, 0));
	}

	private void openFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("PRend avatar");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			avatarFile = fileChooser.getSelectedFile();
		}
	}

	private void doSignUp(String tag, String userName, String password, File avatar) {
		sessionObservers.forEach(res -> res.notifyDoSignUp(tag, userName, password, avatar));
	}

	@Override
	public void notifySignedUp(String tag) {
		infos.setText("Le CBO @" + tag + " a été ajouté dans le poulélé, je répète, le CBO est dans le POULéLé !");
		infos.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/chicken.gif")));
	}

	@Override
	public void notifyUserAlreadyExists(String tag) {
		infos.setText("Le CBO @" + tag + " est déjà dans le POULéLé ! T'es cuits !");
		infos.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/roast.png")));
	}

	@Override
	public void notifyWrongInputs() {
		infos.setText("Les champs ne sont pas correctement remplis, ils sont tout crlus !");
		infos.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/raw.png")));
	}

	public void addSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.add(sessionObserver);
	}

	public void removeSessionObserver(ISessionObserver sessionObserver) {
		sessionObservers.remove(sessionObserver);
	}
}
