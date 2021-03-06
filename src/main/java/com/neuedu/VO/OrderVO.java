package com.neuedu.VO;

import java.math.BigDecimal;
import java.util.List;

public class OrderVO {
    private Long orderNo;//"orderNo": 1485158676346,
    private BigDecimal payment;//"payment": 2999.11,
    private Integer paymentType;//"paymentType": 1,
    private String paymentTypeDesc;//"paymentTypeDesc": "在线支付",
    private Integer postage;//"postage": 0,
    private Integer status;//"status": 10,
    private String statusDesc;//"statusDesc": "未支付",
    private String paymentTime;//"paymentTime": "2017-02-11 12:27:18",
    private String sendTime;//"sendTime": "2017-02-11 12:27:18",
    private String endTime;//"endTime": "2017-02-11 12:27:18",
    private String closeTime;//"closeTime": "2017-02-11 12:27:18",
    private String createTime;//"createTime": "2017-01-23 16:04:36",
    private List<OrderItemVO>orderItemVOList;
    private String imageHost;//"imageHost": "http://img.business.com/",
    private Integer shippingId;//"shippingId": 5,
    private String receiverName;//"receiverName": "betty",
    private ShippingVO shippingVo;//"shippingVo": null
    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<OrderItemVO> getOrderItemVOList() {
        return orderItemVOList;
    }

    public void setOrderItemVOList(List<OrderItemVO> orderItemVOList) {
        this.orderItemVOList = orderItemVOList;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ShippingVO getShippingVo() {
        return shippingVo;
    }

    public void setShippingVo(ShippingVO shippingVo) {
        this.shippingVo = shippingVo;
    }
}
