README
======

Demo app to show how to use java `-Xbootclasspath/a` option.

How to run
----------

Package first:

```bash
# In project root path
$ mvn clean package
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

Case 1
------

Use custom mybatis class(`org.apache.ibatis.executor.statement.PreparedStatementHandler`) to override the same on in `mybatis-3.5.9.jar`.

```bash
# Replace /Users/alphahinex/.m2/repository/org/mybatis/mybatis/3.5.9/mybatis-3.5.9.jar to your mybatis-3.5.9.jar file's path
$ java -Xbootclasspath/a:./hacked/target/classes:/Users/alphahinex/.m2/repository/org/mybatis/mybatis/3.5.9/mybatis-3.5.9.jar -jar app/target/app-0.0.1-SNAPSHOT.jar
```

Access http://localhost:8080 and should find custom message `HACKED::PreparedStatementHandler.query` in console.

Case 2
------

Use modified `CountryMapper.xml` to change sql in jar.

```bash
$ java -Xbootclasspath/a:./hacked/target/classes/sql -jar app/target/app-0.0.1-SNAPSHOT.jar
```

Access http://localhost:8080 , notice that `Country count` changed from 151 to 26, 
because sql in `hacked/src/main/resources/sql/db/mapper/CountryMapper.xml` is different with that in `app/src/main/resources/db/mapper/CountryMapper.xml`.

```bash
$ curl localhost:8080
User count: 3
Country count: 26
```