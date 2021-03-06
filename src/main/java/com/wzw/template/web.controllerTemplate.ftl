package ${package}.web.controller;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ReqMethod;
import org.springframework.web.bind.annotation.ReqProxy;
import org.springframework.web.bind.annotation.RestController;
import com.ztesoft.bss.common.exception.BssException;
import com.ztesoft.bss.common.vo.ResultVO;
import com.ztesoft.bss.salesres.consts.ErrorConsts;
import ${package}.entity.${entityName};
import ${package}.service.${entityName}Service;
import com.ztesoft.common.logger.ErrorCode;
import com.ztesoft.common.logger.api.ZLogger;
import com.ztesoft.common.logger.factory.ZLoggerFactory;


/**
* @ClassName: ${entityName}Controller
* @Description:
* @author ${author}
* @date ${now}
*/
@RestController
@ReqProxy
public class ${entityName}Controller {
    private static final ZLogger ZLOGGER = ZLoggerFactory.getLogger(${entityName}Controller.class, "${instanceName}Controller");

    @Autowired
    ${entityName}Service ${instanceName}Service;

    /**
    * @Description: 列表查询
    * @param Map<String, Object> params
    * @return PageInfo<${entityName}>
    * @author Created by ${author} on ${now}
    */
    @ReqMethod
    public PageInfo<${entityName}> qry${entityName}List(Map<String, Object> params) {
    PageInfo<${entityName}> resultData = null;
        try {
            resultData = ${instanceName}Service.qry${entityName}List(params);
        }
        catch (Exception e) {
            ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "列表查询失败！" + e.getMessage());
        }
        return resultData;
    }

    /**
    * @Description: 根据主键查询
    * @param ${instanceName}
    * @return ${entityName}
    * @author Created by ${author} on ${now}
    */
    @ReqMethod
    public ${entityName} qry${entityName}ById(${entityName} ${instanceName}) {
        ${entityName} resultData = null;
        try {
            resultData = ${instanceName}Service.qry${entityName}ById(${instanceName});
        }
        catch (Exception e) {
            ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "查询失败！" + e.getMessage());
        }
        return resultData;
    }


    /**
    * @Description: 新增
    * @param ${instanceName}
    * @return ResultVO<${entityName}>
    * @author Created by ${author} on ${now}
    */
    @ReqMethod
    public ResultVO<${entityName}> add${entityName}(${entityName} ${instanceName}) {
        ResultVO<${entityName}> result = new ResultVO<${entityName}>();
            try {
                int flag = ${instanceName}Service.add${entityName}(${instanceName});
                if (flag > 0) {
                    result.setResultCode(ErrorConsts.DEAL_SUCCESS_0.errorCode);
                    result.setResultMsg(ErrorConsts.DEAL_SUCCESS_0.errorMsg);
                }
                else {
                    result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                    result.setResultMsg(ErrorConsts.UN_ERROR.errorMsg);
                }
            }
            catch (BssException e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getFailMsg());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "添加失败！" + e.getMessage(), e);
            }
            catch (Exception e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getMessage());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "添加失败！" + e.getMessage(), e);
            }
            return result;
    }


    /**
    * @Description: 修改
    * @param ${instanceName}
    * @return ResultVO<${entityName}>
    * @author Created by ${author} on ${now}
    */
    @ReqMethod
    public ResultVO<${entityName}> update${entityName}ById(${entityName} ${instanceName}) {
        ResultVO<${entityName}> result = new ResultVO<${entityName}>();
            try {
                int flag = ${instanceName}Service.update${entityName}ById(${instanceName});
                if (flag > 0) {
                     result.setResultCode(ErrorConsts.DEAL_SUCCESS_0.errorCode);
                     result.setResultMsg(ErrorConsts.DEAL_SUCCESS_0.errorMsg);
                }
                else {
                    result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                    result.setResultMsg(ErrorConsts.UN_ERROR.errorMsg);
                }
            }
            catch (BssException e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getFailMsg());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "修改失败！" + e.getMessage(), e);
            }
            catch (Exception e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getMessage());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "修改失败！" + e.getMessage(), e);
            }
            return result;
            }

    /**
    * @Description: 删除
    * @param ${instanceName}
    * @return ResultVO<${entityName}>
    * @author Created by ${author} on ${now}
    */
    @ReqMethod
    public ResultVO<${entityName}>  remove${entityName}ById(${entityName} ${instanceName}) {
        ResultVO<${entityName}> result = new ResultVO<${entityName}>();
            try {
                int flag = ${instanceName}Service.remove${entityName}ById(${instanceName});
                if (flag > 0) {
                    result.setResultCode(ErrorConsts.DEAL_SUCCESS_0.errorCode);
                    result.setResultMsg(ErrorConsts.DEAL_SUCCESS_0.errorMsg);
                }
                else {
                    result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                    result.setResultMsg(ErrorConsts.UN_ERROR.errorMsg);
                }
            }
            catch (BssException e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getFailMsg());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "刪除失败！" + e.getMessage(), e);
            }
            catch (Exception e) {
                result.setResultCode(ErrorConsts.UN_ERROR.errorCode);
                result.setResultMsg(e.getMessage());
                ZLOGGER.error(new ErrorCode(ErrorConsts.UN_ERROR.errorCode), "刪除失败！" + e.getMessage(), e);
            }
            return result;
    }

}
