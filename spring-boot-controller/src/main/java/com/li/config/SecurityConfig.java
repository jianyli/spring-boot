package com.li.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.service.impl.UserSecurityServiceImpl;
import com.li.support.dto.RestResultDto;
import com.li.support.dto.UserInfoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    UserSecurityServiceImpl userService;
    @Resource
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Resource
    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login","/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException, JsonProcessingException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        UserInfoDTO userInfoDTO = (UserInfoDTO) authentication.getPrincipal();
                        userInfoDTO.setPassword(null);
                        String s = new ObjectMapper().writeValueAsString(RestResultDto.newSuccess(userInfoDTO,"登录成功"));
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RestResultDto result = RestResultDto.newFail("登录失败");
                        if (e instanceof LockedException) {
                            result.setMsg("账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            result.setMsg("密码过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            result.setMsg("账户被禁用，请联系管理员!");
                        } else if (e instanceof BadCredentialsException) {
                            result.setMsg("用户名或者密码输入错误，请重新输入!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RestResultDto.newSuccess("注销成功!")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        resp.setStatus(401);
                        PrintWriter out = resp.getWriter();
                        RestResultDto result = RestResultDto.newFail("访问失败");
                        if (authException instanceof InsufficientAuthenticationException) {
                            result.setMsg("请求失败，请联系管理员!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                });
    }
}
