## SpringMVC

ssm: mybatis + Spring + SpringMVC  **MVC三层架构**

SSM = javaWeb做项目

Spring: IOC 和 AOP

SpringMVC: SpringMVC的执行流程，SSM框架整合

MVC: 模型（dao，service） 视图（jsp） 控制器（Servlet）

dao: 连接数据库

service: 调用dao层，并且执行具体的业务

servlet: 接收前端的数据，如果数据要处理就交给service层进行处理，接收service返回的数据，并跳转

转发和重定向（注意区别）

![img](https://img-blog.csdnimg.cn/20200623191748796.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzQ1ODk5MDEz,size_16,color_FFFFFF,t_70)

![img](https://img-blog.csdnimg.cn/20200623191249380.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzQ1ODk5MDEz,size_16,color_FFFFFF,t_70)

Model（模型）：数据模型，提供要展示的数据，可以认为是领域模型或JavaBean组件

View（视图）：负责进行模型的展示，一般就是我们见到的用户界面

Controller（控制器）：接收用户请求，进行处理，处理完毕后把返回的模型数据返回给视图，由视图负责展示

**回顾servlet**

在pom.xml中，配置所需要的依赖

```xml
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
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.2</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

Spring MVC初识

## **1、Spring MVC的特点：**

- 轻量级，简单易学
- 高效，基于请求响应的MVC框架
- 与Spring兼容性好
- 约定优于配置

## **2、Spring MVC的原理**

（官方原理图）

![image-20230413143620677](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20230413143620677.png)

这是狂神做的原理图

![image-20230413153753740](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20230413153753740.png)

**SpringMVC流程**

1、 用户发送请求至前端控制器DispatcherServlet。

2、 DispatcherServlet收到请求调用HandlerMapping处理器映射器。

3、 处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。

4、 DispatcherServlet调用HandlerAdapter处理器适配器。

5、 HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。

6、 Controller执行完成返回ModelAndView。

7、 HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。

8、 DispatcherServlet将ModelAndView传给ViewReslover视图解析器。

9、 ViewReslover解析后返回具体View。

10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。

11、 DispatcherServlet响应用户。

## **3、使用注解开发Spring MVC**

为了防止maven出现资源过滤的问题，需要在maven的pom.xml文件中，进行如下配置

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

小结：实现步骤如下：

- 新建一个web项目
- 导入相关jar包
- 编写web.xml，注册DispatcherServlet
- 编写springmvc配置文件
- 接下来就是去创建对应的控制类，controller
- 最后完善前端视图和controller之间的对应
- 测试运行调试

使用springMVC必须配置的三大件：处理器映射器、处理器适配器、视图解析器

## 4、Controller配置总结

首先配置DispatcherServlet

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--1.配置DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

然后在resources下建立一个springmvc-servlet.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">
	<!-- 处理器映射器和适配器，固定不变 -->
    <context:component-scan base-package="com.yin.controller"/>
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

<!--    <bean name="/t1" class="com.yin.controller.ControllerTest2"/>-->

</beans>
```

在com.yin.controller包下建立实体类ControllerTest2

```java
package com.yin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //代表这个类会被Spring接管
//被这个注解的类，中的所有方法，如果返回值是String，并且有具体页面可以跳转，那么就会被视图解析器解析
public class ControllerTest2 {
    @RequestMapping("/ttt")
    public String test1(Model model) {
        //用于往前台传数据
        model.addAttribute("msg","ControllerTest2");
        return "test";
    }
}
```

启动之后，在url最后输入/ttt即可成功访问到msg

## 5、RESTful风格

Restful就是一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。

**传统方式操作资源**：通过不用的参数来实现不同的效果（只能用post和get）

- http://127.0.0.1/item/queryitem.action?id=1 查询，GET
- http://127.0.0.1/item/saveitem.action 新增，POST

**使用RESTful操作资源**：可以通过不同的请求方式来实现不同的效果

如下，请求地址一样，但是功能可以不同

- http://127.0.0.1/item/1 查询，GET
- http://127.0.0.1/item 新增，POST
- http://127.0.0.1/item 更新，PUT

**练习**

新建一个类RESTfulController

在Spring MVC中可以使用@PathVariable注解，让方法参数的值对应绑定到一个URL模板变量

```java
@Controller
public class RESTfulController {

    //原来的方式
    //http://localhost:8080/springmvc_04_controller_war_exploded/add?a=1&b=2
    //RESTful方式
    //http://localhost:8080/add/a/b
    //@RequestMapping(name = "/add/{a}/{b}",method = RequestMethod.GET)
    //@GetMapping("/add/{a}/{b}")
    //@RequestMapping(name = "/add/{a}/{b}",method = RequestMethod.DELETE)
    //@DeleteMapping("/add/{a}/{b}")
    @RequestMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a,@PathVariable int b, Model model) {
        int res = a + b;

        model.addAttribute("msg","结果为：" + res);

        return "test";
    }

}
```

一共有如下几种注解，这样就可以做到，url相同，但是由于访问方式不同，而进行不同的操作

```java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
```

## 6、重定向和转发

通过SpringMVC来实现转发和重定向（无需视图解析器）

测试前，需要将xml中的视图解析器注释掉

```java
@Controller
public class ModelTest1 {

    @RequestMapping("/m1/t1")
    public String test1(Model model) {
        //重定向
        model.addAttribute("msg","ModelTest1");
        return "redirect:/index.jsp";
    }
}

@Controller
public class ModelTest1 {

    @RequestMapping("/m1/t1")
    public String test1(Model model) {
        //转发
        model.addAttribute("msg","ModelTest1");
        return "forward:/index.jsp";
    }
}
```

有视图解析器

```java
@Controller
public class ModelTest1 {

    @RequestMapping("/m1/t1")
    public String test1(Model model) {
        //转发
        model.addAttribute("msg","ModelTest1");
        //只写jsp的名，其他部分都由视图解析器拼接完成
        return "index";
    }
}

@Controller
public class ModelTest1 {

    @RequestMapping("/m1/t1")
    public String test1(Model model) {
        //重定向
        model.addAttribute("msg","ModelTest1");
        return "redirect:/index.jsp";
    }
}
```

## 7、数据处理和数据回显

**SpringMVC：数据处理**

1、提交的域名称和处理方法的参数名一致

提交数据：http://localhost:8080/hello?name=xiaomeng

处理方法：

```java
@RequestMapping("/hello")
public String hello(String name) {
    System.out.println(name);
    return "hello";
}
```

2、提交的域名称和处理方法的参数名不一致

提交数据：http://localhost:8080/hello?username=xiaomeng

处理方法：

```java
@RequestMapping("/hello")
public String hello(@RequestParam("username") String name) {
    System.out.println(name);
    return "hello";
}
```

3、提交的是一个对象

只要保证有一个该对象的实体类，名称类型均一致，便可以直接用对象当参数

User实体类

```java
@Data  //get和set方法
@AllArgsConstructor //有参构造
@NoArgsConstructor  //无参构造
public class User {
    private int id;
    private String name;
    private int age;
}
```

处理方法

```java
@GetMapping("/user/t2")
public String test2(User user) {
    System.out.println(user);
    return "test";
}
```

如果使用对象的话，前端传递的参数名和对象名必须一致，否则就是null

**数据显示到前端**

1、通过Model

```java
@GetMapping("/user/t1")
public String test1(@RequestParam("username") String name, Model model) {
    //接收前端参数
    System.out.println("接收到前端的参数为：" + name);
    //将返回的结果传输给前端
    model.addAttribute("msg",name);
    //视图跳转
    return "test";
}
```

2、通过ModelAndView

```java
public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    ModelAndView mv = new ModelAndView();

    mv.addObject("msg","ControllerTest1");
    mv.setViewName("test");

    return mv;
}
```

## 8、解决乱码问题

在SpringMVC中，给我们提供了一个过滤器，可以在web.xml中进行配置

```xml
<!--配置SpringMVC的乱码过滤器，解决乱码问题-->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

## 9、JSON学习

前后端分离：

后端部署后端，提供接口，提供数据

​					json

前端独立部署，负责渲染后端的数据

**什么是JSON？**（相当于Java的一个字符串对象）

JSON是一种轻量级的数据交换格式，目前使用特别广泛

采用完全独立于编程语言的文本格式来存储和表示数据

- JSON是JavaScript对象的字符串表示法，它使用文本表示一个JS对象的信息，本质是一个字符串
- 要实现从JSON字符串转换为JavaScript对象，使用JSON.parse()方法
- 要实现从JavaScript对象转换为JSON字符串，使用JSON.stringify()方法

## 10、Controller返回JSON对象

Jackson是目前比较好的json解析工具

使用它需要导入它的jar包

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.14.1</version>
</dependency>
```

在web.xml文件中，进行配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--1.配置DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--配置SpringMVC的乱码过滤器，解决乱码问题-->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>
```

在springmvc-servlet.xml中进行配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--自动扫描指定的包，下面所有注解类都交给IOC容器管理-->
    <context:component-scan base-package="com.yin.controller"/>
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>

    <!--JSON乱码问题配置-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```

在实体类UserController中，进行返回JSON对象的操作

```java
@RestController //他就不会走视图解析器，会直接返回一个字符串
public class UserController {

    @RequestMapping("/j1")
    public String json1() throws JsonProcessingException {
        //jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //创建一个对象
        User user = new User("尹天豪",22,"男");

        String str = mapper.writeValueAsString(user);

        return str;
    }
}
```

**fastjson的使用**

fastjson三个主要的类

- JSONObject 代表 json 对象
- JSONArray 代表 json 对象数组
- JSON 代表 JSONObject和JSONArray的转化

在pom.xml文件中，加入依赖

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.83</version>
</dependency>
```

在实体类中调用

```java
@RestController //他就不会走视图解析器，会直接返回一个字符串
public class UserController {

    @RequestMapping("/j4")
    public String json4() throws JsonProcessingException {
        List<User> list = new ArrayList<User>();

        //创建一个对象
        User user1 = new User("尹天豪",22,"男");
        User user2 = new User("尹天豪",22,"男");
        User user3 = new User("尹天豪",22,"男");
        User user4 = new User("尹天豪",22,"男");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
		
        //返回JSON对象
        return JSON.toJSONString(list);
    }
}
```

## 11、SSM项目整合

**整合Mybatis层**

在新建的工程的pom.xml文件中，导入需要的依赖

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
    <!--Junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <!--数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>
    <!-- 数据库连接池 -->
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.2</version>
    </dependency>
    <!--Servlet - JSP -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!--Mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>
    <!--Spring-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.9.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.9.RELEASE</version>
    </dependency>
</dependencies>
```

再配置上，防止静态资源导出问题的配置

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

配置databsse.propereties文件，用于链接数据库

```properties
jdbc.driver=com.mysql.jdbc.Driver
# MySQL8.0+ requires a time zone setting
jdbc.url=jdbc:mysql://localhost:3306/books?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=101266
```

在pojo包下创建实体类Books，属性与数据库表中属性保持一致，名称也尽量保持一致

在dao包下创建BookMapper接口和BookMapper.xml配置文件

在service包下创建BookService接口和BookServiceImpl实体类调用dao的BookMapper接口中的方法

**整合Spring层**

创建spring-dao.xml用于整合dao层

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!--关联数据库配置文件-->
    <context:property-placeholder location="classpath:database.properties"/>

    <!--连接池
        dbcp: 平自动化操作 , 不能自动连接
        c3p0: 自动化操作（自动化的加载配置文件，并且可以自动设置到对象中）
        druid: hikari
    -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!-- c3p0连接池的私有属性 -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!-- 关闭连接后不自动commit -->
        <property name="autoCommitOnClose" value="false"/>
        <!-- 获取连接超时时间 -->
        <property name="checkoutTimeout" value="10000"/>
        <!-- 当获取连接失败重试次数 -->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定Mybatis的配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!--配置dao接口扫描包，动态的实现Dao接口可以注入到Spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入sqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--要扫描的dao包-->
        <property name="basePackage" value="com.yin.dao"/>
    </bean>

</beans>
```

创建spring-service.xml用于整合service层

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!--扫描service下的包-->
    <context:component-scan base-package="com.yin.service"/>

    <!--将我们的所有业务类，注入到Spring，可以通过配置或者注解实现-->
    <bean id="BookServiceImpl" class="com.yin.service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!--声明式事务配置-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--注入数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--aop事务支持-->
</beans>
```

**整合springMVC层**

首先，给该maven项目添加web配置，在web.xml进行配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--DispatcherServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--配置SpringMVC的乱码过滤器，解决乱码问题-->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--Session-->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

</web-app>
```

创建spring-mvc.xml文件，进行配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--注解驱动-->
    <mvc:annotation-driven/>
    <!--静态资源过滤-->
    <mvc:default-servlet-handler/>
    <!--扫描包: controller-->
    <context:component-scan base-package="com.yin.controller"/>
    <!--视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/" />
        <!-- 后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```

将上述三个配置文件，整合到applicationContext.xml配置文件中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="classpath:spring-dao.xml"/>
    <import resource="classpath:spring-service.xml"/>
    <import resource="classpath:spring-mvc.xml"/>

</beans>
```

## 12、添加查询书籍功能

首先，创建BookController这个实体类

```java
@Controller
@RequestMapping("/book")
public class BookController {
    //controller 调用 service 层
    @Autowired
    @Qualifier("BookServiceImpl") //作用是排除掉不匹配的Bean，qualifier 是限制，是约束，是把范围变小
    private BookService bookService;

    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();

        model.addAttribute("list",list);

        return "allBook";
    }
}
```

在WEB-INF的jsp包下创建对应的allBook.jsp

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示页面</title>

    <!--BootStrap美化界面-->
    <!-- 新 Bootstrap 核心 CSS 文件 -->

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表 ----- 显示所有书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                    </tr>
                </thead>
                <%--书籍从数据库中查询出来，从这个list中遍历出来 foreach--%>
                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
```

美化一下默认界面index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>

    <style>
      a {
        text-decoration: none;
        color: black;
        font-size: 18px;
      }

      h3 {
        width: 180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background-color: aquamarine;
        border-radius: 5px;
      }
    </style>

  </head>
  <body>

  <h3>
      <a href="${pageContext.request.contextPath}/book/allBook">进入书籍展示页面</a>
  </h3>

  </body>
</html>
```

## 13、增加添加书籍功能

在前端页面添加一个按钮，用于添加书籍

```jsp
<div class="row">
    <div class="col-md-4 column">
        <%--点击跳转到增加图书页面， toAddBook--%>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
    </div>
</div>
```

在BookController类中，填写对应接口

```java
//跳转到增加书籍页面
@RequestMapping("/toAddBook")
public String toAddPaper() {
    return "addBook";
}
```

创建并完成对应的addBook.jsp页面

注意！input标签中的name属性的值，要与Books类中属性的值相对应

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加书籍</title>

    <!--BootStrap美化界面-->
    <!-- 新 Bootstrap 核心 CSS 文件 -->

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/addBook" method="post">
        <div class="form-group">
            <label for="bkname">书籍名称：</label>
            <input type="text" name="bookName" class="form-control" id="bkname" required>
        </div>
        <div class="form-group">
            <label for="bkcount">书籍数量：</label>
            <input type="text" name="bookCounts" class="form-control" id="bkcount" required>
        </div>
        <div class="form-group">
            <label for="bkdetail">书籍描述：</label>
            <input type="text" name="detail" class="form-control" id="bkdetail" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="添加">
        </div>
    </form>

</div>

</body>
</html>
```

在BookController类中，完成表单提交对应的addBook接口

完成添加操作后，重定向到了查询所有书籍的页面

```java
//添加书籍的请求
@RequestMapping("/addBook")
public String addBook(Books books) {
    System.out.println("addBook=" + books);
    bookService.addBook(books);
    return "redirect:/book/allBook";
}
```

## 14、增加修改删除书籍的功能

首先在allBook.jsp中，添加修改和删除按钮

```jsp
<td>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toUpdateBook?id=${book.bookID}">修改</a>
                                &nbsp; | &nbsp;
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                            </td>
```

在BookController类中，完成修改书籍接口的编写

```java
//跳转到修改书籍页面
@RequestMapping("/toUpdateBook")
public String toUpdatePaper(int id,Model model) {
    Books books = bookService.queryBookById(id);
    model.addAttribute("QBooks",books);
    return "updateBook";
}

//修改书籍
@RequestMapping("/updateBook")
public String updateBook(Books books) {
    //System.out.println("updateBook=>" + books);
    bookService.updateBook(books);
    return "redirect:/book/allBook";
}
```

创建对应的修改书籍页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>

  <!--BootStrap美化界面-->
  <!-- 新 Bootstrap 核心 CSS 文件 -->

  <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="page-header">
        <h1>
          <small>修改书籍</small>
        </h1>
      </div>
    </div>
  </div>

  <form action="${pageContext.request.contextPath}/book/updateBook" method="post">

    <%--出现的问题，提交sql请求但是失败，由于id未被后台获得，所以sql执行失败--%>
    <%--通过传递隐藏域来解决--%>
      <input type="hidden" name="bookID" value="${QBooks.bookID}">

      <div class="form-group">
        <label for="bkname">书籍名称：</label>
        <input type="text" name="bookName" class="form-control" id="bkname" value="${QBooks.bookName}" required>
      </div>

      <div class="form-group">
        <label for="bkcount">书籍数量：</label>
        <input type="text" name="bookCounts" class="form-control" id="bkcount" value="${QBooks.bookCounts}" required>
      </div>

      <div class="form-group">
        <label for="bkdetail">书籍描述：</label>
        <input type="text" name="detail" class="form-control" id="bkdetail" value="${QBooks.detail}" required>
      </div>

      <div class="form-group">
        <input type="submit" class="form-control" value="修改">
      </div>
  </form>

</div>

</body>
</html>
```

在BookController类中，完成删除书籍接口的编写

```java
//删除书籍
@RequestMapping("/deleteBook/{bookId}")
public String deleteBook(@PathVariable("bookId") int id) {
    bookService.deleteBookById(id);
    return "redirect:/book/allBook";
}
```

## 15、Ajax技术

是一种无需重新加载整个网页的情况下，能够更新部分网页的技术

是一种用于创建更好更快以及交互性更强的Web应用程序的技术

- 传统的网页 （不使用ajax），想要更新内容，需要重新加载整个网页

- 使用ajax技术的网页，通过在后台服务器进行少量的数据交换，就可以实现异步局部更新

  ​																						       **ajax作用大致原理图**

![image-20230418092301405](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20230418092301405.png)

```jsp
jQuery.ajax(...)
	部分参数:
		url: 请求地址
		data: 要发送的数据
		success: 成功之后执行的回调函数(全局)
		error: 失败之后执行的回调函数(全局)
```

要了解：js

- 基本语法
- 函数： 闭包
- Dom
  - id,name,tag
  - create,remove
- Bom
  - window
  - document

ES6: import require

**模拟，点击输入框，ajax创建一个异步请求的操作**

index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.4.js"></script>

    <script>
      function a() {
        $.post({
          url:"${pageContext.request.contextPath}/a1",
          data:{"name":$("#username").val()},
          success: function (data) {
              alert(data);
          }
        })
      }
    </script>

  </head>
  <body>

  <!--失去焦点的时候，发起一个请求到后台-->
  用户名: <input type="text" id="username" onblur="a()">

  </body>
</html>
```

由AjaxController这个实体类，通过java后台，提供一个json对象给前端的ajax使用

```java
package com.yin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//作用：在提供json接口时需要的配置操作再也不需要自己配置了
@RestController
public class AjaxController {

    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse response) throws IOException {
        System.out.println("a1:param==>" + name);
        if ("yth".equals(name)) {
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }
    }

}
```



**Ajax异步加载数据**

test2.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.4.js"></script>

    <script>
        $(function () {
            $("#btn").click(function () {
                //$.post (url, param[可以省略], success)
                $.post("${pageContext.request.contextPath}/a2",function (data) {
                    //console.log(data);
                    var html = "";

                    for (let i = 0; i < data.length; i++) {
                        html += "<tr>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].age + "</td>" +
                            "<td>" + data[i].sex + "</td>" +
                            "</tr>"
                    }

                    $("#content").html(html);
                })
            })
        });
    </script>

</head>
<body>

<input type="button" value="加载数据" id="btn">
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">
        <%--数据: 后台--%>
    </tbody>
</table>

</body>
</html>
```

在AjaxController类中，编写接口a2，提供所需数据即可

```java
package com.yin.controller;

import com.yin.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//作用：在提供json接口时需要的配置操作再也不需要自己配置了
@RestController
public class AjaxController {

    @RequestMapping("/a2")
    public List<User> a2() {
        ArrayList<User> userList = new ArrayList<>();

        //添加数据
        userList.add(new User("yth",22,"男"));
        userList.add(new User("meng",21,"女"));

        return userList;
    }

}
```

