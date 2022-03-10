package com.iup.tp.twitup.view;

import com.iup.tp.twitup.component.TwitComponent;
import com.iup.tp.twitup.exception.DirectoryException;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.model.UserModel;
import com.iup.tp.twitup.model.TwitModel;
import com.iup.tp.twitup.model.TwitsModel;
import com.iup.tp.twitup.interfaces.twits.ITwitsActionObserver;
import com.iup.tp.twitup.interfaces.twits.ITwitsObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TwitsView extends ViewBase implements ITwitsActionObserver {

	private final List<ITwitsObserver> twitsObservers;

	private final JTextField searchField;
	private final JPanel twitsContainer;

	public TwitsView(UserModel connectedUserModel) {
		super();

		setLayout(new GridBagLayout());

		twitsObservers = new ArrayList<>();

		JPanel searchContainer = new JPanel();
		searchContainer.setLayout(new GridBagLayout());
		add(searchContainer, new GridBagConstraints(0, 0, 1, 1, 1, 0,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		searchField = new JTextField(20);

		JButton sendTwit = new JButton("nouvo twit dylan kujo");
		sendTwit.addActionListener(e -> doSendTwit(connectedUserModel, searchField.getText()));

		if (connectedUserModel.getUser() != null) {
			searchContainer.add(searchField, new GridBagConstraints(0, 0, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0),
					0, 0));

			searchContainer.add(sendTwit, new GridBagConstraints(1, 0, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0),
					0, 0));
		}

		JButton refresh = new JButton("La base virale COVID-19 a été mise à jour");
		refresh.addActionListener(e -> doGetTwits());
		searchContainer.add(refresh, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));

		twitsContainer = new JPanel();
		twitsContainer.setLayout(new GridBagLayout());

		JScrollPane scroller = new JScrollPane(twitsContainer);
		scroller.getVerticalScrollBar().setUnitIncrement(30);
		add(scroller, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));
	}

	private void doSendTwit(UserModel connectedUser, String text) {
		twitsObservers.forEach(res -> {
			try {
				res.notifyDoSendTwit(connectedUser, text);
			} catch (DirectoryException e) {
				e.printStackTrace();
			}
		});
	}

	private void doGetTwits() {
		twitsObservers.forEach(ITwitsObserver::notifyDoGetTwits);
	}

	private void refresh() {
		revalidate();
		repaint();
	}

	@Override
	public void notifyTextTooLong() {
		twitsContainer.removeAll();
		JLabel textTooLong = new JLabel("Le nouvo twit dylan kujo dépaase 250 caractères !");
		twitsContainer.add(textTooLong, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0),
				0, 0));
		refresh();
	}

	@Override
	public void notifyEmptyTwit() {
		twitsContainer.removeAll();
		JLabel empty = new JLabel("Le nouvo twit dylan kujo est vide enfête...");
		twitsContainer.add(empty, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.CENTER,
				GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0),
				0, 0));
		refresh();
	}

	@Override
	public void notifyGotTwits(TwitsModel twitsModel) {
		int x = 0;
		twitsContainer.removeAll();
		for (Twit twit : twitsModel.getTwits()) {
			TwitModel twitModel = new TwitModel(twit);
			TwitComponent twitComponent = new TwitComponent(twitModel);
			twitsContainer.add(twitComponent, new GridBagConstraints(0, x++, 1, 1, 1, 1,
					GridBagConstraints.CENTER,
					GridBagConstraints.NONE,
					new Insets(0, 0, 0, 0),
					0, 0));
		}
		refresh();
	}

	public void addTwitsObserver(ITwitsObserver twitsObserver) {
		twitsObservers.add(twitsObserver);
	}

	public void removeTwitsObserver(ITwitsObserver twitsObserver) {
		twitsObservers.remove(twitsObserver);
	}
}
