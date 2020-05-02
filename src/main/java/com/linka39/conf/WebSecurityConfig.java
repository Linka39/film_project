package com.linka39.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security配置用户权限
 */
@Configuration //注解必须加
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许全局注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 /*       auth.inMemoryAuthentication()
                .withUser("linka")
                .password("123")
                .roles("ADMIN");//角色一定要加,只允许该角色进行操作*/

        //用户认证要求需要对密码进行加密
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("guowl")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER");//角色一定要加,只允许该角色进行操作

//        super.configure(auth);
    }

    /**
     * 请求授权  重定向，跨域
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf跨域请求过滤，开发时暂时先去除所有请求的过滤
        http.csrf().disable().cors().disable().headers().disable()
            .authorizeRequests().antMatchers("/","/static/**").permitAll()     //配置不需要权限认证的网页
            .anyRequest().authenticated()//其他访问路径需要认证
            .and()
            .formLogin()
            .loginPage("/login")    //指定登录请求地址
            .defaultSuccessUrl("/admin")    //登录成功后的默认页面,如果登陆出错的话会重定向/login，自动返回一个error参数
            .permitAll()//允许执行前面所有的
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .permitAll();//允许执行前面所有的

//        super.configure(http);
    }
}
