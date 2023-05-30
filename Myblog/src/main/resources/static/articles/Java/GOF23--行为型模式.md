设计模式GOF23 (Group of Four)

**创建型模式**：单例模式、工厂模式、抽象工厂模式、建造者模式、原型模式

**结构型模式**：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式

**行为型模式**：模板方式模式、命令模式、迭代器模式、观察者模式、中介者模式、备忘录模式、解释器模式、状态模式、策略模式、职责链模式、访问者模式

****

**行为型模式：**以后用的多的是，除了命令模式、解释器模式、访问者模式外，用的都挺多

核心作用：关注系统中对象之间的相互交互，研究系统在运行时对象之间的相互通信和协作，进一步明确对象的职责

****

**一、职责链模式** (chain of responsibility)

**定义：**将能够处理同一类请求的对象连成一条链，所提交的请求沿着链传递，链上的对象逐个判断是否有能力处理该请求，如果能则处理，如果不能则传递给链上的下一个对象

**细节：**抽象处理者，具体处理者

````java
//封装请假的基本信息
public class LeaveRequest {
    private String empName;
    private int leaveDays;
    private String reason;

    public LeaveRequest(String empName, int leaveDays, String reason) {
        this.empName = empName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

//抽象类，抽象处理者
public abstract class Leader {
    protected String name;
    //责任链上的后继对象
    protected Leader nextLeader;

    public Leader(String name) {
        this.name = name;
    }
    //设定责任链的后继对象
    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }
    //处理请求的核心的业务方法
    public abstract void handleRequest(LeaveRequest request);
}

//具体处理者，主任
public class Director extends Leader{

    public Director(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() < 3) {
            System.out.println("员工：" + request.getEmpName() + "请假，天数：" + request.getLeaveDays() + "，理由：" + request.getReason());
            System.out.println("主任：" + this.name + "，审批通过！");
        }else {
            if (this.nextLeader != null) {
                this.nextLeader.handleRequest(request);
            }
        }
    }
}

//具体处理者，经理
public class Manager extends Leader{
    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (request.getLeaveDays() < 10) {
            System.out.println("员工：" + request.getEmpName() + "请假，天数：" + request.getLeaveDays() + "，理由：" + request.getReason());
            System.out.println("经理：" + this.name + "，审批通过！");
        }else {
            System.out.println("莫非" + request.getEmpName() + "想辞职?");
        }
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Leader a = new Director("yth");
        Leader b = new Manager("mx");
        //组织责任链关系
        a.setNextLeader(b);
        //开始请假操作
        LeaveRequest request = new LeaveRequest("小刘",1,"通宵去");
        a.handleRequest(request);
    }
}
````

****

**二、迭代器模式** (iterator)

**场景：**提供一种可以遍历聚合对象的方式，又称为 cursor 模式，聚合对象（存储数据），迭代器（遍历数据）

 ````java
//自定义的迭代器接口
public interface MyIterator {
    //将游标指向第一个元素
    void first();
    //将游标指向下一个元素
    void next();
    //判断是否存在下一个元素
    boolean hasNext();
    //判断是否是第一个元素
    boolean isFirst();
    //判断是否是最后一个元素
    boolean isLast();
    //获取当前游标指向的对象
    Object getCurrrentObj();
}

//自定义的聚合类
public class ConcreteMyAggregate {
    private List<Object> list = new ArrayList<>();

    public void addObject(Object obj) {
        this.list.add(obj);
    }

    public void removeObject(Object obj) {
        this.list.remove(obj);
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
    //获得迭代器的方法
    public MyIterator createIterator() {
        return new ConcreteIterator();
    }

    //使用内部类定义迭代器，可以直接使用外部类的属性
    private class ConcreteIterator implements MyIterator{
        //定义一个游标，用于记录遍历时的位置
        private int cursor;

        @Override
        public void first() {
            cursor = 0;
        }

        @Override
        public void next() {
            if (cursor < list.size()) {
                cursor++;
            }
        }

        @Override
        public boolean hasNext() {
            if (cursor < list.size()) {
                return true;
            }
            return false;
        }

        @Override
        public boolean isFirst() {
            return cursor == 0 ? true : false;
        }

        @Override
        public boolean isLast() {
            return cursor == (list.size() - 1) ? true : false;
        }

        @Override
        public Object getCurrrentObj() {
            return list.get(cursor);
        }
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        ConcreteMyAggregate cma = new ConcreteMyAggregate();
        cma.addObject("aa");
        cma.addObject("bb");
        cma.addObject("cc");
        MyIterator iterator = cma.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.getCurrrentObj());
            iterator.next();
        }
    }
}
 ````

****

**三、中介者模式** (Mediator)

**核心：**如果一个系统中对象之间的联系呈现为网状结构，对象之间存在大量多对多关系，将导致关系极其复杂，这些对象称为"同事对象"。我们可以引入一个中介者对象，使各个同事对象只跟中介者对象打交道，将复杂的网络结构化解为如下的星形结构。

**本质：**解耦多个同事对象之间的交互关系。每个对象都持有中介者对象的引用，只跟中介者对象打交道。我们通过中介者对象统一管理这些交互关系。

````java
//中介者
public interface Mediator {
    void register(String name,Department d);

    void command(String dname);
}

//同事类的接口
public interface Department {
    //做本部门的事情
    void selfAction();
    //想总经理发出申请
    void outAction();
}

//中介者
public class President implements Mediator{
    private Map<String,Department> map = new HashMap<>();

    @Override
    public void register(String name, Department d) {
        map.put(name,d);
    }

    @Override
    public void command(String dname) {
        map.get(dname).selfAction();
    }
}

//财务部
public class Finacial implements Department{
    //持有中介者(总经理)的引用
    private Mediator m;

    public Finacial(Mediator m) {
        this.m = m;
        m.register("finacial",this);
    }

    @Override
    public void selfAction() {
        System.out.println("数钱！");
    }

    @Override
    public void outAction() {
        System.out.println("汇报工作！钱太多了，怎么花?");
        m.command("development");
    }
}

//研发部
public class Development implements Department{
    //持有中介者(总经理)的引用
    private Mediator m;

    public Development(Mediator m) {
        this.m = m;
        m.register("development",this);
    }

    @Override
    public void selfAction() {
        System.out.println("专心科研，开发项目！");
    }

    @Override
    public void outAction() {
        System.out.println("汇报工作！需要资金支持！");
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Mediator m = new President();
        Development development = new Development(m);
        Finacial finacial = new Finacial(m);
        finacial.selfAction();
        //财务部与研发部的交互，通过总经理President(中介者)
        finacial.outAction();
    }
}
````

****

**四、命令模式** (command)

**介绍：**命令模式：将一个请求封装为一个对象，从而使我们可用不同的请求对客户进行参数化。

**结构：**：抽象命令类，具体命令类，调用者/请求者，接收者，客户类

**常见的场景：**命令的撤销和恢复

****

**五、解释器模式** (Interpreter)

**介绍：**用于描述如何构成一个简单的语言解释器，主要用于使用面向对象语言开发的编译器和解释器设计。

**常见的场景：**EL表达式的处理，正则表达式解释器，SQL语法的解释器

****

**六、访问者模式** (Visitor)

**定义：**表示一个作用于某对象结构中的各元素的操作，它使我们可以在不改变元素的类的前提下定义作用于这些元素的新操作。

****

**七、策略模式** (strategy)

**定义：**策略模式对应于解决某一个问题的一个算法族，允许用户从该算法族中任选一个算法解决某一问题，同时可以方便的更换算法或者增加新的算法。并且由客户端决定调用那个算法。

**本质：**分离算法，选择实现

````java
//策略接口
public interface Strategy {
    public double getPrice(double standarPrice);
}

//新顾客少量购买
public class NewCustomerFewStrategy implements Strategy{
    @Override
    public double getPrice(double standarPrice) {
        System.out.println("不打折！原价");
        return standarPrice;
    }
}

//新顾客大量购买
public class NewCustomerManyStrategy implements Strategy{
    @Override
    public double getPrice(double standarPrice) {
        System.out.println("打九折！");
        return standarPrice * 0.9;
    }
}

//老顾客少量购买
public class OldCustomerFewStrategy implements Strategy{
    @Override
    public double getPrice(double standarPrice) {
        System.out.println("打八五折！");
        return standarPrice * 0.85;
    }
}

//老顾客大量购买
public class OldCustomerManyStrategy implements Strategy{
    @Override
    public double getPrice(double standarPrice) {
        System.out.println("打八折！");
        return standarPrice * 0.8;
    }
}

//负责和具体的策略类去交互
public class Context {
    //当前采用的算法
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void printPrice(double s) {
        System.out.println("您该报价" + strategy.getPrice(s));
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Context c1 = new Context(new NewCustomerFewStrategy());
        Context c2 = new Context(new NewCustomerManyStrategy());
        Context c3 = new Context(new OldCustomerFewStrategy());
        Context c4 = new Context(new OldCustomerManyStrategy());
        c1.printPrice(100);
        c2.printPrice(100);
        c3.printPrice(100);
        c4.printPrice(100);
    }
}
````

**常见的场景：**JavaSE中的GUI编程中的布局管理

****

**八、模板方法模式** (template method)

**定义：**定义了一个操作中的算法骨架，将某些步骤延迟到子类中实现。

**核心：**处理步骤父类中定义好，具体实现延迟到子类中定义。

````java
//父类
public abstract class BankTemplateMethod {
    public void takeNumber() {
        System.out.println("取号排队");
    }

    public abstract void transact();

    public void end() {
        System.out.println("反馈评分");
    }
    //模板方法
    public final void process() {
        takeNumber();
        transact();
        end();
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        BankTemplateMethod btm = new DrawMoney();
        btm.process();
        //采用匿名内部类，子类
        BankTemplateMethod btm1 = new BankTemplateMethod() {
            @Override
            public void transact() {
                System.out.println("我要存钱！！！");
            }
        };
        btm1.process();
    }
}

//取款业务，子类
class DrawMoney extends BankTemplateMethod {
    @Override
    public void transact() {
        System.out.println("我来取款了！！！");
    }
}
````

**方法回调（钩子方法）：**子类不能调用父类，而通过父类调用子类，调用步骤在父类中已经写好了，完全由父类控制整个过程。

**什么时候使用：**实现一个算法时，整体步骤很固定。但是，某些部分易变。易变部分可以抽象出来，供子类实现。

****

**九、状态模式** (state)  当你需要频繁的修改状态时，考虑状态模式

**核心：**用于解决系统中复杂对象的状态转换以及不同状态下行为的封装问题

**结构：**环境类，抽象状态类，具体状态类

````java
//状态接口
public interface State {
    void handle();
}
//空闲状态
class FreeState implements State{
    @Override
    public void handle() {
        System.out.println("房间空闲，无人住");
    }
}
//已预定状态
class BookedState implements State {
    @Override
    public void handle() {
        System.out.println("房间已预订，别人不能订！");
    }
}
//已入住状态
class CheckedInState implements State {
    @Override
    public void handle() {
        System.out.println("房间已有人入住！");
    }
}

//上下文类，用于标志当前状态，也用于切换状态
public class Context {
    private State state;

    public void setState(State state) {
        System.out.println("修改状态！");
        this.state = state;
        state.handle();
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.setState(new FreeState());
        ctx.setState(new BookedState());
        ctx.setState(new CheckedInState());
    }
}
````

常见的场景：银行系统中账号状态的管理，线程对象各状态之间的切换。

****

**十、观察者模式** (Observer)

**核心：**主要用于1：N的通知，当一个对象的状态变化时，他需要及时告知一系列对象，令他们做出响应。通知观察者的方式：推，观察值只能被动接受。拉，观察者自主获取内容。

**分类：**目标对象，观察者对象

````java
//观察者接口
public interface Observer {
    void update(Subject sub);
}
//观察者的具体实现类
class ObserverA implements Observer {
    //myState需要跟目标对象的state值保持一致！
    private int myState;

    @Override
    public void update(Subject sub) {
        myState = ((ConcreteSubject)sub).getState();
    }

    public int getMyState() {
        return myState;
    }

    public void setMyState(int myState) {
        this.myState = myState;
    }
}

//主题对象，目标对象
public class Subject {
    protected List<Observer> list = new ArrayList<>();

    public void register(Observer obs) {
        list.add(obs);
    }

    public void remove(Observer obs) {
        list.remove(obs);
    }
    //通知所有的观察者，更新状态
    public void notifyAllObserver() {
        for (Observer obs : list) {
            obs.update(this);
        }
    }
}

//目标对象，主题对象的子类
public class ConcreteSubject extends Subject{
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        //主题对象（目标对象）值发生了变化，请通知所有的观察者
        this.notifyAllObserver();
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        //目标对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建多个观察者
        ObserverA obs1 = new ObserverA();
        ObserverA obs2 = new ObserverA();
        ObserverA obs3 = new ObserverA();
        //将这三个观察者添加到subject对象的观察者队伍中
        subject.register(obs1);
        subject.register(obs2);
        subject.register(obs3);
        //改变subject的状态
        subject.setState(3000);
        //看观察者的状态是否改变
        System.out.println(obs1.getMyState());
        System.out.println(obs2.getMyState());
        System.out.println(obs3.getMyState());
    }
}
````

**JavaSE提供了Observable和Observer来实现观察者模式**

````java
//目标对象
public class ConcreteSubject extends Observable {
    private int state;

    public void set(int state) {
        //目标对象的状态发生了改变
        this.state = state;
        //表示目标对象发生了更改
        setChanged();
        //通知观察者
        notifyObservers(state);
    }

    public int getState() {
        return state;
    }
}

//观察者对象的具体实现类
public class ObserverA implements Observer {
    private int myState;

    @Override
    public void update(Observable o, Object arg) {
        myState = ((ConcreteSubject)o).getState();
    }

    public int getMyState() {
        return myState;
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        //创建目标对象Observable
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者
        ObserverA obs1 = new ObserverA();
        ObserverA obs2 = new ObserverA();
        ObserverA obs3 = new ObserverA();
        //将观察这对象添加到目标对象的观察者容器中
        subject.addObserver(obs1);
        subject.addObserver(obs2);
        subject.addObserver(obs3);
        //改变subject对象的状态
        subject.set(3000);
        //看观察者的状态是否发生了变换
        System.out.println(obs1.getMyState());
        System.out.println(obs2.getMyState());
        System.out.println(obs3.getMyState());
    }
}
````

**常见的场景：**Servlet中，监听器的实现。邮件订阅。京东商城中，群发某商品打折信息。

****

**十一、备忘录模式** (memento)

**核心：**保存某个对象内部状态的拷贝，这样以后就可以将该对象恢复到原先的状态。

**结构：**源发器类，备忘录类，负责人类。

````java
//源发器类
public class Emp {
    private String name;
    private int age;
    private double salary;

    //进行备忘操作，并返回备忘录对象
    public EmpMemento memento() {
        return new EmpMemento(this);
    }

    //进行数据回复，恢复成指定备忘录对象的值
    public void recovery(EmpMemento mmt) {
        this.name = mmt.getName();
        this.age = mmt.getAge();
        this.salary = mmt.getSalary();
    }

    public Emp(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

//备忘录类
public class EmpMemento {
    private String name;
    private int age;
    private double salary;

    public EmpMemento(Emp e) {
        this.name = e.getName();
        this.age = e.getAge();
        this.salary = e.getSalary();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

//负责人类，负责管理我们的备忘录对象
public class CareTaker {
    private EmpMemento memento;

    public EmpMemento getMemento() {
        return memento;
    }

    public void setMemento(EmpMemento memento) {
        this.memento = memento;
    }
}

//客户类
public class Client {
    public static void main(String[] args) {
        CareTaker taker = new CareTaker();
        Emp emp = new Emp("尹天豪",20,2500);
        System.out.println("第一次创建对象：" + emp.getName() + "，" + emp.getAge() + "，" + emp.getSalary());
        //备忘一次
        taker.setMemento(emp.memento());
        emp.setAge(38);
        emp.setName("尹天豪");
        emp.setSalary(5000);
        System.out.println("第二次打印对象：" + emp.getName() + "，" + emp.getAge() + "，" + emp.getSalary());
        //回复到备忘录对象保存的状态
        emp.recovery(taker.getMemento());
        System.out.println("第三次打印对象：" + emp.getName() + "，" + emp.getAge() + "，" + emp.getSalary());
    }
}
````

**常见的场景：**棋类游戏中的悔棋。数据库软件中的事务管理中的，回滚操作。