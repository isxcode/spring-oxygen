package ${packageName};

<#list entityPackageList as package>
import ${package};
</#list>
import ${freecodeProperties.baseEntityClass};
import lombok.EqualsAndHashCode;
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
@TableName("${tableName}")
@EqualsAndHashCode(callSuper = true)
public class ${className?cap_first}Entity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    <#-- 遍历字段 -->
    <#list tableColumns as field>
        <#if (field.comment)??>
    /**
     * ${field.comment}
     */
        </#if>
    @ColumnName("${field.originField}")
    private ${field.type} ${field.field};

    </#list>
}
