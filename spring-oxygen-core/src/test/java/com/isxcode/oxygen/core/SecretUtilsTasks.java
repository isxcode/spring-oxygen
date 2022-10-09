package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.pojo.Dog;
import com.isxcode.oxygen.core.secret.AesUtils;
import com.isxcode.oxygen.core.secret.JwtUtils;
import com.isxcode.oxygen.core.secret.RsaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
@ContextConfiguration(classes = {OxygenCoreAutoConfiguration.class, FreeMarkerConfigurer.class})
public class SecretUtilsTasks {

	@Test
	public void testAesNoKey() {

		String data = AesUtils.encrypt("love you");
		System.out.println("data-->" + AesUtils.decrypt(data));
	}

	@Test
	public void testJwtNoKey() {

		Dog dog = new Dog("xiao", 12);
		String data = JwtUtils.encrypt(dog);
		System.out.println("data-->" + JwtUtils.decrypt(data, Dog.class).toString());
	}

	@Test
	public void testRsa() {

		String publicKey =
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkycgTz8VdCSh8fwexX42BYKANDFmrgTbfw4L1xwFp1eqIt/SM4bttjaeugwExhSqzuISBLW6OJ8qKMK26H5hJo1sU+2Z4STi0E3UlgdGZpNMiTLdv4Ge/pH0fpit8P01ahoU94c1Mpo0NMoVsHJ16P4ZqdJruL0ZzHjZF1b0vnETbNuFyrrr/L1cAO+n2LWz6ZCHqqXqc2SalWowIIBZV60ik7+a2rLGEHS+DwQYvRLTFIHFsdeKSD/AIcyhWRkoXH2tJVV5cYuwND+VSiz18Rw1Y2XCPXKKbLeby8odQpuvkk51OcehmEKpmF4BIDA3xBZRlIrb4GThvIHc/uH4hwIDAQAB";
		String privateKey =
				"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCTJyBPPxV0JKHx/B7FfjYFgoA0MWauBNt/DgvXHAWnV6oi39Izhu22Np66DATGFKrO4hIEtbo4nyoowrbofmEmjWxT7ZnhJOLQTdSWB0Zmk0yJMt2/gZ7+kfR+mK3w/TVqGhT3hzUymjQ0yhWwcnXo/hmp0mu4vRnMeNkXVvS+cRNs24XKuuv8vVwA76fYtbPpkIeqpepzZJqVajAggFlXrSKTv5rassYQdL4PBBi9EtMUgcWx14pIP8AhzKFZGShcfa0lVXlxi7A0P5VKLPXxHDVjZcI9copst5vLyh1Cm6+STnU5x6GYQqmYXgEgMDfEFlGUitvgZOG8gdz+4fiHAgMBAAECggEAaPem/4BzcEbVUoVS+fPe2y1NAG4g3Y9Pd6FwZsBxc/5/uaLJEcJ0/N6XeQUrvpRth2gSDFQJg2xeKXdD/DJyYIrPNEsv0j96n7Z1J5QG7xKxrMihD4OKA/PfYRLOOTKWEJqMTEmhc/g1t1ZUBGqRm2LusYoNF1mk9otZ6GE58kImCzw9jKlB/A571OvRAwjgwsVHjDJYuN/XECHEv77zteMUlzD8xzJc+bGwswwqPvssXNJ1vFNXmsFqT4n89TQnsoiQ2RODUP1JEnuFK2Doz+ajpf2f8heefSw69imJkUNJwIU3HSH2nNnRhoD73e2VCgCI5tsGGsu8HKYKQNrDiQKBgQDajOtkDkPVcAQkUB1MyvnUPY4wA0cIEc5dgUk0KPivylngwVNvBmfQ+sFN7FjAlsiWQnbuOo6lBs1bJ5iWUl6mCSaAuIc/wVpATwtzK8Xwkr2D7Pw4r4Cn5RU6+nuTNsKH1dwDvjWtdmtOw6qVNjBrYVLonfsSwGnWVN9hG5fguwKBgQCsXjsNA+3Bj0RuLNsxw9iaKpJLjfkpXbZUHzw2OrNp2Zow+H0oeLBJCa7NmiWGQGzF7g8OH6VEFuMJV2HWKjZ3Lvj9IsMKhS+2OFZ8iNAKJ96nlI5yPpUzEp+ffSdPboQ+U21vSO7B+Pn/7p3BmSz52xGuBP3Q8hpoCoZIZahgpQKBgQDK8nVcMor3Vs/h0KuEtjAM0SSKnWCGs9JIrlgGIc3doDCaL6yEuxQI0fJTpBg+r0aRkiLS0nnH8WsdLMXbQZ423ORuP+0IXUqr0Ts2tf1Xi0yFh7ooAYGdZ3OxkwzSBdXbQdLPfwBllkPLnpN3gByyemv9K0cNgLRHngTZLkB5JQKBgBvgVX6CXyZJrFCBMqX2vYYZUnWjrWMyQ7WRI6TFk5SP268F0QAFnMUDKuEXh/ARZUYRfR/UoF7FsMwm9Ky1QP+/egc+YvrFlXTL4bjcS4EI96p0jSEO3ARZg7VAgWYIQpzt0yoHSo3WXWA10A+qXSq/cjmJlx7+uKSQ5yy4PCX9AoGBAI5TjACVq7lr09t+i0N3rdmw9gf2aU+aj8RU6JIlpfASv2tJINYS/Z76giHAKOBdlqSyJwqQylfHxbZdZ155W9j4ExTiQ8J0SQpu+WDvu7OIK8W71516AYopPO1UQVNqLKAyWY9VaDMQqiXlSO0PzvU6jG80uZBmuahxwqk35/8h";
		String data = RsaUtils.encrypt(publicKey, "i love you");
		System.out.println("data-->" + RsaUtils.decrypt(privateKey, data));
	}
}
