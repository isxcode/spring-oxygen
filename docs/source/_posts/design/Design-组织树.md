---
title: Design-部门组织树
subtitle: Design-部门组织树
tags:
  - design
categories: Design
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/design/design.png'
date: 2020-05-28 10:52:59
---
## 🙋 Question

- ![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/design/328DC67D.png)
- 需要实现功能
1. 树形部门查询
2. 部门拖拽排序
3. 部门动态名称拼接

## 💡 Idea

#### 数据库设计

- `xdap_department` | 部门信息表 

| 字段 | 描述 | 类型 | 为空 | 主键 | 默认值 |
| --- | --- | --- | --- | --- | --- |
| id | 部门雪花id  | varchar2(50) | 非空 | 主键| |
| name | 部门名称  | varchar2(100) | 非空 | | |
| parent_id | 父级部门雪花id  | varchar2(50) |  | | |
| status | 部门状态  | varchar2(100) | 非空 | | 'ENABLE' |
| manager | 部门管理者  | varchar2（50) | | | |
| structure_code | 结构id(.拼接)  | varchar2（500) | | | |
| structure_name | 结构名称（.拼接）  | varchar2（500) | | | |
| level | 部门层级  | int | 非空 | | 0 |
| order | 同级部门排序  | int | 非空 | | 0 |
| owner | 系统字段  | varchar2(50) | 非空| | |
| created_by | 系统字段  | varchar2(50) |非空 | | |
| creation_date | 系统字段  | datetime |非空 | | |
| last_updated_by | 系统字段  | varchar2(50) |非空 | | |
| last_update_date | 系统字段  | datetime |非空 | | |
| object_version_number | 系统字段  | int |非空 | | |

#### 代码层设计

```java
package com.xdap.app.moudle.department.service;

import com.xdap.app.moudle.department.dao.DepartmentDao;
import com.xdap.app.moudle.department.pojo.dto.QueryDepartmentResDTO;
import com.xdap.app.moudle.department.pojo.entity.XdapDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门组织服务层
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {

        this.departmentDao = departmentDao;
    }

    /**
     * 查询所有部门组织树
     *
     * @since 0.0.1
     */
    public List<QueryDepartmentResDTO> queryDepartment() {

        // 查询所有的部门组织
        List<XdapDepartment> allDepartments = departmentDao.queryDepartment();

        // 初始化返回变量
        List<QueryDepartmentResDTO> departmentResult = new ArrayList<>();

        // 取出起点部门,即是没有父级id的数据
        List<XdapDepartment> rootDepartments = allDepartments.stream()
                .filter(v -> v.getParentId() == null)
                .collect(Collectors.toList());

        // 执行递归遍历生成树形部门组织树
        parseDepartmentList(rootDepartments, departmentResult, allDepartments);

        return departmentResult;
    }

    /**
     * 递归解析部门组织树
     *
     * @param departmentResult 部门组织树的DTO结果
     * @param departments      部门DO的list
     * @param allDepartments   所有部门信息
     * @since 0.0.1
     */
    public void parseDepartmentList(List<XdapDepartment> departments, List<QueryDepartmentResDTO> departmentResult, List<XdapDepartment> allDepartments) {

        // 遍历父级部门
        for (XdapDepartment metaDepartment : departments) {

            // 获取子部门
            List<XdapDepartment> nextCollect = allDepartments.stream()
                    .filter(v -> v.getParentId() != null && v.getParentId().equals(metaDepartment.getId()))
                    .sorted(Comparator.comparing(XdapDepartment::getDepartmentOrder))
                    .collect(Collectors.toList());

            // 组装返回对象
            QueryDepartmentResDTO metaDepartmentResult = new QueryDepartmentResDTO();
            metaDepartmentResult.setChildren(new ArrayList<>());
            BeanUtils.copyProperties(metaDepartment, metaDepartmentResult);
            departmentResult.add(metaDepartmentResult);

            // 如果没有子部门的结束
            if (!nextCollect.isEmpty()) {
                parseDepartmentList(nextCollect, metaDepartmentResult.getChildren(), allDepartments);
            }
        }

    }

}
```



## 📝 Solution

🏳️‍🌈🏳️‍🌈🏳️‍🌈 组织树对于一家企业而言太重要了,可灵活则配置非常重要

## 🔍 Reference
