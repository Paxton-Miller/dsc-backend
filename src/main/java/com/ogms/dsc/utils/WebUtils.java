package com.ogms.dsc.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @name: WebUtils
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 5/9/2024 8:59 PM
 * @version: 1.0
 */
public class WebUtils {
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
