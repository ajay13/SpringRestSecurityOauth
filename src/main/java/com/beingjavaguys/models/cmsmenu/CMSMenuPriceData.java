package com.beingjavaguys.models.cmsmenu;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cms_menu_price")
public class CMSMenuPriceData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;

	@OneToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_type")
	private CMSMenuUnitData cmsMenuUnitData;

	@Column(name = "price")
	private String price;

	@ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false)
	private CMSMenuData cmsMenuData;

	public CMSMenuData getCmsMenuData() {
		return cmsMenuData;
	}

	public void setCmsMenuData(CMSMenuData cmsMenuData) {
		this.cmsMenuData = cmsMenuData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CMSMenuUnitData getCmsMenuUnitData() {
		return cmsMenuUnitData;
	}

	public void setCmsMenuUnitData(CMSMenuUnitData cmsMenuUnitData) {
		this.cmsMenuUnitData = cmsMenuUnitData;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
