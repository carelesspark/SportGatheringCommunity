//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//        handler.setUseReferer(true); // 기존 페이지로 리다이렉트
//        return handler;
//    }
//
//    @Configuration
//    public static class WebSecurityConfigAdapter extends SecurityConfigurerAdapter {
//
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/", "/home").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessURL("/user-home", true)
//                    .successHandler(successHandler()) // 커스텀 SuccessHandler를 등록
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/login")
//                    .permitAll();
//        }
//    }
//}