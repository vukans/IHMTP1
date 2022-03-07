package com.iup.tp.twitup.observer.profiles;

public interface IProfilesObserver {

	void notifyGetProfile(String tague);

	void notifyGetProfiles();

	void addGetProfilesObserver(IGetProfilesObserver getProfilesObserver);
}
