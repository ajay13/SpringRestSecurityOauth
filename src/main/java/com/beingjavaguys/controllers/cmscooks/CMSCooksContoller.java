package com.beingjavaguys.controllers.cmscooks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmscooks.CMSCooksSpecialityBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.services.cmscooks.CMSCooksService;
import com.beingjavaguys.utility.validations.RestValidation;

@Controller
@RequestMapping("/cms/cooks")
public class CMSCooksContoller {

	@Autowired
	CMSCooksService cmsCooksService;

	@Autowired
	RestValidation restValidation;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	int add(HttpServletResponse response,
			@RequestBody CMSCooksBean cmsCooksBean) {
		int id = 0;
		try {
			id = cmsCooksService.add(cmsCooksBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return id;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	int edit(HttpServletResponse response,
			@RequestBody CMSCooksBean cmsCooksBean) {
		int cookId = 0;
		try {
			cookId = cmsCooksService.edit(cmsCooksBean, response);
		} catch (Exception e) {
			response.setStatus(400);
		}
		return cookId;
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
	BeanList get(HttpServletResponse response, HttpServletRequest httpServletRequest,
			@RequestParam(required = false) String limit,
			@RequestParam(required = false) String pageno) {
		String url = httpServletRequest.getRequestURL().toString();
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

			beanList = cmsCooksService.get(limitInt, pagenoInt,url);
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
	
	@RequestMapping(value = "/fileupload", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public @ResponseBody
	void upload(HttpServletResponse response,
			@RequestParam("file") MultipartFile inputFile,
			@RequestParam(required = true) int cookId) {
		try {
			String rootPath = servletContext.getRealPath("/");
			String originalFilename = inputFile.getOriginalFilename();
			String folderPath = rootPath + File.separator + "image"
					+ File.separator + "cooks" + File.separator;
			String fileName = originalFilename;
			File destinationFile = new File(folderPath + fileName);
			if (!destinationFile.exists()) {
				destinationFile.mkdirs();
			} else {
				boolean trigger = true;
				int i = 0;
				while (trigger) {
					if (destinationFile.exists()) {
						destinationFile = new File(folderPath + i + fileName);
						i++;
					} else {
						trigger = false;
					}
				}
			}
			inputFile.transferTo(destinationFile);
			String prevoiusFileName = cmsCooksService.uploadImage(
					destinationFile.getName(), cookId, response);
			if (prevoiusFileName != null && !prevoiusFileName.trim().isEmpty()) {
				destinationFile = new File(folderPath + prevoiusFileName);
				destinationFile.delete();
			}
		} catch (Exception e) {
			response.setStatus(400);
		}
	}
	
	@RequestMapping(value = "/getCooksSpeciality", method = RequestMethod.GET)
	public @ResponseBody
	List<CMSCooksSpecialityBean> getCast(HttpServletResponse response) {
		List<CMSCooksSpecialityBean> cmsCooksSpecialityBeanList = null;
		try {
			cmsCooksSpecialityBeanList = cmsCooksService.getCooksSpeciality();
		} catch (Exception e) {
			response.setStatus(400);
		}
		return cmsCooksSpecialityBeanList;
	}

}
