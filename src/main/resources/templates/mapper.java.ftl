package ${package.Mapper};

import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * ${table.comment!} Dao
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Mapper
@Component
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    <#-- 自定义mapper内容 -->

}
</#if>
