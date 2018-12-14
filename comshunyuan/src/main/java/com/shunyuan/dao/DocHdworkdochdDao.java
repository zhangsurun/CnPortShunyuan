package com.shunyuan.dao;

import com.shunyuan.po.Box;
import com.shunyuan.po.BoxInfo;
import com.shunyuan.po.DocHdworkdochd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhangsurun@126.com
 * @date 2018/12/14
 */
@Mapper
@Repository
public interface DocHdworkdochdDao {

    public List<DocHdworkdochd> getBoxInfo();

    public void boxInsertBatch(List<Box> boxList);

    public void boxInfoInsertBatch(List<BoxInfo> infoList);
}
