# 一、盒子模型（Box Model）

所谓盒子模型，就是把HTML页面中的布局元素看作是一个矩形的盒子，也就是一个盛装内容的容器

盒子模型由内容、边框（border）、内边距（padding）和外边距（margin）组成

盒子里面的文字和图片等元素是 内容区域

盒子的厚度 我们称为 盒子的边框

**盒子内容与边框的距离是内边距**

**盒子与盒子之间的距离是外边距**

# 二、盒子边框（border）

````html
border : border-width || border-style || border-color
````

border-width    定义边框粗细，单位是px

border-style     边框的样式

border-color     边框颜色

边框的样式：

none：没有边框

solid：边框为单实线

dashed：边框为虚线

dotted：边框为点线

(1)盒子边框写法总结表

很多情况下，我们不需要指定4个边框，我们是可以单独给4个边框分别指定的

上边框：

border-top-style:样式;

border-top-width:宽度;

border-top-color:颜色;

border-top:宽度 样式 颜色;

下边框：

border-bottom-style:样式;

border-bottom-width:宽度;

border-bottom-color:颜色;

border-bottom:宽度 样式 颜色;

左边框：

border-left-style:样式;

border-left-width:宽度;

border-left-color:颜色;

border-left:宽度 样式 颜色;

右边框：

border-right-style:样式;

border-right-width:宽度;

border-right-color:颜色;

border-right:宽度 样式 颜色;

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
        div {
            width: 200px;
            height: 400px;
            border-top: 2px solid red;
            border-left: 2px solid pink;
            border-bottom: 2px solid blue;
            border-right: 2px solid green;
        }

        input {
            border: none;
            border-bottom: 1px dashed red;
        }
    </style>
</head>
<body>
    <div> </div>
    用户名：<input type="text" /> <br />
    密码： <input type="password" />
</body>
</html>
````

(2)表格的细线边框

通过表格的 cellspacing="0" ，将单元格与单元格之间的距离设置为0，

但是两个单元格之间的边框会出现重叠，从而使边框变细

通过css属性

````html
table{ border-collapse:collapse; }
````

表示相邻框合并在一起

# 三、内边距（padding）

(1)内边距

padding属性用于设置内边距，是指边框与内容之间的距离

(2)设置

padding-left    	  左内边距

padding-right    	右内边距

padding-top      	上内边距

padding-bottom    下内边距

当我们给盒子指定padding值之后，发生了两件事

成功添加了内边距，盒子变大了

简写的时候，顺序为 上右下左

````html
padding: 12px 10px 0 25px;
		  上   右  下  左
````

(3)导航栏案例

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        .nav {
            height: 41px;
            background-color: #fcfcfc;
            border-bottom: 1px solid #edeef0;
            border-top: 3px solid #ff8500;
        }

        .nav a {
            height: 41px;
            display: inline-block;
            padding: 0 20px;
            text-decoration: none;
            line-height: 41px;
            color: #4c4c4c;
        }

        .nav a:hover {
            background-color: #eee;
        }
    </style>
</head>
<body>
    <div class="nav">
        <a href="#">设为首页</a>
        <a href="#">手机新浪网</a>
        <a href="#">移动客户端</a>
        <a href="#">博客</a>
        <a href="#">微博</a>
        <a href="#">关注我</a>
    </div>
</body>
</html>
````

(4)内盒尺寸计算

盒子的实际大小 = 内容的宽度和高度 + 内边距 + 边框

(5)padding不影响盒子大小的情况

如果没有给一个盒子指定宽度，此时，如果给这个盒子指定padding，则不会撑开盒子

# 四、外边距（margin）

(1)外边距

margin属性用于设置外边距，margin就是控制盒子和盒子之间的距离

(2)设置

margin-left    	  左外边距

margin-right    	右外边距

margin-top    	  上外边距

margin-bottom    下外边距

(3)外边距实现盒子居中

可以让一个盒子实现水平居中，需要满足以下两个条件

必须是块级元素

盒子必须指定宽度

然后给左右的外边距都设置为auto，就可以使块级元素水平居中

(4)插入图片和背景图片

（用的多）插入图片：比如产品展示类，移动位置只能靠盒子模型 padding  margin

（用的少）背景图片：我们一般用于小图标背景或者超大背景图片 背景图片只能通过 background-position

(5)清除元素默认的内外边距

为了更加方便灵活地控制网页中的元素，制作网页的时候，我们需要将元素的默认内外边距清除

````html
* {
	padding:0;  清除内边距
	margin:0;   清除外边距
}
````

(6)外边距合并

使用margin定义块元素的垂直外边距时，可能出现外边距的合并

1、相邻块元素垂直外边距的合并

当上下相邻的两个块元素相遇时，如果上面的元素有下外边距margin-bottom

下面的元素有上外边距margin-top，则他们之间的垂直间距不是margin-bottom和margin-top之和

**取两个值中的较大者**这种现象被称为相邻块元素垂直外边距的合并

2、嵌套块元素垂直外边距的合并（塌陷）

对于两个嵌套关系的块元素，如果父元素没有上内边距及边框

父元素的上外边距会与子元素的上外边距发生合并

合并后的外边距为两者中的较大者

解决方案：

可以为父元素定义上边框

可以为父元素定义上内边距

可以为父元素添加overflow:hidden

# 五、PS基本操作以及常用快捷键

文件-打开，可以打开我们要测量的图片

ctrl+r 可以打开标尺 或者 视图-标尺

右击标尺，把里面的单位改成 像素

ctrl+空格键，鼠标可以变成小手，拖动ps视图

用选取 拖动 可以 测量 大小

ctrl+d 可以取消选区 或者旁边空白处点击一下也可以取消选区

ctrl+加号放大，ctrl+减号缩小

# 六、去掉列表默认样式

因为无序、有序列表的默认样式在不同的浏览器下显示效果不一样，所以直接去掉就行

````html
li { list-style: none; }
````

# 七、综合案例，新闻页面

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
            width: 298px;
            height: 198px;
            border: 1px solid gray;
            margin: 100px auto;
            background-color: rgba(255, 192, 203, 0.86);
            padding: 15px;
        }

        .box h2 {
            font-size: 18px;
            border-bottom: 1px solid #ccc;
            padding: 5ppx 0;
            text-align: center;
        }

        .box ul li {
            height: 30px;
            border-bottom: 1px dashed #ccc;
        }

        .box ul li a {
            text-decoration: none;
            color: #333;
            font-size: 12px;
            line-height: 30px;
        }

        .box ul li a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="box">
        <h2>最新文章/New Articles</h2>

        <ul>
            <li><a href="#">北京招聘网页设计，平面设计，php</a></li>
            <li><a href="#">体验JavaScript的魅力</a></li>
            <li><a href="#">jQuery世界来临</a></li>
            <li><a href="#">网页设计师的梦想</a></li>
            <li><a href="#">jQuery中的链式编程是什么</a></li>
        </ul>
    </div>
</body>
</html>
````

