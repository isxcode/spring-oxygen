package ${packageName};

<#list importPackages as package>
import ${package};
</#list>

import com.isxcode.ispring.common.BaseEntity;
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
public class ${className} extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;
<#list tableColumns as field>

    /**
     * ${field.comment}
     */
    private ${field.type} ${field.field};
</#list>

}
