package camput.config;

import camput.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/camput/home/**"
                        ,"/camput/join","/camput/login","/camput/findId","/camput/findId/modal","/camput/findPw/id","/camput/findPw","/camput/logout"
                        ,"/camput/login/api-login"
                        ,"/camput/member/update"
                        ,"/camput/detail/{name}","/camput/detail/**"
                        ,"/camput/myPage/**"
                        ,"/camput/Qna/**","/camput/reservation/**","/camput/reservationPage/**"
                        ,"/js/**","/css/**","/*.png","/img/**","/error","/ico/**","/*.jpg","/*.tmp"
                        ,"/fragments/**","/layout/**");
    }
}
