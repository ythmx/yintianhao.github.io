

# 微服务阶段

javase: OOP

mysql: 持久化

html+css+Vue: 视图

javaweb: 独立开发MVC三层架构的网站了: 原始

ssm: 框架：简化了我们的开发流程，配置也开始较为复杂

**war: tomcat运行**

spring再简化：SpringBoot；微服务架构！

**jar:内嵌tomcat**

服务越来越多：springcloud

# 1、什么是Spring Boot

就是一个javaweb的开发框架，和Spring MVC类似，对比其他javaweb框架的好处，就是简化开发。约定大于配置

spring boot整合了所有的框架 

主要优点

- 为所有Spring开发者更快的入门
- 开箱即用，提供各种默认配置简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求

# 2、什么是微服务架构

是一种架构风格、他要求我们在开发一个应用的时候，这个应用必须构建成一系列小服务的组合；可以通过http的方式进行互通。

**单体应用架构**

是指我们将一个应用中的所有应用服务，都封装在一个应用中

优点：易于开发和测试

**微服务架构**

把每个功能元素独立出来，把独立出来的功能元素动态组合，需要的功能元素才去拿来组合，需要多一些时，可以整合多个功能元素。所以微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

优点：节省了调用资源。每个功能元素的服务都是一个可替换的、可独立升级的软件代码。

# 3、第一个SpringBoot程序

环境：

- jdk1.8
- maven 3.6.1
- springboot 最新版
- idea

官方提供了一个快速生成的网站！idea集成了这个网站

- 直接使用idea创建一个SpringBoot项目即可

首先在pom.xml中，进行相关依赖的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!--有一个父项目-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.10</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>demo1</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo1</name>
    <description>demo1</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <!--web依赖：tomcat，dispatcherServlet，xml...-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--spring-boot-starter 所有的springboot依赖都是使用这个开头的-->
        <!--单元测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <!--打jar包插件-->
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.7.0</version>
            </plugin>
        </plugins>
    </build>

</project>
```

在com.yin.helloworld下创建pojo、dao、controller、service包，都放在与Demo1Application.java相同的包下（约定！！！）

在controller包下，实例化一个HelloController类，编写一个hello接口

```java
package com.yin.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//自动装配：原理！
@RestController
public class HelloController {

    //接口 http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello() {
        //调用业务，接收前端的参数
        return "hello,world";
    }

}
```

最后执行Demo1Application.java类，中的main方法，就可以成功执行（比springMVC少了一堆配置！简单多了！）

**修改端口号**

在application.properties文件，写这个就可以修改端口号

```properties
# 更改项目的端口号
server.port=8081
```

# 4、SpringBoot自动装配原理

在pom.xml中的

- spring-boot-dependencies：核心依赖在父工程中！
- 我们在写或者引入一些SpringBoot依赖的时候，不需要指定版本，就因为有这些版本仓库

启动器

- ```xml
  <!--启动器-->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
  </dependency>
  ```

- 启动器：说白了就是SpringBoot的启动场景
- 比如spring-boot-starter-web，他就会帮我们自动导入web环境所有的依赖！
- springboot会将所有的功能场景，都变成一个个的启动器
- 我们要使用什么功能，就只需要找到相对应的启动器就可以了

主程序

```java
package com.yin.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//程序的主入口，本身就是Spring的一个组件
@SpringBootApplication  //标注这个类是一个SpringBoot的应用
public class Demo1Application {

    public static void main(String[] args) {
        //将SpringBoot应用启动
        SpringApplication.run(Demo1Application.class, args);
    }

}
```

注解

- ```java
  @SpringBootConfiguration	//SpringBoot的配置
  	@Configuration	//spring配置类
  	@Component	//说明他是个spring组件
  
  @EnableAutoConfiguration	//自动配置
  	@AutoConfigurationPackage	//自动配置包
  		@Import({AutoConfigurationPackages.Registrar.class}) //自动配置'包注册'
  	@Import({AutoConfigurationImportSelector.class})  //自动配置导入选择
  
  //获取所有的配置
  List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
  ```

获取候选的配置

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = new ArrayList(SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader()));
        ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).forEach(configurations::add);
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```

META-INF/spring.factories : 自动配置的核心文件

结论：springboot 所有自动装配都是在启动的时候扫描并加载的：spring.factories所有的自动配置类都在这里面，但是不一定生效，要判断条件是否成立，只要导入了对应的start，就有对应的启动器了，有了启动器，我们自动装配就会生效，然后就配置成功了。

**执行步骤**

- springboot在启动的时候，从类路径下/META-INF/spring.factories获取指定的值
- 将这些自动配置的类导入容器，自动配置就会生效，帮我们进行自动配置
- 以前我们需要自动配置的东西，现在springboot帮我们做了
- 整合javaEE，解决方案和自动配置的东西都在 spring-boot-autoconfigure-2.2.0.RELEASE.jar这个包下
- 他会把所有需要导入的组件，以类名的方式返回，这些组件就会被添加到容器
- 容器中也会存在非常多的xxxAutoConfiguration的文件，就是这些类给容器中导入了这个场景需要的所有组件;并自动配置，@Configuration，JavaConfig !
- 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作

# 5、主启动类运行分析

最初认为就是运行了一个main方法，没想到是开启了一个服务

```java
package com.yin.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//程序的主入口，本身就是Spring的一个组件
@SpringBootApplication  //标注这个类是一个SpringBoot的应用
public class Demo1Application {

    public static void main(String[] args) {
        //将SpringBoot应用启动
        SpringApplication.run(Demo1Application.class, args);
    }

}
```

分析该方法主要分为两部分，一部分是SpringApplication的实例化，另一部分是run方法的执行

**SpringApplication**

主要做了以下四件事情

- 推断应用的类型是普通的项目还是Web项目
- 查找并加载所有可用初始化器，设置到initializers属性中
- 找出所有的应用程序监听器，设置到listeners属性中
- 推断并设置main方法的定义类，找到运行的主类

# 6、yaml语法

SpringBoot使用一个全局的配置文件，配置文件名称是固定的

- application.properties
  - 语法结构：key=value
- application.yml
  - 语法结构：key: 空格 value

配置文件的作用：修改SpringBoot自动配置的默认值，因为SpringBoot在底层都给我们自动配置好了

yaml中可以封装对象，使用@Value就不可以

**yaml配置**

```yaml
server:
	port: 8080
```

**xml配置**

```xml
<server>
	<port>8081</port>
</server>
```

**基本语法**

```yaml
# 对空格的要求十分高
# 可以注入到我们的配置类中

# 普通的key-value
name: yth

#对象
student:
  name: yth
  age: 18

#行内写法
student: {name: yth,age: 12}

#数组
pets:
  - cat
  - dog
  - pig

pets: [cat,dog,pig]
```

**通过yaml给属性赋值**

在yaml文件中，属性名称要与实体类中的名称一一对应，否则赋不上值，显示为null

```yaml
person:
  name: xiaomeng
  age: 19
  happy: false
  birth: 2001/09/02
  map: {k1: v1,k2: v2}
  list:
    - code
    - music
    - xiaoyin
  dog:
    name: huanhuan
    age: 3
```

Person实体类

通过@ConfigurationProperties(prefix = "person")进行属性值的注入

```java
package com.yin.helloworld.pojo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix = "person") //可与yaml中的设置好的属性绑定
public class Person {
    private String name;
    private int age;
    private boolean happy;
    private String birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happy=" + happy +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
```

# 7、松散绑定，JSR303数据校验

**松散绑定**

在yaml中写的last-name这个和实体类中的lastName是一样的，这就是松散绑定

**JSR303校验**

这就是我们可以在字段增加一层过滤器验证，可以保证数据的合法性

spring-boot中可以用@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理。

需要先导入依赖

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <version>2.7.2</version>
</dependency>
```

在Person实体类中，给name属性加一个Email校验，如果name输入的不是email则报错

```java
package com.yin.helloworld.pojo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix = "person") //可与yaml中的设置好的属性绑定
@Validated //数据校验
public class Person {
    @Email() //email校验
    private String name;
    private int age;
    private boolean happy;
    private String birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happy=" + happy +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
```

# 8、多环境配置

在application.yaml中，编写如下配置，便可以选择一个yaml文件激活

```yaml
spring:
  profiles:
    active: test #这里选择激活的文件是application-test.yaml文件
```

application-test.yaml，若设置端口为8081，则会开启8081端口

```yaml
server:
  port: 8081
```

# 9、SpringBoot Web开发

**总结**

- 在SpringBoot，我们可以使用以下方式处理静态资源
  - webjars  localhost:8080/webjars/
  - public, static, /**, resources   localhost:8080/
- 优先级:  resources>static（默认）>public

# **10、Thymeleaf模板引擎**

模板引擎有非常多，jsp，freemarker，Thymeleaf。他们的思想都是一样的

模板引擎的作用就是我们来写一个页面模板，比如有些值是动态的，我们写一些表达式。而这些值，我们组装一些数据，把这些数据找到。然后把这个模板和这个数据交给我们的模板引擎，模板引擎按照我们这个数据帮你把表达式解析，填充我们指定的位置，然后把这个数据最终生成一个我们想要的内容给我们写出去。

**要使用Thymeleaf，只需要导入相对应的依赖就可以了，然后将html页面放入到templates目录下即可！就可以在Controller包下写实体类，写接口访问**

首先在pom.xml中进行配置

```xml
<!--导入Thymeleaf-->
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
</dependency>
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-java8time</artifactId>
</dependency>
```

test.htm

```html
<!DOCTYPE html>
<!--使用thymeleaf时，需要在头部加配置   xmlns:th="http://www.thymeleaf.org"-->
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>test</h1>
<!--所有的html元素都可以被 thymeleaf 替换接管  th: 元素名-->
<div th:text="${msg}"></div>
</body>
</html>
```

Thymeleaf 语法

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>test</h1>
<!--所有的html元素都可以被 thymeleaf 替换接管  th: 元素名-->
    <!--展示内容-->
<div th:text="${msg}"></div>
<!--取的msg，users都是从接口处获得的-->
    <!--for循环遍历-->
<div th:each="user:${users}" th:text="${user}"></div>
    
</body>
</html>
```

```java
//接口的代码
@RequestMapping("/test")
public String index(Model model) {
    model.addAttribute("msg","你好！");

    model.addAttribute("users", Arrays.asList("yth","meng"));
    return "test";
}
```

# 11、修改SpringBoot的默认配置

这么多的自动配置，原理都是一样的，通过这个WebMVC的自动配置原理分析，我们要学会一种学习方式，通过源码探究，得出结论

SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的，如果有就用用户配置的，如果没有就用自动配置的。如果有些组件可以存在多个，比如视图解析器，就将用户配置的和自己默认的组合起来！

创建MyMvcConfig类

- 实现WebMvcConfigurer接口
- 重写需要修改的方法（addViewControllers这个是视图跳转）

```java
//如果我们要扩展SpringMVC，官方建议我们这样去做！
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/yin").setViewName("test");
    }
}
```

在springboot中，有非常多的 xxx Configuration 帮助我们进行扩展配置，只要看见了这个东西，我们就要注意它扩展了什么功能！

# 12、员工管理系统：首页配置、国际化

首页配置：（使用@{}）

注意点，所有页面的静态资源都需要使用thymeleaf接管;

- 将需要的html文件，都放入templates文件夹下
- 路径：将html所需的css，img，js文件均放入到static文件夹下，写路径时@{/css/signin.css}，不需要写static，直接写下一级文件夹名称即可
- 在applicationContext.yaml中写入如下配置

```yaml
# 关闭模板引擎的缓存
spring:
  thymeleaf:
    cache: false
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>登陆</title>
      <!-- Bootstrap core CSS -->
       <!--这里的路径都是动态拼接成的-->
      <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
      <!-- Custom styles for this template -->
      <link th:href="@{/css/signin.css}" rel="stylesheet">
   </head>

   <body class="text-center">
      <form class="form-signin" action="dashboard.html">
         <img class="mb-4" th:src="@{/img/bootstrap-solid.svg}" alt="" width="72" height="72">
         <h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
         <input type="text" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
         <input type="password" class="form-control" th:placeholder="#{login.password}" required="">
         <div class="checkbox mb-3">
            <label>
          <input type="checkbox" value="remember-me"> [[#{login.remember}]]
        </label>
         </div>
         <button class="btn btn-lg btn-primary btn-block" type="submit">[[#{login.btn}]]</button>
         <p class="mt-5 mb-3 text-muted">© 2023-2024</p>
         <a class="btn btn-sm" th:href="@{/index.html(l='zh_CN')}">中文</a>
         <a class="btn btn-sm" th:href="@{/index.html(l='en_US')}">English</a>
      </form>

   </body>

</html>
```

修改SpringBoot的默认配置：

- 创建config文件夹，创建MyMvcConfig和MyLocaleResolver两个类

```java
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //重写视图转发器，当请求 / 的时候，转到index.html网站，否则无法加载出来css等资源
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
```

```java
public class MyLocaleResolver implements LocaleResolver {

    //解析请求，完成页面国际化操作，就是可以点击切换显示的语言
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求
        String language = request.getParameter("l");
        //如果没有就使用默认的
        Locale locale = Locale.getDefault();
        //如果请求的链接携带了地区化的参数
        if (!StringUtils.isEmpty(language)) {
            //zh_CN
            String[] split = language.split("_");
            //国家，地区
            locale = new Locale(split[0], split[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
```

完成国际化操作，还需要在resources文件夹下，创建一个i18n文件夹，里边创建以下文件

- login.properties

```properties
login.btn=登陆
login.password=密码
login.remember=记住我
login.tip=请登录
login.username=用户名
```

- login_en_US.properties

```properties
login.btn=Sign in
login.password=password
login.remember=Remember me
login.tip=Please sign in
login.username=Username
```

- login_zh_CN.properties

```properties
login.btn=登陆
login.password=密码
login.remember=记住我
login.tip=请登录
login.username=用户名
```

总结：

页面国际化：（使用#{}）

- 我们需要配置i18n文件
- 我们如果需要在项目中进行按钮自动切换，我们需要自定义一个组件LocaleResolver
- 记得将自己写的组件配置到spring容器

```java
//自定义的国际化组件就生效了
@Bean
public LocaleResolver localeResolver() {
    return new MyLocaleResolver();
}
```

# 13、员工管理系统：登陆功能实现

首先，将登陆界面，点击登陆按钮的跳转链接改为thymeleaf形式

th:action="@{/user/login}"

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>登陆</title>
		<!-- Bootstrap core CSS -->
		<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
		<!-- Custom styles for this template -->
		<link th:href="@{/css/signin.css}" rel="stylesheet">
	</head>

	<body class="text-center">
		<form class="form-signin" th:action="@{/user/login}">
			<img class="mb-4" th:src="@{/img/bootstrap-solid.svg}" alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
			<!--如果msg的值为空，则不显示消息-->
			<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>

			<input type="text" name="username" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
			<input type="password" name="password" class="form-control" th:placeholder="#{login.password}" required="">
			<div class="checkbox mb-3">
				<label>
          <input type="checkbox" value="remember-me"> [[#{login.remember}]]
        </label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">[[#{login.btn}]]</button>
			<p class="mt-5 mb-3 text-muted">© 2023-2024</p>
			<a class="btn btn-sm" th:href="@{/index.html(l='zh_CN')}">中文</a>
			<a class="btn btn-sm" th:href="@{/index.html(l='en_US')}">English</a>
		</form>

	</body>

</html>
```

在controller包下创建实体类LoginController，用于实现上面写的/user/login接口

用户的登陆，只要账号不为空，密码是123456就可以登陆成功

```java
@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        //具体的业务
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            return "redirect:/main.html";
        }else {
            //登陆失败
            model.addAttribute("msg","密码错误!!!");
            return "index";
        }
    }
}
```

return "redirect:/main.html";

这里重定向的main.html，并不是一个html页面，而是修改了SpringBoot的默认方法，将该请求跳转到一个已经存在的静态html页面，并且url显示的是main.html，为了美观

在MyMvcConfig类中进行映射配置

```java
//配置了所有的映射资源
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //重写视图转发器，当请求 / 的时候，转到index.html网站，否则无法加载出来css等资源
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //当请求为/main.html的时候，就跳转到dashboard.html中
        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/test.html").setViewName("list");
    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
```

# 14、员工管理系统：登陆拦截器

创建实体类LoginHandlerInterceptor，用于实现登陆拦截器的功能

 ```java
//实现登陆拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //返回true就是放行，返回false就是不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);

        //登陆成功之后，应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        //不是登陆进来的，不让进入
        if (loginUser == null) {
            request.setAttribute("msg","请先登陆!!!");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

}
 ```

在LoginController类中，修改一下，如果成功登陆的话，提交一个session，用于拦截器判断

```java
@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        //具体的业务
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            //用于实现登陆拦截器
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登陆失败
            model.addAttribute("msg","密码错误!!!");
            return "index";
        }
    }
}
```

最后将写好的LoginHandlerInterceptor类，配置到SpringBoot里

通过MyMvcConfig进行配置

```java
//配置了所有的映射资源
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //重写视图转发器，当请求 / 的时候，转到index.html网站，否则无法加载出来css等资源
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //当请求为/main.html的时候，就跳转到dashboard.html中
        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/test.html").setViewName("list");
    }

    //自定义的国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //配置自定义的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
               .addPathPatterns("/**").excludePathPatterns("/index.html",
                        "/","/user/login","/static/**");
    }
}
```

在网页中，可以使用 [[${session.loginUser}]] 来动态显示loginUser的信息

# 15、员工管理系统：展示员工列表

首先创建一个公共页面，用于抽取dashboard和list页面的公共内容，导航栏和侧栏

这样可以实现代码的复用，修改更加简单方便

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<!--头部导航栏-->
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" th:fragment="topbar">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">[[${session.loginUser}]]</a>
  <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">注销</a>
    </li>
  </ul>
</nav>

<!--侧边栏-->
<nav class="col-md-2 d-none d-md-block bg-light sidebar" th:fragment="sidebar">
  <div class="sidebar-sticky">
    <ul class="nav flex-column">
      <li class="nav-item">
        <a th:class="${active=='main.html'?'nav-link active':'nav-link'}" th:href="@{/main.html}">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
            <polyline points="9 22 9 12 15 12 15 22"></polyline>
          </svg>
          首页 <span class="sr-only">(current)</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file">
            <path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
            <polyline points="13 2 13 9 20 9"></polyline>
          </svg>
          Orders
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart">
            <circle cx="9" cy="21" r="1"></circle>
            <circle cx="20" cy="21" r="1"></circle>
            <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
          </svg>
          Products
        </a>
      </li>
      <li class="nav-item">
        <a th:class="${active=='list.html'?'nav-link active':'nav-link'}" th:href="@{/emps}">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users">
            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
            <circle cx="9" cy="7" r="4"></circle>
            <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
          </svg>
          员工管理
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2">
            <line x1="18" y1="20" x2="18" y2="10"></line>
            <line x1="12" y1="20" x2="12" y2="4"></line>
            <line x1="6" y1="20" x2="6" y2="14"></line>
          </svg>
          Reports
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-layers">
            <polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
            <polyline points="2 17 12 22 22 17"></polyline>
            <polyline points="2 12 12 17 22 12"></polyline>
          </svg>
          Integrations
        </a>
      </li>
    </ul>

    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
      <span>Saved reports</span>
      <a class="d-flex align-items-center text-muted" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus-circle"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line></svg>
      </a>
    </h6>
    <ul class="nav flex-column mb-2">
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
            <polyline points="14 2 14 8 20 8"></polyline>
            <line x1="16" y1="13" x2="8" y2="13"></line>
            <line x1="16" y1="17" x2="8" y2="17"></line>
            <polyline points="10 9 9 9 8 9"></polyline>
          </svg>
          Current month
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
            <polyline points="14 2 14 8 20 8"></polyline>
            <line x1="16" y1="13" x2="8" y2="13"></line>
            <line x1="16" y1="17" x2="8" y2="17"></line>
            <polyline points="10 9 9 9 8 9"></polyline>
          </svg>
          Last quarter
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
            <polyline points="14 2 14 8 20 8"></polyline>
            <line x1="16" y1="13" x2="8" y2="13"></line>
            <line x1="16" y1="17" x2="8" y2="17"></line>
            <polyline points="10 9 9 9 8 9"></polyline>
          </svg>
          Social engagement
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://getbootstrap.com/docs/4.0/examples/dashboard/#">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text">
            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
            <polyline points="14 2 14 8 20 8"></polyline>
            <line x1="16" y1="13" x2="8" y2="13"></line>
            <line x1="16" y1="17" x2="8" y2="17"></line>
            <polyline points="10 9 9 9 8 9"></polyline>
          </svg>
          Year-end sale
        </a>
      </li>
    </ul>
  </div>
</nav>

</html>
```

在list和dashboard中对应的位置进行引用即可

在（）内，进行传递参数的编写

```html
<!--顶部导航栏-->
<div th:replace="~{/commons/commons::topbar}"></div>
<!--侧边栏，传递参数给组件，让被选中的侧边一项高亮-->
				<div th:replace="~{/commons/commons::sidebar(active='main.html')}"></div>
```

创建EmployeeController实体类，实现/emps接口，完成点击用户管理，然后页面跳转的功能

```java
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("msg",employees);
        return "emp/list";
    }
}
```

在list.html页面，通过获取到msg，从而取得employees对象，然后将对象的内容显示出来

```html
<div class="container-fluid">
    <div class="row">
        <!--				侧边栏-->
        <div th:replace="~{/commons/commons::sidebar(active='list.html')}"></div>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <h2>员工列表</h2>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                        <tr align="center">
                            <th>id</th>
                            <th>lastName</th>
                            <th>email</th>
                            <th>gender</th>
                            <th>department</th>
                            <th>birth</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="emp:${msg}" align="center">
                            <td th:text="${emp.getId()}"></td>
                            <td th:text="${emp.getLastName()}"></td>
                            <td th:text="${emp.getEmail()}"></td>
                            <td th:text="${emp.getGender()}==0?'女' : '男'"></td>
                            <td th:text="${emp.department.getDepartmentName()}"></td>
                            <td th:text="${#dates.format(emp.getBirth(),'yyyy-MM-dd')}"></td>
                            <td>
                                <button class="btn btn-sm btn-primary">编辑</button>
                                <button class="btn btn-sm btn-danger">删除</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
```

# 16、员工管理系统：增加员工

在EmployeeController实体类中，进行接口编写

```java
//点击增加员工后，通过get方法，走该方法，打开添加页面，并且加载需要的相关数据
@GetMapping("/emp")
public String toAddpage(Model model) {
    //跳转时，查出所有部门的信息
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments",departments);
    return "emp/add";
}

//填写完员工信息之后，点击提交，走该方法，成功提交，则重定向到员工列表展示页面
@PostMapping("/emp")
public String addEmp(Employee employee) {
    //添加员工的操作
    employeeDao.save(employee);

    return "redirect:/emps";
}
```

编写对应的add界面，用于进行员工的增加操作

```html
<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/docs/4.0/examples/dashboard/ -->
<html lang="en" xmlns:th="http://www.thymeleaf.org">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>员工管理</title>
		<!-- Bootstrap core CSS -->
		<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link th:href="@{/css/dashboard.css}" rel="stylesheet">
		<style type="text/css">
			/* Chart.js */
			
			@-webkit-keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			@keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			.chartjs-render-monitor {
				-webkit-animation: chartjs-render-animation 0.001s;
				animation: chartjs-render-animation 0.001s;
			}
		</style>
	</head>

	<body>
<!--		导航栏，引用dashboard.html中写好的，直接复用，修改方便-->
		<div th:replace="~{/commons/commons::topbar}"></div>

		<div class="container-fluid">
			<div class="row">
<!--				侧边栏-->
				<div th:replace="~{/commons/commons::sidebar(active='list.html')}"></div>

				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<!--添加员工时的表单-->
					<form th:action="@{/emp}" method="post">
						<div class="form-group">
							<label>LastName</label>
							<input type="text" name="lastName" class="form-control" placeholder="小孟儿">
						</div>
						<div class="form-group">
							<label>Email</label>
							<input type="email" name="email" class="form-control" placeholder="1411159144@qq.com">
						</div>
						<div class="form-group">
							<label>Gender</label><br>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" value="1">
								<label class="form-check-label">男</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" value="0">
								<label class="form-check-label">女</label>
							</div>
						</div>
						<div class="form-group">
							<label>department</label>
							<select class="form-control" name="department.id">
								<!--text是显示出来的值，value是后台要用的值-->
								<option th:each="dept:${departments}" th:text="${dept.getDepartmentName()}"
										th:value="${dept.getId()}" ></option>
							</select>
						</div>
						<div class="form-group">
							<label>Birth</label>
							<input type="text" name="birth" class="form-control" placeholder="2020/10/12">
						</div>
						<button type="submit" class="btn btn-primary">添加</button>
					</form>

				</main>
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" th:src="@{/js/jquery-3.2.1.slim.min.js}"></script>
		<script type="text/javascript" th:src="@{/js/popper.min.js}"></script>
		<script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>

		<!-- Icons -->
		<script type="text/javascript" th:src="@{/js/feather.min.js}"></script>
		<script>
			feather.replace()
		</script>

		<!-- Graphs -->
		<script type="text/javascript" th:src="@{/js/Chart.min.js}"></script>
		<script>
			var ctx = document.getElementById("myChart");
			var myChart = new Chart(ctx, {
				type: 'line',
				data: {
					labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
					datasets: [{
						data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
						lineTension: 0,
						backgroundColor: 'transparent',
						borderColor: '#007bff',
						borderWidth: 4,
						pointBackgroundColor: '#007bff'
					}]
				},
				options: {
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero: false
							}
						}]
					},
					legend: {
						display: false,
					}
				}
			});
		</script>

	</body>

</html>
```

# 17、员工管理系统：修改员工信息

 在list.html中，添加跳转接口 /emp/id 的形式

```html
<a class="btn btn-sm btn-primary" th:href="@{/emp/{empId}(empId=${emp.id})}">编辑</a>
```

在EmployeeController类中，编写对应的接口

```java
//点击编辑员工后，跳转到员工的修改页面
@GetMapping("/emp/{id}")
public String toUpdateEmp(@PathVariable("id") Integer id,Model model) {
    //查出原来的数据
    Employee employee = employeeDao.getEmployeeById(id);
    model.addAttribute("emp",employee);

    //查出所有部门的信息
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments",departments);

    return "emp/update";
}
```

创建对应的update.html在templates文件夹下的emp文件夹中

由于保存修改之后的员工的操作与增加员工后保存的操作一致，所以直接调用之前的接口即可  （/emp 接口）

```html
<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/docs/4.0/examples/dashboard/ -->
<html lang="en" xmlns:th="http://www.thymeleaf.org">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>员工管理</title>
		<!-- Bootstrap core CSS -->
		<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link th:href="@{/css/dashboard.css}" rel="stylesheet">
		<style type="text/css">
			/* Chart.js */
			
			@-webkit-keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			@keyframes chartjs-render-animation {
				from {
					opacity: 0.99
				}
				to {
					opacity: 1
				}
			}
			
			.chartjs-render-monitor {
				-webkit-animation: chartjs-render-animation 0.001s;
				animation: chartjs-render-animation 0.001s;
			}
		</style>
	</head>

	<body>
<!--		导航栏，引用dashboard.html中写好的，直接复用，修改方便-->
		<div th:replace="~{/commons/commons::topbar}"></div>

		<div class="container-fluid">
			<div class="row">
<!--				侧边栏-->
				<div th:replace="~{/commons/commons::sidebar(active='list.html')}"></div>

				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<!--添加员工时的表单-->
					<form th:action="@{/emp}" method="post">
						<!--通过隐藏域携带上id-->
						<input type="hidden" name="id" th:value="${emp.getId()}">
						<div class="form-group">
							<label>LastName</label>
							<input type="text" name="lastName" class="form-control" th:value="${emp.getLastName()}">
						</div>
						<div class="form-group">
							<label>Email</label>
							<input type="email" name="email" class="form-control" th:value="${emp.getEmail()}">
						</div>
						<div class="form-group">
							<label>Gender</label><br>
							<div class="form-check form-check-inline">
								<input th:checked="${emp.getGender()==1}" class="form-check-input" type="radio" name="gender" value="1">
								<label class="form-check-label">男</label>
							</div>
							<div class="form-check form-check-inline">
								<input th:checked="${emp.getGender()==0}" class="form-check-input" type="radio" name="gender" value="0">
								<label class="form-check-label">女</label>
							</div>
						</div>
						<div class="form-group">
							<label>department</label>
							<!--这里要传department.id!!因为下边option里提交的是id，所以这里也是对应id-->
							<select class="form-control" name="department.id">
								<!--text是显示出来的值，value是后台要用的值-->
								<option th:selected="${dept.getId()==emp.getDepartment().getId()}"
										th:each="dept:${departments}"
										th:text="${dept.getDepartmentName()}"
										th:value="${dept.getId()}" ></option>
							</select>
						</div>
						<div class="form-group">
							<label>Birth</label>
							<input type="text" name="birth" class="form-control" th:value="${#dates.format(emp.getBirth(),'yyyy/MM/dd')}">
						</div>
						<button type="submit" class="btn btn-primary">修改</button>
					</form>

				</main>
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript" th:src="@{/js/jquery-3.2.1.slim.min.js}"></script>
		<script type="text/javascript" th:src="@{/js/popper.min.js}"></script>
		<script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>

		<!-- Icons -->
		<script type="text/javascript" th:src="@{/js/feather.min.js}"></script>
		<script>
			feather.replace()
		</script>

		<!-- Graphs -->
		<script type="text/javascript" th:src="@{/js/Chart.min.js}"></script>
		<script>
			var ctx = document.getElementById("myChart");
			var myChart = new Chart(ctx, {
				type: 'line',
				data: {
					labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
					datasets: [{
						data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
						lineTension: 0,
						backgroundColor: 'transparent',
						borderColor: '#007bff',
						borderWidth: 4,
						pointBackgroundColor: '#007bff'
					}]
				},
				options: {
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero: false
							}
						}]
					},
					legend: {
						display: false,
					}
				}
			});
		</script>

	</body>

</html>
```

# 18、员工管理系统：删除及404处理

首先在，list.html，文件中，给删除按钮，绑定一个接口

```html
<a class="btn btn-sm btn-danger" th:href="@{/delemp/{empId}(empId=${emp.id})}">删除</a>
```

在类中，实现该接口的功能

```java
//点击删除员工后，删除员工信息
@GetMapping("/delemp/{id}")
public String deleteEmp(@PathVariable("id") Integer id) {
    employeeDao.delete(id);
    return "redirect:/emps";
}
```

进行404处理，只需要将写好的404.html文件，放到templates文件夹的error文件夹中即可

当发生404错误的时候，springboot就会直接去访问这个文件，并显示出来

**完成注销操作**

在commons.html文件的头部导航栏中，添加接口去完成注销操作

```html
<a class="nav-link" th:href="@{/user/logout}">注销</a>
```

在LoginController类中，编写对应的接口

```java
//用于实现用户的注销功能
@RequestMapping("/user/logout")
public String logout(HttpSession session) {
    //销毁当前创建的session
    session.invalidate();
    return "redirect:/index.html";
}
```

# 19、如何写一个网站

前端：

- 模板：别人写好的，我们拿来改成自己需要的
- 框架：组件：自己手动组合拼接！  BootStrap（[Bootstrap模板_响应式网站模板 - Bootstrap模板库 (bootstrapmb.com)](http://www.bootstrapmb.com/)），Layui
  - 需要了解一下内容（最起码）
  - 栅格系统：BootStrap，Layui都是将屏幕分成了12份
  - 导航栏
  - 侧边栏
  - 表单

数据库：

- 设计前端的时候，要思考用什么数据，构建数据表

后端：

- 前端写好后，思考后端如何与之对接
- 后台模板：x-admin

最后：前后端联调测试！


