package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
/*
 NOTE: This is the opmode as it was at the end of Fruitport

 Alliance Color: either
 Starting Position: touching wall, contained within tile F2 (red) or A5 (blue); camera pointed at signal
 Tasks:
 	1) determine signal zone from team-supplied signal sleeve
 	2) drive to angled position near midfield left tall junction
 	3) deliver preloaded cone
 	4) turn towards and approach cone stack
 	5) grab top cone
 	6) reverse and turn towards midfield left tall junction
 	7) deliver cone
 	8) park within signal zone (no fallback for not sensing signal)
 	9) turn towards alliance
 */

@Disabled
@Autonomous(name = "Left 2a Autonomous", group = "Left")
public class Left_2a_AutonomousOpMode extends LinearOpMode {
    EdwardScissorliftAutonomousBot bot;
    int foundTagId = -1;

    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Left");
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

	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_LOW); sleep(1000);

	   // Drive forward
	   Trajectory traj1 = bot.navigation.trajectoryBuilder(startingPose)
			 .forward(57)
			 .build();

	   // Forward to the junction after turn
	   Trajectory traj2 = bot.navigation.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(-45))))
			 .forward(7)
			 .build();

	   // back away from junction
	   Trajectory traj3 = bot.navigation.trajectoryBuilder(traj2.end())
			 .back(7)
			 .build();

	   // drive toward stack after turn
	    Trajectory traj4 = bot.navigation.trajectoryBuilder(traj3.end().plus(new Pose2d(0, 0, Math.toRadians(135))))
		  .forward(28)
		  .build();

	    // drive back toward junction
	   Trajectory traj5 = bot.navigation.trajectoryBuilder(traj4.end())
			 .back(24)
			 .build();

	   // drive toward junction
	   Trajectory traj6 = bot.navigation.trajectoryBuilder(traj5.end().plus(new Pose2d(0, 0, Math.toRadians(-135))))
			 .forward(9)
			 .build();

	   // back away from junction
	   Trajectory traj7 = bot.navigation.trajectoryBuilder(traj6.end())
			 .back(10)
			 .build();


	   bot.navigation.followTrajectory(traj1);
	   bot.navigation.turn(Math.toRadians(-45));
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_HIGH); sleep(1000);
	   bot.navigation.followTrajectory(traj2);
	   bot.claw.open(); sleep(1500);
	   bot.navigation.followTrajectory(traj3);
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_STACKTOP); sleep(1000);
	   bot.navigation.turn(Math.toRadians(135));
	   bot.navigation.followTrajectory(traj4);
	   bot.claw.close(); sleep(1000);
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_LOW); sleep(1500);
	   bot.navigation.followTrajectory(traj5);
	   bot.navigation.turn(Math.toRadians(-135));
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_HIGH); sleep(1000);
	   bot.navigation.followTrajectory(traj6);
	   bot.claw.open(); sleep(1000);
	   bot.navigation.followTrajectory(traj7);
	   bot.lift.setPosition( ESBConfiguration.LIFT_POSITION_GROUND); sleep(1250);
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
				.forward(26)
				.build();
		  Trajectory traj9 = bot.navigation.trajectoryBuilder(traj8.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
				.forward(23)
				.build();
		  bot.navigation.followTrajectory(traj8);
		  bot.navigation.turn(Math.toRadians(-90));
		  bot.navigation.followTrajectory(traj9);
	   }
    }
}