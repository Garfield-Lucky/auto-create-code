package ${package}.service;

import com.github.pagehelper.PageInfo;
import java.util.Map;
import ${package}.entity.${entityName};

/**
* @ClassName: ${entityName}Service
* @Description:
* @author ${author}
* @date ${now}
*/
public interface ${entityName}Service {

    /**
    * @Description: 列表查询
    * @param Map<String,Object> params
    * @throws Exception
    * @return PageInfo<${entityName}>
    * @author Created by ${author} on ${now}
    */
    PageInfo<${entityName}>  qry${entityName}List(Map<String, Object> params);

    /**
    * @Description: 通过id查询
    * @param ${instanceName}
    * @return ${entityName}
    * @author Created by ${author} on ${now}
    */
    ${entityName} qry${entityName}ById(${entityName} ${instanceName});

    /**
    * @Description: 修改
    * @param ${instanceName}
    * @return int
    * @author Created by ${author} on ${now}
    */
    int update${entityName}ById(${entityName} ${instanceName});

    /**
    * @Description: 根据id删除
    * @param ${instanceName}
    * @return int
    * @author Created by ${author} on ${now}
    */
    int remove${entityName}ById(${entityName} ${instanceName});

    /**
    * @Description: 新增
    * @param ${instanceName}
    * @return int
    * @author Created by ${author} on ${now}
    */
    int add${entityName}(${entityName} ${instanceName});

}


