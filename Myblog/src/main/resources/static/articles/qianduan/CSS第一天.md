**第一天**

# 一、引入CSS样式表

1，行内式（内联样式）

是通过标签的style属性来设置元素的样式

````html
<标签名 style="属性1:属性值1; 属性2:属性值2;"> 内容 </标签名>
````

实际上任何HTML标签都拥有style属性，用来设置行内式

````html
例子
<h1 style="color: pink; font-size: 18px;">世纪佳缘，我在这里等你</h1>
````

2，内部样式表（内嵌样式表）

是将CSS代码集中写在HTML文档的head头部标签中，并且用style标签定义

````html
<head>
    <style type="text/CSS">
        选择器(就是选择的标签) {
            属性1: 属性值1;
            属性2: 属性值2;
            属性3: 属性值3;
        }
    </style>
</head>

<style>
    div{ 
        color: red;
        font-size: 12px;
    }
</style>

<style>
    tr {
        height: 40px;
    }
</style>
````

只能控制当前的页面，缺点是没有彻底分离

3，外部样式表（外链式）

将所有的样式放在一个或多个以.css为扩展名的外部样式表中，

通过link标签将外部样式表文件链接到HTML文档中

````html
<head>
    <link rel="stylesheet" type="text/css" href="css文件路径" >
</head>
````

rel  定义当前文档与被连接文档之间的关系，在这里需要指定为"stylesheet"，表示被链接的文档是一个样式表文件

type  定义所链接文档的类型，在这里指定为"text/css"，表示链接的外部文档为CSS样式表

href  定义所链接外部样式表文件的URL

# 二、CSS选择器

选择器的作用：找到特定的HTML页面元素（元素=标签）

选择标签用的，把我们想要的标签选择出来

选择器分为基础选择器和复合选择器

**基础选择器**

(1) 标签选择器

指用HTML标签名称作为选择器，按标签名称分类，为页面中某一类标签指定统一的CSS样式

````css
标签名 {属性1:属性值1; 属性2:属性值2;}
````

可以把一类标签全部选择出来

(2)类选择器

类选择器使用"."（英文点号）进行标识，后面紧跟类名

````html
类名选择器
.类名 {
	属性1:属性值1;
	属性2:属性值2;
    属性3:属性值3;
}

标签
<p class='类名'></p>

例子
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .red {
            color: red;
        }
    </style>
</head>
<body>
    
    <div>1111</div>
    <div>2222</div>
    <div>3333</div>
    <div>4444</div>
    <div class="red">5555</div>
    
</body>
</html>

例子：谷歌的图标
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .blue {
            font-size: 100px;
            color: blue;
        }

        .red {
            font-size: 100px;
            color: red;
        }

        .orange {
            font-size: 100px;
            color: orange;
        }

        .green {
            font-size: 100px;
            color: green;
        }

    </style>
</head>
<body>
    
    <span class="blue">G</span>
    <span class="red">o</span> 
    <span class="orange">o</span> 
    <span class="blue">g</span> 
    <span class="green">l</span> 
    <span class="red">e</span>  
    
</body>
</html>
````

(3)类选择器特殊用法-多类名

我们可以给标签指定多个类名，从而达到更多的选择目的

````html
例子：谷歌图标
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .font100 {
            font-size: 100px;
        }

        .blue {
            color: blue;
        }

        .red {
            color: red;
        }

        .orange {
            color: orange;
        }

        .green {
            color: green;
        }

    </style>
</head>
<body>
    <!-- 一个标签内部只能有一个class -->
    <span class="blue font100">G</span>
    <span class="red font100">o</span> 
    <span class="orange font100">o</span> 
    <span class="blue font100">g</span> 
    <span class="green font100">l</span> 
    <span class="red font100">e</span>  
    
</body>
</html>
````

(4)id选择器

id选择器使用#进行标识，后面紧跟id名

````html
id选择器
#id名 {属性1:属性值1; 属性2:属性值2;}

标签
<p id="id名"></p>
````

元素的id值是唯一的，只能对应文档中某一个具体的元素

**id选择器和类选择器区别**

类选择器好比人的名字，是可以多次重复使用的

id选择器好比人的身份证号码，是全国唯一的，不得重复，只能使用一次

他们之间最大的不同在于使用次数上

(5)通配符选择器

用*号表示，就是选择所有的标签，他是所有选择器中作用范围最广的，能匹配页面中所有的元素

````css
* {属性1:属性值1; 属性2:属性值2;}

* {
  margin: 0;   /*定义外边距*/
  padding: 0;  /*定义内边距*/
}
````

会匹配页面所有的元素，降低页面响应速度

# 三、CSS字体样式属性调试工具

1，font字体

(1)font-size：大小

用于设置字号大小

````css
p {
  font-size:20px;
}
````

(2)font-family：字体

用于设置哪一种字体

````css
p {
  font-family:"微软雅黑", "宋体";
}
````

可以同时指定多个字体，中间以逗号隔开，表示如果浏览器不支持第一个字体，则会尝试下一个，直到找到合适的字体

(3)Unicode字体

在css中设置字体名称，直接写中文是可以的，但是在文件编码不匹配时会产生乱码的错误

使用Unicode写中文字体名称，浏览器是可以正确的解析的

````css
font-family: "\5FAE\8F6F\96C5\9ED1"; 表示设置字体为"微软雅黑"
````

(4)font-weight：字体粗细

使用b和strong标签是文本加粗

normal  默认值（不加粗的）

bold  定义粗体（加粗的）

100-900  400等同于normal，而700等同于bold

平常更加提倡使用数字来表示加粗和不加粗

````html
例子
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        
        body {
            font-size: 16px;
        }

        .title {
            /* 字体大小 */
            font-size: 20px;
            /* 设置字体 */
            font-family: "微软雅黑";
            /* 字体加粗 */
            /*font-weight: 700; 与下一行代码等价*/
            font-weight: bold;
        }

    </style>
</head>
<body>
    
    <p class="title">俺来也！！！</p>
    <p>俺来也！！！</p>
    <p>俺来也！！！</p>
    <p>俺来也！！！</p>
    <p>俺来也！！！</p>
    
</body>
</html>
````

(5)font-style：字体风格

normal  默认值

italic  浏览器会显示斜体的字体样式

2，font的综合设置字体样式

````css
选择器 { font: font-style font-weight font-size/line-height font-family; }
````

使用font属性时，必须按照上面语法格式中的顺序书写，不能更换顺序，各个属性以空格隔开

其中font-size和font-family必须保留，其他属性如果不想设置，就可以省略

3，CSS外观属性

(1)color：文本颜色

用于定义文本的颜色

可以用预定义的颜色值，十六进制，RGB代码进行表示

十六进制表示时，必须带#

````css
.title {
	color: #0365A4;
}
````

(2)text-align：文本水平对齐方式

用于设置文本内容的水平对齐，相当于html中的align对齐属性

left  左对齐

right  右对齐

center  居中对齐

注意：是让盒子里面的内容水平居中，而不是让盒子居中对齐

(3)line-height：行间距

用于设置行与行之间的距离，即字符的垂直间距

````css
.title {
	line-height: 24px;
}
````

(4)text-indent：首行缩进

用于设置首行文本的缩进，建议使用em作为设置单位，1em就是一个字的宽度，如果是汉字，1em就是一个汉字的宽度

````css
p {
	/*行间距*/
	line-height: 25px;
	/*首行缩进2个字*/
	text-indent: 2em;
}
````

(5)text-decoration 文本的装饰

none    默认，定义标准的文本，取消下划线

underline    定义文本下的一条线，下划线也是我们链接自带的

overline    定义文本上的一条线

line-through    定义穿过文本的一条线