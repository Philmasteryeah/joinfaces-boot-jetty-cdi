package org.philmaster.boot.service;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.google.common.base.Charsets;

/**
 * @author Philmasteryeah
 *
 */

@Named
@ApplicationScoped
public class ImageService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String API_KEY = "9791405-a7197fafe0a8ea969281cb9f5"; // this should be secret

	private static final String URL = "https://pixabay.com/api/?key=" + API_KEY + "&image_type=photo&pretty=true";

	@PostConstruct
	void init() {
	}

	public String getParsedImage() {
		String url = URL + "&q=yellow+flowers"; // addparam TODO from meal desc
		String dt = urlToString(url); // get json string request
		JSONObject jsonObject = stringToJSON(dt); // transform to JSON
		String imageUrl = urlFromJsonObject(jsonObject); // get url inside of json
		String parsedImage = urlContentToBase64(imageUrl); // and make to base64 string
		return parsedImage;
	}

	//

	private static String urlFromJsonObject(JSONObject jsonObject) {
		try {
			return jsonObject.getJSONArray("hits").getJSONObject(0).get("previewURL").toString();
		} catch (JSONException e) {
			System.err.println(e);
		}
		return null;
	}

	private static JSONObject stringToJSON(String str) {
		try {
			return new JSONObject(str);
		} catch (Exception e) {
			System.err.println(e); // TODO
		}
		return null;
	}

	private static String urlToString(String url) {
		try {
			return new String(new URL(url).openStream().readAllBytes(), Charsets.UTF_8);
		} catch (IOException e) {
			System.err.println(e); // TODO
		}
		return null;
	}

	private static String urlContentToBase64(String url) {
		try {
			return Base64.getEncoder().encodeToString(new URL(url).openStream().readAllBytes());
		} catch (Exception e) {
			System.err.println(e); // TODO
		}
		return null;
	}
}
