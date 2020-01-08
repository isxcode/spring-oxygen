<#--package ${package};-->

<#--&lt;#&ndash;<#list table.importPackages as pkg>&ndash;&gt;-->
<#--&lt;#&ndash;import ${pkg};&ndash;&gt;-->
<#--&lt;#&ndash;</#list>&ndash;&gt;-->

<#--import lombok.Data;-->
<#--import lombok.EqualsAndHashCode;-->
<#--import lombok.experimental.Accessors;-->
<#--import lombok.NoArgsConstructor;-->
<#--import org.springframework.stereotype.Component;-->
<#--import java.io.Serializable;-->
<#--import java.time.LocalDateTime;-->

<#--/**-->
<#-- * Entity-->
<#-- *-->
<#--&lt;#&ndash; * @author ${author}&ndash;&gt;-->
<#--&lt;#&ndash; * @since ${date}&ndash;&gt;-->
<#-- */-->
<#--@NoArgsConstructor-->
<#--@Component-->
<#--@Data-->
<#--@Accessors(chain = true)-->
<#--public class ${fileName} {-->

<#--   private static final long serialVersionUID = 1L;-->

<#--<#list tableColumns as field>-->

<#--    /**-->
<#--     * ${field.comment}-->
<#--     */-->
<#--    private ${field.type} ${field.field};-->
<#--</#list>-->

<#--}-->
