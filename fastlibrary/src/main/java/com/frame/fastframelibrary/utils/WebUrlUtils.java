package com.frame.fastframelibrary.utils;

import android.net.Uri;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import static android.util.Patterns.GOOD_IRI_CHAR;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**URL辅助工具类*/
public class WebUrlUtils {

	public static final String TAG =WebUrlUtils.class.getName();
	
	/**
	 * 获取URL中指定key的参数 
	 * @param webUrl
	 * @return
	 */
	public static String getParam(String webUrl,String key){
		try {
			if (StringUtils.isNotEmpty(webUrl)) {
				Uri uri = Uri.parse(StringUtils.format(webUrl));
				if (uri!=null) {
					return uri.getQueryParameter(key);
				}
			}
		} catch (Exception e) {
			LogUtils.e(TAG, e.getMessage());
		}
		return "";
	}

	/**
	 * 解析URL中的参数,并以map返回
	 * @param webUrl
	 * @return
	 */
	public static HashMap<String, String> getParams2Map(String webUrl){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if (StringUtils.isNotEmpty(webUrl)) {
				Uri uri = Uri.parse(StringUtils.format(webUrl));
				if (uri!=null) {
					Set<String> names = uri.getQueryParameterNames();
					if (names!=null&&names.size()>0) {
						Iterator<String> it = names.iterator();
						while (it.hasNext()){
							String key = it.next();
							if (StringUtils.isNotEmpty(key)) {
								map.put(key,uri.getQueryParameter(key));
							}
						}
					}
					return map;
				}
			}
		} catch (Exception e) {
			LogUtils.e(TAG, e.getMessage());
		}
		return map ;
	}
	public static URI convert2URI(String webUrl){
		try {
			URI uri = new URI(StringUtils.format(webUrl));
			return uri;
		} catch (URISyntaxException e) {
			LogUtils.e(e);
		}
		return null;
	}

	public static String getScheme(String webUrl){
		if(StringUtils.isNotEmpty(webUrl)){
			URI uri = convert2URI(webUrl);
			if(uri!=null&&StringUtils.isNotEmpty(uri.getScheme())){
				return uri.getScheme();
			}
		}
		return "";
	}
	public static String getCallCommand(String webUrl){
		if(StringUtils.isNotEmpty(webUrl)){
			URI uri = convert2URI(webUrl);
			if(uri!=null&&StringUtils.isNotEmpty(uri.getHost())){
				return uri.getHost();
			}
		}
		return "";
	}
}