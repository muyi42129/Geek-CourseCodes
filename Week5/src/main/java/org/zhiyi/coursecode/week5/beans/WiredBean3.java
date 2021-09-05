package org.zhiyi.coursecode.week5.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("bean3")
public class WiredBean3 {

    private WiredBean1 bean1;

    public WiredBean1 getBean1() {
        return bean1;
    }

    @Autowired
//    @Qualifier("bean1_1")
    public void setBean1(WiredBean1 bean1_1) {
        this.bean1 = bean1_1;
    }

    public WiredBean3(WiredBean1 bean1) {
        this.bean1 = bean1;
    }
}
