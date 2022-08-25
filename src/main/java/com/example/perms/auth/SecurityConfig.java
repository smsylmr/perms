package com.example.perms.auth;

import com.example.perms.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Resource
    private RedisUtils redisUtils;

    // 加密器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * description: 加载userDetailsService，用于从数据库中取用户信息
     *
     * @param auth
     * @return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * description: http细节
     *
     * @param http
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域资源共享
        http.cors(withDefaults())
                // 关闭csrf
                .csrf().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(new UnAuthorizedEntryPoint())
                .and()
                .exceptionHandling().accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .authorizeRequests()
                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
                .antMatchers("/user/login", "/register", "/captchaImage").anonymous()
                .antMatchers("/doc.html", "/doc.html/**", "/webjars/**", "/v2/**", "/swagger-resources",
                        "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui.html/**")
                .permitAll()
                // 都要验证登录
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                //通过OncePerRequestFilter，对其他请求过滤
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),redisUtils));

    }

    @Override
    public void configure(WebSecurity web) {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/actuator/health")//consul心跳检测
                .antMatchers("/provide/user/**")//fegin服务提供端
                .antMatchers("/public");
    }

    /**
     * Cors配置优化
     **/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList("Authorization"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}
