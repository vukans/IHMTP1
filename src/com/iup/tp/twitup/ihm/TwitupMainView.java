package com.iup.tp.twitup.ihm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame {

	public TwitupMainView(String titre) {
		super(titre);
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setSize(600, 400);

		ImageIcon imageIcon = new ImageIcon("src/resources/images/logoIUP_20.jpg");
		JLabel jLabel = new JLabel("j'la belle", imageIcon, JLabel.CENTER);

		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenu = new JMenu("Best Of");
		jMenuBar.add(jMenu);

		JMenuItem jMenuItem1 = new JMenuItem("Big Mac");
		jMenuItem1.setIcon(new ImageIcon("src/resources/images/big mac.png"));
		jMenuItem1.addActionListener(this::aboutListener);
		jMenu.add(jMenuItem1);

		JMenuItem jMenuItem2 = new JMenuItem("Mc Chicken");
		jMenuItem2.setIcon(new ImageIcon("src/resources/images/mc chicken.png"));
		jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		jMenuItem2.addActionListener(this::exitListener);
		jMenu.add(jMenuItem2);

		JMenuItem jMenuItem3 = new JMenuItem("Chicken Dips");
		jMenuItem3.setIcon(new ImageIcon("src/resources/images/dips.png"));
		jMenuItem3.addActionListener(this::fileListener);
		jMenu.add(jMenuItem3);

		add(jMenuBar);
	}

	public void showGUI() {
		setVisible(true);
		pack();
	}

	public void fileListener(ActionEvent actionEvent) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
		}
	}

	public void aboutListener(ActionEvent actionEvent) {
		JFrame jFrame = new JFrame("A propos");
		jFrame.setSize(50, 50);
		jFrame.setLocationRelativeTo(null);
		ImageIcon imageIcon = new ImageIcon("src/resources/images/noopy.png");
		JLabel jLabel = new JLabel("IL S'APPELLE NOOPY !", imageIcon, JLabel.CENTER);
		jFrame.add(jLabel);
		jFrame.setVisible(true);
		jFrame.pack();
	}

	public void exitListener(ActionEvent actionEvent) {
		System.exit(0);
	}
}
