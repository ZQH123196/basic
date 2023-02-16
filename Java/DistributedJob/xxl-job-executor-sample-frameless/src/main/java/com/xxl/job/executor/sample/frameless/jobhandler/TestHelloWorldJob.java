package com.xxl.job.executor.sample.frameless.jobhandler;

import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * author: panhe
 * create_time: 2021/4/27 19:39
 * description:
 */
public class TestHelloWorldJob extends IJobHandler {

    private static Logger logger = LoggerFactory.getLogger(TestHelloWorldJob.class);

    @Override
    public void execute() throws Exception {
        System.out.println("我是真的被执行了呀,===>"+ new Date());
        logger.info("hello ,xxl-job,now  is {}",new Date());
    }
}
