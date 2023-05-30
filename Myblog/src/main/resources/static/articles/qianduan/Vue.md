# 1、Vue.js介绍

是一套用于构建用户界面的渐进式框架（只关心视图层），所谓渐进式就是逐步实现新特性的思想，如实现模块化开发、路由、状态管理等新特性。Vue就是为了处理DOM的

视图：给用户看，刷新后台给的数据

**JavaScript**

一门弱类型脚本语言，其源代码在发往客户端运行之前不需要经过编译，而是将文本格式的字符代码发送给浏览器由浏览器解释运行

（Native原生JS开发）原生JS开发，就是按照ECMAScript标准的开发方式，简称是ES

（TypeScript微软的标准）是一种由微软开发的自由和开源的编程语言

# 2、第一个Vue程序

**MVVM模式**

- Model：模型层，在这里表示JavaScript对象
- View：视图层，在这里表示DOM（HTML操作的对象）
- View Model：连接视图和数据的中间件，Vue.js就是MVVM中的View Model层的实现者

MVVM的核心是View Model层，负责转换Model中的数据对象来让数据变得更容易管理和使用

MVVM和MVC一样，主要目的是为了分离模型层和视图层

![image-20230417091017214](C:\Users\dell\AppData\Roaming\Typora\typora-user-images\image-20230417091017214.png)

配置CDN

```javascript
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
```

第一个Vue程序

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">
    {{message}}
</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        //Model : 数据
        data:{
            message: "hello,vue!"
        }
    });
</script>

</body>
</html>
```

# 3、Vue基本语法

现在，数据和DOM已经被建立了关联，所有东西都是响应式的，我们在控制台操作对象属性，界面可以实时更新

我们还可以使用v-bind来绑定元素特性

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">
    <span v-bind:title="message">
        鼠标悬停几秒钟，查看此处动态绑定的提示信息
    </span>
</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        //Model : 数据
        data:{
            message: "hello,vue!"
        }
    });
</script>

</body>
</html>
```

这种v-bind等被称为指令，指令带有前缀v-，以表示他们是Vue提供的特殊特性，他们会在渲染的DOM上应用特殊的响应式行为

该指令的意思是，将这个元素节点的title特性和Vue实例的message属性保持一致

v-if，判断

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">

    <h1 v-if="ok">Yes</h1>
    <h1 v-else>No</h1>

</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        //Model : 数据
        data:{
            ok: false
        }
    });
</script>

</body>
</html>
```

v-for，循环

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">

  <li v-for="item in items">
    {{item.message}}
  </li>

</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
  var vm = new Vue({
    el: "#app",
    //Model : 数据
    data: {
      items: [
          {message: "我是小孟儿"},
          {message: "我是小尹儿"}
      ]
    }
  });
</script>

</body>
</html>
```

# 4、Vue绑定事件

通过在Vue的methods中定义方法，然后通过v-on进行事件的绑定

这里的click是Vue的事件，可以绑定到Vue中的methods中的方法事件

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">
  <!--使用了v-on绑定了click事件，并指定了名为 sayHi 的方法-->
  <button v-on:click="sayHi">click me</button>
</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
  var vm = new Vue({
    el: "#app",
    //Model : 数据
    data: {
      message: "我是小孟儿"
    },
    methods: { //方法必须定义在Vue 的 Methods对象中
        sayHi: function () {
            alert(this.message);
        }
    }
  });
</script>

</body>
</html>
```

# 5、Vue双向绑定

Vue.js是一个MVVM框架，即数据双向绑定，即当数据发生变化的时候，视图也就发生变化，当视图发生变化的时候，数据也会跟着同步变化。

可以用v-model指令，在表单<input>、<textarea>、<select>元素上创建双向数据绑定。它会根据控件类型自动选取正确的方法来更新元素。

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">
   输入的文本: <input type="text" v-model="message"> {{message}}

    <br />
    <br />
    <br />

   性别: <input type="radio" name="sex" v-model="checked" value="男"> 男
        <input type="radio" name="sex" v-model="checked" value="女"> 女
    <br />
   选择的性别是: {{checked}}
    <br />
    <br />
   下拉框:
    <select v-model="selected">
        <option>A</option>
        <option>B</option>
        <option>C</option>
    </select>

</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
  var vm = new Vue({
    el: "#app",
    //Model : 数据
    data: {
      message: "我是小孟儿",
      checked: "",
      selected: "A"
    }
  });
</script>

</body>
</html>
```

# 6、Vue组件

注意：实际开发中，不会用以下方式开发组件，而是会使用 Vue-cli 创建 .vue 模板文件的方式开发

组件是可复用的 Vue 实例，说白了就是一组可以重复使用的模板。通常一个应用会以一棵嵌套的组件树的形式来组织

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>

</head>
<body>
<!--view层 模板-->
<div id="app">
    <!--组件-->
    <yth v-for="item in items" v-bind:yin="item"></yth>
</div>

<!--    导入Vue.js-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>

  //定义一个Vue组件component
  Vue.component("yth",{
    //用于接收传递给组件中的值（就是利用组件为中间商，获得了vm中的items中的数据）
    props: ['yin'],
    template: "<li>{{yin}}</li>>"
  });

  var vm = new Vue({
    el: "#app",
    //Model : 数据
    data: {
      items: ["Java","Linux","PHP"]
    }
  });
</script>

</body>
</html>
```

# 7、Axios异步通信

Axios是一个开源的可以用在浏览器端和 NodeJS 的异步通信框架，它的主要作用就是实现AJAX异步通信

由于Vue是一个视图层框架，并且不包含AJAX的通信功能，所以为了解决通信问题，要使用Axios

因为开发的接口大部分都是采用JSON格式，所以先创建data.json文件，并写如下数据

```json
{
  "name":"狂神说java",
  "url": "http://baidu.com",
  "page": "1",
  "isNonProfit":"true",
  "address": {
    "street": "含光门",
    "city":"陕西西安",
    "country": "中国"
  },
  "links": [
    {
      "name": "B站",
      "url": "https://www.bilibili.com/"
    },
    {
      "name": "4399",
      "url": "https://www.4399.com/"
    },
    {
      "name": "百度",
      "url": "https://www.baidu.com/"
    }
  ]
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!--解决闪烁问题-->
    <style>
        [v-clock] {
            display: none;
        }
    </style>

</head>
<body>

<div id="vue" v-clock>
    <div>{{info.name}}</div>
    <a v-bind:href="info.url">百度</a>
</div>

<!--引入 JS 文件-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
    var vm = new Vue({
      el: "#vue",
      data() {
        return {
            //请求的返回参数格式，必须和json字符串一样
            info: {
                name: null,
                addredd: {
                    street: null,
                    city: null,
                    country: null
                },
                url: null
            }
        }
      },
      mounted() { //钩子函数 链式编程 ES6新特性
          axios.get('../data.json').then(response=>(this.info = response.data));
      }
    });
</script>

</body>
</html>
```

# 8、计算属性

是一个能够将计算结果缓存起来的属性，可以想象为缓存

计算属性的主要特性就是为了将不经常变化的计算结果进行缓存，以节约我们的系统开销

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div id="app">
  <p>{{currentTime()}}</p>

  <p>{{currentTime1}}</p>
</div>

<!--引入 JS 文件-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script>
  var vm = new Vue({
    el: "#app",
    data: {
      message: "hello,menger"
    },
    methods: {
      currentTime: function () {
        return Date.now(); //返回一个时间戳
      }
    },
    computed: { //计算属性
      currentTime1: function () {
        return Date.now();
      }
    }

  });
</script>

</body>
</html>
```

# 9、Vue + ElementUI

**1.首先通过vue-cli创建一个myvue项目** 

vue init webpack myvue

**2.安装依赖，我们需要vue-router、element-ui、sass-loader和node-sass四个插件**

- 进入工程目录

cd myvue

- 安装 vue-router

npm install vue-router --save-dev

- 安装 element-ui -s

npm i element-ui -s

- 安装依赖

npm install

- 安装SASS加载器

cnpm install sass-loader node-sass --save-dev

- 启动测试

npm run dev