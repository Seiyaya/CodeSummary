package cctv.bean;

/**
 * Created by CCTV-1 on 2017/5/15.
 */
public class BasePageBean {
    //定义常量 每页数
    public final static int DEFAULT_SIZE = 10;
    //每页数
    protected int pageSize = DEFAULT_SIZE;
    //起始行
    protected int startRow;//起始行
    //页码
    protected int pageNo = 1;
    //Sql查询字段
    protected String fields;


    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }
    public BasePageBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
        return this;
    }
    public int getStartRow() {
        return startRow;
    }
    public BasePageBean setStartRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    public String getFields() {
        return fields;
    }
    public BasePageBean setFields(String fields) {
        this.fields = fields;
        return this;
    }
}
