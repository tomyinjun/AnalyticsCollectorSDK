package com.cmcc.vr.mid.probe.job.reportjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class BaseReportJob implements Job {
    public BaseReportJob() {
        init();
    }
    public void init() {
        //TODO 数据采集工具初始化工作
    }

    public void getDataFromDB() {
        //TODO 从数据库查询信息并汇总
    }

    public void report() {
        //TODO 执行数据上报操作
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        getDataFromDB();
        report();
    }
}
