package com.fskj.applibrary.domain.login;

import java.io.Serializable;

import lombok.Data;

@Data
public class HandleMan implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6060628395039217892L;
    public int id;
    public String m_name;
    public int q_id;
    public String t_name;
    public String true_name;
    // "id": "2",
    // "m_name": "李四",
    // "q_id": "1",
    // "t_name": "温州市局"
}
