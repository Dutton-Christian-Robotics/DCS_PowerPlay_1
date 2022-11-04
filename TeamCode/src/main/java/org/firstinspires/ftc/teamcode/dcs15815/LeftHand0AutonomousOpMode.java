package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*
 Alliance Color: either blue or red
 Starting Position: touching wall, contained within tile A5 (blue) or F2 (red); camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) park within signal zone (or terminal if there's a detection failure)
 */

@Autonomous(name = "Left Hand 0 Autonomous", group = "Left")
public class LeftHand0AutonomousOpMode extends LinearOpMode {
    ProductionBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
//		if (bot == null) {
//		    telemetry.addData("bot", "null");
//		}
//	   if (bot.drivetrain == null) {
//		  telemetry.addData("drivetrain", "null");
//	   } else {
//		  telemetry.addData("drivetrain", bot.drivetrain.getClass().getName());
//	   }
//		if (bot.navigation == null) {
//		  telemetry.addData("navigation", "null");
//	   }
//	   if (bot.sensors == null) {
//		  telemetry.addData("sensors", "null");
//	   }
//	   if (bot.vision == null) {
//		  telemetry.addData("vision", "null");
//	   }

	   while (!isStarted() && !isStopRequested()) {
		  int mostRecentlyFoundTagId = bot.vision.searchForTags();
		  if (mostRecentlyFoundTagId > 0) {
			 foundTagId = mostRecentlyFoundTagId;
			 telemetry.addData("Signal Position Found", foundTagId);
		  } else if (foundTagId == -1) {
			 telemetry.addData("Signal", "searching...");
		  }
		  telemetry.update();
		  sleep(20);
	   }
//	   waitForStart();

// I'm hoping that the tag will always be identified before start
//	   while (foundTagId < 1) {
//		  sleep(100);
//		  foundTagId = bot.vision.searchForTags();
//	   }

	   if (foundTagId < 1) { // for some reason couldn't find tag, park in terminal
		  bot.drivetrain.driveByVelocity(0.2, 0, 0);
		  sleep(50);

		  bot.drivetrain.stopDriving();
		  sleep(200);

		  bot.drivetrain.driveByVelocity(0, -0.25, 0);
		  sleep(2000);

		  bot.drivetrain.stopDriving();
		  sleep(400);

	   } else if (foundTagId == 1) {
		  bot.drivetrain.driveByVelocity(0.2, 0, 0);
		  sleep(50);

		  bot.drivetrain.stopDriving();
		  sleep(200);

		  bot.drivetrain.driveByVelocity(0, -0.25, 0);
		  sleep(2100);

		  bot.drivetrain.stopDriving();
		  sleep(400);

		  bot.drivetrain.driveByVelocity(0.3, 0, 0);
		  sleep(600);

		  bot.drivetrain.stopDriving();

	   } else if (foundTagId == 2) {
		  bot.drivetrain.driveByVelocity(0.3, 0, 0);
		  sleep(700);

		  bot.drivetrain.stopDriving();

	   } else if (foundTagId == 3) {
		  bot.drivetrain.driveByVelocity(0.3, 0, 0);
		  sleep(725);

		  bot.drivetrain.stopDriving();
		  sleep(400);

		  bot.drivetrain.driveByVelocity(0, 0.5, 0);
		  sleep(1200);

		  bot.drivetrain.stopDriving();

	   } else {
		  telemetry.addData("position", "found");
		  telemetry.update();
	   }
    }
}