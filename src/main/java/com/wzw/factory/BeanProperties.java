package com.wzw.factory;

import com.wzw.util.FieldTypeConsts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:  生成代码的一些参数设置以及部分参数类型转换
* @Author: wuzhangwei
* @Date: 2019/3/18 13:35
*/
public class BeanProperties {

	//实体bean的包名
	private static String _package;

	//实体bean的包名
	private  static String _entityPackage;

	//生成代码人员
	private  static String _author;

	//大小写都可以，表名
	private  static String _tableName;

	//代码生成后存放的位置
	private  static String _path;

    //数据库名称
	private static String _schema;

	//由其它地方自动设置
	private static String _tableNameComments;
	
	public static String getPackage() {
		return _package;
	}

	public static void setPackage(String $package) {
		_package = $package;
		_entityPackage = $package + ".entity";
	}

	public static String getEntityPackage(){
		return _entityPackage;
	}

	public static String getTableName() {
		return _tableName;
	}

	public static void setTableName(String tableName) {
		_tableName = tableName;
	}

	public static String getTableNameComments() {
		return _tableNameComments;
	}

	public static void setTableNameComments(String tableNameComments) {
		_tableNameComments = tableNameComments;
	}

	public static String getPath() {
		return _path;
	}

	public static void setPath(String path) {
		_path = path;
	}

	public static String getAuthor() {
		return _author;
	}

	public static void setAuthor(String author) {
		_author = author;
	}

	public static void setSchema(String schema){
		_schema = schema;
	}

	public static String getSchema(){
		return _schema;
	}

	public static String getCaptureName(String name) {
        char[] cs=name.toCharArray();
        if(cs[0] >= 'a' && cs[0] <= 'z')
        {
             return String.valueOf(cs);
        }else{
        	 cs[0]+=32;
             return String.valueOf(cs);
        }
	}

	 /**
	  * @Description: 数据库字段类型转Java实例变量类型
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/17 19:31
	  */
	public static String fieldType2JavaType(String fileType) {

		if (checkNumber(fileType)) {
			return "Long";
		}else if (checkString(fileType)){
			return "String";
		} else if (checkDate(fileType)) {
			return "Date";
		} else if(FieldTypeConsts.CLOB.equalsIgnoreCase(fileType)){
			return "CLOB";
		} else if(FieldTypeConsts.TEXT.equalsIgnoreCase(fileType)){
			return "TEXT";
		}else{
			return null;
		}

	}

	 /**
	  * @Description: 检查字段类型是否是数字类型
	  *
	  * @param
	  * @author Created by wuzhangwei on 2019/3/17 19:17
	  */
	private static boolean checkNumber(String fieldType) {

		if (FieldTypeConsts.INT.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.INTEGER.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.TINYINT.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.BIGINT.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.NUMBER.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.DECIMAL.equalsIgnoreCase(fieldType)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: 检查字段类型是否是字符串类型
	 *
	 * @param
	 * @author Created by wuzhangwei on 2019/3/17 19:17
	 */
	private static boolean checkString(String fieldType) {

		if (FieldTypeConsts.VARCHAR.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.CHAR.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.VARCHAR2.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.NVARCHAR2.equalsIgnoreCase(fieldType)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: 检查字段类型是否是日期类型
	 *
	 * @param
	 * @author Created by wuzhangwei on 2019/3/17 19:17
	 */
	private static boolean checkDate(String fieldType) {

		if (FieldTypeConsts.DATE.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.TIMESTAMP.equalsIgnoreCase(fieldType) ||
			FieldTypeConsts.DATETIME.equalsIgnoreCase(fieldType)){
			return true;
		} else {
			return false;
		}
	}

	
}
