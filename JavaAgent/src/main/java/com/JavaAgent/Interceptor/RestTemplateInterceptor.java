package com.JavaAgent.Interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class RestTemplateInterceptor {

	public static <T> ResponseEntity<T> intercept(@AllArguments Object[] allArguments, @Origin Method method, @SuperCall Callable<?> zuper) throws Exception {
		System.out.println("Intercepted RestTemplate getForEntity() method");
		
		if (System.getenv("HT_MODE").equals("RECORD")) {
			return (ResponseEntity<T>) zuper.call();
		}
		
		T body = (T) "{\"id\": 2,\"title\": \"iPhone 15\",\"description\": \"An apple mobile which is nothing like apple\",\"price\": 549,\"discountPercentage\": 12.96,\"rating\": 4.69,\"stock\": 94,\"brand\": \"Apple\",\"category\": \"smartphones\",\"thumbnail\": \"https://i.dummyjson.com/data/products/1/thumbnail.jpg\",\"images\": [\"https://i.dummyjson.com/data/products/1/1.jpg\",\"https://i.dummyjson.com/data/products/1/2.jpg\",\"https://i.dummyjson.com/data/products/1/3.jpg\",\"https://i.dummyjson.com/data/products/1/4.jpg\",\"https://i.dummyjson.com/data/products/1/thumbnail.jpg\"]}";
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

}
