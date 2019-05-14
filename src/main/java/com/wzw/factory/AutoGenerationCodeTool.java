package com.wzw.factory;

import com.wzw.enums.ResultEnum;
import com.wzw.exception.AccException;
import com.wzw.template.TemplateClassPath;
import com.wzw.util.ConnectionUtil;
import com.wzw.util.FreeMarkerManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AutoGenerationCodeTool extends BaseTool {

	private List<String> colNames;   //列名数组
	private List<String> colTypes;   //列名类型数组
	private List<String> comments;   //表字段的备注注释
	private List<Integer> intLengths;//数据的存放长度

     /**
      * @Description: 生成代码
      *
      * @param
      * @author Created by wuzhangwei on 2019/5/14 20:06
      */
	public void createCode() {

	    try {
            //模板
            Map<String,Object> freeMarkerData=new HashMap();
            FreeMarkerManager freeMarker=new FreeMarkerManager();

            //获取表的字段、注释、字段类型等信息
            getTableMetaData();

            //获取实体名称
            String entityName = initcapTableName(tableName);

            //初始化模板数据
            initFreeMarkerData(freeMarker, freeMarkerData, entityName);

            //生成实体
            createEntity(freeMarker, freeMarkerData, entityName);

            //开始生成service、bo、mapper等代码
            createCodeByEntityName(entityName, freeMarkerData, freeMarker);
        } catch(AccException ae) {
            ae.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

	}

	 /**
	  * @Description: 获取表的字段、注释、字段类型等信息
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/5/13 19:10
	  */
	private void getTableMetaData() {
		colNames=new ArrayList();
		colTypes=new ArrayList();
		comments=new ArrayList();
		intLengths=new ArrayList();

		ConnectionUtil jdbc = new ConnectionUtil();
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = jdbc.getConn(); // 得到数据库连接
			//1.获取表中的字段名称、字段类型、字段描述、字段类型长度
			PreparedStatement ps = conn.prepareStatement(ConnectionUtil.getMetaDataSql());
			ps.setString(1, schema.toUpperCase());
			ps.setString(2, tableName.toUpperCase());
			rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    colNames.add(rs.getString("column_name"));
                    colTypes.add(rs.getString("data_type"));
                    comments.add(rs.getString("comments"));
                    intLengths.add(rs.getInt("data_length"));
                } while (rs.next());
            } else {
                throw new AccException(ResultEnum.TABLE_NOT_EXIST);
            }
			rs.close();

			//2.获取表名的描述信息
			PreparedStatement psTabname = conn.prepareStatement(ConnectionUtil.getTableCommentsSql());
			psTabname.setString(1, tableName.toUpperCase());
			rs = psTabname.executeQuery();
			if (rs.next()) {
				tableName_comments=rs.getString("comments");
			}
			rs.close();

		} catch (AccException ae) {
            throw new AccException(ResultEnum.ERROR.getCode(), ae.getMessage());
        } catch (Exception e) {
            throw new AccException(ResultEnum.ERROR.getCode(), e.getMessage());
		} finally{
			jdbc.closeConnection(conn);
		}

	}

	 /**
	  * @Description: 初始化生成代码所需的参数值
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/23 13:33
	  */
	private  void initFreeMarkerData(FreeMarkerManager freeMarker, Map<String,Object> freeMarkerData, String entityName) {

		// 获取当前日期
		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

		try {
			//初始化freemarker配置，并设置freemarker模板文件所在的路径
			freeMarker.init(TemplateClassPath.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置生成代码所需的参数
		freeMarkerData.put("instanceName", BeanProperties.getCaptureName(entityName));
		freeMarkerData.put("tableNameComments", tableName_comments);//表名中文解释
		freeMarkerData.put("tableName",tableName.toUpperCase());
		freeMarkerData.put("createPropStr", processAllAttrs());
		freeMarkerData.put("entityPackage",entityPackage);
		freeMarkerData.put("columnName",getColumnName());
		freeMarkerData.put("entityName", entityName);
		freeMarkerData.put("package",_package);
		freeMarkerData.put("author",author);
		freeMarkerData.put("now", createTime);

	}

	/**
	 * @Description: 生成实体
	 *
	 * @param String entityName
	 * @param Map<String,Object> freeMarkerData
	 * @param FreeMarkerManager freeMarker
	 * @author Created by wuzhangwei on 2019/3/21 15:57
	 */
	private void createEntity(FreeMarkerManager freeMarker, Map<String,Object> freeMarkerData, String entityName) {

		String filePath=path+"/"+entityPackage.replace(".", "/");
		System.out.println("实体存放路径:"+filePath);
		boolean y=freeMarker.otherProcess("entityTemplate.ftl", entityName + ".java", "UTF-8", freeMarkerData, filePath);

		if(y){
			System.err.println("生成实体"+entityName+".java成功!");
		}
	}

 /**
  * @Description: 根据entityName生成service、bo、mapper等代码
  *
  * @param String entityName
  * @param Map<String,Object> freeMarkerData
  * @param FreeMarkerManager freeMarker
  * @author Created by wuzhangwei on 2019/3/21 15:57
  */
	private void createCodeByEntityName(String entityName, Map<String,Object> freeMarkerData, FreeMarkerManager freeMarker) {

		Map<String,String> beanMap = new HashMap<String,String>();
		beanMap.put("web.controller","Controller.java");
		beanMap.put("service","Service.java");
		beanMap.put("serviceImpl","ServiceImpl.java");
		beanMap.put("bo","Bo.java");
		beanMap.put("boImpl","BoImpl.java");
		beanMap.put("mapper","Mapper.java");
		beanMap.put("mapperXml","Mapper.xml");

		for (Map.Entry<String, String> entry : beanMap.entrySet()) {
			String filePath=path+"/"+(_package+"."+entry.getKey()).replace(".", "/");
			boolean y=freeMarker.otherProcess(entry.getKey()+"Template.ftl", entityName + entry.getValue(), "UTF-8", freeMarkerData, filePath);

			if(y){
				System.err.println("生成"+entityName+entry.getValue()+"成功!");
			}
		}
	}

	 /**
	  * @Description:
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/23 13:25
	  */
	public static void main(String[] args) throws SQLException {
		
		new AutoGenerationCodeTool().createCode();
 
	}

	/**
	 * 把表名/get/set后半部的属性等字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcapTableName(String str) {

		String word[]=str.trim().toLowerCase().split("_");
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < word.length; i++) {
	        char[] ch = word[i].toCharArray();
	        if (ch[0] >= 'a' && ch[0] <= 'z') {
	            ch[0] = (char) (ch[0] - 32);
	        }
	        sb.append(new String(ch));
		}

        return sb.toString();
    }

	 /**
	  * @Description: 将字段生成属性首字母小写,把列名输入字符串的第二"_"后的首字母改成大写
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/23 13:24
	  */
	private String initcapColName(String ColName) {

		String word[]=ColName.trim().toLowerCase().split("_");
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < word.length; i++) {
			char[] ch = word[i].toCharArray();
			if (i > 0)
				if (ch[0] >= 'a' && ch[0] <= 'z') {
					ch[0] = (char) (ch[0] - 32);
				}
			sb.append(new String(ch));

		}

        return sb.toString();
    }
	

	 /**
	  * @Description: 解析输出属性
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/23 13:23
	  */
	private String processAllAttrs() {

		StringBuffer content=new StringBuffer();
		StringBuffer idStr=new StringBuffer();
		StringBuffer otherStr=new StringBuffer();
		for (int i = 0; i < colNames.size(); i++) {
			String attr=initcapColName(colNames.get(i));//属性，第一个字母小写
			String dataType=BeanProperties.fieldType2JavaType(colTypes.get(i));
			if(attr.equals("id")){
				if(null!=comments.get(i))//字段注释
					idStr.append("\t//"+comments.get(i)+"\r\n");
				idStr.append("\t@Id\r\n");
				idStr.append("\tprivate " + dataType + " "	+ attr + ";\r\n\r\n");
			}else if(dataType.equals("CLOB") || dataType.equals("TEXT")){//String 大字段
				if(null!=comments.get(i))//字段注释
					otherStr.append("\t//"+comments.get(i)+" [String 大字段]\r\n");
				otherStr.append("\tprivate String "	+ attr + ";\r\n\r\n");
			}else{
				if(null!=comments.get(i))//字段注释
					otherStr.append("\t//"+comments.get(i)+"\r\n");
				otherStr.append("\tprivate " + dataType + " "	+ attr + ";\r\n\r\n");
			}

		}
		content.append(idStr.toString());
		content.append(otherStr.toString());
		content.append("\r\n");//换行隔开

		return content.toString();
	}

	 /**
	  * @Description: 获取所有数据库字段名称
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/23 13:24
	  */
	private String getColumnName() {

		StringBuffer content=new StringBuffer();
		for (int i = 0; i < colNames.size(); i++) {
			if(i == colNames.size()-1){
				content.append("\t"+colNames.get(i));//字段名称
			}else{
				content.append("\t"+colNames.get(i)+",");//字段名称
				content.append("\r\n");//换行隔开
			}
		}
		return content.toString();
	}

}
