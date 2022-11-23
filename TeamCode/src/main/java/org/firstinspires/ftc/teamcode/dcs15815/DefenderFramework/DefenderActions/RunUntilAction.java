package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import java.util.function.BooleanSupplier;

public class RunUntilAction extends DefenderAction {
    private Runnable block;
    private BooleanSupplier isFinishedWhen;

    RunUntilAction(Runnable b, BooleanSupplier i) {
	   super();
	   block = b;
	   isFinishedWhen = i;
    }

    @Override
    public void run() {
	   block.run();
	   isFinished = isFinishedWhen.getAsBoolean();
    }

}
