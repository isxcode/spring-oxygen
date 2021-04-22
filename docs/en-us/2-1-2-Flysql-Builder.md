#### Select()
```java
public MetaData getOne(String id) {

    return flysql.build().select(MetaData.class)
                .select("name","age")
                .eq("id", id)
                .getOne();
}
```

#### Eq()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .eq("id", id)
                .eq("name",name)
                .getOne();
}
```

#### NotEq()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .ne("id", id)
                .ne("name",name)
                .getOne();
}
```

#### Gt()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .gt("id", id)
                .getOne();
}
```

#### GtEq()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .gtEq("id", id)
                .getOne();
}
```

#### Lt()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .lt("id", id)
                .getOne();
}
```

#### LtEq()

```java
public MetaData getOne(String id, String name) {

    return flysql.build().select(MetaData.class)
                .ltEq("id", id)     
                .getOne();
}
```

#### In()

```java
public MetaData getOne(String id, String id2) {

    return flysql.build().select(MetaData.class)
                .in("id", id, id2)
                .getOne();
}
```

#### NotIn()

```java
public MetaData getOne(String id, String id2) {

    return flysql.build().select(MetaData.class)
                .notIn("id", id, id2)
                .getOne();
}
```

#### Between()

```java
public MetaData getOne(String id, String id2) {

    return flysql.build().select(MetaData.class)
                .between("id", id, id2)
                .getOne();
}
```

#### NotBetween()

```java
public MetaData getOne(String id, String id2) {

    return flysql.build().select(MetaData.class)
                .notBetween("id", id, id2)
                .getOne();
}
```

#### Like()

```java
public MetaData getOne(String name) {
    
    return flysql.build().select(MetaData.class)
                .like("name", name)
                .getOne();
}
```

#### NotLike()

```java
public MetaData getOne(String name) {

    return flysql.build().select(MetaData.class)
                .notLike("name", name)
                .getOne();
}
```

#### IsNull()

```java
public MetaData getOne(String name) {

    return flysql.build().select(MetaData.class)
                .isNull("name")
                .getOne();
}
```

#### NotNull()

```java
public MetaData getOne(String name) {

    return flysql.build().select(MetaData.class)
                .isNotNull("name")
                .getOne();
}
```
