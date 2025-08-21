package com.ecommerce.genericutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {

	public String getJSONdata(String key, String status) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object object;
		if (status.equalsIgnoreCase("register")) {
			object = parser.parse(new FileReader("./testappdata/register.json"));
		} else {
			object = parser.parse(new FileReader("./testappdata/commondata.json"));
		}
		JSONObject jsonobj = (JSONObject) object;
		return jsonobj.get(key).toString();

	}

}
