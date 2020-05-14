package com.cmcc.vr.mid.probe.job.collectjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class BaseCollectJob  implements Job {
    public void BaseCollectJob(){
        init();
    }
    public void init() {
        //TODO 数据采集工具初始化工作
    }
    public void collectData() {
        //TODO 数据采集
    }
    public void storeData() {
        //TODO 数据入库
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        collectData();
        storeData();
    }
}
