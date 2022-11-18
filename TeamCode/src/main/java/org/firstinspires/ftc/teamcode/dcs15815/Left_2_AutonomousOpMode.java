package org.firstinspires.ftc.teamcode.dcs15815;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/*
 Alliance Color:
 Starting Position: touching wall, contained within tile F2; camera pointed at signal
 Tasks:
 	1)
 	2)
 */

@Autonomous(name = "Left 2 Autonomous", group = "Left")
public class Left_2_AutonomousOpMode extends LinearOpMode {
    ProductionAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Left");
	   telemetry.addData("Bot", "initializing...");
	   telemetry.update();

	   bot = new ProductionAutonomousBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

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

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(1500);

	   Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
			 .forward(55)
			 .build();

	   Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
			 .forward(9)
			 .build();

	   Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end())
			 .back(9)
			 .build();

	    Trajectory traj4 = bot.navigation.trajectoryBuilder(traj3.end().plus(new Pose2d(0, 0, Math.toRadians(135))))
		  .forward(27)
		  .build();

	   Trajectory traj5 = bot.navigation.trajectoryBuilder(traj4.end())
			 .back(26)
			 .build();
	   Trajectory traj6 = bot.navigation.trajectoryBuilder(traj5.end().plus(new Pose2d(0, 0, Math.toRadians(-135))))
			 .forward(9)
			 .build();

	   Trajectory traj7 = bot.navigation.trajectoryBuilder(traj6.end())
			 .back(10)
			 .build();


	   bot.navigation.followTrajectory(traj1);
	   bot.navigation.turn(Math.toRadians(-45));
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(1000);
	   bot.navigation.followTrajectory(traj2);
	   bot.claw.open(); sleep(1500);
	   bot.navigation.followTrajectory(traj3);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_STACKTOP")); sleep(2000);
	   bot.navigation.turn(Math.toRadians(135));
	   bot.navigation.followTrajectory(traj4);
	   bot.claw.close(); sleep(1000);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(1500);
	   bot.navigation.followTrajectory(traj5);
	   bot.navigation.turn(Math.toRadians(-135));
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(1000);
	   bot.navigation.followTrajectory(traj6);
	   bot.claw.open(); sleep(1500);
	   bot.navigation.followTrajectory(traj7);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_GROUND")); sleep(2000);
	   bot.navigation.turn(Math.toRadians(-45));


	   if (foundTagId < 1) {

	   } else if (foundTagId == 1) {
		  Trajectory traj8 = bot.navigation.trajectoryBuilder(traj7.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
				.back(25)
				.build();
		  Trajectory traj9 = bot.navigation.trajectoryBuilder(traj8.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
				.forward(21)
				.build();

		  bot.navigation.followTrajectory(traj8);
		  bot.navigation.turn(Math.toRadians(-90));
		  bot.navigation.followTrajectory(traj9);

	   } else if (foundTagId == 2) {
		  bot.navigation.turn(Math.toRadians(-90));

	   } else if (foundTagId == 3) {
		  Trajectory traj8 = bot.navigation.trajectoryBuilder(traj7.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
				.forward(24)
				.build();
		  Trajectory traj9 = bot.navigation.trajectoryBuilder(traj8.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
				.forward(21)
				.build();
		  bot.navigation.followTrajectory(traj8);
		  bot.navigation.turn(Math.toRadians(-90));
		  bot.navigation.followTrajectory(traj9);
	   }
    }
}