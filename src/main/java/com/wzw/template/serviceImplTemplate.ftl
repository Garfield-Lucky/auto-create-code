package ${package}.service.impl;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${package}.bo.${entityName}Bo;
import ${package}.entity.${entityName};
import ${package}.service.${entityName}Service;

/**
* @ClassName: ${entityName}ServiceImpl
* @Description:
* @author ${author}
* @date ${now}
*/
@Service("${instanceName}Service")
public class ${entityName}ServiceImpl implements ${entityName}Service {

	@Autowired
	${entityName}Bo ${instanceName}Bo;

	@Override
	public PageInfo<${entityName}> qry${entityName}List(Map<String, Object> params) {
    	return ${instanceName}Bo.qry${entityName}List(params);
    }

    @Override
    public ${entityName} qry${entityName}ById(${entityName} ${instanceName}) {
    	return ${instanceName}Bo.qry${entityName}ById(${instanceName});
    }

    @Override
    public int update${entityName}ById(${entityName} ${instanceName}) {
    	return ${instanceName}Bo.update${entityName}ById(${instanceName});
    }

    @Override
    public int remove${entityName}ById(${entityName} ${instanceName}) {
    	return ${instanceName}Bo.remove${entityName}ById(${instanceName});
    }

    @Override
    public int add${entityName}(${entityName} ${instanceName}) {
    	return ${instanceName}Bo.add${entityName}(${instanceName});
    }

}


