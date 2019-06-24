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

	//业务代码的包名
	private static String codePackage;

	//实体bean的包名
	private  static String entityPackage;

	//生成代码人员
	private  static String author;

	//大小写都可以，表名
	private  static String tableName;

	//代码生成后存放的位置
	private  static String path;

    //数据库名称
	private static String schema;

	//由其它地方自动设置
	private static String tableNameComments;
	
	public static String getPackage() {
		return codePackage;
	}

	public static void setPackage(String codePackageValue) {
		codePackage = codePackageValue;
		entityPackage = codePackage + ".entity";
	}

	public static String getEntityPackage(){
		return entityPackage;
	}

	public static String getTableName() {
		return tableName;
	}

	public static void setTableName(String tableNameValue) {
		tableName = tableNameValue;
	}

	public static String getTableNameComments() {
		return tableNameComments;
	}

	public static void setTableNameComments(String tableNameCommentsValue) {
		tableNameComments = tableNameCommentsValue;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String pathValue) {
		path = pathValue;
	}

	public static String getAuthor() {
		return author;
	}

	public static void setAuthor(String authorValue) {
		author = authorValue;
	}

	public static void setSchema(String schemaValue){
		schema = schemaValue;
	}

	public static String getSchema(){
		return schema;
	}

	public static String getCaptureName(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
             return String.valueOf(cs);
        } else {
        	 cs[0] += 32;
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
		} else if (checkString(fileType)) {
			return "String";
		} else if (checkDate(fileType)) {
			return "Date";
		} else if (FieldTypeConsts.CLOB.equalsIgnoreCase(fileType)) {
			return "CLOB";
		} else if (FieldTypeConsts.TEXT.equalsIgnoreCase(fileType)) {
			return "TEXT";
		} else {
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
			FieldTypeConsts.DATETIME.equalsIgnoreCase(fieldType)) {
			return true;
		} else {
			return false;
		}
	}

	
}
