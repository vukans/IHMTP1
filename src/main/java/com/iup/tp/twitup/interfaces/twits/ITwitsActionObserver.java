package com.iup.tp.twitup.interfaces.twits;

import com.iup.tp.twitup.model.TwitsModel;

public interface ITwitsActionObserver {

	void notifyTextTooLong();

	void notifyEmptyTwit();

	void notifyGotTwits(TwitsModel twitsModel);
}
