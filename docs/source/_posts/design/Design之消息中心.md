---
title: Design之消息中心
subtitle: Design之消息中心
tags:
  - design
categories: Design
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/design/design.png'
excerpt: 系统设计之消息中心
date: 2020-05-23 10:00:40
---

## 🙋 问题

- 设计如下图功能

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/design/9215ef.png) 

- 实现一条消息多个跳转链接
- 实现消息中心国际化
- 实现消息中高亮字段

## 💡 方法

- 消息中心接口分析


```text
1. 消息全部/单条已读接口  
2. 消息分页分类查询接口 
3. 消息点击跳转接口
4. 消息未读数量接口
```

#### 数据库表结构设计（支持国际化）

- `xdap_msg_center` | 消息中心记录表 (记录消息的一切信息)

| 字段 | 描述 | 类型 | 为空 | 主键 | 默认值 |
| --- | --- | --- | --- | --- | --- |
| id | 消息雪花id  | varchar2(32) | 非空 | 主键| |
| user_id | 用户雪花id  | varchar2(32) | 非空 | | |
| msg_date | 消息产生时间  | datetime | 非空 | | |
| read_status | 是否已读  | int | 非空 | | 0 |
| msg_type | 消息的类型  | varchar2(50) | 非空| | |
| owner | 系统字段  | varchar2(32) | 非空| | |
| created_by | 系统字段  | varchar2(32) |非空 | | |
| creation_date | 系统字段  | datetime |非空 | | |
| last_updated_by | 系统字段  | varchar2(32) |非空 | | |
| last_update_date | 系统字段  | datetime |非空 | | |
| object_version_number | 系统字段  | int |非空 | | |

- `xdap_msg_params` | 消息中心消息附属参数表 (记录消息跳转的一切信息)

| 字段 | 描述 | 类型 | 为空 | 主键 | 默认值 |
| --- | --- | --- | --- | --- | --- |
| id | 消息参数雪花id  | varchar2(32) | 非空 | 主键| |
| msg_id | 消息雪花id  | varchar2(32) | 非空 | | |
| msg_params_type | 消息附属参数类型 | varchar2(50) | 非空 | | |
| msg_params_value | 消息附属参数value  | varchar2(200) | 非空 | | |
| owner | 系统字段  | varchar2(32) | 非空| | |
| created_by | 系统字段  | varchar2(32) |非空 | | |
| creation_date | 系统字段  | datetime |非空 | | |
| last_updated_by | 系统字段  | varchar2(32) |非空 | | |
| last_update_date | 系统字段  | datetime |非空 | | |
| object_version_number | 系统字段  | int |非空 | | |

- 接口设计 （请求头中添加国际化字段，请求头中添加token）

1. 消息全部已读/单个阅读接口

/msg/update/readMsg
```json
{
  "msgId": "为空阅读全部"
}
```

```json
{
  "code": "200"
}
```

2. 消息分页分类查询接口

/msg/query/queryMsg
```json
{
  "msgType": "SYSTEM_MSG/PERSONAL_MSG/ALL",
  "page": 1,
  "pageSize": 2
}
```

```json
{
  "data": [
    {
      "": "",
      "": "",
      "": ""
    }   
  ],
  "total": 100
}
```

3. 消息点击跳转接口

/msg/query/getMsgInfo
```json
{
  "msgId": ""
}
```

```json
{
  "data":
    {
      "msgId": "",
      "msgContent": "",
      "msgType": ""
    }   
}
```

4. 消息未读数量接口

/msg/calculate/countNoReadMsg
```text
请求头：token
```

```json
{
  "data":
    {
      "msgCount": "0"
    }   
}
```

## 📝 总结

🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- 无