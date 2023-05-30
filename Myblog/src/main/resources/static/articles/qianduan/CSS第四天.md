# **一、浮动**

**1.1CSS布局的三种机制**

网页布局的核心--就是用CSS来摆放盒子

CSS提供了3种机制来设置盒子的摆放位置，分别是普通流（标准流）、浮动和定位，其中：

普通流（标准流）

块级元素独占一行，从上向下顺序排列

常用元素：div、hr、p、h1-h6、ul、ol、dl、form、table

行内元素会按照顺序，从左到右顺序排列，碰到父元素边缘则自动换行

常用元素：span、a、i、em等

浮动

让盒子从普通流中浮起来，主要作用让多个块级盒子一行显示

定位

将盒子定在浏览器的某一位置--CSS离不开定位，特别是后面的 js 特效

**1.2什么是浮动（float）**

元素的浮动是指设置了浮动属性的元素会

脱离标准普通流的控制

移动到指定位置

作用：

让多个盒子(div)水平排列成一行，使得浮动称为布局的重要手段

可以实现盒子的左右对齐等等

浮动最早是用来控制图片，实现文字环绕图片的效果

语法

````html
选择器 { folat: 属性值; }
````

none    元素不浮动

left       元素向左浮动

right     元素向右浮动

**1)浮动口诀之  浮**

浮动----漂浮在普通流的上面

````html
<style>
    .one {
        float: left;
        width: 200px;
        height: 200px;
        background-color: pink;
    }

    .two {
        width: 300px;
        height: 300px;
        background-color: purple;
    }
</style>
````

**2)浮动口诀之  漏**

浮动----浮动的盒子，把自己原来的位置漏给下面标准流的盒子，就是不占有原来位置，是脱离标准流的，我们俗称“脱标”

````html
<style>
    .one {
        float: left;
        width: 200px;
        height: 200px;
        background-color: pink;
    }

    .two {
        width: 300px;
        height: 300px;
        background-color: purple;
    }
</style>
````

**3)浮动口诀之  特**

浮动----特性，float属性会改变元素display属性

任何元素都可以浮动。浮动元素会生成一个块级框，而不论它本身是何种元素。生成的块级框和我们前面的行内块极其相似

案例----div水平排列

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .one,
        .two {
            width: 200px;
            height: 200px;
            background-color: pink;
        }

        .one {
            float: left;
        }

        .two {
            float: left;
            background-color: purple;
        }
    </style>
</head>
<body>
    <div class="one">小孟同学！</div>
    <div class="two">小孟同学！</div>
</body>
</html>
````

注意：

浮动的元素互相贴靠在一起，如果父级宽度装不下这些浮动的盒子，多出的盒子会另起一行对齐

**1.3浮动的应用**

浮动和标准流的父盒子搭配

我们知道，浮动是脱标的，会影响下面的标准流元素，此时，我们需要给浮动的元素添加一个标准流的父亲，这样，最大化的减少了对其他标准流的影响

总结：一个完整的网页，是 标准流 + 浮动 + 定位 一起完成的

例子--布局案例

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        li {
            list-style: none;
        }

        .box {
            width: 1226px;
            height: 615px;
            background-color: pink;
            margin: auto;
        }

        .left {
            float: left;
            width: 234px;
            height: 615px;
            background-color: purple;
        }

        .right {
            float: right;
            width: 992px;
            height: 615px;
            background-color: skyblue;
        }

        .right li {
            float: left;
            width: 234px;
            height: 300px;
            background-color: pink;
            text-align: center;
            line-height: 300px;
            margin-left: 14px;
            margin-bottom: 14px;
        }
    </style>
</head>
<body>
    <div class="box">
        <div class="left"></div>
        <ul class="right">
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
            <li>6</li>
            <li>7</li>
            <li>8</li>
        </ul>
    </div>
</body>
</html>
````

例子--导航栏案例

注意，实际的导航栏中，我们是用 li 包含链接（li + a）的做法

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        li {
            list-style: none;
        }

        .banner {
            width: 760px;
            height: 150px;
            margin: auto;
        }

        .banner img {
            width: 760px;
            height: 150px;
        }

        .nav {
            width: 760px;
            height: 32px;
            margin: 0 auto;
        }

        .nav ul li {
            float: left;
            line-height: 32px;
        }

        .nav ul li a {
            display: inline-block;
            width: 126px;
            height: 32px;
            text-decoration: none;
            text-align: center;
        }

        .nav ul li a:hover {
            color: red;
        }
    </style>
</head>
<body>
    <!-- banner是广告条的意思 -->
    <div class="banner">
        <img src="1.jpg" />
    </div>
    <!-- nav是导航栏的意思 -->
    <div class="nav">
        <ul>
            <li><a href="#">网站首页</a></li>
            <li><a href="#">小孟同学</a></li>
            <li><a href="#">小尹同学</a></li>
            <li><a href="#">开始学习</a></li>
            <li><a href="#">前端学习</a></li>
            <li><a href="#">后端学习</a></li>
        </ul>
    </div>
</body>
</html>
````

# **二、清除浮动**

**1.1为什么要清除浮动**

因为父级盒子在很多情况下，不方便给高度。如果子盒子浮动就不占有位置，最后父级盒子高度为0，就影响了下面的标准流盒子，从而导致布局出现问题。

总结：

由于浮动元素不再占有原文档流的位置，所以他会对后面的元素排版产生影响

准确地说，并不是清除浮动，而是清除浮动后造成的影响

**1.2清除浮动本质**

清除浮动主要为了解决父级元素因为子级浮动引起内部高度为 0 的问题

清除浮动之后，父级就会根据浮动的子盒子自动检测高度。父级有了高度，就不会影响下面的标准流

**1.3清除浮动的方法**

clear属性用于清除浮动

````html
选择器 { clear:属性值; }
````

left    	不允许左侧有浮动元素

right  	不允许右侧有浮动元素

both  	同时清除左右两侧浮动的影响

**1）额外标签法**

在最后一个浮动元素的末尾添加一个空标签

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .father {
            width: 500px;
            background-color: red;
        }

        .one {
            float: left;
            width: 150px;
            height: 150px;
            background-color: pink;
        }

        .two {
            float: left;
            width: 150px;
            height: 200px;
            background-color: skyblue;
        }

        .below {
            width: 500px;
            height: 100px;
            background-color: black;
        }

        .clear {
            clear: both;
        }
    </style>
</head>
<body>
    <div class="father">
        <div class="one"></div>
        <div class="two"></div>
        <!-- 清除浮动 -->
        <div class="clear"></div>
    </div>

    <div class="below"></div>
</body>
</html>
````

缺点：添加许多无意义的标签，结构化较差

**2）父级添加 overflow 属性方法**

可以给父级添加：overflow为 hidden | auto | scroll 都可以实现

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .father {
            width: 500px;
            background-color: red;
            /* 给父级添加overflow，清除浮动 */
            overflow: hidden;
        }

        .one {
            float: left;
            width: 150px;
            height: 150px;
            background-color: pink;
        }

        .two {
            float: left;
            width: 150px;
            height: 200px;
            background-color: skyblue;
        }

        .below {
            width: 500px;
            height: 100px;
            background-color: black;
        }
    </style>
</head>
<body>
    <div class="father">
        <div class="one"></div>
        <div class="two"></div>
    </div>

    <div class="below"></div>
</body>
</html>
````



缺点：内容增多的时候容易造成不会自动换行导致内容被隐藏掉

**3）使用 after 伪元素清除浮动（最流行的方法）**

after 方式为空元素额外标签的升级版，不需要添加额外的空标签

````html
<head>
    <style>
    	/* 声明清除浮动的样式 */
        .clearfix::after {
            content: "";
            display: block;
            height: 0;
            visibility: hidden;
            clear: both;
        }

        .clearfix {
            /*ie6,7 专门清除浮动的样式*/
            *zoom: 1;
        }
    </style>
</head>
````



````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        /* 声明清除浮动的样式 */
        .clearfix:after {
            content: "";
            display: block;
            height: 0;
            visibility: hidden;
            clear: both;
        }

        .clearfix {
            /*ie6,7 专门清除浮动的样式*/
            *zoom: 1;
        }

        .father {
            width: 500px;
            background-color: red;
        }

        .one {
            float: left;
            width: 150px;
            height: 150px;
            background-color: pink;
        }

        .two {
            float: left;
            width: 150px;
            height: 200px;
            background-color: skyblue;
        }

        .below {
            width: 500px;
            height: 100px;
            background-color: black;
        }
    </style>
</head>
<body>
    <div class="father clearfix">
        <div class="one"></div>
        <div class="two"></div>
    </div>

    <div class="below"></div>
</body>
</html>
````

**4）使用双伪元素清除浮动**

使用方法：

````css
.clearfix:before,.clearfix:after {
	content:"";
	display:table;
}

.clearfix:after {
	clear:both;
}

.clearfix {
	*zoom:1;
}
````

# **三、PS切片工具**

1）用切片选中图片

利用切片工具手动导出

图层菜单---新建基于图层的切片

利用标尺  基于参考线的切片

先选个一个整个的切片，切片选择工具--属性面板中有"划分"--可以平等分数平分切图

2）导出切片

文件菜单--存储为web设备所用格式--选择我们要的图片格式--点存储--别忘了选中的切片