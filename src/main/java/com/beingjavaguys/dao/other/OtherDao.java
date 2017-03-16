package com.beingjavaguys.dao.other;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.beingjavaguys.models.other.CastData;

@Repository("otherDao")
public interface OtherDao {
	public List<CastData> getCast();
	public CastData getCast(String cast);
}
