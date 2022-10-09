package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.http.HttpUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class HttpUtilsTests {

	@Test
	public void testPostHttp() throws IOException {

		String url = "https://api.isxcode.com/leo/version";

		Map<String, String> headers = new HashMap<>();
		headers.put("user", "ispong");

		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("user", "ispong");

		try {
			System.out.println(HttpUtils.doPost(url, headers, requestParams, String.class));
		} catch (OxygenException | IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test
	public void testGetHttp() {

		String url = "https://api.isxcode.com/leo/version";

		Map<String, String> headers = new HashMap<>();
		headers.put("user", "ispong");

		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("user", "ispong");

		try {
			System.out.println(HttpUtils.doGet(url, headers, requestParams, String.class));
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
