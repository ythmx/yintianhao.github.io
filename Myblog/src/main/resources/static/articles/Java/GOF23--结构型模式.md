设计模式GOF23 (Group of Four)

**创建型模式**：单例模式、工厂模式、抽象工厂模式、建造者模式、原型模式

**结构型模式**：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式

**行为型模式**：模板方式模式、命令模式、迭代器模式、观察者模式、中介者模式、备忘录模式、解释器模式、状态模式、策略模式、职责链模式、访问者模式

****

**结构型模式**：以后用到最多的是外观模式

核心作用：是从程序的结构上实现松耦合，从而可以扩大整体的类结构，用来解决更大的问题

****

**一、适配器模式** （adapter）

适配器模式：将一个类的接口转换成客户希望的另外一个接口。

模式中的角色：目标接口(Target)，需要适配的类(Adaptee)，适配器(Adapter)

````java
//类适配器
//需要适配的类Adaptee
public class Adaptee {
    public void request() {
        System.out.println("可以完成客户端请求的需要的功能！");
    }
}

//客户端类
public class Client {
    public void test1(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        Client c = new Client();
        Target t = new Adapter();
        c.test1(t);
    }
}

//目标接口
public interface Target {
    void handleReq();
}

//适配器类，需要实现目标接口，继承需要适配的类
public class Adapter extends Adaptee implements Target{
    
    @Override
    public void handleReq() {
        a.request();
    }
}
````

````java
//对象适配器
//需要适配的类Adaptee
public class Adaptee {
    public void request() {
        System.out.println("可以完成客户端请求的需要的功能！");
    }
}

//客户端类
public class Client {
    public void test1(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        Client c = new Client();
        Adaptee a = new Adaptee();
        Target t = new Adapter(a);

        c.test1(t);
    }
}

//目标接口
public interface Target {
    void handleReq();
}

//适配器类，利用组合来完成
public class Adapter implements Target{
    private Adaptee a = new Adaptee();

    @Override
    public void handleReq() {
        a.request();
    }

    public Adapter(Adaptee a) {
        this.a = a;
    }
}
````

****

**二、代理模式** （Proxy pattern），是AOP(Aspect Oriented Programming 面向切面编程)的核心实现机制！

核心作用：通过代理，控制对对象的访问。可以详细控制访问某个对象的方法，在调用这个方法之前做前置处理，调用这个方法后做后置处理。

分类：静态代理（静态定义代理类）  动态代理（动态生成代理类）

模式角色：抽象角色，真实角色，代理角色

````java
//静态代理
//抽象角色接口，Star接口
public interface Star {
    //签协议
    void confer();
    //签合同
    void signContract();
    //订票
    void bookTicket();
    //唱歌
    void sing();
    //收尾款
    void collectMoney();
}

//真实角色类，RealStar类，实现了Star接口
public class RealStar implements Star{

    @Override
    public void confer() {
        System.out.println("真实明星签协议");
    }

    @Override
    public void signContract() {
        System.out.println("真实明星签合同");
    }

    @Override
    public void bookTicket() {
        System.out.println("真实明星订票");
    }

    @Override
    public void sing() {
        System.out.println("真实明星唱歌");
    }

    @Override
    public void collectMoney() {
        System.out.println("真实明星收尾款");
    }
}

//代理角色类，ProxyStar类，实现了Star接口
public class ProxyStar implements Star{
    private Star star;

    public ProxyStar(Star star) {
        this.star = star;
    }

    @Override
    public void confer() {
        System.out.println("代理人签协议");
    }

    @Override
    public void signContract() {
        System.out.println("代理人签合同");
    }

    @Override
    public void bookTicket() {
        System.out.println("代理人订票");
    }
	//注意，代理角色无法唱歌，因此要创建一个真实明星对象，调用他的唱歌方法
    @Override
    public void sing() {
        star.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("代理人收尾款");
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Star real = new RealStar();
        Star proxy = new ProxyStar(real);

        proxy.confer();
        proxy.signContract();
        proxy.bookTicket();
        proxy.sing();
        proxy.collectMoney();
    }
}
````

动态代理相比于静态代理的优点：抽象角色中声明的所有方法都被转移到调用处理器一个集中的方法中处理，这样，我们可以更加灵活和统一的处理众多的方法。

````java
//动态代理
//抽象角色接口，Star接口
public interface Star {
    //签协议
    void confer();
    //签合同
    void signContract();
    //订票
    void bookTicket();
    //唱歌
    void sing();
    //收尾款
    void collectMoney();
}

//真实角色类，RealStar类，实现了Star接口
public class RealStar implements Star{

    @Override
    public void confer() {
        System.out.println("真实明星签协议");
    }

    @Override
    public void signContract() {
        System.out.println("真实明星签合同");
    }

    @Override
    public void bookTicket() {
        System.out.println("真实明星订票");
    }

    @Override
    public void sing() {
        System.out.println("真实明星唱歌");
    }

    @Override
    public void collectMoney() {
        System.out.println("真实明星收尾款");
    }
}

//代理角色类，StarHandler类实现了InvocationHandler接口
public class StarHandler implements InvocationHandler {
    Star realStar;

    public StarHandler(Star realStar) {
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理人干活！");
        if (method.getName().equals("sing")) {
            method.invoke(realStar,args);
        }
        System.out.println("代理人干活！");

        return null;
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Star realStar = new RealStar();

        StarHandler handler = new StarHandler(realStar);

        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{Star.class},handler);

        proxy.sing();
    }
}
````

****

**三、桥接模式** (bridge)

桥接模式：适用于实现多层继承结构的关系

**如果以后要使用的场景中，有多个变化维度的话，就要使用桥接模式。**例如，商城系统中的电脑分类，有两个变化的维度：电脑类型、电脑品牌。

核心要点：处理多层继承结构，处理多维度变化的场景，将各个维度设计成独立的继承结构，使各个维度可以独立的扩展在抽象层建立关联。

````java
//首先创建一个品牌的接口，实现电脑品牌的维度
//品牌
public interface Brand {
    void sale();
}

class lenovo implements Brand {
    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }
}

class Dell implements Brand {
    @Override
    public void sale() {
        System.out.println("销售戴尔电脑");
    }
}

class shenzhou implements Brand {
    @Override
    public void sale() {
        System.out.println("销售神州电脑");
    }
}

//然后创建一个抽象类，实现电脑类型的维度
//电脑类型
public abstract class Computer {
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void sale() {
        brand.sale();
    }
}

class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机！");
    }
}

class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售笔记本！");
    }
}

class Pad extends Computer {
    public Pad(Brand brand) {
        super(brand);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售平板！");
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        //销售联想的笔记本电脑
        Computer c = new Laptop(new lenovo());
        c.sale();

        //销售神州的台式机
        Computer c2 = new Desktop(new shenzhou());
        c2.sale();
    }
}
````

总结：桥接模式极大的提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有的系统，符合开闭原则。桥接模式可以取代多层继承的方法。多层继承违背了单一职责原则，复用性较差。

****

**四、组合模式** (composite)   

**使用组合模式的场景：**把部分和整体的关系用树形结构来表示，从而使客户端可以使用统一的方式处理部分对象和整体对象。

**组合模式核心：**

抽象构件(Component)角色：定义了叶子和容器构件的共同点

叶子(Leaf)构件角色：无子节点

容器(Composite)构件角色：有容器特征，可以包含子节点

````java
//下面是利用了组合模式构建的一个杀毒软件
//抽象组件
public interface AbstractFile {
    void killVirus();
}
//叶子组件
class ImageFile implements AbstractFile {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---图像文件：" + name + "，进行查杀!");
    }
}
//叶子组件
class TextFile implements AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---文本文件：" + name + "，进行查杀!");
    }
}
//叶子组件
class VideoFile implements AbstractFile {
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---视频文件：" + name + "，进行查杀!");
    }
}
//容器组件
class Folder implements AbstractFile {
    private String name;
    //定义容器，用来存放本容器构建下的子节点(叶子组件)
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }
    //添加叶子组件
    public void add(AbstractFile file) {
        list.add(file);
    }
    //移除叶子组件
    public void remove(AbstractFile file) {
        list.remove(file);
    }
    //获得叶子组件
    public AbstractFile getChile(int index) {
        return list.get(index);
    }
    //杀毒
    @Override
    public void killVirus() {
        System.out.println("---文件夹：" + name + "，进行查杀");

        for (AbstractFile file : list) {
            file.killVirus();
        }
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        AbstractFile f2,f3,f4,f5,f6;
        //定义两个文件夹
        Folder f1 = new Folder("游戏");
        Folder f11 = new Folder("学习");
        //定义五个其他文件
        f2 = new ImageFile("LOL图标.png");
        f3 = new TextFile("Hello World.txt");
        f4 = new VideoFile("放空.mp3");
        f5 = new ImageFile("永劫无间.png");
        f6 = new TextFile("学习笔记.txt");
        //将f2,f3,f5放入到f1文件夹中
        f1.add(f2);
        f1.add(f5);
        f1.add(f3);
        //将f4,f6放到f11文件夹中
        f11.add(f4);
        f11.add(f6);
        //将f11文件夹放到f1文件夹中
        f1.add(f11);
        //调用第一个文件的杀毒功能
        f1.killVirus();
    }
}
````

****

**五、装饰模式** (decorator)  是一种用于代替继承的技术，无须通过继承增加子类就能扩展对象的新功能。使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀。

**职责：**动态的为一个对象增加新的功能。

**实现细节：**抽象构件角色(Component)，具体构件角色(真实对象)（ConcreteComponent），装饰角色(Decorator)，具体装饰角色(ConcreteDecorator)

````java
//抽象构建角色
public interface ICar {
    void move();
}
//具体构建角色
class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑！");
    }
}
//装饰角色
class SuperCar implements ICar {
    protected ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}
//具体装饰角色
class FlyCar extends SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    public void fly() {
        System.out.println("天上飞！");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}
//具体装饰角色
class WaterCar extends SuperCar {

    public WaterCar(ICar car) {
        super(car);
    }

    public void swim() {
        System.out.println("水上游！");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}
//具体装饰角色
class AICar extends SuperCar {

    public AICar(ICar car) {
        super(car);
    }

    public void autoMove() {
        System.out.println("自动跑！");
    }

    @Override
    public void move() {
        super.move();
        autoMove();
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Car car = new Car();
        car.move();
        System.out.println("正在增加飞行功能");
        FlyCar flyCar = new FlyCar(car);
        flyCar.move();
        System.out.println("继续增加水里游功能");
        WaterCar waterCar = new WaterCar(flyCar);
        waterCar.move();
    }
}
````

**总结：**装饰模式降低系统的耦合度，可以动态的增加或删除对象的职责，并使得需要装饰的具体构建类和具体装饰类可以独立变化，以便增加新的具体构建类和具体装饰类。

**装饰模式和桥接模式的区别：**桥接模式是对象自身现有机制沿着多个维度变化，是既有部分不稳定。装饰模式是为了增加新的功能。

****

**六、外观模式** (Facade)

**迪米特法则（最少知识原则）：**一个软件实体应当尽可能少的与其他实体发生相互作用。

**外观模式核心：**为子系统提供统一的入口，封装子系统的复杂性，便于客户端调用。

````java
//A接口
public interface A {
    //办理A业务
    void ban_A();
}

class A_company implements A {
    @Override
    public void ban_A() {
        System.out.println("在A公司办理A业务");
    }
}

//B接口
public interface B {
    //办理B业务
    void ban_B();
}

class B_company implements B {
    @Override
    public void ban_B() {
        System.out.println("在B公司办理B业务");
    }
}

//C接口
public interface C {
    //办理C业务
    void ban_C();
}

class C_company implements C {
    @Override
    public void ban_C() {
        System.out.println("在C公司办理C业务");
    }
}

//办理注册公司流程的门面对象
public class RegisterFacade {
    public void register() {
        A a = new A_company();
        a.ban_A();
        B b = new B_company();
        b.ban_B();
        C c = new C_company();
        c.ban_C();
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        new RegisterFacade().register();
    }
}
````

**外观模式是使用最频繁的模式**

****

**七、享元模式** (FlyWeight)

**场景：**如果有很多个完全相同或相似的对象，我们可以通过享元模式，节省内存。

**核心：**享元对象能做到共享的关键是区分了内部状态和外部状态，内部状态（可以共享），外部状态（不可以共享）。

**实现：**享元工厂类，抽象享元类，具体享元类，非共享享元类。

**设计时注意的要点：**1，工厂类中要加入享元池  2，外部状态要使用单独的类来处理  3，内部状态要使用享元类来处理

````java
//抽象享元类
public interface ChessFlyWeight {
    void setColor(String color);
    String getColor();
    void display(Coordinate coordinate);
}

//具体享元类
class ConcreteChess implements ChessFlyWeight {

    private String color;

    public ConcreteChess(String color) {
        this.color = color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void display(Coordinate coordinate) {
        System.out.println("棋子颜色：" + color + " 棋子坐标" + coordinate.getX() + "," + coordinate.getY());
    }
}

//外部状态，非共享享元类
public class Coordinate {
    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

//享元工厂类
public class ChessFlyWeightFactory {
    //享元池
    private static Map<String,ChessFlyWeight> map = new HashMap<>();

    public static ChessFlyWeight getChess(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        }else {
            ChessFlyWeight cfw = new ConcreteChess(color);
            map.put(color,cfw);
            return cfw;
        }
    }
}

//客户类，使用享元模式后，棋子只有两个对象，可以节省很大的内存空间
public class Client {
    public static void main(String[] args) {
        ChessFlyWeight chess1 = ChessFlyWeightFactory.getChess("黑色");
        ChessFlyWeight chess2 = ChessFlyWeightFactory.getChess("白色");
        ChessFlyWeight chess3 = ChessFlyWeightFactory.getChess("黑色");
        ChessFlyWeight chess4 = ChessFlyWeightFactory.getChess("白色");
        System.out.println(chess1);
        System.out.println(chess2);
        System.out.println(chess3);
        System.out.println(chess4);
        System.out.println("增加外部状态的处理***********");
        chess1.display(new Coordinate(10,10));
        chess1.display(new Coordinate(20,20));
    }
}
````

