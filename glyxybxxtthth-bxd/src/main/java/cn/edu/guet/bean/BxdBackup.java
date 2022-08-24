package cn.edu.guet.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author George
 * @project glyxybxxththd
 * @package com.glyxybxhtxt.dataObject
 * @date 2022/1/6 13:09
 * @since 1.0
 * @Readme 报修单数据库备份实体类
 */
public class BxdBackup implements Serializable {
    @ApiModelProperty(value = "自增id")
    private Integer id;

    @ApiModelProperty(value = "数据库名称")
    private String tableName;

    @ApiModelProperty(value = "数据库存储地址")
    private String tableUrl;

    @ApiModelProperty(value = "备份时间")
    private Date backupTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableUrl() {
        return tableUrl;
    }

    public void setTableUrl(String tableUrl) {
        this.tableUrl = tableUrl;
    }

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }
}