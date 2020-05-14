package com.cmcc.vr.mid.probe.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {
    private static TaskExecutor sInstance;
    private ArrayBlockingQueue<Runnable> mQueue;
    private ThreadPoolExecutor mThreadPool;

    private TaskExecutor() {
        mQueue = new ArrayBlockingQueue(1000, true);
        mThreadPool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, mQueue);
        mThreadPool.setRejectedExecutionHandler(new DiscardOldestPolicy());
    }

    public static synchronized TaskExecutor getExecutor() {
        TaskExecutor taskExecutor;
        synchronized (TaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new TaskExecutor();
            }
            taskExecutor = sInstance;
        }
        return taskExecutor;
    }

    public BlockingQueue getQueue() {
        return mThreadPool.getQueue();
    }

    /**
     * submit
     *
     * @param runnable
     */
    public void submit(Runnable runnable) {
        mThreadPool.execute(runnable);
    }

    public long getCompletedTaskCount() {
        return mThreadPool.getCompletedTaskCount();
    }

    public long getTaskCount() {
        return mThreadPool.getTaskCount();
    }

    public boolean isShutdown() {
        return mThreadPool.isShutdown();
    }

    /**
     * shutdown
     */
    public void shutdown() {
        mThreadPool.shutdown();
    }

    /**
     * purge
     */
    public void purge() {
        mThreadPool.purge();
    }
}
