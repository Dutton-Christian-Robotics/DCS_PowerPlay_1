package org.firstinspires.ftc.teamcode.dcs15815;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*
 Alliance Color: blue
 Starting Position: touching wall, contained within tile ?; camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) park within signal zone (or terminal if there's a detection failure)
 */

@Autonomous(name = "Blue Left 0 Autonomous", group = "Blue")
public class Blue_Left_0_AutonomousOpMode extends LinearOpMode {
    ProductionAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Blue Left");
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new ProductionAutonomousBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   Pose2d startingPose = bot.configPose2d("NAVIGATION_START_BLUE_LEFT");

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

	   if (foundTagId < 1) { // for some reason couldn't find tag, park in terminal
		  Trajectory traj = bot.navigation.trajectoryBuilder(startingPose)
				.strafeLeft(24)
				.build();
		  bot.navigation.followTrajectory(traj);

	   } else if (foundTagId == 1) {

		  Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
				.forward(30)
				.build();

		  Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
				.forward(23)
				.build();

		  Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
				.forward(22)
				.build();

		  bot.navigation.followTrajectory(traj1);
		  bot.navigation.turn(Math.toRadians(90));
		  bot.navigation.followTrajectory(traj2);
		  bot.navigation.turn(Math.toRadians(-90));
		  bot.navigation.followTrajectory(traj3);

	   } else if (foundTagId == 2) {
		  Trajectory trajectory = bot.navigation.trajectoryBuilder(startingPose)
				.forward(36)
				.build();
		  bot.navigation.followTrajectory(trajectory);

	   } else if (foundTagId == 3) {

		  Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
				.forward(30)
				.build();

		  Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
				.forward(26)
				.build();

		  Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
				.forward(22)
				.build();

		  bot.navigation.followTrajectory(traj1);
		  bot.navigation.turn(Math.toRadians(-90));
		  bot.navigation.followTrajectory(traj2);
		  bot.navigation.turn(Math.toRadians(90));
		  bot.navigation.followTrajectory(traj3);

	   } else {
		  telemetry.addData("position", "found");
		  telemetry.update();
	   }
    }
}