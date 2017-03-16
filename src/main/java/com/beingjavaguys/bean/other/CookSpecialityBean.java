package com.beingjavaguys.bean.other;

import java.util.List;

public class CookSpecialityBean {
	int cookId;
	String speciality;
	List<Integer> menuIds;

	public int getCookId() {
		return cookId;
	}

	public void setCookId(int cookId) {
		this.cookId = cookId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}

}
