package org.example.kunuz.config;


import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
    @Autowired
    private TokenFilter tokenFilter;
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);
        bean.addUrlPatterns("/region/adm/create");
        bean.addUrlPatterns("/region/adm/**");
        bean.addUrlPatterns("/region/adm/*");
        bean.addUrlPatterns("/category");
        bean.addUrlPatterns("/category/*");
        bean.addUrlPatterns("/category/**");
        bean.addUrlPatterns("/articletype/admin");
        bean.addUrlPatterns("/articletype/admin/");
        bean.addUrlPatterns("/articletype/admin/*");
        bean.addUrlPatterns("/articletype/admin/**");
        bean.addUrlPatterns("/profile");
        bean.addUrlPatterns("/profile/");
        bean.addUrlPatterns("/profile/*");
        bean.addUrlPatterns("/profile/**");

        return bean;
    }


}
