package com.marsarmy.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring Security initialization
 */
@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
