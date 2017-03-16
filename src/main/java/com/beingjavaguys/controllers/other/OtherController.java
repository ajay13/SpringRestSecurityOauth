package com.beingjavaguys.controllers.other;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.bean.other.CastBean;
import com.beingjavaguys.services.other.OtherService;

@Controller
@RequestMapping("/cms/other")
public class OtherController {

	@Autowired
	OtherService otherService;

	@RequestMapping(value = "/getCast", method = RequestMethod.GET)
	public @ResponseBody
	List<CastBean> getCast(HttpServletResponse response) {
		List<CastBean> castBeanList = null;
		try {
			castBeanList = otherService.getCast();
		} catch (Exception e) {
			response.setStatus(400);
		}
		return castBeanList;
	}
}
