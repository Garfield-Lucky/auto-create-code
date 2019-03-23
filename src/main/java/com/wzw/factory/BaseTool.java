package com.wzw.factory;


/**
 * @author wuzhangwei
 * OracleEntityTool.java 
 * @time: 2019-03-18 09:54:25
 * 生成oracle的实体类文件
 */
public class BaseTool { 
	
	//包名
	public static String _package = BeanProperties.getPackage();

	//代码生成后存放的路径
	public static String path = BeanProperties.getPath();

	//实体bean的包名
	public static String entityPackage = BeanProperties.getEntityPackage();

	//代码生成人员
	public static String author = BeanProperties.getAuthor();

	//大小写都可以，表名
	public static String tableName = BeanProperties.getTableName();

	//表名注释
	public static String tableName_comments = BeanProperties.getTableNameComments();

	//数据库名
	public static String schema = BeanProperties.getSchema();
	
	
}
