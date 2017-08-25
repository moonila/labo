package org.moonila.common.user.gui.common;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ChangeLocale implements Serializable {

	// la locale des pages

	/**
	 * 
	 */
	private static final long serialVersionUID = 2699171694496367974L;
	private String locale = "fr";

	public ChangeLocale() {
	}

	public String setFrenchLocale() {
		locale = "fr";
		return null;
	}

	public String setEnglishLocale() {
		locale = "en";
		return null;
	}

	public String getLocale() {
		return locale;
	}

}
