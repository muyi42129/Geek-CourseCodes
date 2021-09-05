package org.zhiyi.starter.learn.starter.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("zhiyi.klass")
@Getter
@Setter
public class KlassProperties {

    private int id;
    private String name;
    private int schoolId;
}
