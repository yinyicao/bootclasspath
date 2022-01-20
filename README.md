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

Only append changed mappers
---------------------------

Beyond Case 2 in main branch, which need to place all mapper files outside the jar (whether changed or not),
we could append modified mapper files only, leave unchanged mapper files still in the jar.

This time, we need to customize mybatis `org.apache.ibatis.session.Configuration` 
to avoid `java.lang.IllegalArgumentException: Mapped Statements collection already contains value xxx` exception 
to be thrown when loading same mapper file from two places.

```diff
@@ -1013,9 +1013,11 @@ public class Configuration {
     @Override
     @SuppressWarnings("unchecked")
     public V put(String key, V value) {
+      System.out.println("HACKED::Put key [" + key + "] with value [" + (value instanceof MappedStatement ? ((MappedStatement)value).getResource() : value) + "]");
       if (containsKey(key)) {
-        throw new IllegalArgumentException(name + " already contains value for " + key
+        System.out.println(name + " already contains value for " + key
             + (conflictMessageProducer == null ? "" : conflictMessageProducer.apply(super.get(key), value)));
+        return null;
       }
       if (key.contains(".")) {
         final String shortKey = getShortName(key);
```

// TODO

```bash
# Replace /Users/alphahinex/.m2/repository/org/mybatis/mybatis/3.5.9/mybatis-3.5.9.jar to your mybatis-3.5.9.jar file's path
$ java -Xbootclasspath/a:./hacked/target/classes/sql:./hacked/target/classes:/Users/alphahinex/.m2/repository/org/mybatis/mybatis/3.5.9/mybatis-3.5.9.jar -jar app/target/app-0.0.1-SNAPSHOT.jar --mybatis.mapper-locations=classpath*:db/mapper/*Mapper.xml
```

Access http://localhost:8080 , notice that `Country count` changed from 151 to 26, 
and there is only `hacked/src/main/resources/sql/db/mapper/CountryMapper.xml` one mapper file.
Case 2 has all (two) mapper files here.

```bash
$ curl localhost:8080
User count: 3
Country count: 26
```