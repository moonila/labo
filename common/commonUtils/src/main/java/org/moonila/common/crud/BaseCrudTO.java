package org.moonila.common.crud;

import java.io.Serializable;

public class BaseCrudTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3720050637838364571L;
	protected String id;
	protected String name;
	protected String description;
	protected boolean selected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
	
}
