package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.matcher.MatcherUtils;
import org.junit.jupiter.api.Test;

public class MatcherUtilsTests {

	@Test
	public void testExcelParseFile() {

		String url = "https://baidu.com";
		String matcherParse = MatcherUtils.matcherParse("https://", ".com", url);
		System.out.println(matcherParse);
	}
}
