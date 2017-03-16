package com.beingjavaguys.models.cmsmenu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.beingjavaguys.models.cmscooks.CMSCooksData;

@Entity
@Table(name = "cms_menu")
public class CMSMenuData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "description")
	private String description;

	@Column(name = "menu_image_path")
	private String menuImagePath;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cook_id", nullable = false)
	private CMSCooksData cmsCooksData;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_catagory_id", nullable = false)
	private CMSMenuCatagoryData cmsMenuCatagoryData;

	@Column(name = "unit")
	private int unit;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "cms_menu_price_mapping", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = { @JoinColumn(name = "unit_id") })
	private List<CMSMenuPriceData> cmsMenuPriceDataList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CMSCooksData getCmsCooksData() {
		return cmsCooksData;
	}

	public void setCmsCooksData(CMSCooksData cmsCooksData) {
		this.cmsCooksData = cmsCooksData;
	}

	public CMSMenuCatagoryData getCmsMenuCatagoryData() {
		return cmsMenuCatagoryData;
	}

	public void setCmsMenuCatagoryData(CMSMenuCatagoryData cmsMenuCatagoryData) {
		this.cmsMenuCatagoryData = cmsMenuCatagoryData;
	}

	public String getMenuImagePath() {
		return menuImagePath;
	}

	public void setMenuImagePath(String menuImagePath) {
		this.menuImagePath = menuImagePath;
	}

	public List<CMSMenuPriceData> getCmsMenuPriceDataList() {
		return cmsMenuPriceDataList;
	}

	public void setCmsMenuPriceDataList(
			List<CMSMenuPriceData> cmsMenuPriceDataList) {
		this.cmsMenuPriceDataList = cmsMenuPriceDataList;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

}
