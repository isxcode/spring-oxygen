package ${packageName};

<#if (freecodeProperties.baseControllerClass)??>
import ${freecodeProperties.baseControllerClass};
</#if>
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller - ${tableComment!""}
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@RestController
@RequestMapping("/${tableName}")
<#if (freecodeProperties.baseControllerClass)??>
public class ${className?cap_first}Controller extends BaseController {
<#else>
public class ${className?cap_first}Controller {
</#if>

    public final ${className?cap_first}Service ${className?uncap_first}Service;

    public ${className?cap_first}Controller(${className?cap_first}Service ${className?uncap_first}Service) {

        this.${className?uncap_first}Service = ${className?uncap_first}Service;
    }

    /**
     * query ${className?cap_first}Entity
	 *
     * @return String
     */
    @GetMapping("/query${className?cap_first}")
    public List<${className?cap_first}Entity> query${className?cap_first}() {

        return ${className?uncap_first}Service.query${className?cap_first}();
    }

}

