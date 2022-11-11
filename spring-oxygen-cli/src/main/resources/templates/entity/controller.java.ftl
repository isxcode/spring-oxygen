package ${packageName};

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isxcode.oxygen.flysql.response.SuccessResponse;

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
@RequestMapping("/${className?uncap_first}")
public class ${className?cap_first}Controller {

    public final ${className?cap_first}Service ${className?uncap_first}Service;

    public ${className?cap_first}Controller(${className?cap_first}Service ${className?uncap_first}Service) {

        this.${className?uncap_first}Service = ${className?uncap_first}Service;
    }

    /**
     * query ${className?cap_first}Entity
	 *
     * @return String
     */
    @SuccessResponse("Success")
    @GetMapping("/query${className?cap_first}")
    public List<${className?cap_first}Entity> query${className?cap_first}() {

        return ${className?uncap_first}Service.query${className?cap_first}();
    }

}
