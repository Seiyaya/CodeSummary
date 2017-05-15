package cctv.mybatis;

import java.util.List;

/**
 * Created by CCTV-1 on 2017/5/12.
 * 映射到表中的一行
 */
public class SQLColumnData {
    /**
     * 列名称
     */
    private String colName;
    /**
     * 列的类型
     */
    private String colType;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

}
