package org.zhiyi.coursecode.week5.beans;

import org.springframework.context.annotation.Primary;

@Primary
public class WiredBean1 {

    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
