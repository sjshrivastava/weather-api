package com.api.weatherapi.aspect;

import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.time.Instant;

@Aspect
@Component
public class RateLimitAspect {

    @Before("@annotation(RateLimit)")
    public void beforeRequest() throws IOException {

     RateLimiter rateLimiter = new RateLimiter( );

        if (!rateLimiter.allowRequest()) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Rate limit exceeded.");
        }
    }


    static class RateLimiter {
        private static int capacity = 3;
        private static double refillRate = 2; // Requests per minute
        private static int tokens = 3;
        private Instant lastRefillTime;

        public RateLimiter() {
                this.lastRefillTime = Instant.now();
        }

        public  boolean allowRequest() {
            refillTokens();
            if (tokens > 0) {
                tokens--;
                return true; // Request allowed
            }
            return false; // Request denied due to rate limiting
        }

         void refillTokens() {
            Instant now = Instant.now();
            double timeElapsed = now.toEpochMilli() - lastRefillTime.toEpochMilli();
            int tokensToAdd = (int) (timeElapsed / 60000* refillRate); // Refill tokens based on elapsed time
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTime = now;
        }
    }
}

