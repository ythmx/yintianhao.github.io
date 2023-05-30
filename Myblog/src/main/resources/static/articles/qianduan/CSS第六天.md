**定位（position）**

# **一、CSS布局的三种机制**

标准流（普通流）

浮动

定位

# **二、为什么使用定位**

定位：将盒子定在某一个位置，自由的漂浮在其他盒子的上面

有三种布局机制的上下顺序

标准流在最底层（海底）

浮动的盒子在中间层（海面）

定位的盒子在最上层（天空）

# **三、定位详解**

定位 = 定位模式 + 边偏移

**1、边偏移**

我们定位的盒子，是通过边偏移来移动位置的

在CSS中，通过top、left、right、bottom属性定义元素的边偏移

定位的盒子有了边偏移才有价值，一般情况下，凡是有定位地方必定有边偏移

**2、定位模式（定位的分类）**

在CSS中，通过position属性定义元素的定位模式

````html
选择器 { position: 属性值; }
````

定位模式是有不同分类的，在不同情况下，使用不同的定位模式

static    	 静态定位

relative      相对定位

absolute    绝对定位

fixed    	  固定定位

**1）静态定位**

静态定位是元素的默认定位方式，无定位的意思，他是在不要定位的时候使用

静态定位是按照标准流特性摆放位置，它没有边偏移

**2）相对定位**

相对定位是元素相对于他原来在标准流中的位置来说的（自恋型）

特点：

原来在标准流的区域继续占有，后面的盒子仍然以标准流的方式对待它

**3）绝对定位**

绝对定位是元素以带有定位的父级元素来移动位置（拼爹型）

特点：

完全脱标----完全不占有位置

父元素没有定位，则以浏览器为准定位

如果父级有定位，绝对定位盒子以父级为准定位

**定位口诀----子绝父相**

子级是绝对定位，父级要用相对定位

因为父级要占有位置，所以必须用相对定位，子级需要任意摆放，所以用绝对定位

**4）固定定位**

固定定位是绝对定位的一种特殊形式（认死理型）如果说绝对定位是一个矩形 那么 固定定位就类似于正方形

特点：

完全脱标----完全不占位置

只认浏览器的可视窗口----浏览器可视窗口 + 边偏移属性  来设置元素的位置

（跟父元素没有任何关系、不随滚动条滚动）

例子：图片始终固定在可视区域的右下角

````html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>css的练习</title>
    <style>
        body {
            height: 1500px;
        }

        .up img {
            position: fixed;
            bottom: 0;
            right: 0;
        }
    </style>
</head>
<body>
    <div class="up">
        <img src="mssj.png" width="150px" height="">
    </div>
</body>
</html>
````

# **四、定位的扩展**

**1、绝对定位的盒子居中**

在使用绝对定位时，不能用 margin : auto; 实现水平居中

可使用以下方法：

left : 50%;  让盒子的左侧移动到父级元素的水平中心位置

margin-left : -100px;  让盒子向左移动自身宽度的一半（这里盒子宽度为200px，所以是-100px）

**2、堆叠顺序（z-index）**

在使用定位布局时，可能会出现盒子重叠的情况

加了定位的盒子，默认后来者居上，后面的盒子会压住前面的盒子

应用 z-index 层叠等级属性，可以调整盒子的堆叠顺序

特点：

属性值：正整数、负整数或0，默认值是0，数值越大，盒子越靠上

如果属性值相同，则按照书写顺序，后来者居上

数字后面不能加单位

z-index 只能应用于相对定位、绝对定位和固定定位的元素，其他标准流、浮动和静态定位无效

**3、定位改变display属性**

可以改变display的方法有以下几种：

可以用 inline-block 转换为行内块

可以用浮动 float 默认转换为行内块

绝对定位和固定定位也和浮动类似，默认转换为行内块

一个行内的盒子，如果加了浮动、固定定位和绝对定位，不用转换，就可以直接给这个盒子设置宽度和高度

# **五、案例**

圆角矩形设置四个角

圆角矩形可以为4个角分别设置圆度，但是是有顺序的

````html
border-top-left-radius:20px;
border-top-right-radius:20px;
border-bottom-right-radius:20px;
border-bottom-left-radius:20px;

如果四个角数值相同
border-radius: 15px;

数值不同，可以简写（顺时针顺序）
border-radius: 左上角 右上角 右下角 左下角;
````

轮播图案例

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

        .taobao {
            position: relative;
            width: 520px;
            height: 280px;
            background-color: pink;
            margin: 100px auto;
        }

        .arrow-l {
            position: absolute;
            width: 20px;
            height: 30px;
            left: 0;
            top: 50%;
            margin-top: -15px;
            background: rgba(0, 0, 0, .2);
            text-decoration: none;
            color: #fff;
            /* text-align: center; */
            line-height: 30px;
            /* 右上角 */
            border-top-right-radius: 20px;
            /* 右下角 */
            border-bottom-right-radius: 20px;
        }

        .arrow-l:hover,
        .arrow-r:hover {
            color: skyblue;
            background: rgba(0, 0, 0, .4);
        }

        .arrow-r {
            position: absolute;
            width: 20px;
            height: 30px;
            right: 0;
            top: 50%;
            margin-top: -15px;
            background: rgba(0, 0, 0, .2);
            text-decoration: none;
            color: #fff;
            text-align: right;
            line-height: 30px;
            /* 左上角 */
            border-top-left-radius: 20px;
            /* 左下角 */
            border-bottom-left-radius: 20px;
        }

        .circle {
            position: absolute;
            bottom: 15px;
            left: 50%;
            margin-left: -35px;
            width: 70px;
            height: 13px;
            background-color: rgba(255, 255, 255, .3);
            border-radius: 7px;
        }

        .circle li {
            float: left;
            width: 8px;
            height: 8px;
            background-color: #fff;
            border-radius: 50%;
            margin: 3px;
        }
        /* 注意优先级的问题 */
        .circle .current {
            background-color: #ff5000;
        }
    </style>
</head>
<body>
    <div class="taobao">
        <!-- 左按钮 -->
        <a href="#" class="arrow-l">&lt;</a>
        <!-- 图片 -->
        <img src="2.jpg" width="520px" height="280px">
        <!-- 右按钮 -->
        <a href="#" class="arrow-r">&gt;</a>
        <!-- 小圆点 -->
        <ul class="circle">
            <li></li>
            <li class="current"></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</body>
</html>
````

# **六、网页布局总结**

一个完整的网页，有标准流、浮动、定位一起完成布局的

1）标准流

可以让盒子上下排列 或者 左右排列

2）浮动

可以让多个块级元素一行显示 或者 左右对齐盒子  浮动的盒子就是按照顺序左右排列

3）定位

有层叠的概念，就是可以让多个盒子 前后 叠压来显示，每个盒子需要测量数值