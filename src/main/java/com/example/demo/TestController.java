package com.example.demo;

import java.util.Arrays;
import java.util.StringJoiner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
// https://github.com/spring-projects/spring-framework/wiki/Upgrading-to-Spring-Framework-5.x#forwarded-headers
public class TestController {

	@GetMapping("/test")
	public String test(UriComponentsBuilder uriBuilder, HttpEntity<Void> entity) {
		final StringJoiner result = new StringJoiner("\n", "", "\n");
		result.add("FOOOOOOO");
		result.add(uriBuilder.build().getScheme());
		result.add(uriBuilder.build().toString());
		addHeaders(entity, result);
		//addStactktrace(result);
		return result.toString();
	}

	private void addHeaders(HttpEntity<Void> entity, StringJoiner result) {
		result.add("HEADERS");
		HttpHeaders headers = entity.getHeaders();
		if (headers != null) {
			headers.forEach((headerName, headerValues) ->
					headerValues.forEach(
							headerValue -> result.add(headerName + ": " + headerValue))
			);
		}
	}

	private void addStactktrace(StringJoiner result) {
		result.add("STACKTRACE");
		Arrays.stream(Thread.currentThread().getStackTrace())
				.map(StackTraceElement::toString)
				.forEach(result::add);
	}
}
