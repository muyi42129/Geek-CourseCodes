package org.zhiyi.starter.learn.sources;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

    private int id;
    private String name;
    private int klassId;
    private int schoolId;

    public void init(){
        System.out.println("hello...........");
    }

    public void print() {
        this.toString();
    }


}
