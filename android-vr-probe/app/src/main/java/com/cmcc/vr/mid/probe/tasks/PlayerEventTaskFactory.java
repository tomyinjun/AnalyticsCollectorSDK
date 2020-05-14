package com.cmcc.vr.mid.probe.tasks;

import com.cmcc.vr.mid.probe.constant.PlayerEventCode;

public class PlayerEventTaskFactory {
    public Runnable getEventTaskByCode(int code) throws Exception {
        Class<?> cls = Class.forName(PlayerEventCode.loanPlayerEventCode(code).getEntityName());
        Runnable obj = (Runnable)cls.newInstance();
        return obj;
    }
}
