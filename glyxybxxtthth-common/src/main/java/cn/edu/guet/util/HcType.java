package cn.edu.guet.util;


import org.springframework.stereotype.Component;

/**
 * @author Young Kbt
 * @date 2021/12/20 15:50
 * @description 耗材类型解析
 */
@Component
public class HcType {
    
    private String [][]hcType = new String[6][];
    
    public HcType() {
        hcType[0] = new String[1];
        hcType[0][0] = "无需耗材";

        hcType[1] = new String[5];
        hcType[1][0] = "安装";
        hcType[1][1] = "电气设备安装工程-通用";
        hcType[1][2] = "电气设备安装工程-明装";
        hcType[1][3] = "电气设备安装工程-暗装";
        hcType[1][4] = "给排水、燃气工程";

        hcType[2] = new String[4];
        hcType[2][0] = "安装拆除";
        hcType[2][1] = "变配电及低压电器拆除";
        hcType[2][2] = "给排水、燃气工程拆除";
        hcType[2][3] = "消防工程拆除";

        hcType[3] = new String[4];
        hcType[3][0] = "拆除";
        hcType[3][1] = "人工拆除工程";
        hcType[3][2] = "机械拆除工程";
        hcType[3][3] = "其他项目";

        hcType[4] = new String[9];
        hcType[4][0] = "建筑";
        hcType[4][1] = "土（石）方工程";
        hcType[4][2] = "砌筑工程";
        hcType[4][3] = "屋面及防水工程";
        hcType[4][4] = "保温、隔热、防腐工程";
        hcType[4][5] = "脚手架工程";
        hcType[4][6] = "混凝土模板及支架(撑)";
        hcType[4][7] = "其他项目";
        hcType[4][8] = "特殊项目及机械台班";

        hcType[5] = new String[9];
        hcType[5][0] = "市政";
        hcType[5][1] = "市政";
        hcType[5][2] = "楼地面工程";
        hcType[5][3] = "墙、柱面工程";
        hcType[5][4] = "天棚工程";
        hcType[5][5] = "油漆、涂料、裱糊工程";
        hcType[5][6] = "其他装饰工程";
        hcType[5][7] = "门窗工程";
        hcType[5][8] = "玻璃价格调差";
    }

    /**
     * 根据耗材类别名，解析成对应的数字
     */
    public String getHcLbNumByHcLb(String hcLb) {
        for (int i = 0; i < hcType.length; i++) {
            if(hcLb.equals(hcType[i][0])) {
                return String.valueOf(i);
            }
        }
        return null;
    }

    /**
     * 根据耗材型号名，解析成对应的数字
     */
    public String getHcXhNumByHcXh(String hcXh) {
        for (String[] strings : hcType) {
            for (int j = 0; j < strings.length; j++) {
                if (hcXh.equals(strings[j])) {
                    return String.valueOf(j);
                }
            }
        }
        return null;
    }
}
