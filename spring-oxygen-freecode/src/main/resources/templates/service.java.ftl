package ${packageName};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${tableName} service
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@Service
public class ${tableName?cap_first}Service {

    private final ${tableName?cap_first}Repository ${tableName}Repository;

    public ${tableName?cap_first}Service(${tableName?cap_first}Repository ${tableName}Repository){

        this.${tableName}Repository=${tableName}Repository;
    }

	/**
	 * query ${tableName?cap_first}Entity
	 *
	 * @return List[${tableName?cap_first}Entity]
	 * @since ${freecodeProperties.version}
	 */
	public List<${tableName?cap_first}Entity> query${tableName?cap_first}(){

		return ${tableName}Repository.query${tableName?cap_first}();
	}

}