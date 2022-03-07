package com.iup.tp.twitup.observer.profiles;

public interface IGetProfilesObserver {

	void notifyGotProfiles(String tague, String name, int follows, String avatar);
}
