package com.iup.tp.twitup.observer.profiles;

public interface IProfilesObserver {

	void notifyGetProfiles();

	void addGetProfilesObserver(IGetProfilesObserver getProfilesObserver);
}
