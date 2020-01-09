package ${packageName};

<#list importPackages as package>
import ${package};
</#list>

<#if (baseClassList.entity)??>
import ${baseClassList.entity};
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ${tableComment!} Entity
 *
 * @author ${author}
 * @since ${date}
 */
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "${tableName}")
<#if (baseClassList.entity)??>
public class ${className} extends BaseEntity implements Serializable{
<#else>
public class ${className} implements Serializable{
</#if>

    private static final long serialVersionUID = 1L;
<#-- 遍历字段 -->
<#list fieldList as field>

    /**
     * ${field.comment}
     */
    private ${field.type} ${field.field};
</#list>

}
