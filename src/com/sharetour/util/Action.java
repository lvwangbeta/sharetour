package com.sharetour.util;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	public String execute(HttpServletRequest request);
}
