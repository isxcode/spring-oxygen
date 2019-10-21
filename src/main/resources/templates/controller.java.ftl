<#-- package -->
package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package.Service}.${table.serviceName};
<#-- 使用启动RestController -->
<#if restControllerStyle>
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#-- 导入其他的包 -->
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} Api
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${table.name}</#if>/<#if controllerMappingHyphenStyle??>${table.name}<#else>${table.name}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
 <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    <#-- 自定义controller内容 -->

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Autowired
    public ${table.controllerName}(${table.serviceName} ${table.serviceName?uncap_first}) {
        this.${table.serviceName?uncap_first} = ${table.serviceName?uncap_first};
    }

}
</#if>
