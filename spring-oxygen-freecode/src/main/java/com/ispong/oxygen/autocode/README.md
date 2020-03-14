# 自动代码生成使用手册

> 前提：要使用自动代码功能，就必须配置spring-oxygen的sql集成工具

## 启动项目
> 访问接口http://localhost:8888/project-context/generateCode

```http request
POST http://localhost:port/project-context/generateCode
Content-Type: application/json

{
  "tableName": "table_name"
}
```
```yaml
isxcode:
  code:
    module-path: com.ispong.oxygen.demo  # 模块地址
    file-paths: # 依赖于模块的地址
      'controller': controller 
      'service': service
      'dao': dao
      'entity': model.entity 
    templates-path: templates # 模版地址 
    template-suffix: .ftl # 模板的后缀
    author: ispong # 作者
    ignore-fields: # 需要忽略生成的字段名
      - uuid
      - version
      - created_date
      - created_by
      - last_modified_date
      - last_modified_by
```
```json
{
  "code": "200",
  "message": "自动代码生成成功",
  "data": ""
}
```