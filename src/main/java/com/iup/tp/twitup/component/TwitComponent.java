package com.iup.tp.twitup.component;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.model.TwitModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TwitComponent extends JPanel {

	public TwitComponent(TwitModel twitModel) {
		super();

		setLayout(new GridBagLayout());
		setSize(500, 200);
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		Twit twit = twitModel.getTwit();

		ImageIcon twitImage = new ImageIcon(new ImageIcon(twit.getTwiter().getAvatarPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel twitLabel = new JLabel(twit.getText(), twitImage, SwingConstants.CENTER);
		add(twitLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));

		JLabel twitLabel2 = new JLabel("Par @" + twit.getTwiter().getUserTag() + " le " + new Date(twit.getEmissionDate()), SwingConstants.CENTER);
		add(twitLabel2, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5),
				0, 0));
	}
}
