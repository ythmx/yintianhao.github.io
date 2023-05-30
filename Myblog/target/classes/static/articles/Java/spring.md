

**Spring FrameWork系统架构**

是Spring生态圈中最基础的项目，是其他项目的根基

SSM：SpringMVC + Spring + Mybatis

SpringBoot：一个快速开发的脚手架，基于SpringBoot可以快速的开发单个微服务（约定大于配置）

SpringCloud：基于SpringBoot实现的

## **一、核心概念**

控制反转是一种通过描述并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（DI）

1、IoC/DI

**IoC（Inversion of Control）控制反转**

使用对象时，由主动new产生对象转换为由外部提供对象，此过程中对象创建控制权由程序转移到外部，这种思想称为控制反转

控制：传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的

反转：程序本身不创建对象，而变成被动的接收对象

**所谓的IoC，一句话搞定：对象由Spring来创建，管理，装配！**

**DI （Dependency Injection）依赖注入**

在容器中建立bean与bean之间的依赖关系的整个过程，称为依赖注入

本质就是利用类中的set方法来进行注入的

2、IOC容器

spring提供了一个容器，称为IoC容器，用来充当IoC思想中的外部

3、Bean

IoC容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在IoC容器中称为Bean

**总结：**

目标：充分解耦

使用IoC容器管理bean（IoC）

在IoC容器内将有依赖关系的bean进行关系绑定（DI）

## **二、IoC入门案例思路分析**

1、管理什么？（Service与Dao）

2、如何将被管理的对象告知IoC容器？（配置）

3、被管理的对象交给IoC容器，如何获取到IoC容器？（接口）

4、IoC容器得到后，如何从容器中获取bean？（接口方法）

## **三、bean的配置**

````xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="Impl" class="com.yin.dao.UserDaoImpl"></bean>
    <bean id="mysqlImpl" class="com.yin.dao.UserDaoMysqlImpl"></bean>
    <bean id="OracleImpl" class="com.yin.dao.UserDaoOracleImpl"></bean>
    <!--
        ref: 引用Spring容器中创建好的对象
        value: 具体的值，基本数据类型

        <bean id="hello" class="com.yin.pojo.Hello">
            <property name="str" value="Spring"/>
        </bean>
    -->
    <bean id="UserServiceImpl" class="com.yin.service.UserServiceImpl">
        <property name="userDao" ref="Impl"></property>
    </bean>

</beans>
````

## **四、IoC创建对象的方式**

1、使用无参构造创建对象（默认）

2、假设要使用有参构造创建对象

下标赋值

````xml
<bean id="user" class="com.yin.pojo.User">
    <constructor-arg index="0" value="Java"></constructor-arg>
</bean>
````

直接通过参数名来设置

````xml
<bean id="user" class="com.yin.pojo.User">
    <constructor-arg name="name" value="yth"></constructor-arg>
</bean>
````

总结：在配置文件加载的时候，容器中管理的对象就已经初始化了！

## **五、Spring配置**

1、别名

如果添加了别名，我们也可以使用别名来获取到这个对象

````xml
<alias name="user" alias="userNew"/>
````

2、bean的配置

id: bean 的唯一标识符，也就是相当于我们学的对象名

class: bean对象所对应的全限定名（包名 + 类名）

name: 别名

````xml
<bean id="user" class="com.yin.pojo.User" name="user1,user2"></bean>
````

3、import

这个import，一般用于团队开发使用，它可以将多个配置文件，导入合并为一个

假设，现在项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的beans.xml合并为一个总的

applicationContext.xml

````xml
<import resource="beans.xml"/>
<import resource="beans2.xml"/>
<import resource="beans3.xml"/>
````

使用的时候，直接使用总的配置就可以了

## **六、依赖注入**

1、构造器注入

2、Set方式注入（重点）

依赖注入：Set注入！

​	依赖：bean对象的创建依赖于容器

​	注入：bean对象中的所有属性，由容器来注入

Student类

````java
package com.yin.pojo;

import java.util.*;

public class Student {

    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbys() {
        return hobbys;
    }

    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", hobbys=" + hobbys +
                ", card=" + card +
                ", games=" + games +
                ", info=" + info +
                '}';
    }
}
````

Address类

````java
package com.yin.pojo;

public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
````

applicationContext.xml

````xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="com.yin.pojo.Address" />

    <bean id="student" class="com.yin.pojo.Student">
        <!--    第一种，普通值注入,value-->
        <property name="name" value="尹天豪"></property>
        <!--    第二种，bean注入,ref-->
        <property name="address" ref="address"></property>
        <!--    第三种，数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>
        <!--    第四种，list注入-->
        <property name="hobbys">
            <list>
                <value>听歌</value>
                <value>写代码</value>
                <value>看电影</value>
            </list>
        </property>
        <!--    第五种，Map注入-->
        <property name="card">
            <map>
                <entry key="身份证" value="130402200010123312"></entry>
            </map>
        </property>
        <!--    第六种，set注入-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>CS</value>
            </set>
        </property>
        <!--    第七种，空值注入-->
        <property name="wife">
            <null></null>
        </property>
        <!--    特殊的类型-->
        <property name="info">
            <props>
                <prop key="学号">2019984040504</prop>
            </props>
        </property>

    </bean>

</beans>
````

测试类

````java
import com.yin.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.toString());
    }
}
````

3、拓展方式注入

p命名注入

````xml
需要在beans里添加这一行，才能使用p命名注入
xmlns:p="http://www.springframework.org/schema/p"

<bean id="user" class="com.yin.pojo.User" p:name="水杯" p:age="18">
</bean>
````

c命名注入

````xml
需要在beans里添加这一行，才能使用c命名注入
xmlns:c="http://www.springframework.org/schema/c"

<bean id="user2" class="com.yin.pojo.User" c:age="18" c:name="尹天豪"></bean>
````

## **七、bean的作用域**

````xml
默认是singleton(单例模式)-只创建一个对象
<bean id="user2" class="com.yin.pojo.User" c:age="18" c:name="尹天豪" scope="singleton"></bean>
可选prototype(原型模式)-用一次创建一个对象
<bean id="user2" class="com.yin.pojo.User" c:age="18" c:name="尹天豪" scope="prototype"></bean>
````

## **八、Bean的自动装配**

自动装配是Spring满足bean依赖的一种方式

Spring会在上下文中自动寻找，并自动给bean装配属性

**在Spring中有三种装配方式**

1、在xml中显示的配置

2、在java中显示配置

3、隐式的自动装配bean（重要）

````xml
ByName自动装配
<!--byName: 会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid-->
<bean id="people" class="com.yin.pojo.People" autowire="byName">
    <property name="name" value="xiaomeng"></property>
</bean>
ByType自动装配
<!--byName: 会自动在容器上下文中查找，和自己对象属性类型相同的bean-->
<bean id="people" class="com.yin.pojo.People" autowire="byType">
    <property name="name" value="xiaomeng"></property>
</bean>
````

小结：byname的时候，要保证所有bean的id唯一

​		   bytype的时候，要保证所有bean的类型唯一

**使用注解实现自动装配**

在xml文件中，进行设置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
	<!--开启注解的支持，如果没有这一行，注解就无效-->
    <context:annotation-config/>

</beans>
```

**@Autowired**

直接在类中的属性上使用即可！（也可以放在set方法上）

使用注解可以在类中不写set方法

```java
@Nullable  字段标记了这个注释，说明这个字段可以为null
```

如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解(@Autowired)完成的时候我们可以使用@Qualifier(value="xxx")去配合@Autowired的使用，指定一个唯一的bean对象注入！

```java
public class People {
    @Autowired
    @Qualifier(value="cat111")
    private Cat cat;
    
    @Autowired
    @Qualifier(value="dog222")
    private Dog dog;
}
```

小结：

@Resource和@Autowired的区别：

都是用来自动装配的，都可以放在属性字段上

@Autowired 通过byType的方式实现，而且必须要求这个对象存在

@Resource 默认通过byname的方式实现，如果找不到名字，则通过byType实现，如果两个都找不到，就报错

## **九、使用注解开发**

在Spring4之后，要使用注解开发，必须要保证aop的包导入了

使用注解需要导入context约束，增加注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
	<!--开启注解的支持，如果没有这一行，注解就无效-->
    <context:annotation-config/>

</beans>
```

1、bean

xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!--开启注解的支持，如果没有这一行，注解就无效-->
    <context:annotation-config/>
    <!--指定要扫描的包，这个包下面的注解就会生效-->
    <context:component-scan base-package="com.yin.pojo"/>

</beans>
```

java类

```java
//等价于 <bean id="user" class="com.kuang.pojo.User"/>
//@Component 组件
@Component
public class User {
    public String name = "尹天豪";
}
```

2、属性如何注入

java类

```java
@Component
public class User {
    public String name;

    //相当于<property name="name" value="xiaomeng" />
    @Value("xiaomeng")
    public void setName(String name) {
        this.name = name;
    }
}
```

3、衍生的注解

@Component 有几个衍生注解，我们在web开发中，会按照mvc三层架构分层！

- dao 【@Repository】

- service 【@Service】

- controller 【@Controller】

  这四个注解功能都是一样的，都是代表将某个类注册到Spring中，装配Bean

4、自动装配置

- @Autowired: 自动装配通过类型,名字 如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="xxx")
- @Nullable: 字段标记了这个注解,说明这个字段可以为null
- @Resource: 自动装配通过名字,类型

5、作用域

设置为单例模式

```java
@Scope("singleton")
public class User {
    public String name;

    //相当于<property name="name" value="xiaomeng" />
    @Value("xiaomeng")
    public void setName(String name) {
        this.name = name;
    }
}
```

6、小结

xml与注解：

- xml更加万能，适用于任何场合，维护简单方便
- 注解  不是自己的类使用不了，维护相对复杂

xml与注解最佳实践：

- xml用来管理bean
- 注解只负责完成属性的注入
- 我们在使用的过程中，只需要注意一个问题：必须让注解生效，就需要开启注解的支持

```xml
<!--指定要扫描的包，这个包下的注解就会生效-->
<context:component-scan base-package="com.yin"/>
<context:annotation-config/>
```

## 十、使用java的方式配置Spring

我们现在要完全不使用Spring的xml配置了，全权交给Java来做！

JavaConfig 是Spring的一个子项目，在Spring4之后

yinConfig类（配置类）

```java
package com.yin.config;

import com.yin.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//这个也会被Spring容器托管，注册到容器中，因为他本来就是一个Component
//@Configuration 代表这是一个配置类，就是我们之前看的beans.xml
@Configuration
@ComponentScan("com.yin.pojo")
@Import(yinConfig2.class) //合并yinConfig2这个配置文件
public class yinConfig {

    //注册一个bean，就相当于我们之前写的一个bean标签
    //这个方法的名字，就相当于bean标签中的id属性
    //这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User user() {
        return new User(); //就是返回要注入到bean的对象
    }

}

```

测试类

```java
public class MyTest {
    public static void main(String[] args) {
//如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(yinConfig.class);

        User user = (User) context.getBean("user");

        System.out.println(user.getName());
    }
}
```

User类（实体类）

```java
package com.yin.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("xiaomeng") //属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

这种纯java的配置方式，在SpringBoot中随处可见！

## 十一、代理模式

代理模式的分类：

- 静态代理
- 动态代理

**静态代理**

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人

代理模式的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务交给代理角色！实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理

缺点：

- 一个真实角色就会产生一个代理角色，代码量会翻倍

**动态代理**

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的
- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
  - 基于接口---JDK动态代理
  - 基于类：cglib
  - java字节码实现：javasist

需要了解两个类：Proxy：代理，InvocationHandler：调用处理程序

好处：

- 一个动态代理类代理的是一个接口，一般就是对应的一类业务

ProxyInvocationHandler类，用于自动生成代理类

```java
//我们会用这个类，自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现
        Object result = method.invoke(target,args);
        return result;
    }
}
```

通过Client类进行调用

```java
public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色(用上边的类进行创建得到)
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置要代理的对象
        pih.setTarget(userService);
		
        UserService proxy = (UserService) pih.getProxy();
		//通过代理对象，间接调用真实角色的方法(真实角色==房东角色)
        proxy.add();
    }
}
```

## 十二、AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

AOP在Spring中的作用：提供声明式事务（允许用户自定义切面）

```xml
使用aop时要配置的依赖
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.6.0</version>
</dependency>
```

**使用Spring的API接口**

applicationContext配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--注册bean-->
    <bean id="userservice" class="com.yin.service.UserServiceImpl"/>
    <bean id="log" class="com.yin.log.log"/>
    <bean id="afterlog" class="com.yin.log.AfterLog"/>
    <!--使用原生Spring API接口-->
    <!--配置aop，需要导入aop的约束-->
    <aop:config>
        <!--切入点 expression表达式 execution(要执行的位置！ * * * * *)-->
        <aop:pointcut id="pointcut" expression="execution(* com.yin.service.UserServiceImpl.*(..))"/>
        <!--执行环绕增加！-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterlog" pointcut-ref="pointcut"/>
    </aop:config>

</beans>
```

执行函数前输出的日志

```java
public class log implements MethodBeforeAdvice {
    //method: 要执行的目标对象的方法
    //objects: 参数
    //o: 目标对象
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"的"+method.getName()+"被执行了");
    }
}
```

执行函数后输出的日志

```java
public class AfterLog implements AfterReturningAdvice {

    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回结果为"+o);
    }
}
```

**自定义来实现AOP**

自定义的类

```java
public class DiyPoint {
    public void before() {
        System.out.println("方法执行之前");
    }

    public void after() {
        System.out.println("方法执行之后");
    }
}
```

配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userservice" class="com.yin.service.UserServiceImpl"/>
    <bean id="log" class="com.yin.log.log"/>
    <bean id="afterlog" class="com.yin.log.AfterLog"/>

    <bean id="diy" class="com.yin.diy.DiyPoint"/>

    <aop:config>
        <!--自定义切面-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.yin.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>

</beans>
```



**使用注解实现**

配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="userservice" class="com.yin.service.UserServiceImpl"/>
    <bean id="log" class="com.yin.log.log"/>
    <bean id="afterlog" class="com.yin.log.AfterLog"/>

    <bean id="annotationPointCut" class="com.yin.diy.AnnotationPointCut"/>
<!--    开启注解支持-->
    <aop:aspectj-autoproxy/>
</beans>
```

自定义的类

```java
@Aspect//标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* com.yin.service.UserServiceImpl.*(.. ))")
    public void before() {
        System.out.println("方法执行前");
    }

    @After("execution(* com.yin.service.UserServiceImpl.*(.. ))")
    public void after() {
        System.out.println("方法执行后");
    }

    //在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.yin.service.UserServiceImpl.*(.. ))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

        //执行方法
        Object proceed = jp.proceed();

        System.out.println("环绕后");
    }
}
```

## 十三、整合Mybatis

步骤：

1、导入相关jar包

- junit
- mybatis
- mysql数据库
- spring相关的
- aop织入
- mybatis-spring

```xml
<dependencies>
    
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.1.10.RELEASE</version>
    </dependency>
    
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.47</version>
    </dependency>
    
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.2</version>
    </dependency>
    <!--Spring操作数据库的话，还需要一个spring-jdbc-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.1.10.RELEASE</version>
    </dependency>
    
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.6.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.2</version>
    </dependency>

  </dependencies>
```



2、编写配置文件

3、测试

**回忆mybatis**

1.编写实体类

2.编写核心配置文件

3.编写接口

4.编写Mapper.xml

5.测试

**Mybatis-spring，整合使用Mybatis**

1、编写数据源配置

2、sqlSessionFactory

3、sqlSessionnTemplate

这个配置是基本不变的，就是数据库路径会改变（spring-dao.xml）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--DataSource：使用Spring的数据源替换mybatis的配置-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/yin?useSSL=false"/>
        <property name="username" value="root"/>
        <property name="password" value="101266"/>
    </bean>
    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/yin/mapper/UserMapper.xml"/>
    </bean>

    <!--SqlSessionTemplate: 就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

</beans>
```

4、需要给接口加实现类

```java
public class UserMapperImpl implements UserMapper{

    //我们的所有操作，都使用sqlSession来执行，在原来，现在都使用SqlSessionTemplate;
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

5、将自己写的实现类，注入到Spring中

spring-dao.xml

```xml
<!--SqlSessionTemplate: 就是我们使用的sqlSession-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用构造器注入-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

6、测试使用即可

将多个xml文件，整合到applicationContext.xml中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.yin.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession" />
    </bean>

</beans>
```

测试使用

```java
public class MyTest {
    @Test
    public void test() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMapperImpl userMapper = (UserMapperImpl) context.getBean("userMapper");

        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }
}
```

## 十四、声明式事务

**1、回顾事务**

- 要么都成功，要么都失败
- 事务在项目开发中，十分的重要，涉及到数据的一致性问题
- 确保完整性和一致性

事务的ACID原则：

- 原子性
- 一致性
- 隔离性
- 持久性

**2、spring中的事务管理**

- 声明式事务：AOP
- 编程式事务：需要在代码中，进行事务的管理

为什么需要事务？

- 如果不配置事务，可能存在数据提交不一致的情况下
- 如果我们不在spring中去配置声明式事务，我们就需要在代码中手动配置事务

配置了事务的xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--DataSource：使用Spring的数据源替换mybatis的配置-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/yin?useSSL=false"/>
        <property name="username" value="root"/>
        <property name="password" value="101266"/>
    </bean>
    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/yin/mapper/UserMapper.xml"/>
    </bean>

    <!--SqlSessionTemplate: 就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <!--配置声明式事务-->
    <!--要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 DataSourceTransactionManager 对象-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--结合AOP实现事务的织入-->
    <!--配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--给那些方法配置事务-->
        <tx:attributes>
            <!--所有方法-->
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPointCut" expression="execution(* com.yin.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>

</beans>
```

