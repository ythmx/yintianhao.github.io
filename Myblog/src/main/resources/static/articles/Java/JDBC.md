1，MySQL数据库，做java开发以后，数据库方面用到的最多的就是MySQL

2，**JDBC (Java Database Connection)**为java开发者使用数据库提供了统一的编程接口，它由一组java类和接口组成。

3，凡是数据库想与java进行连接的，数据库厂商自己必须实现JDBC这套接口。而数据库厂商的JDBC实现，我们就叫它此数据库的**数据库驱动**

4，**访问数据库流程：**加载JDBC驱动程序，建立与数据库的连接，发送SQL查询，得到查询结果

````java
/**
 * 测试跟数据库建立连接
 */
public class Demo01 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            long start = System.currentTimeMillis();
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println(end-start);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````



5，**Statement接口：**用于执行静态SQL语句并返回它所生成结果的对象。

6，三种Statement类：

。Statement：用于发送简单的SQL语句，不带参数的

。PreparedStatement：用于发送含有一个或多个输入参数的sql语句 （可以防止SQL注入）

。CallableStatement：用于调用存储过程

7，**常用的Statement方法：**

execute()：运行语句，返回是否有结果集

executeQuery()：运行 select 语句，返回 ResultSet 结果集

executeUpdate()：运行 insert / update / delete操作，返回更新的行数

````java
/**
 * 测试执行SQL语句，以及SQL注入问题
 */
public class Demo02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");

            stmt = conn.createStatement();
            //测试执行SQL语句
            /*String sql = "insert into t_user\n" +
                    "(username,pwd,regTime)\n" +
                    "values ('小六',8888,now());";
            stmt.execute(sql);*/

            //测试SQL注入
            //该语句会导致数据库中的所有东西都被删除
            String id = "5 or 1=1";
            //这句是正常的可执行SQL语句
            //String sql = "delete from t_user where id = 5";
            //这是发生了SQL注入的语句
            String sql = "delete from t_user where id = " + id;
            stmt.execute(sql);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

8，使用PreparedStatement，来防止SQL注入

````java
/**
 * 测试PreparedStatement的基本用法
 */
public class Demo03 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");

            //? 表示占位符
            String sql = "insert into t_user (username,pwd) values (?,?)";
            ps = conn.prepareStatement(sql);
            //参数索引是从1开始计算，而不是0
            /*ps.setString(1,"yth");
            ps.setString(2,"8888");*/

            ps.setObject(1,"孟轩");
            ps.setObject(2,"6666");

            System.out.println("插入一行记录");
            int count = ps.executeUpdate();
            System.out.println(count);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

9，测试ResultSet结果集的基本用法

````java
/**
 * 测试 ResultSet 结果集的基本用法
 */
public class Demo04 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");

            //? 表示占位符
            String sql = "select id,username,pwd from t_user where id > ?";
            ps = conn.prepareStatement(sql);
            //把id大于8的记录都取出来
            ps.setObject(1,8);

            rs = ps.executeQuery();
            //底层是用迭代器实现的
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "---" + rs.getString(2) + "---" + rs.getString(3));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

10，批处理（Batch），对于大量的批处理，建议使用Statement，因为PreparedStatement的预编译空间有限，对数据量特别大时，会发生异常。

````java
/**
 * 测试批处理的基本用法
 */
public class Demo05 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");
            //将事务设为手动提交
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            //插入2w条数据
            for (int i = 0;i < 20000;i++) {
                stmt.addBatch("insert into t_user (username,pwd,regTime) values ('尹天豪"+i+"',888888,now() )");
            }
            //执行sql语句
            stmt.executeBatch();

            //最后提交事务
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

11，**事务基本概念：**一组要么同时执行成功，要么同时执行失败的sql语句。是数据库操作的一个执行单位

**事务的四大特点（ACID）**

atomicity（原子性）：表示一个事务内的所有操作是一个整体，要么全部成功，要么全失败

consistency（一致性）：表示一个事务内有一个操作失败时，所有的更改过的数据都必须回滚到修改前的状态

isolation（隔离性）：事务查看数据时数据所处的状态，要么是另一并发事务修改它之前的状态，要么是另一事务修改它之后的状态，事务不会查看中间状态的数据。

durability（持久性）：持久性事务完成之后，他对于系统的影响是永久性的。

**事务隔离级别从低到高：**

读取未提交、读取已提交、可重复读、序列化。

````java
/**
 * 测试事务
 */
public class Demo06 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");
            //在JDBC中是默认自动提交事务的，这里我们要手动提交
            conn.setAutoCommit(false);

            //? 表示占位符
            String sql = "insert into t_user (username,pwd) values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"孟轩");
            ps.setObject(2,"6666789");
            ps.execute();

            //? 表示占位符
            String sql1 = "insert into t_user (username,pwd) values (?,?,?)";
            ps1 = conn.prepareStatement(sql1);
            ps1.setObject(1,"孟轩987");
            ps1.setObject(2,"6666");
            ps1.execute();



            //手动提交事务
            conn.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

12，时间类型

java.sql.Date：只有年、月、日

java.sql.Time：只有时、分、秒

java.sql.Timestamp：有年、月、日、时、分、秒、纳秒

````java
/**
 * 测试时间处理（java.sql.Date,Time,Timestamp）
 */
public class Demo07 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");

            ps = conn.prepareStatement("insert into t_user (username,pwd,regTime) values (?,?,?)");
            ps.setObject(1,"孟轩");
            ps.setObject(2,"123654");
            //创建时间对象，Date
            //Date date = new Date(System.currentTimeMillis());
            //创建时间对象，Timestamp
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ps.setObject(3,timestamp);

            ps.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

13，CLOB（Character Large Object），用于存储大量的文本数据

大字段有些特殊，不同数据库处理的方式不一样，大字段的操作常常是以流的方式来处理的，而非一般的字段，一次即可读出数据。

BLOB（Binary Large Object），用于存储大量的二进制数据

````java
/**
 * 测试 CLOB 文本大对象的使用
 */
public class Demo07 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接，比较耗时，这是Connection对象管理的一个要点！)
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","101266");

            ps = conn.prepareStatement("insert into t_user (username,clob) values (?,?)");
            ps.setObject(1,"尹天豪");
            //将文本文件内容直接输入到数据库中
            ps.setClob(2,new FileReader("d:/a.txt"));
            ps.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
````

14，ORM（Object Relationship Mapping）基本思想

表结构跟类对应，表中字段和类的属性对应，表中记录和对象对应。