package com.reservation.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.Contract;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class FeignClientConfig {

	private final ObjectFactory<HttpMessageConverters> messageConverters = () -> new HttpMessageConverters(
			new MappingJackson2HttpMessageConverter());

	@Bean
	public Encoder feignEncoder() {
		return new SpringEncoder(messageConverters);
	}

	@Bean
	public Decoder feignDecoder() {
		return new SpringDecoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter()));
	}

	@Bean
	public Contract feignContract() {
		return new SpringMvcContract();
	}
}
