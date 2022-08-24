package cn.edu.guet.bean;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Shy {
    // 易班id
    private String ybid;
    // 工号
    private String gh;
    // 姓名
    private String xm;
    // 1普通职员，2高级职员
    private Integer zw;
    // 当前校区
    private String xq;
    // X坐标
    private String x;
    // Y坐标
    private String y;

    public String getYbid() {
        return ybid;
    }

    public void setYbid(String ybid) {
        this.ybid = ybid == null ? null : ybid.trim();
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh == null ? null : gh.trim();
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public Integer getZw() {
        return zw;
    }

    public void setZw(Integer zw) {
        this.zw = zw;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }
}