package ${packageName};

<#list entityPackageList as package>
import ${package};
</#list>
<#if (freecodeProperties.baseEntityClass)??>
import ${freecodeProperties.baseEntityClass};
import lombok.EqualsAndHashCode;
</#if>
import lombok.*;
import com.isxcode.oxygen.flysql.annotation.ColumnName;
import com.isxcode.oxygen.flysql.annotation.TableName;
import java.io.Serializable;

/**
 * Entity - ${tableComment!""}
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("${primaryTableName}")
<#if (freecodeProperties.baseEntityClass)??>
@EqualsAndHashCode(callSuper = true)
public class ${className?cap_first}Entity extends BaseEntity implements Serializable{
<#else>
public class ${className?cap_first}Entity implements Serializable{
</#if>

    private static final long serialVersionUID = 1L;

<#-- 遍历字段 -->
<#list tableColumns as field>
    /**
     * ${field.comment!""}
     */
    @ColumnName("${field.originField}")
    private ${field.type} ${field.field};

</#list>
}