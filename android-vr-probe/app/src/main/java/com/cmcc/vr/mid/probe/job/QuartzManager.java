package com.cmcc.vr.mid.probe.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

public class QuartzManager {
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "CMCC_VR_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "CMCC_VR_TRIGGERGROUP_NAME";

	public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, String quartzCronTime) {
		addJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, jobClass, quartzCronTime, null);
	}
	
	public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, String quartzCronTime,Map<String, Object> paramMap) {
		addJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, jobClass, quartzCronTime, paramMap);
	}
	
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String quartzCronTime,Map<String, Object> paramMap) {
		try {
			// 获得调度器示例
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
			// 可以传递参数
			if ((null != paramMap) && (0 < paramMap.size())) {
				jobDetail.getJobDataMap().putAll(paramMap);
			}
			// 触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					//在任务调度器中，使用任务调度器的 CronScheduleBuilder 来生成一个具体的CronTrigger对象
					.withSchedule(CronScheduleBuilder.cronSchedule(quartzCronTime)).build();
			// 向Scheduler中添加job任务和trigger触发器
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void updateJobCron(String triggerName, String quartzCronTime) {
		updateJobCron(triggerName, TRIGGER_GROUP_NAME, quartzCronTime);
	}
	
	/**
	 * 更新定时任务调度时间
	 */
	public static void updateJobCron(String triggerName, String triggerGroupName, String quartzCronTime) {
		try {
			// 获得调度器示例
			Scheduler scheduler = schedulerFactory.getScheduler();
			//组织触发器
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
			if(null == trigger) return;
			// 触发器
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(quartzCronTime)).build();
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除任务
	 */
	public static void deleteJob(String jobName,String jobGroupName, String triggerName, String triggerGroupName ) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 启动所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
