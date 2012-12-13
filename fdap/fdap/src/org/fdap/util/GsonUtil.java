package org.fdap.util;

import com.google.gson.Gson;

public class GsonUtil {

	private Gson gson = new Gson();
	
	public void print(Object obj) {
	  System.out.println(gson.toJson(obj));
	}
}
