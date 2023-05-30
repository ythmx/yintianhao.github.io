**Servlet规范**

**一、Servlet规范介绍：**

1，servlet规范来自于JavaEE规范中的一种

2，作用

​	1）在Servlet规范中，指定【动态资源文件】开发步骤（给人看的）

​	2）在Servlet规范中，指定Http服务器调用动态资源文件规则

​	3）在Servlet规范中，指定Http服务器管理动态资源文件实例对象规则

**二、Servlet接口实现类：**

1，Servlet接口来自于Servlet规范下一个接口，这个接口存在Http服务器提供jar包

2，Tomcat服务器下lib文件有一个servlet-api.jar存放Servlet接口(javax.servlet.Servlet接口)

3，Servlet规范中认为，Http服务器能调用的【动态资源文件】必须是一个Servlet接口实现类

**三、Servlet接口实现类开发步骤**

1，第一步：创建一个Java类继承HttpServlet父类，使之成为一个servlet接口实现类

2，第二步：重写HttpServlet父类两个方法。doGet或者doPost。

3，第三步：将Servlet接口实现类信息【注册】到Tomcat服务器

​					【网站】--->【web】--->【WEB-INF】---> web.xml

````xml
<!--将Servlet接口实现类类路径地址交给Tomecat-->
<Servlet>
    <!--声明一个变量存储servlet接口实现类类路径-->
	<servlet-name>test</servlet-name>
    <!--声明servlet接口实现类类路径-->
    <servlet-class>com.yth.controller.OneServlet</servlet-class>
</Servlet>
<!--需要设置简短请求别名，方便访问-->
<servlet-mapping>
    <!--这里的变量名要与上边一致，表示给上述变量起别名-->
	<servlet-name>test</servlet-name>
    <!--设置别名-->
    <url-pattern>/one</url-pattern>
</servlet-mapping>
````

**四、Servlet对象生命周期：**

1，网站中所有的Servlet接口实现类的实例对象，只能由Http服务器负责创建。开发人员不能手动创建Servlet接口实现类的实例对象

2，在默认的情况下，Http服务器接收到对于当前Servlet接口实现类第一次请求时，自动创建这个Servlet接口实现类的实例对象

在手动配置情况下，要求Http服务器在启动时自动创建某个Servlet接口实现类的实例对象

````xml
<servlet>
	<servlet-name>test</servlet-name>
    <setvlet-class>com.yth.controller.OneServlet</setvlet-class>
    <!--填写一个大于0的整数即可-->
    <load-on-startup>30</load-on-startup>
</servlet>
````

3，在Http服务器运行期间，一个Servlet接口实现类只能创建出一个实例对象

4，在Http服务器关闭时刻，自动将网站中所有的Servlet对象进行销毁

**五、HttpServletResponse接口**

1，介绍：

​		1）：HttpServletResponse接口来自于Servlet规范中，在Tomcat中存在servlet-api.jar

​		2）：HttpServletResponse接口实现类由Http服务器负责提供

​		3）：HttpServletResponse接口负责将doGet / doPost执行结果写入到响应体中交给浏览器

​		4）：开发人员习惯将HttpServletResponse接口修饰的对象称为【响应对象】

2，主要功能：

​		1）：将执行结果以二进制形式写入到【响应体】

```java
PrintWriter out = response.getWriter();
out.print(result)
```

​		2）：设置响应头中[content-type]属性值，从而控制浏览器使用对应编译器将响应体二进制数据编				  译为【文字，图片，视频，命令】

````java
response.setContentType("text/html");
response.setCharacterEncoding("UTF-8");
````



​		3）：设置响应头中【location】属性，将一个请求地址赋值给location，从而控制浏览器向指定服				  务器发送请求

````java
String result = "http://www.baidu.com";
response.sendRedirect(result);
````

**六、HttpServletRequest接口**

1，介绍

​		1）：HttpServletRequest接口来自于Servlet规范中，在Tomcat中存在servlet-api.jar

​		2）：HttpServletRequest接口实现类由Http服务器负责提供

​		3）：HttpServletRequest接口负责在doGet / doPost方法运行时读取Http请求协议包中信息

​		4）：开发人员习惯于将HttpServletRequest接口修饰的对象称为【请求对象】

2，作用

​		1）：可以读取Http请求协议包中【请求行】信息

````java
String url = request.getRequestURL().toString();

String method = request.getMethod();
````



​		2）：可以读取保存在Http请求协议包中【请求头】或者【请求体】中请求参数信息

（浏览器以GET方式发送请求，请求参数保留在【请求头】，在Http请求协议包到达Http服务器之后，第一件事就是进行解码，请求头二进制内容由Tomcat负责解码，Tomcat默认使用【UTF-8】）

（浏览器以POST方式发送请求，请求参数保留在【请求体】，在Http请求协议包到达Http服务器之后，第一件事就是进行解码，请求头二进制内容由request负责解码，request默认使用【ISO-8859-1】）

````java
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通知请求对象，使用utf-8字符集进行解码
        req.setCharacterEncoding("utf-8");
        //通过请求对象获得【请求体】中【所有请求参数名】
        String value = req.getParameter("username");
        System.out.println("从请求体中得到的参数值：" + value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过请求对象获得【请求头】中【所有请求参数名】
        String userName = request.getParameter("username");
        System.out.println("从请求头得到的参数值：" + userName);
    }
````



​		3）：可以代替浏览器向Http服务器申请资源文件调用

**七、请求对象和响应对象生命周期**

1，在Http服务器接收到浏览器发送的【Http请求协议包】之后，自动为当前的【Http请求协议包】生成一个【请求对象】和一个【响应对象】

2，在Http服务器调用doGet / doPost方法时，负责将【请求对象】和【响应对象】作为实参传递到方法，确保doGet / doPost正确执行

3，在Http服务器准备推送Http响应协议包之前，负责将本次请求关联的【请求对象】和【响应对象】销毁

【请求对象】和【响应对象】的生命周期贯穿一次请求的处理过程中

**八、欢迎资源文件**

1，前提：用户可以记住网站名，但不会记住网站资源文件名

2，默认欢迎资源文件：用户发送了一个针对某个网站的【默认请求】时，此时由Http服务器自动从当前网站返回的资源文件

正常请求：http://localthost:8080/myWeb/index.html

默认请求：http://localhost:8080/myWeb/

3，Tomcat对于默认欢迎资源文件定位规则

​		1）规则位置：Tomcat安装位置 /conf/web.xml

4，设置当前网站的默认欢迎资源文件规则

​		1）规则位置：网站/web/WEB-INF/web.xml

​		2）规则命令：（将login.html设置为默认登陆界面）

````xml
<welcome-file-list>
	<welcome-file>login.html</welcome-file>
</welcome-file-list>
````

​		3）网站设置自定义默认文件定位规则，此时Tomcat自带定位规则将失效

**九、多个servlet之间的调用规则**

1，前提条件

某些来自于浏览器发送的请求，往往需要服务器端中多个servlet协同处理。但是浏览器一次只能访问一个servlet，导致用户需要手动通过浏览器发起多次请求才能得到服务。

这样增加用户获得服务的难度，会导致用户放弃访问当前网站

2，提高用户使用感受规则

无论本次请求涉及到多少个servlet，用户只需要【手动】通知浏览器发起一次请求即可

3，多个servlet之间调用规则

​		1）重定向解决方案

​		2）请求转发解决方案

**十、重定向解决方案**

1，工作原理：

服务器端把其他网站资源文件地址发送给了浏览器端

2，请求次数：

浏览器至少发送两次请求，但是只有一次请求是用户手动发送。后续请求都是浏览器自动发送的

3，请求方式：

重定向解决方案中，通过地址栏通知浏览器发起下一次请求，因此通过重定向解决方案调用的资源文件接收的请求方式一定是【GET】

4，缺点：

重定向解决方案需要在浏览器与服务器之间进行多次往返，大量时间消耗在往返次数上，增加用户等待服务时间

**十一、请求转发解决方案**

1，原理：

用户第一次通过手动方式要求浏览器访问OneServlet。OneServlet工作完毕之后，通过当前的请求对象代替浏览器向Tomcat发送请求，申请调用TwoServlet。Tomcat在接收到这个请求之后，自动调用TwoServlet来完成剩余任务

2，实现命令：

请求对象代替浏览器向Tomcat发送请求

3，优点：

​		1）无论本次请求涉及到多少个Servlet，用户只需要手动通过浏览器发送一次请求

​		2）Servlet之间调用发生在服务端计算机上，节省服务端与浏览器之间往返次数增加处理服务速度

4，特征：

​		1）请求次数

​			在本次请求转发过程中，浏览器只发送一次请求

​		2）请求地址

​			只能向Tomcat服务器申请调用当前网站下资源文件地址

````java
request.getRequestDispather("/资源文件名") //不要写网站名
````



​		3）请求方式

​			在本次请求转发过程中，浏览器只发送一个Http请求协议包。参与本次请求的所有Servlet共享同一个请求协议包，因此，这些Servlet接收的请求方式与浏览器发送的请求方式保持一致。

**十二、多个Servlet之间数据共享实现方案**

1，数据共享：

OneServlet工作完毕后，将产生数据交给TwoServlet来使用

2，在Servlet规范中提供了四种数据共享方案

​		1）ServletContext接口

​		2）Cookie类

​		3）HttpSession接口

​		4）HttpServletRequest接口

**十三、ServletContext接口**

1，介绍：

​		1）来自于Servlet规范中一个接口。在Tomcat中存在servlet-api.jar

​			在Tomcat中负责提供这个接口实现类

​		2）如果两个Servlet来自于同一个网站。彼此之间通过网站的ServletContext实例对象

​			实现数据共享

​		3）开发人员习惯于将ServletContext对象成为【全局作用域对象】

2，工作原理：

​		每一个网站都存在一个全局作用域对象。这个全局作用域对象【相当于】一个Map

​		在这个网站中OneServlet可以将一个数据存入到全局作用域对象，当前网站中其他

​		Servlet此时都可以从全局作用域对象得到这个数据进行使用

3，全局作用域对象生命周期：

​		1）在Http服务器启动过程中，自动为当前网站在内存中创建一个全局作用域对象

​		2）在Http服务器运行期间时，一个网站只有一个全局作用域对象

​		3）在Http服务器运行期间，全局作用域对象一直处于存活状态

​		4）在Http服务器准备关闭时，负责将当前网站中全局作用域对象进行销毁处理

4，命令实现：【同一个网站】OneServlet将数据共享给TwoServlet

````java
OneServlet {
    public void doGet(HttpServletRequser request,HttpServletResponse response) {
        //1。通过【请求对象】向Tomcat索要当前网站中的【全局作用域对象】
        ServletContext application = request.getServletContext();
        
        //2。将数据添加到全局作用域对象作为【共享数据】
        application.setAttribute("key1",数据);
    }
}

TwoServlet {
    public void doGet(HttpServletRequser request,HttpServletResponse response) {
    	//1。通过【请求对象】向Tomcat索要当前网站中的【全局作用域对象】
        ServletContext application = request.getServletContext();
        
        //2。从【全局作用域对象】得到指定关键字对应数据
        Object 数据 = application.getAttribute("key1");
    }
}
````

**十四、HttpServletRequest接口实现数据共享**

1，介绍：

​		1）在同一个网站中，如果两个Servlet之间通过【请求转发】方式进行调用，

​			彼此之间共享同一个请求协议包。而一个请求协议包只对应一个请求对象

​			因此Servlet之间共享同一个请求对象，此时可以利用这个请求对象在两个

​			Servlet之间实习数据共享

​		2）在请求对象实现Servlet之间数据共享功能时，开发人员将请求对象称为【请求作用域对象】

2，命令实现：OneServlet通过请求转发申请调用TwoServlet时，需要给TwoServlet提供共享数据

````java
OneServlet {
    public void doGet(HttpServletRequser request,HttpServletResponse response) {
        //1。将数据添加到【请求作用域对象】中attribute属性
        request.serAttribute("key1",数据); //数据类型是任意的
        
        //2。向Tomcat申请调用TwoServlet
        request.getRequestDispatcher("/two").forward(request,response);
    }
}


TwoServlet {
    public void doGet(HttpServletRequser request,HttpServletResponse response) {
        //从当前请求对象得到OneServlet写入到共享数据
        Object 数据 = request.getAttribute("key1");
    }
}
````

