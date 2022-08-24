package cn.edu.guet.bean;

import lombok.ToString;

@ToString
public class Jdr {
    // 易班id
    private String ybid;
    // 工号
    private String gh;
    // 姓名
    private String xm;
    // 手机
    private String sj;
    // 邮箱
    private String yx;
    // 1在职，2休假，3离职
    private String state;
    // （业务范围）1,2,4,8,16,32,64
    private String ywfw;
    // 
    private int zjds;
    
    private int dqds;
    
    private int pjpj;

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getZjds() {
		return zjds;
	}

	public void setZjds(int zjds) {
		this.zjds = zjds;
	}

	public int getDqds() {
		return dqds;
	}

	public void setDqds(int dqds) {
		this.dqds = dqds;
	}

	public int getPjpj() {
		return pjpj;
	}

	public void setPjpj(int pjpj) {
		this.pjpj = pjpj;
	}

	public String getYwfw() {
		return ywfw;
	}

	public void setYwfw(String ywfw) {
		this.ywfw = ywfw;
	}

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

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj == null ? null : sj.trim();
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx == null ? null : yx.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}