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
 * ${tableName} controller
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@RestController
@RequestMapping("${tableName}")
<#if (freecodeProperties.baseControllerClass)??>
public class ${tableName?cap_first}Controller extends BaseController {
<#else>
public class ${tableName?cap_first}Controller {
</#if>

    public final ${tableName?cap_first}Service ${tableName}Service;

    public ${tableName?cap_first}Controller(${tableName?cap_first}Service ${tableName}Service) {

        this.${tableName}Service = ${tableName}Service;
    }

    /**
     * query ${tableName?cap_first}Entity
	 *
     * @return String
     */
    @GetMapping("query${tableName?cap_first}")
    public List<${tableName?cap_first}Entity> query${tableName?cap_first}() {

        return ${tableName}Service.query${tableName?cap_first}();
    }

}

