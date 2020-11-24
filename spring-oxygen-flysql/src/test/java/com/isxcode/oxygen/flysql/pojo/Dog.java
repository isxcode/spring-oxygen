package com.isxcode.oxygen.flysql.pojo;

import com.isxcode.oxygen.flysql.annotation.TableName;
import com.isxcode.oxygen.flysql.common.BaseEntity;

@TableName("dogs")
public class Dog extends BaseEntity {

    private String name;

    private Integer age;

    public Dog() {
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", IsDelete=" + getIsDelete() +
            ", Version=" + getVersion() +
            ", CreatedBy=" + getCreatedBy() +
            ", CreatedDate=" + getCreatedDate() +
            ", LastModifiedBy=" + getLastModifiedBy() +
            ", LastModifiedDate=" + getLastModifiedDate() +
            "}\n";
    }
}
