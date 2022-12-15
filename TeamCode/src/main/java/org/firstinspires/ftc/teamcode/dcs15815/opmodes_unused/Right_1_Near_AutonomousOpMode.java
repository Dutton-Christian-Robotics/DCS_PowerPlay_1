package org.firstinspires.ftc.teamcode.dcs15815.opmodes_unused;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
/*
 Alliance Color: either
 Starting Position: touching wall, contained within tile F5 (red) or A2 (blue); camera pointed at signal
 Tasks:
	1) determine signal zone from team-supplied signal sleeve
 	2) drive to position near closest tall junction
 	3) deliver preloaded cone
 	4) park within signal zone (no fallback for not sensing signal)
  */

@Disabled
@Autonomous(name = "Right 1: near junction", group = "Right")
public class Right_1_Near_AutonomousOpMode extends LinearOpMode {
    EdwardScissorliftAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Right");
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new EdwardScissorliftAutonomousBot(hardwareMap, ESBConfiguration.class, telemetry);

//	   Pose2d startingPose = bot.configPose2d("NAVIGATION_START_RED_LEFT");
	   Pose2d startingPose = new Pose2d();

	   bot.claw.close();

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

	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_LOW); sleep(1500);

	   Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
			 .forward(30)
			 .build();

	   Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
			 .forward(22)
			 .build();

	   Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
			 .forward(12)
			 .build();

	   Trajectory traj4 = bot.navigation.trajectoryBuilder(traj3.end())
			 .back(11)
			 .build();

	   Trajectory traj5_1 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
			 .forward(14)
			 .build();
	   Trajectory traj5_2 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(-135))))
			 .forward(23)
			 .build();
	   Trajectory traj5_3 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(-135))))
			 .forward(48)
			 .build();

	   bot.navigation.followTrajectory(traj1);
	   bot.navigation.turn(Math.toRadians(90));
	   bot.navigation.followTrajectory(traj2);
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_HIGH); sleep(1250);
	   bot.navigation.turn(Math.toRadians(-45));
	   bot.navigation.followTrajectory(traj3);
	   bot.claw.open(); sleep(2000);
	   bot.navigation.followTrajectory(traj4);
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_GROUND); sleep(2000);


	   if (foundTagId < 1) {

	   } else if (foundTagId == 1) {
		  bot.navigation.turn(Math.toRadians(-45));
		  bot.navigation.followTrajectory(traj5_1);
	   } else if (foundTagId == 2) {
		  bot.navigation.turn(Math.toRadians(-135));
		  bot.navigation.followTrajectory(traj5_2);
	   } else if (foundTagId == 3) {
		  bot.navigation.turn(Math.toRadians(-135));
		  bot.navigation.followTrajectory(traj5_3);
	   }
    }
}