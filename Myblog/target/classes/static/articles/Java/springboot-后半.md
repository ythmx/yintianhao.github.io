# 1、整合JDBC使用

首先，在pom.xml文件中，导入相对应的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

在application.yml中，配置所需要的配置，连接的是mybatis数据库

Mysql 8.0+版本，用这个driver-class-name

```yaml
spring:
  datasource:
    username: root
    password: 101266
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
```

创建controller包，在包下创建JDBCController这个实体类

springboot封装好了JdbcTemplate类，里边有所有jdbc用到的方法，只需要调用即可

```java
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，数据库中的东西，怎么获取? Map
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //往数据库中插入数据
    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into mybatis.user(id,name,pwd) values(4,'小孟儿','123456')";
        jdbcTemplate.update(sql);
        return "update ok!";
    }

    //修改数据库中的数据
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        //这是一个预编译sql语句
        String sql = "update mybatis.user set name = ? where id =" + id;
        Object[] objects = new Object[1];
        objects[0] = "yth";
        jdbcTemplate.update(sql,objects);
        return "update ok!";
    }

    //删除数据库中的数据
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from mybatis.user where id =" + id;
        jdbcTemplate.update(sql);
        return "update ok!";
    }

}
```

# 2、整合Mybatis框架

Druid（德鲁伊）简介

是阿里巴巴开源平台上一个数据库连接池实现，结合了C3P0、DBCP等DB池的优点，同时加入了日志监控

在pom.xml中配置依赖

```xml
<!--Druid-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.21</version>
</dependency>
```

在application.yml中进行类型配置

```yaml
spring:
  datasource:
    username: root
    password: 101266
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
```

M：数据和业务（持久层dao、mapper） 

C：交接（业务层service）

V：HTML（视图层controller、servlet）

在pom.xml中，导入响应的依赖

```xml
<!--mybatis，整合用-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>
```

在application.yaml文件中，进行响应的配置

```yaml
spring:
  datasource:
    username: root
    password: 101266
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver

# 整合 mybatis
mybatis:
  type-aliases-package: com.yth.pojo
  mapper-locations: classpath:mybatis/mapper/*.xml
```

创建mapper包，创建UserMapper接口

```java
//这个注解，表示了这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {
    //查询所有User
    List<User> queryUserList();
    //通过id查询User
    User queryUserById(int id);
    //添加user
    int addUser(User user);
    //删除user
    int deleteUser(int id);
    //修改user
    int updateUser(User user);
}
```

在resources下创建mybatis包，创建mapper包，创建UserMapper.xml

注意，xml的名要与mapper包下接口名保持一致

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yth.mapper.UserMapper">

    <select id="queryUserList" resultType="User">
        select * from user
    </select>

    <select id="queryUserById" resultType="User">
        select * from user where id = #{id}
    </select>

    <insert id="addUser" parameterType="User">
        insert into user(id,name,pwd) values(#{id},#{name},#{pwd})
    </insert>

    <update id="updateUser" parameterType="User">
        update user set name = #{name},pwd = #{pwd}
        where id = #{id}
    </update>

    <delete id="deleteUser" parameterType="int">
        delete from user where id = #{id}
    </delete>

</mapper>
```

最后创建UserController类编写对应接口进行调用即可

```java
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> users = userMapper.queryUserList();
        return users;
    }

}
```

总结：

- 导入包
- 配置文件
- mybatis配置
- 编写sql
- service层调用dao层
- controller层调用service层