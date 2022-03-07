package com.iup.tp.twitup.component;

import javax.swing.*;
import java.awt.*;

public class ProfilComponent extends JPanel {

	public ProfilComponent(String tague, String name, int follows, String avatar) {
		super();

		ImageIcon imageIcon = new ImageIcon(new ImageIcon(avatar).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel oui = new JLabel("@" + tague + " " + name + " followers : " + follows, imageIcon, JLabel.CENTER);

		add(oui);
	}
}
