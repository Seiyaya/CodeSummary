package cctv.mybatis;

import cctv.mybatis.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCTV-1 on 2017/5/12.
 */
public class SQLTableData {

    private String attrStr;
    private String methodStr;

    private List<SQLColumnData> columnData;
    private String tableName;
    
    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }
    
    public List<SQLColumnData> getColumnData() {
		return columnData;
	}

	public void setColumnData(List<SQLColumnData> columnData) {
		this.columnData = columnData;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public SQLTableData(String tableName) {
		columnData = getColsData(tableName);
		this.tableName = tableName;
	}
	
	public SQLTableData() {
		
	}


	/**
     * 通过表名获取列的信息
     * @param tableName
     * @return
     */
    public List<SQLColumnData> getColsData(String tableName){
        String sql = "SELECT COLUMN_NAME, DATA_TYPE FROM information_schema.columns WHERE table_name =  '"
                + tableName + "' order by ordinal_position";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SQLColumnData> columnList = new ArrayList<SQLColumnData>();
        try {
            conn = JDBCUtil.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String type = resultSet.getString(2);
                String javaType = this.getType(type);
                SQLColumnData column = new SQLColumnData();
                column.setColName(name);
                column.setColType(javaType);
                columnList.add(column);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,statement,resultSet);
        }
        return columnList;
    }


    /**
     * 将列名生成对应的属性和方法
     * @param tableName
     * @return
     */
    public String getBeanField(){
        List<SQLColumnData> dataList = this.columnData;
        String className = getTableName2ClassName(tableName);
        StringBuilder str = new StringBuilder();
        StringBuilder getsetStr = new StringBuilder();
        for(SQLColumnData column : dataList){
            String name = column.getColName();
            String type = column.getColType();
            String maxChar = name.substring(0, 1).toUpperCase();//大写第一个字符
            str.append("\r\t").append("private ").append(type + " ").append(name).append(";\n");
            String method = maxChar + name.substring(1, name.length());
            getsetStr.append("\r\t").append("public ").append(type + " ").append("get" + method + "(){\n");
            getsetStr.append("\t\t").append("return this.").append(name).append(";\n\t}\n");
            getsetStr.append("\r\t").append("public "+className).append(" set" + method + "(" + type + " " + name + "){\n");
            getsetStr.append("\t\t").append("this." + name + "=").append(name).append(";\n\t\treturn this;").append("\n\t}\n");

        }

        attrStr = str.toString();//属性
        methodStr = getsetStr.toString();//方法
        return attrStr+methodStr;
    }

    /**
     * 根据表名生成类名
     * @param tableName
     * @return
     */
    public String getTableName2ClassName(String tableName){
        if(tableName.contains("_")){
            String[] strings = tableName.split("_");
            String className = strings[strings.length-1];
            return (className.charAt(0)+"").toUpperCase()+className.substring(1);
        }else{
            return (tableName.charAt(0)+"").toUpperCase()+tableName.substring(1);
        }
    }


    /**
     * 根据sql的类型返回java的数据类型
     * @param sqlType
     * @return
     */
    public String getType(String sqlType){
        sqlType = sqlType.toLowerCase();
        if ("char".equals(sqlType) || "varchar".equals(sqlType)|| "nvarchar".equals(sqlType)){
            return "String";
        }else if("int".equals(sqlType)){
            return "Integer";
        }else if("bigint".equals(sqlType)){
            return "Long";
        }else if("timestamp".equals(sqlType) || "date".equals(sqlType) || "datetime".equals(sqlType)){
            return "java.sql.Timestamp";
        } else if ("decimal".equals(sqlType)){
            return "Double";
        }else if ("image".equals(sqlType)){
            return "byte[]";
        }else if("smallint".equals(sqlType)){
            return "int";
        }
        return null;
    }
}
