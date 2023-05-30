设计模式GOF23 (Group of Four)

**创建型模式**：单例模式、工厂模式、抽象工厂模式、建造者模式、原型模式

**结构型模式**：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式

**行为型模式**：模板方式模式、命令模式、迭代器模式、观察者模式、中介者模式、备忘录模式、解释器模式、状态模式、策略模式、职责链模式、访问者模式

****

**创建型模式**：以后用到最多的是单例模式、简单工厂模式、原型模式。

**创建型模型是用来帮助你创建对象的**

****

**一、单例模式**

**核心作用**：保证一个类只有一个实例，并且提供一个访问该实例的全局访问点

**常见的五种单例模式实现方式**：

主要：饿汉式(线程安全，调用效率高。但是不能延时加载)

​		   懒汉式(线程安全，调用效率不高。但是可以延时加载)

其他：双重检测锁式，静态内部类式，枚举单例

**饿汉式**，私有化构造器，类初始化时加载对象，生成getter方法(不需要同步)

````java
public class Demo1{
    //类初始化时，立即加载这个对象
    //由于加载类时，天然的是线程安全的
    private static Demo1 instance = new Demo1();

    private Demo1() {
    }

    //方法不需要同步synchronized，效率高
    public static Demo1 getInstance() {
        return instance;
    }
}
````



**懒汉式**，私有化构造器，类初始化时不初始化对象，需要调用时才创建对象

````java
public class Demo1{
    private static Demo1 instance;

    private Demo1() {
    }

    //方法需要同步synchronized，效率低
    public static synchronized Demo1 getInstance() {
        //等到需要用对象的时候，才去new，延时加载!
        if (instance == null) {
            instance = new Demo1();
        }
        return instance;
    }
}
````



**静态内部类式**，兼并了并发高效调用和延迟加载的优势

````java
public class Demo1{
    //只有真正调用了getInstace方法，才会加载静态内部类的内容
    //实现了延时加载
    private static class Demo2 {
        //令该对象是static final类型，保证了内存中只有这样一个实例存在，从而保证了线程安全性
        private static final Demo1 instance = new Demo1();
    }

    public static Demo1 getInstance() {
        return Demo2.instance;
    }

    private Demo1() {
    }
}
````



**枚举单例**，实现简单，由JVM从根本上提供保障，避免通过反射和反序列化的漏洞，缺点是没有延迟加载

````java
public enum Demo1{
    //这个枚举元素，本身就是单例对象
    INSTANCE;

    //添加自己需要的操作
    public void singletonOperation() {

    }
}
````

**如何选用？**

占用资源少，不需要延时加载：枚举式 好于 饿汉式

占用资源大，需要延时加载：静态内部类式 好于 懒汉式

****

**二、工厂模式**

实现了创建者和调用者的分离。

详细分类：简单工厂模式、工厂方法模式、抽象工厂模式

**面向对象设计的基本原则**

OCP (开闭原则 Open-Closed Principle) 一个软件的实体应当对扩展开放，对修改关闭

DIP (依赖倒转原则 Dependence Inversion Principle) 要针对接口编程，不要针对实现编程

LoD (迪米特法则 Law of Demeter) 只与你直接的朋友通信，而避免和陌生人通信

**核心本质**：实例化对象，用工厂方法代替new操作。将选择实现类、创建对象统一管理和控制，从而将调用者跟我们的实现类解耦。

**简单工厂模式**，也叫静态工厂模式，由于工厂类一般是使用静态方法，对应增减新产品，不修改代码的话，就无法扩展 （实际使用最多）

````java
//Car接口
public interface Car {
    void run();
}

//Demo1类，继承Car接口
public class Demo1 implements Car{
    @Override
    public void run() {
        System.out.println("Demo1 再跑！");
    }
}

//Demo2类，继承Car接口
public class Demo2 implements Car{
    @Override
    public void run() {
        System.out.println("Demo2 再跑！");
    }
}

//CarFactory类，工厂类
public class CarFactory {
    public static Car createCar(String type) {
        if ("yth".equals(type)) {
            return new Demo1();
        }else if ("mx".equals(type)) {
            return new Demo2();
        }
        return null;
    }
}

//Test类，使用者类
public class Test {
    public static void main(String[] args) {
        Car c1 = CarFactory.createCar("yth");
        Car c2 = CarFactory.createCar("mx");

        c1.run();
        c2.run();
    }
}
````



**工厂方法模式**，相比于简单工厂模式而言，更加符合OCP原则，但是会创建更多的类

````java
//Car接口
public interface Car {
    void run();
}

//Demo1类，继承Car接口
public class Demo1 implements Car{
    @Override
    public void run() {
        System.out.println("Demo1 再跑！");
    }
}

//Demo2类，继承Car接口
public class Demo2 implements Car{
    @Override
    public void run() {
        System.out.println("Demo2 再跑！");
    }
}

//Demo3类，继承Car接口
public class Demo3 implements Car{
    @Override
    public void run() {
        System.out.println("Demo3 再跑！");
    }
}

//CarFactory接口
public interface CarFactory {
    Car createCar();
}

//Demo1Factory类，工厂类，继承CarFactory接口
public class Demo1Factory implements CarFactory{
    @Override
    public Car createCar() {
        return new Demo1();
    }
}

//Demo2Factory类，工厂类，继承CarFactory接口
public class Demo2Factory implements CarFactory{
    @Override
    public Car createCar() {
        return new Demo2();
    }
}

//Demo3Factory类，工厂类，继承CarFactory接口
public class Demo3Factory implements CarFactory{
    @Override
    public Car createCar() {
        return new Demo3();
    }
}
````



**根据设计理论建议：工厂方法模式。但实际上，一般使用简单工厂模式。**

****

**三、抽象工厂模式** （不可以增加产品，可以增加产品族）

````java
//Engine接口
public interface Engine {
    void run();
    void start();
}

class LuxuryEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的快！");
    }

    @Override
    public void start() {
        System.out.println("启动快！可以自动启停！");
    }
}

class LowEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的慢！");
    }

    @Override
    public void start() {
        System.out.println("启动慢！");
    }
}

//Seat接口
public interface Seat {
    void massage();
}

class LuxurySeat implements Seat {

    @Override
    public void massage() {
        System.out.println("可以自动按摩");
    }
}

class LowSeat implements Seat {

    @Override
    public void massage() {
        System.out.println("不能自动按摩");
    }
}

//Tyre接口
public interface Tyre {
    void revolve();
}

class LuxuryTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("旋转不磨损！");
    }
}

class LowTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("旋转磨损快！");
    }
}

//CarFactory接口
public interface CarFactory {
    Engine createEngine();
    Seat createSeat();
    Tyre createTyre();
}

//LowCarFactory类，继承了CarFactory接口
public class LowCarFactory implements CarFactory{
    @Override
    public Engine createEngine() {
        return new LowEngine();
    }

    @Override
    public Seat createSeat() {
        return new LowSeat();
    }

    @Override
    public Tyre createTyre() {
        return new LowTyre();
    }
}

//LuxuryCarFactory类，继承了CarFactory接口
public class LuxuryCarFactory implements CarFactory{
    @Override
    public Engine createEngine() {
        return new LuxuryEngine();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }
}

//Client类，测试
public class Client {
    public static void main(String[] args) {
        CarFactory factory = new LowCarFactory();
        Engine e = factory.createEngine();
        e.run();
        e.start();
    }
}
````



****

**四、建造者模式**

**本质：实现了组件的单独构造(Builder负责)和装配(Director负责)的分离，从而可以构造出复杂的对象**

````java
//AirShipBuilder接口,是我们的建造者的接口
public interface AirShipBuilder {
    Engine builderEngine();
    OrbitalModule builderOrbitalModule();
    EscapeTower builderEscapeTower();
}

//AirShipDirector接口,是我们的装配者的接口
public interface AirShipDirector {
    //组装飞船对象
    AirShip directAirShip();
}

//ythAirShipBuilder类,实现了AirShipBuilder接口
public class ythAirShipBuilder implements AirShipBuilder{

    @Override
    public Engine builderEngine() {
        System.out.println("构建尹天豪的发动机！");
        return new Engine("尹天豪牌发动机");
    }

    @Override
    public OrbitalModule builderOrbitalModule() {
        System.out.println("构建尹天豪的轨道舱模块！");
        return new OrbitalModule("尹天豪的轨道舱模块");
    }

    @Override
    public EscapeTower builderEscapeTower() {
        System.out.println("构建尹天豪的逃逸塔！");
        return new EscapeTower("尹天豪的逃逸塔");
    }
}

//ythAirShipDirector类,实现了AirShipDirector接口
public class ythAirShipDirector implements AirShipDirector{
    private AirShipBuilder builder;

    public ythAirShipDirector(AirShipBuilder builder) {
        this.builder = builder;
    }

    @Override
    public AirShip directAirShip() {
        Engine e = builder.builderEngine();
        OrbitalModule o = builder.builderOrbitalModule();
        EscapeTower et = builder.builderEscapeTower();
        //装配飞船对象
        AirShip ship = new AirShip();
        ship.setEngine(e);
        ship.setEscapeTower(et);
        ship.setOrbitalModule(o);

        return ship;
    }
}

//AirShip类,是我们的飞船类,里边包含了发动机类,逃逸塔类,轨道舱模块类
public class AirShip {
    //轨道舱模块
    private OrbitalModule orbitalModule;
    //发动机
    private Engine engine;
    //逃逸塔
    private EscapeTower escapeTower;

    public OrbitalModule getOrbitalModule() {
        return orbitalModule;
    }

    public void setOrbitalModule(OrbitalModule orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public EscapeTower getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(EscapeTower escapeTower) {
        this.escapeTower = escapeTower;
    }
}

class OrbitalModule {
    private String name;

    public OrbitalModule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Engine {
    private String name;

    public Engine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class EscapeTower {
    private String name;

    public EscapeTower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//Client类,该类只需要用到AirShip类和ythAirShipDirector类,通过后者进行飞船的组装,通过前者进行组装好的飞船的调用即可
public class Client {
    public static void main(String[] args) {
        AirShipDirector director = new ythAirShipDirector(new ythAirShipBuilder());

        AirShip ship = director.directAirShip();

        System.out.println(ship.getEngine().getName());
        System.out.println(ship.getEscapeTower().getName());
        System.out.println(ship.getOrbitalModule().getName());
    }
}
````

****

**五、原型模式** （prototype），也叫克隆模式，拷贝模式

原型模式实现：Cloneable接口和clone方法 

````java
//创建类，继承Cloneable接口，并且重写clone方法，此时是浅克隆
public class Sheep implements Cloneable{
    private String name;
    private Date birthday;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        return obj;
    }

    public Sheep() {
    }

    public Sheep(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

//要实现深克隆，就要把属性也克隆，修改Sheep类中重写的clone方法
protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        //添加如下代码实现深克隆
        Sheep s = (Sheep) obj;
        //相当于把属性也克隆
        s.birthday = (Date) this.birthday.clone();
        return obj;
 }

//创建类去测试，可以分别测试深克隆和浅克隆。深克隆改变date前后输出不同，浅克隆相同
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        //浅克隆
        Date date = new Date(415469146548L);
        Sheep sheep1 = new Sheep("名趣",date);
        Sheep sheep2 = (Sheep) sheep1.clone();

        System.out.println(sheep1);
        System.out.println(sheep1.getName() + " " + sheep1.getBirthday());
        System.out.println(sheep2);
        System.out.println(sheep2.getName() + " " + sheep2.getBirthday());

        date.setTime(89487151648L);

        System.out.println(sheep1);
        System.out.println(sheep1.getName() + " " + sheep1.getBirthday());
        System.out.println(sheep2);
        System.out.println(sheep2.getName() + " " + sheep2.getBirthday());
    }
}

````

通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式

