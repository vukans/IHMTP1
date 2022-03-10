package com.iup.tp.twitup.model;

import com.iup.tp.twitup.datamodel.Twit;

import java.util.ArrayList;
import java.util.List;

public class TwitsModel {

	private List<Twit> twits;

	public TwitsModel() {
		this.twits = new ArrayList<>();
	}

	public List<Twit> getTwits() {
		return twits;
	}

	public void setTwits(List<Twit> twits) {
		this.twits = twits;
	}
}
