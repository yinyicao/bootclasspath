README
======

Demo app to show how to use java `-Xbootclasspath/a` option.

How to run
----------

Package first:

```bash
# In project root path
$ mvn clean package -DskipTests
```

Run spring boot app:

```bash
$ java -jar app/target/app-0.0.1-SNAPSHOT.jar
```

Access http://localhost:8080 after started:

```bash
$ curl localhost:8080
User count: 3
Country count: 151
```

Mybatis-plus helps to avoid exception
-------------------------------------

If Mybatis-plus is used, we could do the same thing as in `mybatis-override` branch easier, 
cause `com.baomidou.mybatisplus.core.MybatisConfiguration` already override `addMappedStatement` method.

```bash
$ java -Xbootclasspath/a:./hacked/target/classes/sql -jar app/target/app-0.0.1-SNAPSHOT.jar --mybatis-plus.mapper-locations=classpath*:db/mapper/*Mapper.xml
```

Access http://localhost:8080 , notice that `Country count` changed from 151 to 26:

```bash
$ curl localhost:8080
User count: 3
Country count: 26
```