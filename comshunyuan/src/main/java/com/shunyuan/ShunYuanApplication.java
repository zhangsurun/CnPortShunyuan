package com.shunyuan;

import com.shunyuan.service.DocHdworkdochdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhangsurun@126.com
 * @date 2018/12/14
 */
@RestController
@SpringBootApplication
public class ShunYuanApplication {


    @Autowired
    private DocHdworkdochdService docHdworkdochdService;

    public static void main(String[] args) {
        SpringApplication.run(ShunYuanApplication.class, args);
        ShunYuanApplication application = new ShunYuanApplication();
        application.run();
    }

    private void run() {
        docHdworkdochdService.saveBoxInfo(docHdworkdochdService.getBoxInfo());
    }

}
