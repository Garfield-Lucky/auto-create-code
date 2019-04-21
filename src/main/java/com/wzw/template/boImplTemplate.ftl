package ${package}.bo.impl;

import java.util.Map;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ztesoft.bss.base.util.db.DbUtil;
import com.ztesoft.bss.common.exception.BssException;
import com.ztesoft.bss.common.util.ContextUtil;
import ${package}.bo.${entityName}Bo;
import com.ztesoft.bss.salesres.consts.ErrorConsts;
import ${package}.entity.${entityName};
import ${package}.mapper.${entityName}Mapper;
import com.ztesoft.common.logger.ErrorCode;
import com.ztesoft.common.logger.api.ZLogger;
import com.ztesoft.common.logger.factory.ZLoggerFactory;

/**
* @ClassName: ${entityName}BoImpl.java
* @Description:
* @author ${author}
* @date ${now}
*/
@Service("${instanceName}Bo")
public class ${entityName}BoImpl implements ${entityName}Bo {

    private static ZLogger LOG = ZLoggerFactory.getLogger(${entityName}BoImpl.class, "${entityName}Bo");

	private static final String logModule = "${entityName}Bo";

	@Autowired
	private ${entityName}Mapper ${instanceName}Mapper;

	@Override
	public PageInfo<${entityName}> qry${entityName}List(Map<String,Object> params) throws Exception{
        PageHelper.startPage((int) params.get("pageIndex"), (int) params.get("pageSize"));
		List<${entityName}> resultData = null;
		try {
			resultData = ${instanceName}Mapper.qry${entityName}List(params);
		} catch(Exception e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getMessage());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, "查询列表失败");
		}
		return new PageInfo<${entityName}>(resultData);
	}

	@Override
	public ${entityName} qry${entityName}ById(${entityName} ${instanceName}) throws Exception{
		${entityName} resultData = null;
		try {
			resultData = ${instanceName}Mapper.selectByPrimaryKey(${instanceName}.get${entityName}Id());
		} catch(Exception e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getMessage());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, "查询失败");
		}
		return resultData;
	}

	@Override
	public int update${entityName}ById(${entityName} ${instanceName}) throws Exception{
		int flag = 0;
		try {
			${instanceName}.setCreateStaff(ContextUtil.getUserId());
			flag = ${instanceName}Mapper.updateByPrimaryKeySelective(${instanceName});
		} catch(BssException e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getFailMsg());
		throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, e.getFailMsg());
		} catch(Exception e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getMessage());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, e.getMessage());
		}
		return flag;
	}

	@Override
	public int remove${entityName}ById(${entityName} ${instanceName}) throws Exception{
		int flag = 0;
		try {
			flag = ${instanceName}Mapper.deleteByPrimaryKey(${instanceName}.get${entityName}Id());
		} catch(Exception e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getMessage());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, "删除失败");
		}
		return flag;
	}

	@Override
	public int add${entityName}(${entityName} ${instanceName}) throws Exception{
		int flag = 0;
		try {
			Long ${instanceName}Id = DbUtil.getSequenceNumber("BRAND", "ID");
			${instanceName}.set${entityName}Id(${instanceName}Id);
			${instanceName}.setCreateStaff(ContextUtil.getUserId());
			flag = ${instanceName}Mapper.insertSelective(${instanceName});
		} catch(BssException e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getFailMsg());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, e.getFailMsg());
		} catch(Exception e) {
			LOG.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), e.getMessage());
			throw new BssException(logModule, ErrorConsts.UN_BUSINESS_ERROR.errorCode, e.getMessage());
		}
		return flag;

	}

}

