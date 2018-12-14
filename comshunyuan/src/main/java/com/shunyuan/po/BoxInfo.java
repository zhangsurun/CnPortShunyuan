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
public class BoxInfo implements Serializable {

    private static final long serialVersionUID = -2722736595451799412L;
    /**
     * 箱号
     */
    private String cntr;
    /**
     * 动态发生地点
     */
    private String place;
    /**
     * state
     */
    private String state;

    /**
     * 动态次序
     */
    private String stateSeq;

    /**
     * 动态发生时间
     */
    private String stateTime;
    /**
     * 运输方式(船赋值为船名，其他以船公司为准)
     */
    private String transMode;
    /**
     * 船名
     */
    private String vesselName;
    /**
     * 航次(可为空)
     */
    private String vesselVoyage;
    /**
     * 更新时间
     */
    private String updateTime;


}
