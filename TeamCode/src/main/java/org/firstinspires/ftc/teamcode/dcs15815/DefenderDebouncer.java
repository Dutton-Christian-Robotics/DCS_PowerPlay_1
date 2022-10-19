package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.Callable;

// this is code originally written for the 2019-2020 season
public class DefenderDebouncer implements Runnable {
    public long timeout;
    private ElapsedTime timer;
    private Runnable codeBlock;
    private boolean isFirstRun = true;

    DefenderDebouncer(long t, Runnable block) {
	   timeout = t;
	   timer = new ElapsedTime();
	   timer.reset();
	   codeBlock = block;
    }

    public void run() {
	   if (isFirstRun || (timer.milliseconds() > timeout)) {
		  isFirstRun = false;
		  codeBlock.run();
		  timer.reset();
	   }
    }
}