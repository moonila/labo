package org.moonila.planning.manager.core;

public class OtherBean extends ActorPortal {

	private String machin;

	public OtherBean() {
//		super();

	}

	public OtherBean(String machin) {
		this.machin = machin;
	}

	public String getMachin() {
		return machin;
	}

	public void setMachin(String machin) {
		this.machin = machin;
	}

}
