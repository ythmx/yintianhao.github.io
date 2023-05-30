**第二天**

# 一、CSS复合选择器

CSS选择器分为基础选择器和复合选择器，但是基础选择器不能满足我们实际开发中，快速高效的选择标签

复合选择器是由两个或多个基础选择器，通过不同的方式组合而成的

(1)后代选择器（重点）

又称为包含选择器

用来选择元素或元素组的子孙后代

写法就是把外层标签写在前面，内层标签写在后面，中间用空格分隔，先写父亲爷爷，再写儿子孙子

````html
父级 子级{ 属性:属性值;属性:属性值; }

例子
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        /* 父级 子级 */
        .nav a {
            color: pink;
        }

        .yin ul li {
            color: red;
        }
    </style>
</head>
<body>
    <div class="nav">
        <a href="#">内部链接</a>
        <a href="#">内部链接</a>
        <a href="#">内部链接</a>
    </div>

    <a href="#">外部链接</a>
    <a href="#">外部链接</a>
    <a href="#">外部链接</a>

    <ul>
        <li>一个人</li>
        <li>一个人</li>
        <li>一个人</li>
    </ul>

    <div class="yin">
        <ul>
            <li>两个人</li>
            <li>两个人</li>
            <li>两个人</li>
        </ul>
    </div>
</body>
</html>
````

(2)子元素选择器

子元素选择器只能选择作为某元素子元素（亲儿子）的元素

这里的子是指亲儿子不包含孙子，重孙子之类的

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
        /* 子元素选择器 */
        div>p>strong {
            color: pink;
        }
    </style>
</head>
<body>
    <div class="">
        <strong>儿子</strong>
        <strong>儿子</strong>
        <strong>儿子</strong>
    </div>

    <div>
        <p>
            <strong>孙子</strong>
            <strong>孙子</strong>
            <strong>孙子</strong>
        </p>
    </div>
</body>
</html>
````

(3)交集选择器

由两个选择器构成，找到的标签必须满足：既有标签一的特点，也有标签二的特点

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
        p.red {
            color: red;
        }
    </style>
</head>
<body>
    <p class="red">红色</p>
    <p class="red">红色</p>
    <p class="red">红色</p>
    <div>红色</div>
    <div>红色</div>
    <div>红色</div>
    <p>蓝色</p>
    <p>蓝色</p>
    <p>蓝色</p>
</body>
</html>
````

(4)并集选择器（重点）

如果某些选择器定义的相同样式，就可以利用并集选择器，可以让代码更简洁

并集选择器通常用于集体声明，逗号隔开

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
        /* 分开写
        p {
            color: red;
        }

        span {
            color: red;
        } */
        /* 合起来写，并集选择器 */
        p, span {
            color: red;
        }
    </style>
</head>
<body>
    <p>红色</p>
    <p>红色</p>
    <p>红色</p>
    <div>红色</div>
    <div>红色</div>
    <div>红色</div>
    <span>红色</span>
    <span>红色</span>
    <h1>红色</h1>
    <h1>红色</h1>
</body>
</html>
````

(5)链接伪类选择器

伪类选择器：

为了和我们刚才学的类选择器相区别

类选择器是一个点，比如.demo{}

伪类用两个点即冒号，比如:link{}

作用：

用于向某些选择器添加特殊的效果，比如给链接添加特殊效果，比如可以选择第一个，第n个元素

四种常见状态：

a:link  未访问的链接

a:visited  已访问的链接

a:hover  鼠标移动到链接上

a:active  选定的链接

注意：写的时候，顺序尽量不要颠倒，按照lvha的顺序来写（love hate 记忆方法）

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
        /* 未访问过 */
       a:link {
        color: pink;
        /*去除链接的下划线*/
        text-decoration: none;
       }

       /* 已经访问过 */
       a:visited {
        color: orange;
       }

       /* 鼠标移动到链接上的状态 */
       a:hover {
        color: red;
       }
    </style>
</head>
<body>
    <a href="http://www.xiaomi.com">小米手机</a>
</body>
</html>
````

# 二、标签显示模式

定义：就是标签以什么方式进行显示，例如div标签独占一行，span一行可以放很多个

HTML标签一般分为块标签和行内标签两种类型，他们也称块元素和行内元素

(1)块级元素（block-level）

常见的块级元素有<h1>-<h6>、<p>、<div>、<ul>、<ol>、<li>等，其中<div>标签是最典型的块元素

特点：（重要！！！）

自己独占一行

高度、宽度、外，内边距都可以控制

宽度默认是容器的100%

是一个容器及盒子，里面可以放行内或者块级元素

（p里面不能包含div，段落里面尽量不要放块级元素）

(2)行内元素（inline-level）

常见的行内元素有<a>、<strong>、<b>、<em>、<i>、<del>、<s>、<ins>、<u>、<span>等，其中<span>标签最典型的行内元素

特点：

一行可以显示多个

高、宽直接设置是无效的

默认宽度就是它本身内容的宽度

行内元素只能容纳文本或其他行内元素

(3)行内块元素（inline-block）

在行内元素中有几个特殊的标签<img>、<input>、<td>可以对它们设置高度和对齐属性

特点：

一行上显示多个

可以设置高度、行高、外边距和内边距

(4)标签显示模式的转换

块转行内：display:inline;

行内转块：display:block;

块、行内元素转换为行内块：display:inline-block;

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
       span {
        /* 把行内元素转换为块级元素 */
        display: block;
        width: 100px;
        height: 100px;
        background-color: pink;
       }
    </style>
</head>
<body>
    <span>行内</span>
</body>
</html>
````

# 三、行高（line-height）

(1)行高测量

一行文字是由以下四条线组成的

顶线

中线

基线

底线

其中，基线和基线的距离为行高（实际上，中文的量取就是上一行和这一行底线之间的距离）

(2)单行文本垂直居中

只需要让**文字的行高等于盒子的高度**就可以实现

行高和高度的三种关系：

如果 行高=高度，文字会垂直居中

如果 行高>高度，文字会偏下

如果 行高<高度，文字会偏上

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
       a {
        display: inline-block;
        width: 100px;
        height: 30px;
        background-color: pink;
        text-align: center;
        text-decoration: none;
        color: white;
        line-height: 30px;
       }

       a:hover {
        background-color: orange;
        color: yellow;
       }
    </style>
</head>
<body>
    <a href="#">新闻</a>
    <a href="#">体育</a>
    <a href="#">汽车</a>
</body>
</html>
````

# 四、CSS背景

(1)背景颜色（color）

````html
background-color: pink;
````

(2)背景图片（image）

url里不提倡加引号

````html
background-image: url(1.jpg);
````

(3)背景平铺（repeat）

````html
background-repeat: repeat|no-repeat|repeat-x|repeat-y
````

repeat  平铺

no-repeat  不平铺

repeat-x  在横向上平铺

repeat-y  在纵向上平铺

(4)背景位置（position）

````html
background-position: length||length
background-position: position||position
/*上下左右水平居中*/
background-position: center center;
````

length  长度值

position  top | center | bottom | left | right等方位名词

注意：

必须先指定background-image属性

position后面是x坐标和y坐标

如果只指定了一个方位名词，另一个默认居中

如果只指定了一个数值，那么该数值用于x坐标

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
            /* 一般做超大图片的方法都是这样，水平居中，垂直靠上 */
            background-image: url(mssj.png);
            background-position: center top;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    
</body>
</html>
````

(5)背景附着

背景附着就是解释背景是滚动的还是固定的

````html
background-attachment: scroll | fixed
````

scroll    背景图像是随对象内容滚动

fixed    背景图像固定

(6)背景透明（CSS3）

````html
background: rgba(0, 0, 0, 0.3);
````

最后一个参数是alpha透明度，取值范围在0-1之间

我们习惯把0.3的0省去  background: rgba(0, 0, 0, .3);

背景半透明是指盒子背景半透明，盒子里面的内容不受影响

因为是CSS3，所以低于ie9的版本是不支持的

# 五、CSS的三大特性

(1)CSS层叠性

所谓层叠性就是指多种CSS样式的叠加

是浏览器处理冲突的一个能力，如果一个属性通过两个相同选择器设置到同一个元素上，那么这个时候一个性值就会将另一个属性层叠掉。

就近原则（长江后浪推前浪，前浪死在沙滩上）

(2)CSS继承性

子标签会继承父标签的某些样式，想要设置一个可继承的属性，只需要将他应用于父元素即可（子承父业）

子元素可以继承父元素的样式（text-，font-，line-这些元素开头的可以继承，以及color属性）

(3)CSS优先级

定义CSS样式时，经常出现两个或者更多规则应用在同一元素上，此时，

选择器相同，则执行层叠性

选择器不同，就会出现优先级的问题

**权重计算公式**

标签选择器						 计算权重公式

继承或者*    						0，0，0，0

每个元素（标签选择器）    0，0，0，1

每个类，伪类    				  0，0，1，0

每个ID    							 0，1，0，0

每个行内样式 style=""    	 1，0，0，0

每个!important  重要的    	    无穷大

**权重叠加**

就是一个简单的加法

div ul li ---->  0，0，0，3

.nav ul li ---->  0，0，1，2

a:hover  ---->  0，0，1，1

.nav a  ---->  0，0，1，1