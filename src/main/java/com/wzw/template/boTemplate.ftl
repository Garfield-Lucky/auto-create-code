package ${package}.bo;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import ${package}.entity.${entityName};

/**
* @ClassName: ${entityName}Bo.java
* @Description:
* @author ${author}
* @date ${now}
*/
public interface ${entityName}Bo {

	/**
	* @Description:
	* @param ${instanceName}
	* @throws Exception
	* @return PageInfo<${entityName}>
    * @author Created by ${author} on ${now}
    */
    public PageInfo<${entityName}> qry${entityName}List(Map<String,Object> params) throws Exception;

    /**
    * @Description: 通过id查询终端品牌
    * @param ${instanceName}
    * @return ${entityName}
    * @author Created by ${author} on ${now}
    */
    ${entityName} qry${entityName}ById(${entityName} ${instanceName}) throws Exception;

    /**
    * @Description: 修改终端品牌
    * @param ${instanceName}
    * @return int
    * @author Created by ${author} on ${now}
    */
    int update${entityName}ById(${entityName} ${instanceName}) throws Exception;

    /**
    * @Description: 根据id删除终端品牌
    * @param ${instanceName}
    * @return int
    * @author Created by ${author} on ${now}
    */
    int remove${entityName}ById(${entityName} ${instanceName}) throws Exception;

    /**
    * @Description: 新增终端品牌
    * @param ${instanceName}
    * @return int
    * @author Created by wuzhangwei on ${now}
    */
    int add${entityName}(${entityName} ${instanceName}) throws Exception;



}
