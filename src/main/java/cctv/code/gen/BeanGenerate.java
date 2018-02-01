package cctv.code.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import cctv.mybatis.SQLTableData;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class BeanGenerate extends AbstractGenerateCode {

	public BeanGenerate(String path, String tableName) {
		super(path, tableName);
	}

	@Override
	public void doGenerate(SQLTableData table) {
		String beanField = table.getBeanField();

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("className", this.className);
		paramMap.put("hasPage", true);
		paramMap.put("fields", beanField);

		//生成模版
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File("/Theme"));
			Template t = config.getTemplate("JavaClass.ftl");
			t.process(paramMap, new OutputStreamWriter(new FileOutputStream(path + className + ".java")));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
