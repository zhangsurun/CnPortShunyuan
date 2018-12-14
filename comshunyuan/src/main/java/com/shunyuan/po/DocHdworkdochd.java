package com.shunyuan.po;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhangsurun@126.com
 * @date 2018/12/14
 */
@Data
public class DocHdworkdochd implements Serializable {
    private static final long serialVersionUID = -3467918243420186628L;

    /**
     * 箱号,
     */
    private String containerid;

    /**
     * 封号,
     */
    private String containermaskno;
    /**
     * 箱型,
     */
    private String containercode;
    /**
     * 业务编号,
     */
    private String doccode;
    /**
     * m主提单号,
     */
    private String blcode;
    /**
     * 船名,
     */
    private String shipname;

    /**
     * 航次,
     */
    private String voyage;
    /**
     * atd,
     */
    private String atd;
    /**
     * 船公司,
     */
    private String entshortname;
    /**
     * 船公司ID,
     */
    private String shipcompanyId;
}
