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
public class Box implements Serializable {
    private static final long serialVersionUID = -5197190427377603391L;

    private Integer id;

    /**
     * 箱号
     */
    private String cntr;

    /**
     * 最新动态地点
     */
    private String currentPlace;

    /**
     * lastState
     */
    private String lastState;

    /**
     * lastStateDate
     */
    private String lastStateDate;

    /**
     * sealNo
     */
    private String sealNo;

    /**
     * sizeType
     */
    private String sizeType;

    /**
     * 更新时间
     */
    private String updateTime;


}
