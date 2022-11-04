package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Disabled
@Autonomous(name = "Left Hand 1 AuUtonomous", group = "Left")
public class LeftHand1AutonomousOpMode extends LinearOpMode {
    ProductionBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   while (!isStarted() && !isStopRequested()) {
		  foundTagId = bot.vision.searchForTags();
		  if (foundTagId > 0) {
			 telemetry.addData("Signal Position", foundTagId);
		  } else {
			 telemetry.addData("Signal Position", "NOT FOUND");
		  }
		  telemetry.update();
		  sleep(20);
	   }
//	   waitForStart();

	   while (foundTagId < 1) {
		  sleep(100);
		  foundTagId = bot.vision.searchForTags();
	   }

	   bot.navigation.driveToPosition(0, 2);
	   bot.navigation.driveToPosition(24, 2);
	   bot.navigation.driveToPosition(24, 26);
	   bot.navigation.driveToPosition(24, 26);
//
//	   if (barcodePosition == 1) {
//		  bot.arm.awaitTiltMotorPosition(64);
//		  bot.arm.awaitExtendMotorPosition(500);
////	       bot.navigation.driveToPosition(34, 19);
//		  bot.claw.open();
//		  sleep(1000);
//
//	   } else if (barcodePosition == 2) {
//		  // opens claw too early
//		  bot.arm.awaitTiltAndExtendMotorPositions(80, 1026);
//		  bot.claw.open();
//		  sleep(1000);
//		  bot.arm.awaitTiltAndExtendMotorPositions(100, 0);
//
//	   } else if (barcodePosition == 3) {
//
//		  bot.arm.awaitTiltMotorPosition(154);
//		  bot.arm.awaitExtendMotorPosition(1500);
//
//		  bot.claw.open();
//		  sleep(1000);
//		  bot.arm.setTiltMotorPosition(0);
//		  bot.arm.setExtendMotorPosition(0);
//
//	   }

	   bot.navigation.resetAndDriveToPosition(0, -2);
	   bot.navigation.comeToHeading(-90);
	   bot.navigation.resetAndDriveToPosition(0, 65);
    }
}