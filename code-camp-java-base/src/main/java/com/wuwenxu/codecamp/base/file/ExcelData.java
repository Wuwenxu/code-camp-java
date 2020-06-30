package com.wuwenxu.codecamp.base.file;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hongdongdong
 * @Description:
 * @Date: Create in 18:41 2019/12/14 0014
 */
public class ExcelData implements Serializable {
    /**
     * 版本号
     * */
    private static final Long serialVersionUID = 1L;
    /**
     * 模块名
     * */
    private String moduleName;
    /**
     * excel列名
     * */
    private String[] columns;
    /**
     * excel数据
     * */
    private List<String[]> data;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
