package org.philmaster.boot.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * @author Philmasteryeah
 *
 */
public enum ImageService {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(ImageService.class);

	private static final String API_KEY = "9791405-a7197fafe0a8ea969281cb9f5"; // this should be secret
	private static final int API_QUERY_LIMIT = 100;

	private static final String URL = "https://pixabay.com/api/?key=" + API_KEY
			+ "&image_type=photo&pretty=true&category=food";

	public static String getTestImage() {
		return getBase64ImageFromTags("yellow flowers");
	}

	public static String getBase64ImageFromTags(String desc) {

		List<String> tagList = new ArrayList<>();
		Pattern p = Pattern.compile("[a-zA-Z]{5,}");
		Matcher m = p.matcher(desc);
		if (m.find())
			tagList.add(m.group(0));

		String tags = tagList.stream()
				.map(tag -> {
					try {
						return URLEncoder.encode(tag, StandardCharsets.UTF_8.name());
					} catch (UnsupportedEncodingException e) {
						LOGGER.warn(e.getMessage());
					}
					return tag;
				})
				.collect(Collectors.joining("+"));

		tags = tags.substring(0, tags.length() > API_QUERY_LIMIT ? API_QUERY_LIMIT : tags.length());

		String url = URL + "&q=" + tags; // add param
		JSONObject jsonObject = getUrlContentJSON(url); // get json string request
		String imageUrl = urlFromJsonObject(jsonObject); // get url inside of json
		return getUrlContentBase64(imageUrl); // make picture to base64 string
	}

	private static String urlFromJsonObject(JSONObject jsonObject) {
		if (jsonObject == null)
			return null;
		try {
			JSONArray arr = jsonObject.getJSONArray("hits");
			return arr != null && arr.length() > 0 ? arr.getJSONObject(0)
					.get("previewURL")
					.toString() : null;
		} catch (JSONException e) {
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	private static JSONObject stringToJSON(String str) {
		try {
			return str != null ? new JSONObject(str) : null;
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	private static String getUrlContent(String url) {
		byte[] barry = urlToBarry(url);
		LOGGER.info(MessageFormat.format("--> {0}", url));
		return barry != null ? new String(barry, StandardCharsets.UTF_8) : null;
	}

	private static String getUrlContentBase64(String url) {
		return url != null ? Base64.getEncoder()
				.encodeToString(urlToBarry(url)) : null;
	}

	private static JSONObject getUrlContentJSON(String url) {
		return stringToJSON(getUrlContent(url));
	}

	private static byte[] urlToBarry(String url) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			URLConnection conn = new URL(url).openConnection();
			conn.setRequestProperty("User-Agent", "Firefox"); // fake a browser
			try (InputStream inputStream = conn.getInputStream()) {
				int n = 0;
				byte[] buffer = new byte[1024];
				while (-1 != (n = inputStream.read(buffer))) {
					output.write(buffer, 0, n);
				}
			}
			return output.toByteArray();
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}
		return new byte[0];
	}

}
