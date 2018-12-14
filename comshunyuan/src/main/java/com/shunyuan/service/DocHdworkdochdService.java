package com.shunyuan.service;

import com.google.gson.Gson;
import com.shunyuan.dao.DocHdworkdochdDao;
import com.shunyuan.po.Box;
import com.shunyuan.po.BoxInfo;
import com.shunyuan.po.DocHdworkdochd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhangsurun@126.com
 * @date 2018/12/14
 */
@Service("docHdworkdochdService")
@ComponentScan({"com.shunyuan.dao"})
public class DocHdworkdochdService implements DocHdworkdochdIService {

    @Autowired
    private DocHdworkdochdDao docHdworkdochdDao;

    @Override
    public List<DocHdworkdochd> getBoxInfo() {
        return docHdworkdochdDao.getBoxInfo();
    }


    @Override
    public void saveBoxInfo(List<DocHdworkdochd> boxId) {
        List<Box> boxList = new ArrayList<>();
        List<BoxInfo> boxInfoList = new ArrayList<>();
        boxId.forEach(d -> {

            String box = null;
            try {
                String url = "http://zhixiangsou.huadongdata.net/PushDataApi/pushDataTest?clientId=" +
                        d.getShipcompanyId() + "&searchType=CTNR_NO&searchValue=" + d.getContainerid();
                box = load(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            Map cntrMap = gson.fromJson(box, Map.class);
            Map resultCntrMap = (Map) cntrMap.get("result");
            List<Map<String, Object>> coscoCntrRecordList = (List) resultCntrMap.get("coscoCntrRecordList");
            List<Map<String, Object>> coscoCntrStateList = (List) coscoCntrRecordList.get(0).get("coscoCntrStateList");

            if (resultCntrMap.size() > 0 && coscoCntrRecordList.size() > 0 && coscoCntrStateList != null && coscoCntrStateList.size() > 0) {
                Map<String, Object> boxMap = coscoCntrRecordList.get(0);
                Box b = new Box();
                String cntr = getSafeValue(boxMap.get("cntr"));
                b.setCntr(cntr);
                b.setCurrentPlace(getSafeValue(boxMap.get("currentPlace")));
                b.setLastState(getSafeValue(boxMap.get("lastState")));
                b.setLastStateDate(getSafeValue(boxMap.get("lastStateDate")));
                b.setSealNo(getSafeValue(boxMap.get("sealNo")));
                b.setSizeType(getSafeValue(boxMap.get("sizeType")));
                boxList.add(b);

                coscoCntrStateList.forEach(boxInfoMap -> {
                    BoxInfo bI = new BoxInfo();
                    bI.setCntr(cntr);
                    bI.setPlace(getSafeValue(boxInfoMap.get("place")));
                    bI.setState(getSafeValue(boxInfoMap.get("state")));
                    bI.setStateSeq(getSafeValue(boxInfoMap.get("stateSeq")));
                    bI.setStateTime(getSafeValue(boxInfoMap.get("stateTime")));
                    bI.setTransMode(getSafeValue(boxInfoMap.get("transMode")));
                    bI.setVesselName(getSafeValue(boxInfoMap.get("vesselName")));
                    bI.setVesselVoyage(getSafeValue(boxInfoMap.get("vesselVoyage")));
                    boxInfoList.add(bI);
                });
            }
        });

        batchExecute1(boxList);
        batchExecute2(boxInfoList);
    }

    private String load(String url) throws Exception {
        URL restURL = new URL(url);
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //请求方式
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setAllowUserInteraction(false);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line, resultStr = "";

        while (null != (line = bReader.readLine())) {
            resultStr += line;
        }

        bReader.close();

        return resultStr;

    }

    private String getSafeValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    private boolean batchExecute1(List<Box> allList) {
        //一次处理数据的数量
        int sum = 2500;
        int n = allList.size() / sum;
        try {
            if (n == 0) {
                //小于2500条
                docHdworkdochdDao.boxInsertBatch(allList);
            } else {
                //大于2500条
                for (int i = 1; i <= n; i++) {
                    docHdworkdochdDao.boxInsertBatch(allList.subList(sum * (i - 1), sum * i));
                    if (i == n) {
                        docHdworkdochdDao.boxInsertBatch(allList.subList(sum * i, allList.size()));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("---------------------------------sql错误打印---------------------------------:" + e.getMessage());
            return false;
        }
    }

    private boolean batchExecute2(List<BoxInfo> allList) {
        //一次处理数据的数量
        int sum = 2500;
        int n = allList.size() / sum;
        try {
            if (n == 0) {
                //小于2500条
                docHdworkdochdDao.boxInfoInsertBatch(allList);
            } else {
                //大于2500条
                for (int i = 1; i <= n; i++) {
                    docHdworkdochdDao.boxInfoInsertBatch(allList.subList(sum * (i - 1), sum * i));
                    if (i == n) {
                        docHdworkdochdDao.boxInfoInsertBatch(allList.subList(sum * i, allList.size()));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("---------------------------------sql错误打印---------------------------------:" + e.getMessage());
            return false;
        }
    }
}
