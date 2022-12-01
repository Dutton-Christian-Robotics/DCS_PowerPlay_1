package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions.DefenderAction;

public class LiftAction extends DefenderAction {

    private int position;
    private long wait = 0;


    public LiftAction(int p) {
	   position = p;
    }

    public static LiftAction gotoHeight(int p) {
	   return new LiftAction(p);
    }

    public static LiftAction gotoGround() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_GROUND);
    }

    public static LiftAction gotoStackTop() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_STACKTOP);
    }

    public static LiftAction gotoLow() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_LOW);
    }

    public static LiftAction gotoMiddle() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_MIDDLE);
    }

    public static LiftAction gotoBelowHigh() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_BELOW_HIGH);
    }

    public static LiftAction gotoHigh() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_HIGH);
    }

    public static LiftAction gotoMax() {
	   return new LiftAction(EdwardScissorliftBotConfiguration.LIFT_POSITION_MAX);
    }

    public LiftAction thenWait(int m) {
	   wait = m;
	   return this;
    }


    @Override
    public void run() {
	   EdwardScissorliftAutonomousBot bot = (EdwardScissorliftAutonomousBot)actionSequence.bot;
	   bot.lift.setPosition( position);
	   if (wait > 0) {
		  bot.sleep(wait);
	   }
	   isFinished = true;
    }

}
