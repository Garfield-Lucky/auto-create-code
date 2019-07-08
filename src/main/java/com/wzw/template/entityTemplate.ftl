package ${package}.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author ${author}
 * @className: ${entityName}.java
 * @time: ${now}
 * @tableName_comments ${tableNameComments}
 */
@Data
@Table(name = ${entityName}.tableName)
public class ${entityName} implements Serializable {

    private static final long serialVersionUID = 1L;
	protected static final String tableName = "${tableName}";

	${createPropStr}
	

}
