package com.wzw.main;

import java.sql.SQLException;

import com.wzw.factory.AutoGenerationCodeTool;
import com.wzw.factory.BeanProperties;
import com.wzw.util.ConnectionUtil;


public class CreateCodeMain {

	/**
	 * @param  args
	 * @throws SQLException 
	 * @author wzw
	 */
	public static void main(String[] args) throws Exception {

		BeanProperties.setPackage("com.ztesoft.bss.salesres");//包名
		BeanProperties.setTableName("MKT_RES_TERM_CONTACT_DETAIL");//数据库表名
		BeanProperties.setSchema("springboot");//数据库名称
 		BeanProperties.setPath("D:/java");//生成的类存放的路径
 		BeanProperties.setAuthor("wuzhangwei");//代码生成者
		ConnectionUtil.dbUrl="jdbc:mysql://localhost:3306/springboot?characterEncoding=utf-8&serverTimezone=UTC";
		ConnectionUtil.theUser="work";//数据库登录用户
		ConnectionUtil.thePw="123456";//数据库登录用户密码
		//生成代码
		new AutoGenerationCodeTool().createCode();

	}

}