package com.itdan.springdata_demo.pojo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 */
@Entity
@Table(name = "product")
public class Product implements Serializable{
    @Id
    private String id;//产品编号
    private String name;//产品名称
    /**
     * @see com.itdan.springdata_demo.pojo.enums.ProductStatus
     */
    @ApiModelProperty(value = "状态",dataType = "com.itdan.entity.enums.ProductStatus")
    private String status ;
    //起投金额
    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount ;
    //投资步长
    @Column(name = "step_amount")
    private BigDecimal stepAmount;
    //锁定期
    @Column(name = "lock_term")
    private Integer lockTerm;
    //收益率，因为要与其他数相乘，所以使用BigDecimal
    @Column(name = "reward_rate")
    private BigDecimal rewardRate;
    private String memo;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "create_user")
    private Date updateAt;
    @Column(name = "update_at")
    private String createUser;
    @Column(name = "update_user")
    private String updateUser;

    public Product(){}

    public Product(String id, String name, String status, BigDecimal thresholdAmount, BigDecimal stepAmount, BigDecimal rewardRate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.thresholdAmount = thresholdAmount;
        this.stepAmount = stepAmount;
        this.rewardRate = rewardRate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public BigDecimal getStepAmount() {
        return stepAmount;
    }

    public void setStepAmount(BigDecimal stepAmount) {
        this.stepAmount = stepAmount;
    }

    public Integer getLockTerm() {
        return lockTerm;
    }

    public void setLockTerm(Integer lockTerm) {
        this.lockTerm = lockTerm;
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
