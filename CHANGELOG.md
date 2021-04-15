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
- 日志打印可以配置

### 🎨 优化

- 优化打包大小，提出lombok以来
- 添加系统性测试
- 项目启动时,扫描创建所有类属性对象
- rowId没有值的时候，自动补齐雪花id

### 🐛 修复

- 修复boolean类型无法插入问题
- 修复Date类型无法直接插入问题
