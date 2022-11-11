package ${packageName};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service - ${tableComment!""}
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@Service
public class ${className?cap_first}Service implements ${className}InnerService{

    private final ${className?cap_first}Repository ${className?uncap_first}Repository;

    public ${className?cap_first}Service(${className?cap_first}Repository ${className?uncap_first}Repository){

        this.${className?uncap_first}Repository=${className?uncap_first}Repository;
    }

	/**
	 * query ${className?cap_first}Entity
	 *
	 * @return List[${className?cap_first}Entity]
	 * @since ${freecodeProperties.version}
	 */
	public List<${className?cap_first}Entity> query${className?cap_first}(){

		return ${className?uncap_first}Repository.query${className?cap_first}();
	}

}