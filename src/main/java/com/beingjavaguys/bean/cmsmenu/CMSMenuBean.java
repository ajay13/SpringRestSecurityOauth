package com.beingjavaguys.bean.cmsmenu;

public class CMSMenuBean {

	private String itemName;

	private String price;

	private String description;

	private int cooksId;

	private int menuCatagoryId;

	private String menuImagePath;

	private byte[] image;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

}
