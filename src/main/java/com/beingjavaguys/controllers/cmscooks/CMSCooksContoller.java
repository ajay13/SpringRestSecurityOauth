package com.beingjavaguys.controllers.cmscooks;

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

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.services.cmscooks.CMSCooksService;
import com.beingjavaguys.utility.validations.RestValidation;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping("/cms/cooks")
public class CMSCooksContoller {

	@Autowired
	CMSCooksService cmsCooksService;

	@Autowired
	RestValidation restValidation;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	void add(HttpServletResponse response,
			@RequestBody CMSCooksBean cmsCooksBean) {
		try {
			cmsCooksService.add(cmsCooksBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	void edit(HttpServletResponse response,
			@RequestBody CMSCooksBean cmsCooksBean) {
		try {
			cmsCooksService.edit(cmsCooksBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	void delete(HttpServletResponse response,
			@RequestParam(required = true) int id) {
		try {
			cmsCooksService.delete(id, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	@RequestMapping(value = "/cooklist", method = RequestMethod.GET)
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

			beanList = cmsCooksService.get(limitInt, pagenoInt);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return beanList;
	}

	@RequestMapping(value = "/cook", method = RequestMethod.GET)
	public @ResponseBody
	List<CMSCooksBean> get(HttpServletResponse response,
			@RequestParam(required = true) String cookname) {
		List<CMSCooksBean> cmsCooksBeanList = new ArrayList<CMSCooksBean>();
		try {
			cmsCooksBeanList = cmsCooksService.get(cookname, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return cmsCooksBeanList;
	}

}
