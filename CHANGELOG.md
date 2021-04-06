## 0.0.2

### 💥️ 重大变动

- Flysql从静态导入转为注入方式
- Flysql使用语法变法

```java
class demo{
    
    private final Flysql flysql;
    
    public demo(Flysql flysql){
        this.flysql = flysql;
    }
    
    void test(){
        flysql.build().select().eq().query();    
    }   
}
```

### ✨ 新功能

- 支持新的数据源 -- Oracle
- 支持新的数据源 -- MongoDB

### 🎨 优化

- 优化打包大小，提出lombok以来
- 添加系统性测试

### 🐛 修复

- 
