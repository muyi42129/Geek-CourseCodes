package org.zhiyi.coursecode.week5.beans;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration("config1")
public class Configuration {

    WiredBean1 configBean;

    @Bean("config1_bean1")
    public WiredBean1 getConfigBean() {
        WiredBean1 bean1 = new WiredBean1();
        bean1.setBeanName("BEAN_NAME");
        return bean1;
    }
}
