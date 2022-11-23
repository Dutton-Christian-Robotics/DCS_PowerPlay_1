package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

public class RunOnceAction extends DefenderAction {
    private Runnable block;

    RunOnceAction(Runnable b) {
	   super();
	   block = b;
    }

    @Override
    public void run() {
	   block.run();
	   isFinished = true;
    }
}