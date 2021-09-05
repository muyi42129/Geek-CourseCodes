package org.zhiyi.coursecode.week5.beans;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component("bean4")
public class WiredBean4 {

    @Resource(name = "bean1_1")
    private WiredBean1 bean1;

    public WiredBean1 getBean() {
        return bean1;
    }

}
