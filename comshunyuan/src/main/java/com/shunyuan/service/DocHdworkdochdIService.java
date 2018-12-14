package com.shunyuan.service;

import com.shunyuan.po.DocHdworkdochd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhangsurun@126.com
 * @date 2018/12/14
 */
public interface DocHdworkdochdIService {

    public List<DocHdworkdochd> getBoxInfo();

    public void saveBoxInfo(List<DocHdworkdochd> list);
}
