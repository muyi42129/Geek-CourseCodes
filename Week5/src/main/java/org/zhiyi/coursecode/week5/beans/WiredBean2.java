package org.zhiyi.coursecode.week5.beans;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("bean2")
public class WiredBean2 {

    private String beanName;

    @Resource(name = "bean1")
    private WiredBean1 bean1;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public WiredBean1 getBean1() {
        return bean1;
    }

    public void setBean1(WiredBean1 bean1) {
        this.bean1 = bean1;
    }
}
