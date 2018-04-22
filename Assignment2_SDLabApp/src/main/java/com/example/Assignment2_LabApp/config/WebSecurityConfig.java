//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    private static final String[] AUTH_WHITELIST = {
//
//            // -- config ui
//            "/config-resources/**",
//            "/config-ui.html",
//            "/v2/api-docs",
//            "/webjars/**"
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers("/**/*").denyAll();
//    }
//}
