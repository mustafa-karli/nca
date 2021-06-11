package com.nauticana.basis.bgjob;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackgroundJob {

	private int maxRunningThreads = 10;
	private AnalyzedThread[] threads = null;
	public static int loglevel = 1;
	private long scheduleTime = System.currentTimeMillis();
	private int checkInterval = 1000;
	private int running;
	private int blocked;
	private int finished;
	//private int waiting;
	
	private long startTime = 0;
	private long totalProcess = 0;
	private long totalTime = 0;
	
	private String name;
	private String username;
	
	private int status = 0;

	public static String longToDateStr(long time, String format) {
		Date date = new Date(time);
		Format f = new SimpleDateFormat(format);
		return f.format(date);
	}

	public BackgroundJob(AnalyzedThread[] threads, int maxRunningThreads, int checkInterval, String name, String username) {
		super();
		this.maxRunningThreads = maxRunningThreads;
		this.threads = threads;
		this.checkInterval = checkInterval;
		this.name = name;
		this.username = username;
	}
	
	public long getScheduleTime() {
		return scheduleTime;
	}
	
	public void setScheduleTime(long scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	
	public boolean virgin() {
		return (status == 0);
	}
	
	public boolean started() {
		return (status == 1);
	}
	
	public boolean completed() {
		return (status == 2);
	}

	public String name() {
		return name;
	}
	
	public String username() {
		return username;
	}
	
	public void run() throws InterruptedException {
		
		boolean go = true;
		status = 1;
		startTime = System.currentTimeMillis();
		
		if (maxRunningThreads > threads.length)
			maxRunningThreads = threads.length;
		
		while (go) {
			running = 0;
			blocked = 0;
			finished = 0;
			// waiting = 0;

			//String runnings = "";
			//String blockeds = "";
			//String waitings = "";
			//String finisheds = "";
			
			for (int i = 0; i < threads.length; i++) {
				Thread.State st = threads[i].getState();
				if (st == null) {
					//waiting++;
					//waitings = waitings + " " + threads[i].getThreadName();
				}
				else
				switch (st) {
				case RUNNABLE:
					running++;
					//runnings = runnings + " " + threads[i].getThreadName();
					break;
				case BLOCKED:
					blocked++;
					//blockeds = blockeds + " " + threads[i].getThreadName();
					break;
				case TERMINATED:
					finished++;
					//finisheds = finisheds + " " + threads[i].getThreadName();
					break;
//				case NEW:
//					waiting++;
//					//waitings = waitings + " " + threads[i].getThreadName();
//					break;
				case WAITING:
					blocked++;
					//blockeds = blockeds + " " + threads[i].getThreadName();
					break;
				case TIMED_WAITING:
					blocked++;
					//blockeds = blockeds + " " + threads[i].getThreadName();
					break;
				default:
					break;
				}
			}
			
			for (int i = 0; i < threads.length; i++) {
				if ((running+blocked) < maxRunningThreads) {
					if (threads[i].getState() == null) {
						threads[i].start();
						running++;
						//waiting--;
					}
				}
			}
			
			if (finished == threads.length)
				go = false;
			else
				Thread.sleep(checkInterval);
		}
		status = 2;
		totalTime = System.currentTimeMillis() - startTime;
	}

		
	public String[] threadStatus() {
		String runnings = "";
		String blockeds = "";
		String waitings = "";
		String finisheds = "";
		
		for (int i = 0; i < threads.length; i++) {
			Thread.State st = threads[i].getState();
			if (st == null) {
				waitings = waitings + " " + threads[i].getThreadName();
			}
			else
			switch (st) {
			case RUNNABLE:
				runnings = runnings + " " + threads[i].getThreadName();
				break;
			case BLOCKED:
				blockeds = blockeds + " " + threads[i].getThreadName();
				break;
			case TERMINATED:
				finisheds = finisheds + " " + threads[i].getThreadName();
				break;
			case NEW:
				waitings = waitings + " " + threads[i].getThreadName();
				break;
			case WAITING:
				blockeds = blockeds + " " + threads[i].getThreadName();
				break;
			case TIMED_WAITING:
				blockeds = blockeds + " " + threads[i].getThreadName();
				break;
			default:
				break;
			}
		}

		String s[] = new String[4];
		s[0] = runnings;
		s[1] = blockeds;
		s[2] = waitings;
		s[3] = finisheds;
		return s;
	}
	
	public String status() {
		switch (status) {
		case 0:	return "virgin";
		case 1:	return "running";
		case 2:	return "completed";
		}
		return null;
	}
	
	public String[] print() {
		String[] s = new String[8];
		
		s[0] = name;
		s[1] = username;
		s[2] = longToDateStr(scheduleTime,"dd.mm.yyyy HH:mm:ss");
		s[3] = status();
		String[] ss = threadStatus();
		s[4] = ss[0];
		s[5] = ss[1];
		s[6] = ss[2];
		s[7] = ss[3];
		return s;
	}

	
	public String println() {
		String[] ss = threadStatus();
		String s = "Job Name : " + name + "\nUsername : " + username + "\nScheduled : " + longToDateStr(scheduleTime,"dd.mm.yyyy HH:mm:ss") + "\nStatus" + status();
		return  s + "\nThread status: running " + ss[0]  + "\n  waiting " + ss[1] + "\n  finished " + ss[2] + "\n  blocked " + ss[3];
	}
	
	public void stop() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].stop();
		}
	}
	
	public String statistics() {

		String s = "";
		for (int i = 0; i < threads.length; i++) {
			totalProcess = totalProcess + threads[i].getTotalProcess();
			totalTime = totalTime + threads[i].getTotalTime();
			s = s + threads[i].getPerformance() + "\n";
		}
		s = s + "Overall performance " + totalProcess + " processed in " + totalTime + " ms or " + AnalyzedThread.timeStr(totalTime)+ "  Performance: " + totalTime/totalProcess + " ms/proc " + (float) totalProcess/(totalTime/1000) + " proc/sec\n";				
		s = s + "Total Time elapsed " + totalTime + " or " + AnalyzedThread.timeStr(totalTime)+ "  Performance: " + totalTime/totalProcess + " ms/proc " + (float) totalProcess/(totalTime/1000) + " proc/sec";				
		return s;
	}
}
