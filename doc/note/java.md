# java

> assert 用法
```java
public class Demo{
    
    public void demo(int bit){
        // assert表达式为ture放行,反之就会被拦截
        // 需要在java启动命令中添加 -ea 参数
        assert bit<16:"bit 不能超过16位";
    }

}
```