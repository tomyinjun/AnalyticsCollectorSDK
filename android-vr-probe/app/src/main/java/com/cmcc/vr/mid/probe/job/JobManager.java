package com.cmcc.vr.mid.probe.job;

import com.cmcc.vr.mid.probe.job.collectjob.BaseCollectJob;
import com.cmcc.vr.mid.probe.job.reportjob.BaseReportJob;

public class JobManager {
    public static void startCollectJob() {
        //TODO 获取cron表达式
        String transmitTaskSchedule = "0 0 8 * * ? *";
        //TransmitTask
        //TODO 用QuartzManager添加cronJob
        QuartzManager.addJob("TRANSMITTASK_JOB", "TRANSMITTASK_TRIGGER",
                BaseCollectJob.class, transmitTaskSchedule);
    }

    public static void startReportJob() {
        String transmitTaskSchedule = "0 0 8 * * ? *";
        //TransmitTask
        QuartzManager.addJob("TRANSMITTASK_JOB", "TRANSMITTASK_TRIGGER",
                BaseReportJob.class, transmitTaskSchedule);
    }

    public static void stopJob() {

    }
}
