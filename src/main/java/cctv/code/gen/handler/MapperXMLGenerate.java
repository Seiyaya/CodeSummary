package cctv.code.gen.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import cctv.code.gen.AbstractGenerateCode;
import cctv.code.gen.GenerateCode;
import cctv.mybatis.SQLTableData;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成xml文件，统一生成在resource/mybatis下 且需要配置bean的别名(mybatis)
 * 
 * @author 王佳
 * @created 2018年2月1日 下午2:51:50
 */
public class MapperXMLGenerate extends AbstractGenerateCode {

	/**
	 * 命名空间的名称，mapper的class路径
	 */
	private String mapperPackageName;
	
	
	public String getMapperPackageName() {
		return mapperPackageName;
	}

	public void setMapperPackageName(String mapperPackageName) {
		this.mapperPackageName = mapperPackageName;
	}

	public MapperXMLGenerate(String path, String tableName,String mapperPackageName) {
		super(path, tableName);
		this.mapperPackageName = mapperPackageName;
	}

	@Override
	public void doGenerate(SQLTableData table) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("package", mapperPackageName);
		map.put("beanName", className);
		map.put("properties", columnData);
		map.put("tableName", tableName);

		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File(GenerateCode.TEMPLATE_DIR));
			Template t = config.getTemplate("JavaMapper.ftl");
			t.process(map, new OutputStreamWriter(new FileOutputStream(path+"resources/mybatis/"+className+"Mapper.xml")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
