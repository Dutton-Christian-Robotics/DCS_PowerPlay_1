package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 Alliance Color: either blue or red
 Starting Position: touching wall, contained within tile A5 (blue) or F2 (red); camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) navigate to closest aliiance-sided high junction
 	3) deposit pre-loaded cone on junction
 	2) park within signal zone
 */


@Disabled
@Autonomous(name = "Left Hand 1 Autonomous", group = "Left")
public class LeftHand1AutonomousOpMode extends LinearOpMode {
    ProductionBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

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

//	   while (foundTagId < 1) {
//		  sleep(100);
//		  foundTagId = bot.vision.searchForTags();
//	   }

	   // Close claw to ensure grip on cone
	   bot.claw.close(); sleep(1500);

	   // Drive forward and knock signal out of way
	   bot.drivetrain.driveByVelocity(0.3, 0, 0); sleep(850);
	   bot.drivetrain.stopDriving(); sleep(400);

	   // Backup a smidge to prepare for strafe
	   bot.drivetrain.driveByVelocity(-0.3, 0, 0); sleep(200);
	   bot.drivetrain.stopDriving(); sleep(400);


	   bot.drivetrain.driveByVelocity(0, 0.5, 0); sleep(1800);
	   bot.drivetrain.stopDriving(); sleep(400);

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(3000);

	   bot.drivetrain.driveByVelocity(0.05, 0, 0); sleep(50);
	   bot.drivetrain.stopDriving(); sleep(500);

	   bot.claw.open(); sleep(750);

//	   bot.drivetrain.driveByVelocity(-0.05, 0, 0); sleep(50);
//	   bot.drivetrain.stopDriving(); sleep(500);

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_GROUND")); sleep(2000);

	   bot.navigation.comeToHeading(80, 0.25);


	   if (foundTagId == 1) {
		  bot.drivetrain.driveByVelocity(0.3, 0, 0); sleep(1400);
	   } else if (foundTagId == 2) {
		  bot.drivetrain.driveByVelocity(0.3, 0, 0); sleep(600);
	   } else if (foundTagId == 3) {
		  bot.drivetrain.driveByVelocity(0.3, 0, 0); sleep(150);
	   }
	   bot.drivetrain.stopDriving(); sleep(500);

    }
}