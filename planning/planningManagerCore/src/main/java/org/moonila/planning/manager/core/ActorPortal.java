package org.moonila.planning.manager.core;

public class ActorPortal {
	private String login;
	private String password;

	public ActorPortal() {
		login = AppConfigPropertiesService.getInstance().getProperties()
				.get(AppConfigPropertiesEnum.LOGIN.toString());
		password = AppConfigPropertiesService.getInstance().getProperties()
				.get(AppConfigPropertiesEnum.PWD.toString());
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
