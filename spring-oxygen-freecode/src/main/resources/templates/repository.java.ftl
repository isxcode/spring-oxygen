package ${packageName};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.Flysql;

import java.util.List;

/**
 * ${tableName} repository
 *
 * @author ${freecodeProperties.author}
 * @since ${freecodeProperties.version}
 */
@Slf4j
@Repository
public class ${tableName?cap_first}Repository {

	/**
	 * query ${tableName?cap_first}Entity
	 *
	 * @return List[${tableName?cap_first}Entity]
	 * @since 0.0.1
	 */
	public List<${tableName?cap_first}Entity> query${tableName?cap_first}(){

		return Flysql.select(${tableName?cap_first}Entity.class).query();
	}

}