package cn.edu.guet.bean;

import lombok.ToString;

import java.util.Date;

/**
 * 报修单
 */
@ToString
public class Bxd {
    // 报修单id
    private Integer id;
    // 申报时间
    private Date sbsj;
    // 二维码id
    private Integer eid;
    // 详细地点
    private String xxdd;
    // 预约时间（不是具体时间，是字符串）公共区域不用
    private String yysj;
    // 报修类别
    private String bxlb;
    // 报修内容
    private String bxnr;
    // 视频（视频地址）
    private String sp;
    // 图片（图片地址）
    private String tp;
    // 申报人
    private String sbr;
    // 0未审核，1审核通过，2审核未通过
    private Integer shy1state;
    // 申报人学号
    private String sbrxh;
    // 耗材使用
    private String hc;
    // 工时
    private String gs;
    // 接单人ybid
    private String jid;
    // 第一位审核员
    private String shy1;
    // 申报人手机号码
    private String sbrsj;
    // 第二位审核员
    private String shy2;
    // 0未审核，1审核通过，2审核未通过
    private Integer shy2state;
    // 维修时间(或者是撤销时间）
    private Date wxsj;
    // 撤销事由
    private String cxsy;
    // 评价星级
    private String pj;
    // 评价内容
    private String pjnr;
    // 评价追加
    private String pjzj;
    // 1已派单，2已维修，3撤销单，4已验收，5不通过验收
    private Integer state;
    // 报修区域
    private String qy;
    // 报修区域列表
    private String qylb;
    // 校区
    private String xq;
    // 二维码所在区域
    private String ewmdd;
    // 返工天数
    private Integer fgts;
    // 交单时间？
    private Date jdsj;
    // 验收时间
    private Date yssj;
    // 曾经是否导出
    private Integer isExported;
    //接单人
    private Shy s1;
    private Shy s2;
    //接单人
    private Jdr j;

    public Date getJdsj() {
        return jdsj;
    }

    public void setJdsj(Date jdsj) {
        this.jdsj = jdsj;
    }

    public Date getYssj() {
        return yssj;
    }

    public void setYssj(Date yssj) {
        this.yssj = yssj;
    }

    public String getEwmdd() {
		return ewmdd;
	}

    public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public void setEwmdd(String ewmdd) {
		this.ewmdd = ewmdd;
	}

	
	public Shy getS1() {
		return s1;
	}

	public void setS1(Shy s1) {
		this.s1 = s1;
	}

	public Shy getS2() {
		return s2;
	}

	public void setS2(Shy s2) {
		this.s2 = s2;
	}

    public Jdr getJ() {
		return j;
	}

	public void setJ(Jdr j) {
		this.j = j;
	}

	public String getQy() {
		return qy;
	}

	public void setQy(String qy) {
		this.qy = qy;
	}

	public String getQylb() {
		return qylb;
	}

	public void setQylb(String qylb) {
		this.qylb = qylb;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSbsj() {
        return sbsj;
    }

    public void setSbsj(Date sbsj) {
        this.sbsj = sbsj;
    }

   

    public String getXxdd() {
        return xxdd;
    }

    public void setXxdd(String xxdd) {
        this.xxdd = xxdd == null ? null : xxdd.trim();
    }

    public Integer getFgts() {
        return fgts;
    }

    public void setFgts(Integer fgts) {
        this.fgts = fgts;
    }

    public String getYysj() {
        return yysj;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj == null ? null : yysj.trim();
    }

    public String getBxlb() {
        return bxlb;
    }

    public void setBxlb(String bxlb) {
        this.bxlb = bxlb == null ? null : bxlb.trim();
    }

    public String getBxnr() {
        return bxnr;
    }

    public void setBxnr(String bxnr) {
        this.bxnr = bxnr == null ? null : bxnr.trim();
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp == null ? null : tp.trim();
    }

    public String getSbr() {
        return sbr;
    }

    public void setSbr(String sbr) {
        this.sbr = sbr == null ? null : sbr.trim();
    }

    public String getSbrsj() {
        return sbrsj;
    }

    public void setSbrsj(String sbrsj) {
        this.sbrsj = sbrsj == null ? null : sbrsj.trim();
    }

    public String getSbrxh() {
        return sbrxh;
    }

    public void setSbrxh(String sbrxh) {
        this.sbrxh = sbrxh == null ? null : sbrxh.trim();
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc == null ? null : hc.trim();
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs == null ? null : gs.trim();
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid == null ? null : jid.trim();
    }

    public String getShy1() {
        return shy1;
    }

    public void setShy1(String shy1) {
        this.shy1 = shy1 == null ? null : shy1.trim();
    }

    public Integer getShy1state() {
        return shy1state;
    }

    public void setShy1state(Integer shy1state) {
        this.shy1state = shy1state;
    }

    public String getShy2() {
        return shy2;
    }

    public void setShy2(String shy2) {
        this.shy2 = shy2 == null ? null : shy2.trim();
    }

    public Integer getShy2state() {
        return shy2state;
    }

    public void setShy2state(Integer shy2state) {
        this.shy2state = shy2state;
    }

    public Date getWxsj() {
        return wxsj;
    }

    public void setWxsj(Date wxsj) {
        this.wxsj = wxsj;
    }

    public String getCxsy() {
        return cxsy;
    }

    public void setCxsy(String cxsy) {
        this.cxsy = cxsy == null ? null : cxsy.trim();
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj == null ? null : pj.trim();
    }

    public String getPjnr() {
        return pjnr;
    }

    public void setPjnr(String pjnr) {
        this.pjnr = pjnr == null ? null : pjnr.trim();
    }

    public String getPjzj() {
        return pjzj;
    }

    public void setPjzj(String pjzj) {
        this.pjzj = pjzj == null ? null : pjzj.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public Integer getIsExported() {
        return isExported;
    }

    public void setIsExported(Integer isExported) {
        this.isExported = isExported;
    }
}