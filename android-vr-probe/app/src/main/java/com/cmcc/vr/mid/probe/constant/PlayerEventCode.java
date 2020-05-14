package com.cmcc.vr.mid.probe.constant;

public enum PlayerEventCode {
    //枚举所有定义的播放器事件
    SEEK_BEGIN(0, "SeekBeginPlayerEventTask", "This is a seek begin player event.");
    private int code;
    private String taskName;
    private String msg;

    PlayerEventCode(int code, String taskName, String msg) {
        this.code = code;
        this.taskName = taskName;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getEntityName() {
        return "com.***"+taskName;
    }

    public String getMsg() {
        return msg;
    }

    public static PlayerEventCode loanPlayerEventCode(int code) {
        for (PlayerEventCode playerEventCode : PlayerEventCode.values()) {
            if (code == playerEventCode.getCode()) {
                return playerEventCode;
            }
        }
        return null;
    }
}
