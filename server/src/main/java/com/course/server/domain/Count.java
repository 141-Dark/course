package com.course.server.domain;

public class Count {
    private Integer userid;

    private byte[] money;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getMoney() {
        return money;
    }

    public void setMoney(byte[] money) {
        this.money = money;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", money=").append(money);
        sb.append("]");
        return sb.toString();
    }
}