package org.zhiyi.starter.learn.sources;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@ToString
@Setter
@Getter
public class School implements ISchool {

    @Autowired
    private Klass class1;

    private int id;

    private String name;

    @Override
    public void ding(){
        System.out.println("Class1 name is " + this.class1.getName());
    }
    
}
