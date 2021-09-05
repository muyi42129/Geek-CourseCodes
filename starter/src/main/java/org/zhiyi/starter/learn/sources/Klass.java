package org.zhiyi.starter.learn.sources;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class Klass {

    private int id;
    private String name;
    private int schoolId;

}
