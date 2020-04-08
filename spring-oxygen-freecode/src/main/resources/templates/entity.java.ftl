package ${packageName};

<#list entityPackageList as package>
import ${package};
</#list>

<#if (freecodeProperties.baseEntityClass)??>
import ${freecodeProperties.baseEntityClass};
import lombok.EqualsAndHashCode;
</#if>
import lombok.Data;
import com.ispong.oxygen.flysql.annotation.TableName;
import java.io.Serializable;

/**
 * ${tableName} entity
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Data
@TableName("${tableName}")
<#if (freecodeProperties.baseEntityClass)??>
@EqualsAndHashCode(callSuper = true)
public class ${tableName?cap_first}Entity extends BaseEntity implements Serializable{
<#else>
public class ${tableName?cap_first}Entity implements Serializable{
</#if>

    private static final long serialVersionUID = 1L;

<#-- 遍历字段 -->
<#list tableColumns as field>
    private ${field.type} ${field.field};

</#list>
}