package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions.DefenderAction;

public class ClawAction extends DefenderAction {

    private boolean shouldOpen;
    private long wait = 0;


    public ClawAction(boolean b) {
	   shouldOpen = b;
    }

    public static ClawAction close() {
	   return new ClawAction(false);
    }

    public static ClawAction open() {
	   return new ClawAction(true);
    }

    public ClawAction thenWait(int m) {
	   wait = m; return this;
    }


    @Override
    public void run() {
	   EdwardScissorliftAutonomousBot bot = (EdwardScissorliftAutonomousBot)actionSequence.bot;
	   if (shouldOpen) {
		  bot.claw.open();
	   } else {
		  bot.claw.close();
	   }
	   if (wait > 0) {
		  bot.sleep(wait);
	   }
	   isFinished = true;
    }

}
