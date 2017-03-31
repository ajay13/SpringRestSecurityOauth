package com.beingjavaguys.bean.cmsmenu;

import java.util.List;

public class CMSMenuBean {

	private int id;

	private String itemName;

	private String description;

	private int cooksId;

	private int menuCatagoryId;

	private String menuImagePath;

	private byte[] image;

	private int unit;

	private List<CMSMenuPriceBean> cmsMenuPriceBeanList;

	private String imageURL;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCooksId() {
		return cooksId;
	}

	public void setCooksId(int cooksId) {
		this.cooksId = cooksId;
	}

	public int getMenuCatagoryId() {
		return menuCatagoryId;
	}

	public void setMenuCatagoryId(int menuCatagoryId) {
		this.menuCatagoryId = menuCatagoryId;
	}

	public String getMenuImagePath() {
		return menuImagePath;
	}

	public void setMenuImagePath(String menuImagePath) {
		this.menuImagePath = menuImagePath;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CMSMenuPriceBean> getCmsMenuPriceBeanList() {
		return cmsMenuPriceBeanList;
	}

	public void setCmsMenuPriceBeanList(
			List<CMSMenuPriceBean> cmsMenuPriceBeanList) {
		this.cmsMenuPriceBeanList = cmsMenuPriceBeanList;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

}
