package ru.RateLimiter.Config;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		//Global settings
		Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
	    Bandwidth limit = Bandwidth.classic(10, refill).withInitialTokens(1);
	    Bucket bucket = Bucket.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1))
	    										 .addPathPatterns("/home")
	    										 .addPathPatterns("/user");
	    
	    //Setting for orders page
	    refill = Refill.intervally(3, Duration.ofMinutes(1));
	    limit = Bandwidth.classic(3, refill);
	    bucket = Bucket.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1))
	    										 .addPathPatterns("/orders");
	}
}
