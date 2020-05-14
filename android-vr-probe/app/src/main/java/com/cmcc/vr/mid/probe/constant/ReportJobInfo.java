package com.cmcc.vr.mid.probe.constant;

public enum ReportJobInfo {
    //枚举所有上报任务信息
    ALARM_REPORT("", "", "This is a alarm report task.");
    private String name;
    private String triggerName;
    private String msg;

    ReportJobInfo(String name, String triggerName, String msg) {
        this.name = name;
        this.triggerName = triggerName;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public String getMsg() {
        return msg;
    }
}
