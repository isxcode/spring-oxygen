## 0.0.2

### 💥️ Breaking Change

- flysql need manual dependency injection

```java
class demo{

    private final Flysql flysql;

    public demo(Flysql flysql) {
        
        this.flysql = flysql;
    }
}
```

- flysql add new function for mongoDB

```java
flysql.buildMongo().select(MetaData.class).query()
```

### ✨ Feature

- Support new database -- `Oracle`
- Support new database -- `MongoDB`
- add new property for show sql -- `oxygen.flysql.showLog` 

### 🎨 Enhancement

- add more flysql unit test
- when rowId is null , will auto set id by snowFlakeId

### 🐛 Fix

- fix column type is `Boolean` can not insert table
- fix column type is `Date` can not insert table
