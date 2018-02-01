package cctv.code.gen;

import java.util.List;

import cctv.mybatis.SQLColumnData;
import cctv.mybatis.SQLTableData;

public abstract class AbstractGenerateCode implements GenerateCode {

	/**
	 * 存储表字段的数据结构的列表
	 */
	protected List<SQLColumnData> columnData;
	
	/**
	 * 文件路径
	 */
	protected String path;
	
	/**
	 * 表名
	 */
	protected String tableName;
	
	/**
	 * 生成的类名
	 */
	protected String className;
	
	
	public AbstractGenerateCode(String path , String tableName) {
		this.path = path;
		this.tableName = tableName;
	}
	
	@Override
	public void generate() {
		SQLTableData table = new SQLTableData(tableName);
		columnData = table.getColumnData();
		className = table.getTableName2ClassName(tableName);
		
		doGenerate(table);
	}
	
	public void doGenerate(SQLTableData table) {
		//留给子类复写
	}

}
