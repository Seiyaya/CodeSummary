package cctv.code.gen.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cctv.code.gen.AbstractGenerateCode;
import cctv.code.gen.GenerateCode;
import cctv.mybatis.SQLTableData;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成mapper.java文件
 * 
 * @author 王佳
 * @created 2018年2月1日 下午1:27:41
 */
public class MapperClassGenerate extends AbstractGenerateCode {

	private static final Logger logger = Logger.getLogger(MapperClassGenerate.class);

	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public MapperClassGenerate(String path, String tableName, String packageName) {
		super(path, tableName);
		this.packageName = packageName;
	}

	@Override
	public void doGenerate(SQLTableData table) {
		logger.info("------------开始生成mapper接口文件------------");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beanName", className);
		paramMap.put("hasPage", true);
		paramMap.put("beanPackageName", transBeanPackageName(className,packageName));
		paramMap.put("packageName", packageName);
		logger.info("------------参数信息" + packageName + className + "-----------------------");

		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File(GenerateCode.TEMPLATE_DIR));
			Template t = config.getTemplate("JavaDao.ftl");
			t.process(paramMap, new OutputStreamWriter(new FileOutputStream(path + className + "Mapper.java")));
		} catch (Exception e) {
			logger.error("----------生成mapper接口失败-----------------");
			e.printStackTrace();
		}                                   

		logger.info("-----------生成mapper接口成功--------------------");
	}

	private String transBeanPackageName(String className, String pName) {
		//获取前缀报名
		return pName.substring(0, pName.lastIndexOf(".")+1)+"bean";
	}
}
