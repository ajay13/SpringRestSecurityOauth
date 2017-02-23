package com.beingjavaguys.models.cmscooks;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.beingjavaguys.models.cmsmenu.CMSMenuData;

@Entity
@Table(name = "cms_cooks")
public class CMSCooksData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "specility")
	private String specility;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mobileno")
	private String mobileno;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "cmsCooksData")
	private List<CMSMenuData> cmsMenuDataList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSpecility() {
		return specility;
	}

	public void setSpecility(String specility) {
		this.specility = specility;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CMSMenuData> getCmsMenuDataList() {
		return cmsMenuDataList;
	}

	public void setCmsMenuDataList(List<CMSMenuData> cmsMenuDataList) {
		this.cmsMenuDataList = cmsMenuDataList;
	}

}
