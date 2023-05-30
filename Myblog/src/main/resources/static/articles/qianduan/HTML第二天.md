**第二天**

三个表，表格，列表，表单

表格：用来展示数据以及让数据显示整齐规范

列表：用来布局可以让页面布局整齐规范

表单：用来收集用户信息

# 一、创建表格

````html
<table>
    <tr>
    	<td>单元格内的文字</td>
    </tr>
</table>
````

1，table用于定义一个表格标签

2，tr标签用于定义表格中的行，必须嵌套在table标签中

3，td标签用于定义表格中的单元格，必须嵌套在tr标签中

````html
例子
<table>
     <tr> 
          <td>姓名</td>  
          <td>年龄</td>  
          <td>性别</td>  
     </tr>
     <tr> 
          <td>小明</td>  
          <td>18</td>  
          <td>男</td>  
     </tr>
     <tr> 
          <td>张三</td>  
          <td>18</td>  
          <td>男</td>  
     </tr>
</table>
````

# 二、表格属性

border  设置表格的边框(默认border="0"无边框)

cellspacing  设置单元格与单元格边框之间的空白间距

cellpadding  设置单元格内容与单元格边框之间的空白间距

align  设置表格在网页中的水平对齐方式(left，center，right)

width  设置表格的宽度

height  设置表格的高度

# 三、表头单元格标签th

作用：一般表头单元格位于表格的第一行或第一列，并且文本加粗居中

语法：只需要用th标签代替td标签即可

````html
<table border="1" width="500" height="200" align="center" cellpadding="20">
        <tr> 
            <th>姓名</th>  
            <th>年龄</th>  
            <th>性别</th>  
        </tr>
        <tr> 
            <td>小明</td>  
            <td>18</td>  
            <td>男</td>  
        </tr>
        <tr> 
            <td>张三</td>  
            <td>18</td>  
            <td>男</td>  
        </tr>
</table>
````

# 四、表格标题caption

````html
<table>
    <caption>我是表格标题</caption>
</table>
````

caption元素定义表格标题，通常这个标题会被居中且显示于表格之上

caption标签必须紧随table标签之后

````html
<table border="1" width="500" height="200" align="center">
        <caption>人口信息表</caption>
        <tr> 
            <th>姓名</th>  
            <th>年龄</th>  
            <th>性别</th>  
        </tr>
        <tr> 
            <td>小明</td>  
            <td>18</td>  
            <td>男</td>  
        </tr>
        <tr> 
            <td>张三</td>  
            <td>18</td>  
            <td>男</td>  
        </tr>
</table>
````

# 五、合并单元格

1，合并单元格的两种方式

跨行合并：rowspan="合并单元格的个数"

跨列合并：colspan="合并单元格的个数"

合并单元格的顺序按照**先上后下先左后右**的顺序

2，合并单元格的三步曲

先确定是跨行合并还是跨列合并

根据先上后下先左后右的原则找到目标单元格，然后写上合并方式和要合并的单元格的数量

````html
<td colspan="3"> </td>
````

删除多余的单元格

````html
例子
<table border="1" width="500" height="200" align="center">
        <caption> <h3>小说排行榜</h3> </caption>
        <tr> 
            <th>排名</th>  
            <th>关键词</th>  
            <th>趋势</th>  
            <th>今日搜索</th>  
            <th>最近七日</th>  
            <th>相关链接</th>  
        </tr>
        <tr> 
            <td>1</td>  
            <td>鬼吹灯</td>  
            <td>↓</td>  
            <td>345</td>  
            <!--跨行合并两行-->
            <td rowspan="2">123</td>  
            <td> 
                <a href="#">贴吧</a> 
                <a href="#">图片</a>
                <a href="#">百科</a>
            </td>  
        </tr>
        <tr> 
            <td>2</td>  
            <td>盗墓笔记</td>  
            <td>↓</td>  
            <td>124</td>  
            <!-- <td>675432</td>   删除多余单元格-->
            <td> 
                <a href="#">贴吧</a> 
                <a href="#">图片</a>
                <a href="#">百科</a>
            </td> 
        </tr>
</table>
````

# 六、列表标签

列表是用来布局的，他的最大的特点就是整齐、整洁、有序，跟表格类似，但是他可组合自由度更高

1，无序列表ul（常用！）

无序列表的各个列表项之间没有顺序级别之分，是并列的

````html
<ul>
    <li>列表项1</li>
    <li>列表项1</li>
    <li>列表项1</li>
    .......
</ul>

例子
<h2>1.无序列表</h2>
    你喜欢的水果？
<ul>
        <li>香蕉</li>
        <li>苹果</li>
        <li>榴莲</li>
</ul>

注意
1，<ul></ul>中只能嵌套<li></li>
2，<li></li>之间相当于一个容器，可以容纳所有的元素
````

2，有序列表ol

有序列表即为有排列顺序的列表，其各个列表项按照一定的顺序排列定义

````html
<ol>
    <li>列表项1</li>
    <li>列表项1</li>
    <li>列表项1</li>
    .......
</ol>

例子
<h2>2.有序列表</h2>
    排行榜
<ol>
        <li>小孟</li>
        <li>小轩</li>
        <li>小尹</li>
        <li>老屁</li>
</ol>
````

3，自定义列表

定义列表常用于对术语或名词进行解释和描述

````html
<dl>
    <dt>名词1</dt>
    <dd>名词1解释1</dd>
    <dd>名词1解释2</dd>
    ......
    <dt>名词2</dt>
    <dd>名词2解释1</dd>
    <dd>名词2解释2</dd>  
    ......
</dl>

例子
<h2>3.自定义列表</h2>
地区:
<dl>
    <dt>北京</dt>
    <dd>昌平区</dd>
    <dd>海淀区</dd>
    <dd>大兴区</dd>

    <dt>山东</dt>
    <dd>威海</dd>
    <dd>济南</dd>
    <dd>青岛</dd>
</dl>
````

# 七、表单标签

作用：

表单的目的是为了收集用户的信息，在我们的网页中，我们也需要跟用户进行交互，收集用户资料，此时也需要表单

在HTML中，一个完整的表单通常由表单控件（表单元素）、提示信息和表单域三个部分构成

表单域：相当于一个容器，用来容纳所有的表单控件和提示信息，可以通过他定义处理表单数据所用程序的url地址，以及数据提交到服务器的方法。如果不定义表单域，表单中的数据就无法传送到后台服务器。

1，input控件

````html
<input type="属性值" value="你好">
````

type的属性：

text    单行文本输入框

password    密码输入框

radio    单选按钮

checkbox    复选框

button    普通按钮

submit    提交按钮

reset    重置按钮

image    图像形式的提交按钮

file    文件域

value的含义：

表示input控件中的默认文本值

size的含义：

表示input控件在页面中的显示宽度

maxlength的含义：

表示控件允许输入的最多字符数

````html
例子
<!-- text表示是个文本框 -->
用户名： <input type="text" value="请输入用户名" />  <br />
<!-- password表示是个密码框 -->
密码：   <input type="password" /> <br />

单选和复选按钮的例子
性别：  
    男<input type="radio" name="sex" />  
    女<input type="radio" name="sex" /> <br />
爱好：<br />
    <input type="checkbox" name="hobby" /> 睡觉 <br />
    <input type="checkbox" name="hobby" /> 打游戏 <br />
    <input type="checkbox" name="hobby" /> 看电视剧 <br />
    <input type="checkbox" name="hobby" /> 看电影 <br />

提交，重置，图像按钮，文件域等的例子
<!-- 普通按钮需要value值 -->
    <input type="button" value="获取短信验证码"/>
    <input type="submit" />
    <input type="reset" /> <br />
<!-- 图片提交按钮里面必须包含src值 -->
    <input type="image" src="1.jpg" /> <br />
上传头像：
<!-- 文件域 -->
    <input type="file" />
````

2，表单的name属性

name的值，用于后端对数据进行处理时使用

````html
<!-- text表示是个文本框 -->
用户名： <input type="text" value="请输入用户名" name="username" />  <br />
昵称： <input type="text" value="请输入昵称" name="nickname" />  <br />
密码：   <input type="password" />
````

3，checked属性

表示默认选中状态，常见于单选按钮和复选按钮

````html
性别：  
男<input type="radio" name="sex" checked="checked"/>  
女<input type="radio" name="sex" /> <br />
````

# 八、label标签

目标：label标签主要目的是为了提高用户的体验，为用户提高最优秀的服务

概念：label标签为input元素定义标注

作用：用于绑定一个表单元素，当点击label标签的时候，被绑定的表单元素就会获得输入焦点

如何绑定元素？

1，第一种用法就是用label直接包括input表单

````html
<label> 用户名： <input type="radio" name="usernname" value="请输入用户名" /> </label>
````

适合单个表单选择

2，第二种用法就是for属性规定label与哪个表单元素绑定

````html
<label for="sex">男</label>
<input type="radio" name="sex" id="sex" />
````

总结：当我们鼠标点击label标签里面的文字时，光标会定位到指定的表单里面

````html
例子
<h4>label直接包含表单</h4>
<label> 用户名： <input type="text" /> </label> <br />

<h4>通过for和id来控制</h4>
<label for="nc"> 昵称：  </label>  
<input type="text" id="nc"/>
````

# 九、textarea控件（文本域）

作用：通过textarea控件可以轻松地创建多行文本输入框

常应用于留言板

````html
<textarea cols="每行中的字符数" rows="显示的行数">
	文本内容
</textarea>
````

# 十、下拉列表select

````html
<select>
    <option>选项1</option>
    <option>选项1</option>
    <option>选项1</option>
    <option>选项1</option>
    ........
</select>
````

注意

1，select标签中至少应包含一对option标签

2，在option标签中定义selected="selected"时，当前项即为默认选中项

````html
例子

籍贯：
<select>
    <option>--请选择省份--</option>
    <option>北京</option>
    <option>天津</option>
    <option selected="selected">邯郸</option>
    <option>石家庄</option>
</select>
````

# 十一、form表单域

目的：在HTML中，form标签被用于定义表单域，以实现用户信息的收集和传递，form中的所有内容都会被提交给服务器

````html
<form action="url地址" method="提交方式" name="表单名称">
    各种表单控件
</form>
````

action    用于指定接收并处理表单数据的服务器程序的url地址

method    用于设置表单数据的提交方式，其取值为get或post

name    用于指定表单的名称，以区分同一个页面中的多个表单

````html
例子
<form action="" method="post">
    用户名： <input type="text" name="username"/> <br />
    密码：  <input type="password" name="pwd"/> <br />
    <input type="submit" />
    <input type="reset" />
</form>
````

# 十二、综合案例（注册表格）

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>练习页面</title>
    <style>
        tr {
            height: 40px;
        }
    </style>
</head>
<body>
    
    <table width="600" align="center">
        <caption> <h4 style="color: pink;">青春不常在，抓紧谈恋爱</h4> </caption>
        <!-- 第一行 -->
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" checked="checked"/> 男
                <input type="radio" name="sex"/> 女
            </td>
        </tr>
        <!-- 第二行 -->
        <tr>
            <td>生日</td>
            <td>
                <!-- 年 -->
                <select>
                    <option>--请选择年--</option>
                    <option>1995</option>
                    <option>1996</option>
                    <option>1997</option>
                    <option>1998</option>
                </select>

                <!-- 月 -->
                <select>
                    <option>--请选择月--</option>
                    <option>9</option>
                    <option>10</option>
                </select>

                <!-- 日 -->
                <select>
                    <option>--请选择日--</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </td>
        </tr>
        <!-- 第三行 -->
        <tr>
            <td>所在地区</td>
            <td>
                <input type="text" value="邯郸" style="color: gray;"/>
            </td>
        </tr>
        <!-- 第四行 -->
        <tr>
            <td>婚姻状况</td>
            <td>
                <input type="radio" name="marry" checked="checked"/>未婚
                <input type="radio" name="marry"/>离婚
                <input type="radio" name="marry"/>丧偶
            </td>
        </tr>
        <!-- 第五行 -->
        <tr>
            <td>学历</td>
            <td>
                <input type="text" value="大学本科" style="color: gray;"/>
            </td>
        </tr>
        <!-- 第六行 -->
        <tr>
            <td>月薪</td>
            <td>
                <input type="text" value="5000-10000" style="color: gray;"/>
            </td>
        </tr>
        <!-- 第七行 -->
        <tr>
            <td>手机号</td>
            <td>
                <input type="text" value=""/>
            </td>
        </tr>
        <!-- 第八行 -->
        <tr>
            <td>昵称</td>
            <td>
                <input type="text" value="小孟同学" style="color: gray;"/>
            </td>
        </tr>
        <!-- 第九行 -->
        <tr>
            <td>喜欢类型</td>
            <td>
                <input type="checkbox" name="love"/> 妩媚 
                <input type="checkbox" name="love"/> 柔美
                <input type="checkbox" name="love"/> 可爱
                <input type="checkbox" name="love"/> 小鲜肉
                <input type="checkbox" name="love"/> 型男
                <input type="checkbox" name="love"/> 气质
            </td>
        </tr>
        <!-- 第十行 -->
        <tr>
            <td>自我介绍</td>
            <td>
                <textarea>
                    我是......
                </textarea>
            </td>
        </tr>
        <!-- 第十一行 -->
        <tr>
            <!-- 美观，令左侧单元格为空 -->
            <td></td>
            <td>
                <form>
                    <input type="submit" />
                </form>
            </td>
        </tr>
         <!-- 第十二行 -->
         <tr>
            <!-- 美观，令左侧单元格为空 -->
            <td></td>
            <td>
                <input type="checkbox" name="agree" checked="checked" /> 我同意注册条款和会员加入标准
            </td>
        </tr>
        <!-- 第十三行 -->
        <tr>
            <!-- 美观，令左侧单元格为空 -->
            <td></td>
            <td>
                <h3>我承诺</h3>
                <ul>
                    <li>年满18、单身</li>
                    <li>抱着严肃的态度</li>
                    <li>真诚寻找另一半</li>
                </ul>
            </td>
        </tr>

    </table>

</body>
</html>
````

# 十三、查文档

W3C：http://www.w3school.com.cn/

MDN:  https://developer.mozilla.org/zh-CN/