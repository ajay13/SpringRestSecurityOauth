package com.beingjavaguys.dao.cmsmenu;

import java.util.List;

import com.beingjavaguys.models.cmsmenu.CMSMenuUnitData;


public interface CMSMenuUnitDao {
	CMSMenuUnitData get(String unitName);
	List<CMSMenuUnitData> getMenuUnit();
}
