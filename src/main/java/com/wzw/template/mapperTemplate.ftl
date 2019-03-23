package ${package}.mapper;

import java.util.List;
import java.util.Map;
import com.ztesoft.bss.base.extmapper.BaseMapper;
import ${entityPackage}.entity.${entityName};

/**
* @ClassName: ${entityName}Mapper.java
* @Description:
* @author ${author}
* @date ${now}
*/
public interface ${entityName}Mapper extends BaseMapper<${entityName}> {

    /**
    * @Description:
    * @param Map<String,Object> params
    * @throws Exception
    * @return List<${entityName}>
    * @author Created by ${author} on ${now}
    */
    public List<${entityName}> qry${entityName}List(Map<String,Object> params);

}