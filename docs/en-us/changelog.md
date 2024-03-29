## v1.10.3

### 💥️ Breaking Change

- nothing

### ✨ Feature

- support set userId to OxygenHolder
- add matcherUtils feature
- add dateUtils() feature

### 🎨 Enhancement

- Fix code scanning alert - Equals method does not inspect argument type
- art command utils art
- art email utils art
- art freemarker utils art
- art file utils art
- art reflect utils art
- support print error info and trace feature

### 🐛 Fix

- conflict @QuartzDataSource and jdbcTemplate bug
- fix getOne() org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2 bug
- ExcelUtils not support LOCAL_DATE bug
- fix commandUtils logfile always in process bug

## v1.9.1

### 💥️ Breaking Change

- nothing

### ✨ Feature

- add isToday() and isNotToday()
- support isNotDeleted() for false deletion
- support isDeleted() for ture deletion
- add doIsDelete() for false deletion
- add andStart() and andEnd() feature
- auto update @LastModifiedDate column for executor and date feature

### 🎨 Enhancement

- optimize in() use java strong conversion

### 🐛 Fix

- fix orderBy() used before eq()
- fix andStart() use first error bug
- fix limit() error bug
- fix use between() error bug
- fix H2 use isToday() and isNotToday() error bug

## v1.2.1

### 💥️ Breaking Change

- turn down java version to 1.8

### ✨ Feature

- add file utils and support delete dir
- add command utils

### 🎨 Enhancement

- http utils doPost add default content-type `application/json;charset=UTF-8`

### 🐛 Fix

- nothing

## v0.11.5

### 💥️ Breaking Change

- nothing

### ✨ Feature

- nothing

### 🎨 Enhancement

- enhance get more exception for oxygen

```java
public class demo{ 
    public void demo1(){
        flysql.build()
                .select(NotesEntity.class)
                .eq("error_col", userId)
                .getOne();
    }
}
```
- support @successResponse for i18n

### 🐛 Fix

- fix http utils can not visit GitHub oauth url
- fix springboot-2.5.x generate plain.jar can not publish jar to maven center

## v0.8.1

### 💥️ Breaking Change

- nothing

### ✨ Feature

- update spring framework to 2.5.4

### 🎨 Enhancement

- nothing

### 🐛 Fix

- nothing

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

```text
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
