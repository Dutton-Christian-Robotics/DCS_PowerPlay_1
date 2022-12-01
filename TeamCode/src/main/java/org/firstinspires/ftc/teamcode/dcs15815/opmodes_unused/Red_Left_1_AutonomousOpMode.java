package org.firstinspires.ftc.teamcode.dcs15815.opmodes_unused;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
/*
 Alliance Color: red
 Starting Position: touching wall, contained within tile F2; camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) park within signal zone (or terminal if there's a detection failure)
 */

@Disabled
@Autonomous(name = "Red Left 1 Autonomous", group = "Red")
public class Red_Left_1_AutonomousOpMode extends LinearOpMode {
    EdwardScissorliftAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Red Left");
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new EdwardScissorliftAutonomousBot(hardwareMap, ESBConfiguration.class, telemetry);

	   Pose2d startingPose = bot.configPose2d("NAVIGATION_START_RED_LEFT");

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

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(1500);

	   Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
			 .forward(30)
			 .build();

	   Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
			 .forward(36)
			 .build();

	   Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
			 .forward(4)
			 .build();

	   Trajectory traj4 = bot.navigation.trajectoryBuilder(traj3.end())
			 .back(5)
			 .build();

	   Trajectory traj5_1 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
			 .forward(60)
			 .build();
	   Trajectory traj5_2 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
			 .forward(36)
			 .build();
	   Trajectory traj5_3 = bot.navigation.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
			 .forward(10)
			 .build();

	   bot.navigation.followTrajectory(traj1);
	   bot.navigation.turn(Math.toRadians(-90));
	   bot.navigation.followTrajectory(traj2);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(1250);
	   bot.navigation.turn(Math.toRadians(90));
	   bot.navigation.followTrajectory(traj3);
	   bot.claw.open(); sleep(2000);
	   bot.navigation.followTrajectory(traj4);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_GROUND")); sleep(2000);
	   bot.navigation.turn(Math.toRadians(90));

	   if (foundTagId < 1) {

	   } else if (foundTagId == 1) {
		  bot.navigation.followTrajectory(traj5_1);
	   } else if (foundTagId == 2) {
		  bot.navigation.followTrajectory(traj5_2);
	   } else if (foundTagId == 3) {
		  bot.navigation.followTrajectory(traj5_3);
	   }
    }
}