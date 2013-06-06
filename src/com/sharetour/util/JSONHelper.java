package com.sharetour.util;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

public class JSONHelper {
	
	/*
	 * 从缓冲中获取json字符串
	 * @param reader
	 * @return jsonString
	 */
	public static String retrieveJSON(BufferedReader reader) throws IOException{
		StringBuffer json = new StringBuffer();
		String line = reader.readLine();
		while(line != null){
			json.append(line + "\n");
			line = reader.readLine();
		}
		return json.toString();
	}
	
	/*
	 * convert json string ro object
	 * @param json string
	 * @param obj class type
	 */
	public static <T> T json2obj(String json, Class<T> clazz){
		if(json == null || json.length() == 0)
			return null;
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
	
	/*
	 * convert obj to json string
	 * @param obj
	 * @return string json
	 */
	public static String obj2json(Object obj) {
		if(obj == null)
			return null;
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
}
