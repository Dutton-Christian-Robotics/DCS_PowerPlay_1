package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitAction extends DefenderAction {
    private long waitTime;
    private ElapsedTime timer;

    WaitAction(long ms) {
	   super();
	   waitTime = ms;
	   timer = new ElapsedTime();
    }

    @Override
    public void beforeStart() {
	   timer.reset();
//        System.out.println("Beginning to wait");
    }

    @Override
    public void run() {
	   if (timer.milliseconds() >= waitTime) {
		  isFinished = true;
	   }
    }


}
