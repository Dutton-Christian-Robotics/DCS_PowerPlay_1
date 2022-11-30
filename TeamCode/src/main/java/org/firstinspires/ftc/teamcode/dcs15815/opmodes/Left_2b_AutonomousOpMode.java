package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.ProductionAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.ProductionBotConfiguration;
/*
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
 	8) repeat 4-6
 	9) park within signal zone (no fallback for not sensing signal)
 	10) turn towards alliance
 */

@Autonomous(name = "Left 2b Autonomous", group = "Left")
public class Left_2b_AutonomousOpMode extends LinearOpMode {
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

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(150);

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
			 .back(9)
			 .build();


	   bot.navigation.followTrajectory(traj1);
	   bot.navigation.turn(Math.toRadians(-45));
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(500);
	   bot.navigation.followTrajectory(traj2);

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_BELOW_HIGH")); sleep(250);
	   bot.claw.open(); sleep(150);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(250);


	   bot.navigation.followTrajectory(traj3);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_STACKTOP")); sleep(150);
	   bot.navigation.turn(Math.toRadians(135));
	   bot.navigation.followTrajectory(traj4);
	   bot.claw.close(); sleep(150);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(150);
	   bot.navigation.followTrajectory(traj5);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_GROUND")); sleep(150);

	   bot.navigation.turn(Math.toRadians(-135));
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(500);
	   bot.navigation.followTrajectory(traj6);

	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_BELOW_HIGH")); sleep(150);
	   bot.claw.open(); sleep(150);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(500);

	   bot.navigation.followTrajectory(traj7);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_STACKTOP")); sleep(150);
	   bot.navigation.turn(Math.toRadians(135));

	   // drive toward stack after turn
	   Trajectory traj7_1 = bot.navigation.trajectoryBuilder(traj7.end().plus(new Pose2d(0, 0, Math.toRadians(135))))
			 .forward(28)
			 .build();
	   bot.navigation.followTrajectory(traj7_1);
	   bot.claw.close(); sleep(150);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_LOW")); sleep(150);

	   Trajectory traj7_2 = bot.navigation.trajectoryBuilder(traj7_1.end())
			 .back(24)
			 .build();
	   bot.navigation.followTrajectory(traj7_2);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_GROUND")); sleep(150);
	   bot.navigation.turn(Math.toRadians(-135));

	   // drive toward junction
	   Trajectory traj7_3 = bot.navigation.trajectoryBuilder(traj7_2.end().plus(new Pose2d(0, 0, Math.toRadians(-135))))
			 .forward(9)
			 .build();
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(500);
	   bot.navigation.followTrajectory(traj7_3);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_BELOW_HIGH")); sleep(150);
	   bot.claw.open(); sleep(150);
	   bot.lift.setPosition( bot.getConfigInt("LIFT_POSITION_HIGH")); sleep(500);


	   // back away from junction
	   Trajectory traj7_4 = bot.navigation.trajectoryBuilder(traj7_3.end())
			 .back(9)
			 .build();




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