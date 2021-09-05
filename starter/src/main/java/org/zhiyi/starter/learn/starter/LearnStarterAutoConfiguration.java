package org.zhiyi.starter.learn.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.zhiyi.starter.learn.sources.Klass;
import org.zhiyi.starter.learn.sources.School;
import org.zhiyi.starter.learn.sources.Student;
import org.zhiyi.starter.learn.starter.prop.KlassProperties;
import org.zhiyi.starter.learn.starter.prop.SchoolProperties;
import org.zhiyi.starter.learn.starter.prop.StudentProperties;
import javax.annotation.Resource;

/**
 * 组件被 SpringBoot 配置的入口点
 */
@Configuration
// 绑定配置文件
@EnableConfigurationProperties({KlassProperties.class, SchoolProperties.class, StudentProperties.class}) // 绑定配置文件
public class LearnStarterAutoConfiguration implements EnvironmentAware {

    @Resource
    StudentProperties studentProperties;

    @Resource
    KlassProperties klassProperties;

    @Resource
    SchoolProperties schoolProperties;

    @Bean("student")
    public Student student() {
        System.out.println("Bean student init ...");
        Student student = new Student();
        student.setId(studentProperties.getId());
        student.setName(studentProperties.getName());
        student.setKlassId(studentProperties.getKlassId());
        student.setSchoolId(studentProperties.getSchoolId());
        return student;
    }

    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setId(klassProperties.getId());
        klass.setName(klassProperties.getName());
        klass.setSchoolId(klassProperties.getSchoolId());
        return klass;
    }

    @Bean
    public School school() {
        School school = new School();
        school.setId(schoolProperties.getId());
        school.setName(schoolProperties.getName());
        return school;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}
