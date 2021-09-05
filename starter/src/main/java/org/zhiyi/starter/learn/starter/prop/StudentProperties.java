package org.zhiyi.starter.learn.starter.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("zhiyi.student")
@Getter
@Setter
public class StudentProperties {

    private int id;
    private String name;
    private int KlassId;
    private int SchoolId;
}
