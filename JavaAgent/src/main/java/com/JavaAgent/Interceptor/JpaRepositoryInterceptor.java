package com.JavaAgent.Interceptor;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class JpaRepositoryInterceptor {
	@RuntimeType
//	public static Object intercept(@AllArguments Object[] allArguments, @Origin Method method,
//			@SuperCall Callable<?> zuper) throws Exception {
	public static Object intercept(@AllArguments Object[] allArguments, @SuperCall Callable<?> zuper) throws Exception {
		System.out.println("Intercepted JpaRepository save() method");
		String mode = System.getenv("HT_MODE");
		System.out.println("MODE :: " + mode);

		if (System.getenv("HT_MODE").equals("RECORD")) {
			return zuper.call();
		}

		return allArguments[0];
	}

}
