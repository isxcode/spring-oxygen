## v0.7.3

### 💥️ Breaking Change

- nothing

### ✨ Feature

- support new database -- `sqlServer`
- add oxygen exception for any service 

### 🎨 Enhancement

- add more junit test for flysql(h2/oracle/mysql/sqlserver/mongodb)
- enhance translate sql value for oracle database
- enhance new response for BadSqlGrammarException

### 🐛 Fix

- fix oracle database can not save() function

## v0.6.7

### 💥️ Breaking Change

- change query(page,size) to queryPage(page,size) and return result become to FlysqlPage<A>

### ✨ Feature

- add saveBatch() and only support for mysql database

### 🎨 Enhancement

- nothing

### 🐛 Fix

- fix notIn() not support empty list 
- fix sql() can not contain special symbol like `'` 
- fix sql() can not support queryPage() to select 
- fix save() can not save custom column
- fix @SuccessResponse can not return resource

## v0.0.3

### 💥️ Breaking Change

- nothing

### ✨ Feature

- nothing

### 🎨 Enhancement

- @SuccessResponse properties msg not has default value
- remove baseEntity id property
- show all error trace
- custom jwtUtils jwtKey
- add jwtUtils jwtKey minutes
- add LocalDateDeserializer and LocalDateSerializer
- support sql() function and add condition

### 🐛 Fix

- fix empty object class throw exception
- fix can not use core module function
- fix insert sql can not execute
- fix datasource connect not close
- fix notIn() function not support list object

## v0.0.2

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
- fix flysql can not bean
- add String result type
