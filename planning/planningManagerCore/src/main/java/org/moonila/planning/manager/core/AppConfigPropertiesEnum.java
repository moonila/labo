package org.moonila.planning.manager.core;

public enum AppConfigPropertiesEnum {
	WS_URL("url"), Auth_Method("getMethod"), LOGIN("login"), PWD("mot.de.passe");

	private final String text;

	/**
	 * @param text
	 */
	private AppConfigPropertiesEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
