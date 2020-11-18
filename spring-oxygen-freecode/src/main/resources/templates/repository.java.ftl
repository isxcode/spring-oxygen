package ${packageName};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.core.Flysql;

import java.util.List;

/**
 * Repository - ${tableComment!""}
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@Repository
public class ${className?cap_first}Repository {

	/**
	 * query ${className?cap_first}Entity
	 *
	 * @return List[${className?cap_first}Entity]
	 * @since ${freecodeProperties.version}
	 */
	public List<${className?cap_first}Entity> query${className?cap_first}(){

		return Flysql.select(${className?cap_first}Entity.class).query();
	}

}