package com.nauticana.basis.bgjob;

public abstract class AnalyzedThread implements Runnable {
	
	private String threadName;
	private Thread thread;
	protected long totalProcess = 0;
	protected boolean live = true;
	private long totalTime = 0;
	
	
	public static String timeStr(long millis) {
		long totalSec = millis / 1000; 
		long totalMin = totalSec / 60;
		long totalHour = totalMin / 60;
		return totalHour + ":" + (totalMin - (totalHour * 60)) + ":" + (totalSec - (totalMin * 60));
	}
	
	public String getPerformance() {
		float rps;
		float mpr;
		try {
			rps = totalProcess/(totalTime/1000);
		} catch (Exception e) {
			rps = totalProcess;
		}
		try {
			mpr = totalTime/totalProcess;
		} catch (Exception e) {
			mpr = 0;
		}
		
		return threadName + " processed total " + totalProcess + " in " + totalTime + " ms or " + timeStr (totalTime)+ "  Performance: " + mpr + " ms/row " + rps + " row/sec";
	}
	
	public long getTotalProcess() {
		return totalProcess;
	}
	
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadName() {
		return threadName;
	}
	
	public Thread.State getState() {
		if (thread == null)
			return null;
		else
			return thread.getState();
	}
	
	public void start() {
		System.out.println("Starting thread " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	public void stop() {
		this.live=false;
	}

}
