package com.example.demo.bean;

import com.example.demo.config.repository.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Alex
 * @date 2021/9/24 15:37
 */
public class BaseController {

    @Autowired
    public HttpSession httpSession;

    @Autowired
    public HttpServletRequest httpServletRequest;

    @Autowired
    public HttpServletResponse httpServletResponse;

    public RestResponse error(String msg) {
        return RestResponse.error(msg);
    }

    public RestResponse success() {
        return RestResponse.success();
    }

    public PageBean getPageBean() {

        PageBean pageBean = new PageBean();

        try {
            int pageSize = Integer.parseInt(httpServletRequest.getParameter("pageSize"));
            pageBean.setPageSize(pageSize);
        } catch (Exception e) {
            pageBean.setPageSize(10);
        }

        try {
            int page = Integer.parseInt(httpServletRequest.getParameter("currentPage"));
            pageBean.setPage(page);
        } catch (Exception e) {
            pageBean.setPage(1);
        }

        return pageBean;
    }

}
