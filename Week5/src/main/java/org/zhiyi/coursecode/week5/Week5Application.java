package org.zhiyi.coursecode.week5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.zhiyi.coursecode.week5.beans.Configuration;
import org.zhiyi.coursecode.week5.beans.WiredBean1;
import org.zhiyi.coursecode.week5.beans.WiredBean2;
import org.zhiyi.coursecode.week5.beans.WiredBean3;
import org.zhiyi.coursecode.week5.beans.WiredBean4;
import org.zhiyi.starter.learn.sources.Klass;
import org.zhiyi.starter.learn.sources.School;
import org.zhiyi.starter.learn.sources.Student;

@SpringBootApplication
@ImportResource(locations = "applicationContext.xml")
//@ComponentScan("org.zhiyi.*")
public class Week5Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Week5Application.class, args);

        // 通过@ImportResource引入 applicationContext.xml 配置文件
        WiredBean1 bean1 = (WiredBean1) applicationContext.getBean("bean1");
        System.out.println(bean1.getBeanName());

        // 通过 @Component 装配 WiredBean2; 使用 @Resource 实现属性 WiredBean1 的装配
        WiredBean2 bean2 = (WiredBean2) applicationContext.getBean("bean2");
        System.out.println(bean2.getBean1().getBeanName());

        // 通过@AutoWired 根据 type 装配 Bean
        /*
         * 默认根据 type 注入，若存在多个相同 type 的 Bean 可以通过
         * 1）Field、Setter形参、Constructor形参 命名解决歧义
         * 2）使用 @Qualifier 明确注入的 Bean id 消除歧义
         * 3）使用 @Primary 设置首选项消除歧义， 当@Qualifier 和 @Primary 同时存在时，@Qualifier 优先级更高
         * */
        WiredBean3 bean3 = (WiredBean3) applicationContext.getBean("bean3");
        System.out.println(bean3.getBean1().getBeanName());

        // 通过@Resource name属性解析为bean的id，type属性则解析为bean的类型
        WiredBean4 bean4 = (WiredBean4) applicationContext.getBean("bean4");
        System.out.println(bean4.getBean().getBeanName());

        // 通过@Configuation @Bean返回的实例都会经过动态代理，每次都返回相同的实例，@Component 则不会
        Configuration bean = (Configuration) applicationContext.getBean("config1");
        System.out.println(bean.getConfigBean().getBeanName());
        System.out.println(bean.getConfigBean());
        System.out.println(bean.getConfigBean());

        // 获取通过@Configuration 声明的Bean
        WiredBean1 bean5 = (WiredBean1) applicationContext.getBean("config1_bean1");
        System.out.println(bean5.getBeanName());


        /*
         * Starter 作业
         * 通过 application.properties 统一前缀配置对Bean进行装配
         * 注意 starter 中 spring.factories 命名，一定要放在META-INF/ 下，具体原因是 @SpringBootApplication
         * SpringFactoriesLoader.loadFactoryNames方法调用loadSpringFactories方法从所有的jar包中读取META-INF/spring.factories文件信息
         *
         * 2021-09-05
         */
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);

        Klass klass = (Klass) applicationContext.getBean("klass");
        System.out.println(klass);

        School school = (School) applicationContext.getBean("school");
        System.out.println(school);

    }

}
