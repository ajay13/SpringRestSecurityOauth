package com.beingjavaguys.controllers.cmsmenu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.bean.cmsmenu.CMSCatagoryBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.services.cmsmenu.CMSCatagoryService;
import com.beingjavaguys.utility.validations.RestValidation;

@Controller
@RequestMapping("/cms/menucatagory")
public class CMSMenuCatagoryController {

	@Autowired
	CMSCatagoryService cmsCatagoryService;

	@Autowired
	RestValidation restValidation;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	void add(HttpServletResponse response,
			@RequestBody CMSCatagoryBean cmsCatagoryBean) {
		try {
			cmsCatagoryService.add(cmsCatagoryBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	void edit(HttpServletResponse response,
			@RequestBody CMSCatagoryBean cmsCatagoryBean) {
		try {
			cmsCatagoryService.edit(cmsCatagoryBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	void delete(HttpServletResponse response,
			@RequestParam(required = true) int id) {
		try {
			cmsCatagoryService.delete(id, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}
	
	@RequestMapping(value = "/catagorylist", method = RequestMethod.GET)
	public @ResponseBody
	BeanList get(HttpServletResponse response,
			@RequestParam(required = false) String limit,
			@RequestParam(required = false) String pageno) {
		int limitInt = 0, pagenoInt = 0;
		BeanList beanList = null;
		try {
			if (limit == null || limit.equalsIgnoreCase("null")
					|| limit.equals("")) {
				limitInt = 10;
			} else {
				restValidation.numberFormatValidation(limit);
				limitInt = Integer.parseInt(limit);
			}
			if (pageno == null || pageno.equalsIgnoreCase("null")
					|| pageno.equals("")) {

				pagenoInt = 1;
			} else {
				restValidation.numberFormatValidation(pageno);
				pagenoInt = Integer.parseInt(pageno);
			}

			beanList = cmsCatagoryService.get(limitInt, pagenoInt);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return beanList;
	}

	@RequestMapping(value = "/catagory", method = RequestMethod.GET)
	public @ResponseBody
	List<CMSCatagoryBean> get(HttpServletResponse response,
			@RequestParam(required = true) String catagoryname) {
		List<CMSCatagoryBean> cmsCatagoryBeanList = new ArrayList<CMSCatagoryBean>();
		try {
			cmsCatagoryBeanList = cmsCatagoryService.get(catagoryname, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return cmsCatagoryBeanList;
	}
	
	@RequestMapping(value = "/allcatagory", method = RequestMethod.GET)
	public @ResponseBody
	List<CMSCatagoryBean> get(HttpServletResponse response) {
		List<CMSCatagoryBean> cmsCatagoryBeanList = new ArrayList<CMSCatagoryBean>();
		try {
			cmsCatagoryBeanList = cmsCatagoryService.get();
		} catch (Exception e) {
			response.setStatus(400);
		}
		return cmsCatagoryBeanList;
	}
	
	@RequestMapping(value = "/groupingAndSeq", method = RequestMethod.POST)
	public @ResponseBody
	void groupingAndSeq(HttpServletResponse response,
			@RequestParam(required = true) List<Integer> ids,
			@RequestParam(required = true) String groupName) {
		try {
			cmsCatagoryService.grouppingAndSeq(ids,groupName);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}
}
