package com.fskj.applibrary.domain.login;


import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class User implements Serializable {

    public int id;
    public String m_name;
    public String true_name;
    public String m_pass;
    public int q_id;
    public String create_time;
    public String create_manid;
    public int is_delete;
    public ArrayList<HandleMan> m_list;
    public ArrayList<HandleManOther> m_help_list;
    public String xzddm;
    public String province;
    public String city;
    public int part_id;
    public String part_name;
    public int a_id;
    public int m_id;
    public int is_expert;
    public String xk_pass;

    public String getJson() {
        StringBuffer sb = new StringBuffer();
        sb.append("[{\"operdm\":\"");
        sb.append(m_id);
        sb.append("\",\"login_name\":\"");
        sb.append(m_name);
        sb.append("\",\"rael_name\":\"");
        sb.append(true_name);
        sb.append("\",\"xzddm\":\"");
        sb.append(xzddm);
        sb.append("\",\"xk_pass\":\"");
        sb.append(xk_pass);
        sb.append("\"}]");
        return sb.toString();

    }
    // {
    // "error": 0,
    // "desc": "ok",
    // "retData": {
    // "id": "1",
    // "m_name": "张三",
    // "m_pass": "96e79218965eb72c92a549dd5a330112",
    // "xzddm": "332701100000",
    // "q_id": "2",
    // "province": "浙江省",
    // "city": "温州市",
    // "part_id": "1",
    // "a_id": "4",
    // "create_time": "2016-10-31 10:22:33",
    // "create_manid": "4",
    // "is_delete": "0"
    // },
    // "m_list": [
    // {
    // "id": "1",
    // "m_name": "张三",
    // "q_id": "2",
    // "t_name": "勘查人员"
    // },
    // {
    // "id": "2",
    // "m_name": "李四",
    // "q_id": "2",
    // "t_name": "勘查人员"
    // },
    // {
    // "id": "3",
    // "m_name": "王五",
    // "q_id": "2",
    // "t_name": "勘查人员"
    // }
    // ],
    // "area_list": [
    // {
    // "id": "4",
    // "area_name": "鹿城区",
    // "p_id": "2",
    // "deep": "3"
    // },
    // {
    // "id": "6",
    // "area_name": "瓯海区",
    // "p_id": "2",
    // "deep": "3"
    // },
    // {
    // "id": "7",
    // "area_name": "龙湾区",
    // "p_id": "2",
    // "deep": "3"
    // },
    // {
    // "id": "8",
    // "area_name": "永嘉县",
    // "p_id": "2",
    // "deep": "3"
    // }
    // ]
    // }

}
