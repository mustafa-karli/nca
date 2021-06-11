package com.nauticana.basis.bgjob;

import java.util.ArrayList;
import java.util.TimerTask;

public class BGRunner extends TimerTask {

	private ArrayList<BackgroundJob> backgroundJobs;

	public BGRunner(ArrayList<BackgroundJob> backgroundJobs) {
		super();
		this.backgroundJobs = backgroundJobs;
	}

	public void run() {
//		while (true)
		try {
			for (int i = 0; i < backgroundJobs.size(); i++) {
				BackgroundJob bgj = backgroundJobs.get(i);
				//if (bgj.completed())
				//	backgroundJobs.remove(i);
				if (bgj.virgin() && bgj.getScheduleTime() < System.currentTimeMillis()) {
					System.out.println("Starting " + bgj.name());
					bgj.run();
				}
//				System.out.println(bgj.status());
			}
//			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
