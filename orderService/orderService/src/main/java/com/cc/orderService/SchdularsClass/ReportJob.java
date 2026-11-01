package com.cc.orderService.SchdularsClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportJob {
    private static final Logger log = LoggerFactory.getLogger(ReportJob.class);

    @Scheduled(fixedRate = 10000)   // every 10s
    public void run() { log.info("job ran"); }
}
