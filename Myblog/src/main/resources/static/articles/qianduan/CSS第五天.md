# **制作学成在线网站**

# html部分

````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学成在线</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
    <!-- 头部模块开始 -->
    <div class="header w">
        <!-- logo盒子 -->
        <div class="logo">
            <img src="images/logo.png" />
        </div>
        <!-- nav盒子 -->
        <div class="nav">
            <ul>
                <li><a href="#">首页</a></li>
                <li><a href="#">课程</a></li>
                <li><a href="#">职业规划</a></li>
            </ul>
        </div>
        <!-- search盒子 -->
        <div class="search">
            <input type="text" value="输入关键词"/>
            <button></button>
        </div>
        <!-- user盒子 -->
        <div class="user">
            <img src="images/user.jpg">
            小孟同学
        </div>
    </div>
    <!-- 头部模块结束 -->

    <!-- banner模块开始 -->
    <div class="banner">
        <div class="w">
            <!-- 左侧导航栏 -->
            <div class="subnav">
                <ul>
                    <li><a href="#">前端开发 <span>></span></a></li>
                    <li><a href="#">后端开发 <span>></span></a></li>
                    <li><a href="#">移动开发 <span>></span></a></li>
                    <li><a href="#">人工智能 <span>></span></a></li>
                    <li><a href="#">商业预测 <span>></span></a></li>
                    <li><a href="#">云计算&大数据 <span>></span></a></li>
                    <li><a href="#">运维&测试 <span>></span></a></li>
                    <li><a href="#">UI设计 <span>></span></a></li>
                    <li><a href="#">产品 <span>></span></a></li>
                </ul>
            </div>
            <!-- 右侧课程表 -->
            <div class="course">
                <div class="course-hd">我的课程表</div>
                <div class="course-bd">
                    <ul>
                        <li>
                            <h4>继续学习 JavaSE课程</h4>
                            <p>正在学习-变量</p>
                        </li>
                        <li>
                            <h4>继续学习 web前端课程</h4>
                            <p>正在学习-html标签</p>
                        </li>
                        <li>
                            <h4>继续学习 JavaEE课程</h4>
                            <p>正在学习-函数</p>
                        </li>
                    </ul>
                    <a href="#" class="all">全部课程</a>
                </div>
            </div>
        </div>
    </div>
    <!-- banner模块结束 -->

    <!-- 精品推荐模块开始 -->
    <div class="goods w">
        <h3>精品推荐</h3>
        <div class="goods-item">
            |  <a href="#">jQuery</a>
            |  <a href="#">Spark</a>
            |  <a href="#">MySQL</a>
            |  <a href="#">JavaWeb</a>
            |  <a href="#">SpringBoot</a>
            |  <a href="#">Vue</a>
        </div>
        <div class="mod">
            修改兴趣
        </div>
    </div>
    <!-- 精品推荐模块结束 -->

    <!-- 内容模块1开始 -->
    <div class="box w">
        <div class="box-hd">
            <h3>精品推荐</h3>
            <a href="#">查看全部</a>
        </div>
        <!-- 此地方需要清除浮动 -->
        <div class="box-bd clearfix">
            <ul>
                <li>
                    <img src="images/pic1.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic2.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic3.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic4.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic5.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic1.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic2.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic3.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic4.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic5.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
            </ul>
        </div>
    </div>
    <!-- 内容模块1结束 -->

    <!-- 内容模块2开始 -->
    <div class="box w">
        <div class="box-hd">
            <h3>精品推荐</h3>
            <a href="#">查看全部</a>
        </div>
        <!-- 此地方需要清除浮动 -->
        <div class="box-bd clearfix">
            <ul>
                <li>
                    <img src="images/pic1.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic2.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic3.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic4.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
                <li>
                    <img src="images/pic5.png">
                    <h4>Think PHP 5.0 博客系统实战项目演练</h4>
                    <p><span>高级</span> 1125人在学习</p>
                </li>
            </ul>
        </div>
    </div>
    <!-- 内容模块2结束 -->

    <!-- 底部模块开始 -->
    <div class="footer">
        <div class="w">
            <!-- 左侧 -->
            <div class="copyright">
                <img src="images/logo.png">
                <p>学成在线致力于普及中国最好的教育它与中国一流大学和机构合作提供在线课程。<br />
                    2017年XTCG Inc.保留所有权利。
                </p>
                <a href="#" class="app">下载APP</a>
            </div>
            <!-- 右侧，使用自定义列表 -->
            <div class="links">
                <dl>
                    <dt>关于学成网</dt>
                    <dd><a href="#">关于</a></dd>
                    <dd><a href="#">管理团队</a></dd>
                    <dd><a href="#">工作机会</a></dd>
                    <dd><a href="#">客户服务</a></dd>
                    <dd><a href="#">帮助</a></dd>
                </dl>
                <dl>
                    <dt>新手指南</dt>
                    <dd><a href="#">如何注册</a></dd>
                    <dd><a href="#">如何选课</a></dd>
                    <dd><a href="#">如何拿到毕业证</a></dd>
                    <dd><a href="#">学分是什么</a></dd>
                    <dd><a href="#">考试未通过怎么办</a></dd>
                </dl>
                <dl>
                    <dt>合作伙伴</dt>
                    <dd><a href="#">合作机构</a></dd>
                    <dd><a href="#">合作导师</a></dd>
                </dl>
            </div>
        </div>
    </div>
    <!-- 底部模块结束 -->
</body>
</html>
````

# CSS部分

````css
/* 清除元素默认的内外边距 */
* {
    padding: 0;
    margin: 0;
}

/* 网页整体背景色 */
body {
    background-color: #f3f5f7;
}

/* 清除浮动 */
.clearfix:before,
.clearfix:after {
    content: "";
    display: table;
}
.clearfix:after {
    clear: both;
}
.clearfix {
    *zoom: 1;
}

/* 清除a标签中链接的下划线 */
a {
    text-decoration: none;
}

/* 清除button自带边框 */
button {
    border: none;
}

/* 清除无序列表前的黑点 */
li {
    list-style: none;
}

/* 版心 宽1200 水平居中 */
.w {
    width: 1200px;
    margin: auto;
}

/* 头部开始 */
/* 头部样式 */
.header {
    height: 42px;
    margin: 30px auto;
}

.logo {
    float: left;
}

.nav {
    float: left;
    margin-left: 60px;
}

.nav ul li {
    float: left;
}

.nav ul li a {
    display: block;
    height: 40px;
    padding: 0 20px;
    margin-right: 20px;
    line-height: 40px;
    color: #050505;
    text-decoration: none;
    font-size: 18px;
}

/* 鼠标经过链接时出现下边框 */
.nav ul li a:hover {
    border-bottom: 2px solid #00a4ff;
}

.search {
    float: left;
    margin-left: 70px;
}

.search input {
    float: left;
    width: 340px;
    height: 40px;
    border: 1px solid #00a4ff;
    border-right: 0;
    padding-left: 20px;
    color: #bfbfbf;
}

.search button {
    float: left;
    width: 50px;
    height: 42px;
    background: url(images/btn.png);
}

.user {
    float: left;
    height: 42px;
    line-height: 42px;
    margin-left: 30px;
    font-size: 16px;
    color: #666;
}
/* 头部结束 */

/* banner 开始 */
.banner {
    height: 420px;
    background: #1c036c url(images/banner2.png) no-repeat top center;
}

.subnav {
    float: left;
    width: 150px;
    height: 420px;
    padding: 0 20px;
    /* 背景半透明 */
    background: rgba(0, 0, 0, .3);
}

.subnav ul li {
    height: 45px;
    line-height: 45px;
}

.subnav ul li a {
    color: #fff;
    font-size: 14px;
    text-decoration: none;
}

.subnav ul li a:hover {
    color: #00b4ff;
}

.subnav ul li a span {
    float: right;
}

.course {
    float: right;
    width: 228px;
    height: 300px;
    margin-top: 50px;
    background-color: #fff;
}

.course-hd {
    height: 48px;
    line-height: 48px;
    background-color: #9bceea;
    font-size: 18px;
    text-align: center;
    color: #fff;
    /* 文字加粗 */
    font-weight: bold;
}

.course-bd {
    padding: 0 15px;
}

.course-bd ul {
    padding: 10px;
}

.course-bd ul li {
    height: 50px;
    border-bottom: 1px solid #ccc;
    margin-top: 10px;
}

.course-bd ul li h4 {
    font-size: 14px;
    color: #4e4e4e;
}

.course-bd ul li p {
    font-size: 12px;
    color: #a5a5a5;
}

.all {
    display: block;
    height: 38px;
    border: 1px solid #00a4ff;
    margin-top: 5px;
    text-align: center;
    line-height: 38px;
    font-size: 16px;
    color: #00a4ff;
}

.all:hover {
    background-color: #00a4ff;
    color: #fff;
}
/* banner 结束 */

/* 精品推荐开始 */
.goods {
    height: 60px;
    /* 利用行高可以继承的特性 */
    line-height: 60px;
    background-color: #fff;
    margin-top: 10px;
    box-shadow: 2px 2px 2px rgba(0, 0, 0, .2);
}

.goods h3 {
    float: left;
    margin: 0 30px;
    font-size: 16px;
    color: #00a4ff;
}

.goods-item {
    float: left;
    color: #bfbfbf;
}

.goods-item a {
    margin: 0 30px;
    font-size: 16px;
    color: #050505;
}

.mod {
    float: right;
    margin-right: 30px;
    font-size: 14px;
    color: #00a4ff;
}
/* 精品推荐结束 */

/* 内容模块开始 */
.box {
    margin-top: 15px;
}

.box-hd {
    height: 60px;
    line-height: 60px;
}

.box-hd h3 {
    float: left;
    font-size: 20px;
    color: #494949;
    font-weight: normal;
}

.box-hd a {
    float: right;
    margin-right: 30px;
    font-size: 12px;
    color: #a5a5a5;
}

.box-bd {
    /* 这个盒子不要给高度，因为放多少行是不确定的 */
    /* 如果不加15px，那么子盒子每个都有右边距15px，浮动后就会超出父盒子大小从而掉落 */
    width: 1215px;
}

.box-bd ul li{
    float: left;
    width: 228px;
    height: 270px;
    margin-right: 15px;
    margin-bottom: 15px;
    background-color: #fff;
    box-shadow: 2px 2px 2px rgba(0, 0, 0, .3);
}

.box-bd ul li img {
    width: 100%;
}

.box-bd ul li h4 {
    margin: 20px;
    font-size: 14px;
    color: #050505;
    font-weight: normal;
} 

.box-bd ul li p {
    margin: 0 20px;
    font-size: 12px;
    color: #999;
}

.box-bd ul li p span {
    color: orange;
}
/* 内容模块结束 */

/* 底部模块开始 */
.footer {
    height: 385px;
    padding-top: 30px;
    background-color: #fff;
}

.copyright {
    float: left;
}

.copyright p {
    font-size: 12px;
    color: #666;
    /* 上右下左 */
    margin: 20px 0 15px 0;
}

.app {
    display: block;
    width: 118px;
    height: 34px;
    line-height: 34px;
    border: 1px solid #00a4ff;
    text-align: center;
    font-size: 16px;
    color: #00a4ff;
}

.links {
    float: right;   
}

.links dl {
    float: left;
    margin-left: 100px;
}

.links dt {
    height: 35px;
    font-size: 16px;
    color: #333;
}

.links dd a {
    font-size: 12px;
    color: #333;
}
/* 底部模块结束 */
````

