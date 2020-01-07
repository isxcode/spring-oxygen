package ${package};

<#--<#list table.importPackages as pkg>-->
<#--import ${pkg};-->
<#--</#list>-->

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity
 *
<#-- * @author ${author}-->
<#-- * @since ${date}-->
 */
@NoArgsConstructor
@Component
@Data
@Accessors(chain = true)
public class ${fileName} {

   private static final long serialVersionUID = 1L;

<#list tableColumns as field>

    /**
     * ${field.comment}
     */
    private ${field.type} ${field.field};
</#list>

}
