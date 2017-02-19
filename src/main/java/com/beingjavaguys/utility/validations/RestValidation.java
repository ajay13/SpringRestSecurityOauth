package com.beingjavaguys.utility.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class RestValidation {

	public Boolean numberFormatValidation(String value) {
		int valueId = 0;
		try {
			valueId = Integer.parseInt(value);

			if (valueId < 0) {
				throw new Exception("Positive Number Required");
			}
		} catch (NumberFormatException e1) {
			throw e1;
		} catch (Exception e2) {
			return false;
		}
		return true;

	}


	public void checkNullValueValidation(HttpServletResponse response,
			String firstVariable, String secondVariable) throws Exception {
		if (firstVariable == null || firstVariable.equals("null")
				|| firstVariable.equals("") && secondVariable == null
				|| secondVariable.equals("null") || secondVariable.equals("")) {
			response.setStatus(400);
			throw new Exception();
		}
	}

	public void checkNullValueValidation(HttpServletResponse response,
			String firstVariable, String secondVariable, String thiredVariable)
			throws Exception {
		if ((firstVariable == null || firstVariable.equals("null") || firstVariable
				.equals(""))
				|| (secondVariable == null || secondVariable.equals("null") || secondVariable
						.equals(""))
				|| (thiredVariable == null || thiredVariable.equals("null") || thiredVariable
						.equals(""))) {
			response.setStatus(400);
			throw new Exception();
		}
	}

	public void checkNullValueValidation(HttpServletResponse response,
			Object firstVariable) throws Exception {

		if (firstVariable == null || firstVariable.equals("null")) {

			response.setStatus(400);
			throw new Exception();
		}
		if (firstVariable instanceof String) {
			if (((String) firstVariable).equals("")) {
				response.setStatus(400);
				throw new Exception();
			}
		}
	}

	public int sortValueValidation(HttpServletResponse response, String sort)
			throws Exception {
		int sortId = 0;
		if (sort == null) {
			sortId = 1;
		} else {
			Boolean numberFormatValidation = numberFormatValidation(sort);
			if (!numberFormatValidation) {
				throw new NumberFormatException();
			}
			sortId = Integer.parseInt(sort);
		}
		return sortId;
	}

	public int validatePasswordPolicyType(int passwordPolicyType)
			throws Exception {

		if (passwordPolicyType < 0) {
			throw new Exception("Positive Number Required");
		}

		if (passwordPolicyType > 4) {

			throw new Exception("PasswordType is invalid");

		}
		if (passwordPolicyType == 0) {
			passwordPolicyType = 1;
		}
		return passwordPolicyType;
	}

	public Boolean validateNotify(boolean notify) throws Exception {
		// System.out.println("Notify value:" + notify);
		if (notify != false && notify != true) {
			throw new Exception("notify is invalid");
		}
		return notify;

	}

	public String validateLanguage(String language) throws Exception {

		if (language == null) {
			language = "en_US";
		}
		return language;

	}

	public void emailPatternMatch(String email) throws Exception {
		String REGEX = "^([a-zA-Z0-9_\\.\\-+])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(email);

		if (m.find() == false) {
			throw new Exception("Invalid email id");
		}

	}

	public void dateRegularExpression(HttpServletResponse response, String date)
			throws Exception {
		@SuppressWarnings("unused")
		String REGEX1 = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";
		String REGEX = "^([0-3][0-9])-([0-1][0-9])-([0-9]{4})\\s([0-1][0-9]|[2][0-3]):([0-5][0-9])$";

		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(date);

		if (m.find() == false) {
			response.setStatus(400);
			throw new Exception("Invalid date");
		}

	}

	@SuppressWarnings("unused")
	public boolean isNumeric(String str) {
		try {
			int num = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
