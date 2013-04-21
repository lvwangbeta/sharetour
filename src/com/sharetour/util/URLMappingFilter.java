package com.sharetour.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Servlet Filter implementation class URLMappingFilter
 */
public class URLMappingFilter implements Filter {

	private ServletContext context;
	private String PAGE_LOCATION;								//模板或jsp页面位置的根位置
	private String base;										//www访问的模板存放位置
	private String EXT = ".jsp";								//默认模板扩展名
	private String INDEX = "/index" + EXT;
	private List<String> ignoreURIs = new ArrayList<String>();	//忽略的URL
	private List<String> ignoreExts = new ArrayList<String>();	//忽略的扩展名
	 															//这些将直接放过访问
	private static final List<String> pathcache = new ArrayList<String>();	//访问文件路径缓存
	
    /**
     * Default constructor. 
     */
    public URLMappingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		context = fConfig.getServletContext();
		PAGE_LOCATION = fConfig.getInitParameter("pageLocation");
		if(PAGE_LOCATION.endsWith("/"))
		{
			PAGE_LOCATION = PAGE_LOCATION.substring(0, PAGE_LOCATION.length()-1);
		}
		base = PAGE_LOCATION + fConfig.getInitParameter("www");
		
		//某些URL前缀不予处理
		String ignores = fConfig.getInitParameter("ignore");
		if(ignores != null)
		for(String ig : StringUtils.split(ignores, ','))
			ignoreURIs.add(ig.trim());
		
		//某些URL扩展名不予处理
		ignores = fConfig.getInitParameter("ignoreExts");
		if(ignores != null)
		for(String ig : StringUtils.split(ignores, ','))
			ignoreExts.add('.'+ig.trim());
	}
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getServletPath();
		String query = request.getQueryString();
		String paths[] = StringUtils.split(uri, '/');
		// pass the request along the filter chain
		
		for(String ignore_url: ignoreURIs)
		{
			if(uri.startsWith(ignore_url))
			{
				chain.doFilter(request, response);
				return ;	//直接返回客户端，不传回上一个filter
			}
		}
		
		for(String ignore_ext: ignoreExts)
		{
			if(uri.endsWith(ignore_ext))
			{
				chain.doFilter(request, response);
				return ;
			}
		}
		
		
		String page = getPage(paths, paths.length);
		if(query != null && query.length() == 0)
		{
			page += query;
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/*
	 * getPage 获得访问路径对应的模板文件路径
	 * 
	 */
	public String getPage(String[] paths, int length)
	{
		StringBuffer location = new StringBuffer(base);
		if(length == 0)
		{
			return location.toString() + INDEX + QueryString(paths, length);
		}
		
		for(int i=0; i<length; i++)
		{
			location.append("/");
			location.append(paths[i]);
		}
		String path = location.toString();
		if(isExist(path + EXT))
		{
			return path + EXT + QueryString(paths, length);
		}
		path += INDEX;
		
		if(isExist(path))
		{
			return path + QueryString(paths, length);
		}
		
		return getPage(paths, length-1);
		
	}
	
	/*
	 * QueryString 构造uri之后的参数
	 * 
	 */
	public String QueryString(String[] paths, int length)
	{
		StringBuffer params = new StringBuffer("?");
		int count = 1;
		for(int i=length; i<paths.length; i++)
		{
			if(i != length)
				params.append("&");
			params.append("p");
			params.append(count);
			count++;
			params.append("=");
			params.append(paths[i]);
		}
		return params.toString()+"&";
	}
	
	/*
	 * isExist 检测文件是否存在
	 * 如果存在，将文件路径加入缓存
	 * 
	 */
	public boolean isExist(String path)
	{
		if(pathcache.contains(path))
			return true;
		File file = new File(context.getRealPath(path));
		if(file.exists() && file.isFile())
		{
			pathcache.add(path);
			return true;
		}
		return false;
		
	}

}
