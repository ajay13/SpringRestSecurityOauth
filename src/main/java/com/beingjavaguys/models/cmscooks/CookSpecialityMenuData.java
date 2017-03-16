package com.beingjavaguys.models.cmscooks;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuPriceData;

@Entity
@Table(name = "cook_speciality_menu")
public class CookSpecialityMenuData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "cms_speciality", nullable = false)
	private CMSCooksSpecialityData cmsCooksSpecialityData;

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "cms_cook", nullable = false)
	private CMSCooksData cmsCooksData;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "cook_speciality_menu_mapping", joinColumns = { @JoinColumn(name = "speciality_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	private List<CMSMenuData> cmsMenuDataList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CMSCooksSpecialityData getCmsCooksSpecialityData() {
		return cmsCooksSpecialityData;
	}

	public void setCmsCooksSpecialityData(
			CMSCooksSpecialityData cmsCooksSpecialityData) {
		this.cmsCooksSpecialityData = cmsCooksSpecialityData;
	}

	public CMSCooksData getCmsCooksData() {
		return cmsCooksData;
	}

	public void setCmsCooksData(CMSCooksData cmsCooksData) {
		this.cmsCooksData = cmsCooksData;
	}

	public List<CMSMenuData> getCmsMenuDataList() {
		return cmsMenuDataList;
	}

	public void setCmsMenuDataList(List<CMSMenuData> cmsMenuDataList) {
		this.cmsMenuDataList = cmsMenuDataList;
	}

}
