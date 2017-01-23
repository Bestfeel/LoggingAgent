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
02:26:55.853 [main] INFO com.gizwits.logging.Logging - 方法调用method:public void com.gizwits.logging.Logging.parse(),value:[],耗时:0毫秒
02:26:55.930 [main] INFO com.gizwits.logging.Logging - 方法调用method:public java.lang.Double com.gizwits.logging.Logging.pay(double),value:[100.0],耗时:3毫秒
```




###  Attach 方式


```
java   -cp ${JAVA_HOME}/lib/tools.jar:./LoggingAttach/target/LoggingAttach-with-dependencies-1.0.jar  com.gizwits.Attach   ./LoggingCore/target/LoggingCore-with-dependencies-1.0.jar   pid

java   -cp  ./LoggingAttach/target/LoggingAttach-with-dependencies-1.0.jar  com.gizwits.Attach   /Users/feel/githome/idea/LoggingAgent/LoggingCore/target/LoggingCore-with-dependencies-1.0.jar       20328
 
```

#### 输出

```
Logging.parse
Logging.parse
Logging.parse
objc[20328]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java (0x108b434c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x1126924e0). One of the two will be used. Which one is undefined.
Logging.parse
Logging.parse
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/feel/githome/idea/LoggingAgent/LoggingTest/target/LoggingTest-with-dependencies-1.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/feel/githome/idea/LoggingAgent/LoggingCore/target/LoggingCore-with-dependencies-1.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
00:43:56.268 [main] INFO com.gizwits.logging.Logging - 方法调用method:public void com.gizwits.logging.Logging.parse(),value:[],耗时:1毫秒
00:43:56.284 [main] INFO com.gizwits.logging.Logging - 方法调用method:public java.lang.Double com.gizwits.logging.Logging.pay(double),value:[100.0],耗时:5毫秒
Logging.parse
00:43:57.290 [main] INFO com.gizwits.logging.Logging - 方法调用method:public void com.gizwits.logging.Logging.parse(),value:[],耗时:0毫秒
00:43:57.290 [main] INFO com.gizwits.logging.Logging - 方法调用method:public java.lang.Double com.gizwits.logging.Logging.pay(double),value:[100.0],耗时:0毫秒
Logging.parse
00:43:58.296 [main] INFO com.gizwits.logging.Logging - 方法调用method:public void com.gizwits.logging.Logging.parse(),value:[],耗时:1毫秒
00:43:58.296 [main] INFO com.gizwits.logging.Logging - 方法调用method:public java.lang.Double com.gizwits.logging.Logging.pay(double),value:[100.0],耗时:0毫秒
Logging.parse


```