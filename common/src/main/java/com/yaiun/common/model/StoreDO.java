package com.yaiun.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wj
 * @since 2021-09-22
 */
@TableName("store")
public class StoreDO extends Model<StoreDO> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 店铺名
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 每日人流量,1(1~99),2(100~199)......10(900-999)
     */
    private Integer peopleCount;

    private Integer willCooperation;

    /**
     * 探店日期
     */
    private LocalDateTime shopVisitDate;

    /**
     * 店长id
     */
    private Integer sellerId;

    /**
     * 类型,0餐饮
     */
    private Integer type;

    /**
     * 状态,0正常,1关停
     */
    private Integer status;

    private LocalDateTime craeteTime;

    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getWillCooperation() {
        return willCooperation;
    }

    public void setWillCooperation(Integer willCooperation) {
        this.willCooperation = willCooperation;
    }

    public LocalDateTime getShopVisitDate() {
        return shopVisitDate;
    }

    public void setShopVisitDate(LocalDateTime shopVisitDate) {
        this.shopVisitDate = shopVisitDate;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCraeteTime() {
        return craeteTime;
    }

    public void setCraeteTime(LocalDateTime craeteTime) {
        this.craeteTime = craeteTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreDO{" +
        "id=" + id +
        ", name=" + name +
        ", address=" + address +
        ", peopleCount=" + peopleCount +
        ", willCooperation=" + willCooperation +
        ", shopVisitDate=" + shopVisitDate +
        ", sellerId=" + sellerId +
        ", type=" + type +
        ", status=" + status +
        ", craeteTime=" + craeteTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
