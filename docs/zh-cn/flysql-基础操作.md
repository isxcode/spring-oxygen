### Flysql注入

```java
public class demo {

    public final Flysql flysql;

    public demo(Flysql flysql) {
        this.flysql = flysql;
    }
}
```

### 增

```java
public void save(MetaData metaData) {
    
    flysql.build().insert(MetaData.class)
        .save(metaData);
}
```

### 删

```java
public void delete(String id) {
    
    flysql.build().delete(MetaData.class)
        .eq("id",id)
        .doDelete();
}
```

### 改 

```java
public void update(String id, String name) {
    
    flysql.build().update(MetaData.class)
        .eq("id",id)
        .update("name",name)   
        .doUpdate();
}
```

### 列表查询

```java
public List<MetaData> query(String name) {
    
    flysql.build().select(MetaData.class)
        .eq("name",name)    
        .query();
}
```

### 单条查询

```java
public MetaData getOne(String name) {
    
    return flysql.build().select(MetaData.class)
                .eq("name",name)    
                .getOne();
}
```
