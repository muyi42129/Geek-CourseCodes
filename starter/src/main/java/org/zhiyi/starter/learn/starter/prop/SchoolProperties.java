package org.zhiyi.starter.learn.starter.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("zhiyi.school")
@Getter
@Setter
public class SchoolProperties {

    private int id;
    private String name;
}
