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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.other.CastData;

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

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "availability")
	private int availability;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cast")
	private CastData castData;

	@OneToMany(mappedBy = "cmsCooksData")
	private List<CMSMenuData> cmsMenuDataList;

	@OneToMany(mappedBy = "cmsCooksData")
	private List<CookSpecialityMenuData> cookSpecialityMenuDataList ;

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public CastData getCastData() {
		return castData;
	}

	public void setCastData(CastData castData) {
		this.castData = castData;
	}

	public List<CookSpecialityMenuData> getCookSpecialityMenuDataList() {
		return cookSpecialityMenuDataList;
	}

	public void setCookSpecialityMenuDataList(
			List<CookSpecialityMenuData> cookSpecialityMenuDataList) {
		this.cookSpecialityMenuDataList = cookSpecialityMenuDataList;
	}

}
