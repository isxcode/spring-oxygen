---
hide: true
---


# spring-security doc

## spring官方使用说明

1- 登录身份认证
- 配置 WebSecurityConfigurerAdapter
- 进入 UsernamePasswordAuthenticationFilter
- 创建 UsernamePasswordAuthenticationToken
- 进入 ProviderManager
- 调用 AuthenticationProvider
- 通过 UserDetailsService 获取用户的真实信息
- 对用户检查校验
- 对用户授权

> 重构说明
* 配置 WebSecurityConfigurerAdapter 设置拦截器 
* 实现 AbstractAuthenticationProcessingFilter 拦截登录接口
* 实现 AuthenticationManager 配置处理器
* 实现 UserDetailsService 实现数据库获取用户信息
* 实现 AuthenticationProvider 校验授权用户

> 建表
权限表
角色表
权限角色关联表
用户表
用户角色关联表

2- token身份认证
WebSecurityConfigurerAdapter
BasicAuthenticationFilter

