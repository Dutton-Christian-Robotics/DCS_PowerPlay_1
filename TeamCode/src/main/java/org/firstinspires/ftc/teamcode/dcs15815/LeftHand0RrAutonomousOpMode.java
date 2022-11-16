package org.firstinspires.ftc.teamcode.dcs15815;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*
 Alliance Color: either blue or red
 Starting Position: touching wall, contained within tile A5 (blue) or F2 (red); camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) park within signal zone (or terminal if there's a detection failure)
 */

@Autonomous(name = "RR Left Hand 0 Autonomous", group = "Left")
public class LeftHand0RrAutonomousOpMode extends LinearOpMode {
    ProductionAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new ProductionAutonomousBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
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

	   Pose2d startingPose = new Pose2d(-65, 36, 0);
//	   Pose2d startingPose = new Pose2d(65, -36, Math.toRadians(90));

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

	   } else if (foundTagId == 1) {
		  Trajectory traj = bot.navigation.trajectoryBuilder(startingPose)
				.splineTo(new Vector2d(-38, 36), 0)
				.splineTo(new Vector2d(-30, 63), 0)
				.build();
		  bot.navigation.followTrajectory(traj);
//		  Trajectory traj1 = bot.navigation.trajectoryBuilder(new Pose2d())
//				.forward(30)
//				.build();
//
//		  Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end())
//				.strafeLeft(80)
//				.build();
//
//		  bot.navigation.followTrajectory(traj1);
//		  bot.navigation.followTrajectory(traj2);

//		  bot.navigation.followTrajectory(traj);


	   } else if (foundTagId == 2) {
		  Trajectory trajectory = bot.navigation.trajectoryBuilder(startingPose)
				.forward(36)
				.build();
		  bot.navigation.followTrajectory(trajectory);


	   } else if (foundTagId == 3) {
		  Trajectory traj = bot.navigation.trajectoryBuilder(startingPose)
				.splineTo(new Vector2d(-38, 36), 0)
				.splineTo(new Vector2d(-30, 12), 0)
				.build();
		  bot.navigation.followTrajectory(traj);
//		  Trajectory traj1 = bot.navigation.trajectoryBuilder(new Pose2d())
//				.forward(30)
//				.build();
//
//		  Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end())
//				.strafeRight(80)
//				.build();
//
//		  bot.navigation.followTrajectory(traj1);
//		  bot.navigation.followTrajectory(traj2);


	   } else {
		  telemetry.addData("position", "found");
		  telemetry.update();
	   }
    }
}