package com.iup.tp.twitup.model;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitModel {

	private Twit twit;

	public TwitModel(Twit twit) {
		this.twit = twit;
	}

	public Twit getTwit() {
		return twit;
	}

	public void setTwit(Twit twit) {
		this.twit = twit;
	}
}
