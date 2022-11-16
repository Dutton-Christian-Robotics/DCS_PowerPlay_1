package org.firstinspires.ftc.teamcode.dcs15815;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*
 Alliance Color: red
 Starting Position: touching wall, contained within tile F2; camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) park within signal zone (or terminal if there's a detection failure)
 */

@Autonomous(name = "Red Left 1 Autonomous", group = "Left")
public class Red_Left_1_AutonomousOpMode extends LinearOpMode {
    ProductionAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new ProductionAutonomousBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   Pose2d startingPose = bot.configPose2d("NAVIGATION_START_RED_LEFT");

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

	   Trajectory traj = bot.navigation.trajectoryBuilder(startingPose)
			 .splineTo(new Vector2d(-40, 36), 0)
			 .splineTo(new Vector2d(-31, 0), 0)
			 .build();
	   bot.navigation.followTrajectory(traj);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(3000);

	   if (foundTagId < 1) {

	   } else if (foundTagId == 1) {

	   } else if (foundTagId == 2) {

	   } else if (foundTagId == 3) {
	   }
    }
}