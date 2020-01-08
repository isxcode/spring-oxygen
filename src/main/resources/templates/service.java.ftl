<#--package ${package.Service};-->

<#--import org.springframework.stereotype.Component;-->
<#--import ${package.Entity}.${entity};-->
<#--import ${superServiceClassPackage};-->

<#--/**-->
<#-- * ${table.comment!} Service-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @since ${date}-->
<#-- */-->
<#--<#if kotlin>-->
<#--interface ${table.serviceName} : ${superServiceClass}<${entity}>-->
<#--<#else>-->
<#--@Component-->
<#--public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {-->

<#--}-->
<#--</#if>-->
