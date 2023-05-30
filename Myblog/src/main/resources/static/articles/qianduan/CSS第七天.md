**CSS高级技巧**

# **一、元素的显示与隐藏**

目的：让一个元素在页面中消失或者显示出来

**1、display 显示**

````html
display: none;隐藏对象
display: block;除了转换为块级元素之外，同时还有显示元素的意思
````

特点：隐藏之后，不在保留位置

**2、visibility 可见性**

设置或检索是否显示对象

````html
visibility: visible; 对象可见
visibility: hidden; 对象隐藏
````

特点：隐藏之后，继续保留原有位置

**3、overflow 溢出**

检索或设置当对象的内容超过其指定高度及宽度时如果管理内容

属性值			描述

visible			不剪切内容也不添加滚动条

hidden		   不显示超过对象尺寸的内容，超出的部分隐藏掉

scroll			 不管超出内容否，总是显示滚动条

auto			   超出自动显示滚动条，不超出不显示滚动条

# **二、CSS用户界面样式**

**1、鼠标样式 cursor**

设置或检索在对象上移动的鼠标指针采用何种系统预定义的光标形状

属性值				描述

default				小白（默认）

pointer				小手

move				  移动

text					 文本

not-allowed		禁止

**2、轮廓线 outline**

是绘制于元素周围的一条线，位于边框边缘的外围，可起到突出元素的作用

````html
outline : outline-color || outline-style || outline-width
````

我们平时都是去掉的，最直接的写法

````html
<input type="text" style="outline: 0;" />
````

**3、防止拖拽文本域 resize**

实际开发中，我们文本域的右下角是不可以拖拽的：

````html
<textarea style="resize: none;"></textarea>
````

# **三、vertical-align 垂直对齐**

**1、图片、表单和文字对齐**

只针对于行内元素或者行内块元素

````html
vertical-align : baseline | top | middle | bottom
````

设置或检索对象内容的垂直对齐方式

不影响块级元素中的内容对齐，它只针对行内元素或者行内块元素

特别是行内块元素，通常用来控制图片/表单与文字的对齐

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        /* 图片与文字中线对齐 */
        .one {
            vertical-align: middle;
        }
        /* 图片与文字顶线对齐 */
        .two {
            vertical-align: top;
        }
    </style>
</head>
<body>
    <div>
        <img src="2.jpg" class="one" width="200px" height="200px"> 小孟同学！
    </div>
    <div>
        <img src="2.jpg" class="two" width="200px" height="200px"> 小孟同学！
    </div>
</body>
</html>
````

**2、去除图片底侧空白缝隙**

原因：图片的底线和父级盒子的底线对齐

解决方法：

给img vertical-align:middle | top | bottom等等，让图片不要和基线对齐

给img添加 display: block;转换为块级元素

# **四、溢出的文字省略号显示**

**1、white-space**

设置或检索对象内文本显示方式，通常我们使用于强制一行显示内容

````html
white-space:normal; 默认处理方式
white-space:nowrap; 强制在同一行内显示所有文本，直到文本结束或者遭遇br标签对象才换行
````

**2、text-overflow 文字溢出**

设置或检索是否使用一个省略标记（...）标示对象内文本的溢出

````html
text-overflow: clip; 不显示省略标记
text-overflow: ellipsis; 当对象内文本溢出时显示省略标记
````

注意：一定要强制一行内显示，再和overflow属性搭配使用

总结

````html
<style>
    div {
        /* 先强制一行内显示文本 */
        white-space: nowrap;
        /* 超出的部分隐藏 */
        overflow: hidden;
        /* 文字用省略号替代超出部分 */
        text-overflow: ellipsis;
    }
</style>
````

# **五、CSS精灵技术（sprite）**

**1、为什么需要精灵技术**

为了有效地减少服务器接受和发送请求的次数，提高页面的加载速度

**2、精灵技术讲解**

CSS精灵其实是将网页中的一些背景图像整合到一张大图中（精灵图），然而，各个网页元素通常只需要精灵图中不同位置的某个小图

我们需要使用CSS的

background-image

background-repeat

background-position 属性进行背景定位（关键）

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .icon1 {
            width: 23px;
            height: 23px;
            background: url(index.png) no-repeat;
            background-position: 0 -107px;
        }
    </style>
</head>
<body>
    <div class="icon1"></div>
</body>
</html>
````

总结：

精确测量，每个小背景图片的大小和位置

给盒子指定小背景图片时，背景定位基本都是负值

# **六、滑动门**

为了使各种特殊形状的背景能够自适应元素中文本内容的多少，出现了CSS滑动门技术。使各种特殊形状的背景能够自由拉伸滑动，以适应元素内部的文本内容，可用性更强

**核心技术**

核心技术就是利用CSS精灵（主要是背景位置）和盒子 padding 撑开宽度，以便能适应不同字数的导航栏

一般的经典布局都是这样的

````html
<li>
	<a href="#">
    	<span>导航栏内容</span>
    </a>
</li>
````

