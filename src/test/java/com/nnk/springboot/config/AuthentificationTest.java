package com.nnk.springboot.config;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;

@UtilityClass
public class AuthentificationTest
{
    @Retention(RetentionPolicy.RUNTIME)
    @WithSecurityContext(factory = WithUserAuthSecurityContextFactory.class)
    public @interface WithUserAuth
    {    }

    private static class WithUserAuthSecurityContextFactory implements WithSecurityContextFactory<WithUserAuth>
    {
        @Override
        public @NotNull SecurityContext createSecurityContext(WithUserAuth annotation)
        {
            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            UserDetails user = new org.springframework.security.core.userdetails.User(
                    "xGuix",
                    "Admin!123",
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
            ctx.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
            return ctx;
        }
    }
}
