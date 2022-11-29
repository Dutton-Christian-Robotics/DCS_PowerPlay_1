package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import java.lang.reflect.InvocationTargetException;

abstract public class DefenderActionsOpMode extends LinearOpMode {
    protected DefenderActionSequence actionSequence;

    protected Class<? extends DefenderBot> botClass;
    protected Class<? extends DefenderBotConfiguration> configClass;
    public DefenderBot bot;

//    public DefenderGamepad controller1;
//    public DefenderGamepad controller2;

    public void setBot(DefenderBot b) {
	   this.bot = b;
    }

    public void setBotClass(Class<? extends DefenderBot> botClass) {
	   this.botClass = botClass;
    }

    public void setConfigClass(Class<? extends DefenderBotConfiguration> configClass) {
	   this.configClass = configClass;
    }

    public abstract void configureBot();

    public abstract void configureActions();

    @Override
    public void runOpMode() {
//	   controller1 = new DefenderGamepad(gamepad1);
//	   controller2 = new DefenderGamepad(gamepad2);

	   configureBot();
	   try {
		  if (bot == null) {
			 bot = botClass.getConstructor(HardwareMap.class, DefenderBotConfiguration.class, Telemetry.class).newInstance(hardwareMap, configClass, telemetry);
		  }
	   } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
		  e.printStackTrace();
		  throw new RuntimeException("Could not create bot instance.");
	   }

	   actionSequence = new DefenderActionSequence(bot);
//	   actionSequence.addEventSource(controller1);
//	   actionSequence.addEventSource(controller2);
//	   for (DefenderBotSystem s : bot.getSystems()) {
//		  actionSequence.addEventSource(s);
//	   }
	   configureActions();

	   // somehow execute actions designed for init mode

	   waitForStart();

	   while (opModeIsActive() && !actionSequence.isFinished()) {
		  actionSequence.run();
	   }

    }

    public void setFirstAction(DefenderAction s) {
	   actionSequence.setAction(s);
    }

    public void beginWith(DefenderAction s) {
	   // should check to see if a action has been set with
	   actionSequence.setAction(s);
    }

//    public DefenderBotPosition configDefenderBotPosition(String key) {
//	   return (DefenderBotPosition) bot.configuration.get(key);
//    }

}

