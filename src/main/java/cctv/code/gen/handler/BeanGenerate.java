package cctv.code.gen.handler;

import java.io.File;
import java.io.FileOutputStream;
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
 * 生成bean文件
 * @author 王佳
 * @created 2018年2月1日 下午1:27:24
 */
public class BeanGenerate extends AbstractGenerateCode {

	private static final Logger logger = Logger.getLogger(BeanGenerate.class);
	
	/**
	 * 生成的包名
	 */
	private String packageName;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public BeanGenerate(String path, String tableName,String packageName) {
		super(path, tableName);
		this.packageName = packageName;
	}

	@Override
	public void doGenerate(SQLTableData table) {
		logger.info("------------开始生成bean-----------------");
		String beanField = table.getBeanField();

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("className", this.className);
		paramMap.put("packageName",packageName);
		paramMap.put("hasPage", true);
		paramMap.put("fields", beanField);

		logger.info("----------生成bean的信息:class"+packageName+"."+className+"------------");
		
		//生成模版
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File(GenerateCode.TEMPLATE_DIR));
			Template t = config.getTemplate("JavaClass.ftl");
			t.process(paramMap, new OutputStreamWriter(new FileOutputStream(path + className + ".java")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("----------生成bean完毕---------------");

	}
}
