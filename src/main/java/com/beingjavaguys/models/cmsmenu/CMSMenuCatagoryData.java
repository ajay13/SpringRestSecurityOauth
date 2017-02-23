package com.beingjavaguys.models.cmsmenu;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cms_menu_catagory")
public class CMSMenuCatagoryData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "sequence")
	private int sequence;

	@OneToMany(mappedBy = "cmsMenuCatagoryData")
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public List<CMSMenuData> getCmsMenuDataList() {
		return cmsMenuDataList;
	}

	public void setCmsMenuDataList(List<CMSMenuData> cmsMenuDataList) {
		this.cmsMenuDataList = cmsMenuDataList;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
