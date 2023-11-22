package com.JavaAgent.Agent;

import java.lang.instrument.Instrumentation;

import org.springframework.data.repository.CrudRepository;

import com.JavaAgent.Interceptor.JpaRepositoryInterceptor;
import com.JavaAgent.Interceptor.RestTemplateInterceptor;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {
//	public static void premain(String arguments, Instrumentation instrumentation) {
//		new AgentBuilder.Default()
//        .type(ElementMatchers.nameContains("Service")) // Adjust this to match your Hello project classes
//        .transform(new AgentBuilder.Transformer() {
//			@Override
//			public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader,
//					JavaModule module, ProtectionDomain protectionDomain) {
//				return builder
//	                    .method(ElementMatchers.named("helloService")) // Intercept the hello() method
//	                    .intercept(FixedValue.value("Hello! How are you?"));
////	                    .method(ElementMatchers.named("hi")) // Intercept the hi() method
////	                    .intercept(FixedValue.value("Hi my name is Ali"));
//			}
//        }).installOn(instrumentation);
//    }
		
//	public static void premain(String arguments, Instrumentation instrumentation) {
//        new AgentBuilder.Default()
//            // Use a listener to report transformations and errors
//            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
//            // Match the RestTemplate class
//            .type(ElementMatchers.named("org.springframework.web.client.RestTemplate"))
//            // Specify the transformation
//            .transform(new AgentBuilder.Transformer() {
//                @Override
//                public net.bytebuddy.dynamic.DynamicType.Builder<?> transform(
//                        net.bytebuddy.dynamic.DynamicType.Builder<?> builder,
//                        TypeDescription typeDescription,
//                        ClassLoader classLoader,
//                        JavaModule module,
//                        ProtectionDomain protectionDomain) {
//                    // Intercept the specific getForEntity method
//                    return builder.method(ElementMatchers.named("getForEntity")
//                            .and(ElementMatchers.takesArguments(3))
//                            .and(ElementMatchers.takesArgument(0, String.class))
//                            .and(ElementMatchers.takesArgument(1, Class.class))
//                            .and(ElementMatchers.takesArgument(2, Object[].class))) // Match the varargs as Object[]
//                            .intercept(MethodDelegation.to(RestTemplateInterceptor.class));
//                }
//            })
//            // Install the agent on the JVM
//            .installOn(instrumentation);
//    }
	
	public static void premain(String arguments, Instrumentation instrumentation) {
	    AgentBuilder agentBuilder = new AgentBuilder.Default()
	        .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly());

	    // Intercept RestTemplate.getForEntity
	    agentBuilder.type(ElementMatchers.named("org.springframework.web.client.RestTemplate"))
	        .transform((builder, type, classLoader, module, protectionDomain) -> builder
	            .method(ElementMatchers.named("getForEntity")
	                .and(ElementMatchers.takesArguments(3))
	                .and(ElementMatchers.takesArgument(0, String.class))
	                .and(ElementMatchers.takesArgument(1, Class.class))
	                .and(ElementMatchers.takesArgument(2, Object[].class)))
	            .intercept(MethodDelegation.to(RestTemplateInterceptor.class)))
	        .installOn(instrumentation);

	    // Intercept JpaRepository.save
	    agentBuilder.type(ElementMatchers.isSubTypeOf(CrudRepository.class))
	        .transform((builder, type, classLoader, module, protectionDomain) -> builder
	            .method(ElementMatchers.named("save"))
	            .intercept(MethodDelegation.to(JpaRepositoryInterceptor.class)))
	        .installOn(instrumentation);
	}


}
