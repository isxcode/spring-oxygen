package ${autoCodeProperties.modulePath};

import ${autoCodeProperties.modulePath};

<#--<#list autoCodeProperties.baseClassList.keySet() as key>-->
<#--    ${autoCodeProperties.baseClassList[key]}-->
<#--</#list>-->
<#--<#list products as name, price>-->
<#--<p>${name}: ${price}-->
<#--    </#list>-->

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * ${tableComment!} Controller
 *
 * @author ${autoCodeProperties.author}
<#-- * @since ${autoCodeProperties.date}-->
 */
@Slf4j
@RestController
@RequestMapping("${className}")
public class ${className}Controller extends BaseController {

    public final ${className}Service ${className}Service;

    public ${className}Controller(${className}Service ${className}Service) {

        this.${className}Service = ${className}Service;
    }

    /**
     * 查询${tableComment!}
     *
<#--     * @since ${autoCodeProperties.date}-->
     */
    @PostMapping("query${className}")
    public ResponseEntity<BaseResponse> query${className}() {

        return successResponse("查询${tableComment!}成功", ${className}Service.query${className}());
    }


}