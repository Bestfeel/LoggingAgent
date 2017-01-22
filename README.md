###    日志框架增强

### 使用

maven 依赖

```

  <dependency>
            <groupId>com.gizwits</groupId>
            <artifactId>LoggingAnnotation</artifactId>
            <version>1.0</version>
   </dependency>
```
在我们需要的方法上加上GizwitsLogging注解

```
public class Logging {


    @GizwitsLogging
    public void parse() {

        System.out.println("Logging.parse");

    }

    @GizwitsLogging
    public Double pay(double money) {


        return money;
    }
}

```


###  打包

```
mvn  clean  compile package
```

###  运行

```
java  -javaagent:./LoggingCore/target/LoggingCore-with-dependencies-1.0.jar    -cp    ./LoggingTest/target/LoggingTest-with-dependencies-1.0.jar  com.gizwits.main.App

```

### 输出

```
01:58:34.363 [main] INFO com.gizwits.agent.LoggingInterceptor - 方法调用method:public void com.gizwits.logging.Logging.parse(),value:[],耗时:0
01:58:34.461 [main] INFO com.gizwits.agent.LoggingInterceptor - 方法调用method:public java.lang.Double com.gizwits.logging.Logging.pay(double),value:[100.0],耗时:6

```