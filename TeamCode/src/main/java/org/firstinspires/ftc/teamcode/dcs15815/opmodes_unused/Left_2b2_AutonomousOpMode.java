package org.firstinspires.ftc.teamcode.dcs15815.opmodes_unused;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
/*

ABANDONED. Attempted spline and other compound methods to drive to high junction and the stack.
	Was having a hard time getting placement to be as precise as desired.

	Doesn't seem worth the change.

 */

@Disabled
@Autonomous(name = "Left 2b2 Autonomous", group = "Left")
public class Left_2b2_AutonomousOpMode extends LinearOpMode {
    EdwardScissorliftAutonomousBot bot;
    int foundTagId = -1;
    double CLOCKWISE_45 = Math.toRadians(-45);
    double COUNTER_CLOCKWISE_45 = Math.toRadians(45);
    double CLOCKWISE_90 = Math.toRadians(-90);
    double COUNTER_CLOCKWISE_90 = Math.toRadians(90);
    double CLOCKWISE_135 = Math.toRadians(-135);
    double COUNTER_CLOCKWISE_135 = Math.toRadians(135);

    int LIFT_POSITION_HIGH = ESBConfiguration.LIFT_POSITION_HIGH;
    int LIFT_POSITION_BELOW_HIGH = ESBConfiguration.LIFT_POSITION_BELOW_HIGH;
    int LIFT_POSITION_STACKTOP = ESBConfiguration.LIFT_POSITION_STACKTOP;
    int LIFT_POSITION_LOW = ESBConfiguration.LIFT_POSITION_LOW;
    int LIFT_POSITION_GROUND = ESBConfiguration.LIFT_POSITION_GROUND;


    @Override
    public void runOpMode() {
	   telemetry.addData("Position", "Left");
	   telemetry.addData("Bot", "initializing...");

	   bot = new EdwardScissorliftAutonomousBot(hardwareMap, ESBConfiguration.class, telemetry);

	   telemetry.addData("claw", bot.claw != null);
	   telemetry.update();


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

	   bot.lift.setPosition( LIFT_POSITION_LOW);
	   // sleep(1000);

	   // TODO: replace t1-t3 with spline?
	   // Drive forward


	   Trajectory t1_initialForward = bot.navigation.trajectoryBuilder(new Pose2d(0, 0, 0))
		  .splineTo(new Vector2d(48, 0), 0)
		  .addDisplacementMarker(() -> {
			 bot.lift.setPosition( LIFT_POSITION_HIGH);
		  })
		  .splineTo(new Vector2d(61, -6), CLOCKWISE_45)
		  .build();

	   bot.navigation.followTrajectory(t1_initialForward);
	   bot.claw.open(); sleep(250);


	   Trajectory t2_backup = bot.navigation.trajectoryBuilder(t1_initialForward.end())
		  .lineTo(new Vector2d(48, 2))
		  .build();
	   bot.navigation.followTrajectory(t2_backup);

	   Trajectory t3_toStack = bot.navigation.trajectoryBuilder(t2_backup.end())
		  .addDisplacementMarker(() -> {
			 bot.lift.setPosition( LIFT_POSITION_STACKTOP);
		  })
		  .lineToLinearHeading(new Pose2d(38, 31, COUNTER_CLOCKWISE_90))
		  .build();
	   bot.navigation.followTrajectory(t3_toStack);



//	   // Forward to the junction after turn
//	   Trajectory t2_approachHighJunction = bot.navigation.trajectoryBuilder(t1_initialForward.end().plus(new Pose2d(0, 0, CLOCKWISE_45)))
//			 .forward(7)
//			 .build();
//
//	   // back away from junction
//	   Trajectory t3_backAwayFromHighJunction = bot.navigation.trajectoryBuilder(t2_approachHighJunction.end())
//			 .back(7)
//			 .build();
//
//	   // TODO: replace with spline?
//	   // drive toward stack after turn
//	    Trajectory t4_approachStack = bot.navigation.trajectoryBuilder(t3_backAwayFromHighJunction.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_135)))
//		  .forward(28)
//		  .build();
//
//	    // TODO: use spline?
//	   Trajectory t5_splineToHighJunction = bot.navigation.trajectoryBuilder(new Pose2d(0, 0, COUNTER_CLOCKWISE_90))
//		  .lineToConstantHeading(new Vector2d(-2, -12))
//		  .lineToSplineHeading(new Pose2d(4, -27, CLOCKWISE_45))
//		  .build();


//	   .lineToSplineHeading(new Pose2d(4, -27, CLOCKWISE_45))


 //	   Trajectory t5_leaveStack = bot.navigation.trajectoryBuilder(t4_approachStack.end())
//			 .back(24)
//			 .build();

	   // drive toward junction
//	   Trajectory t6_approachHighJunction = bot.navigation.trajectoryBuilder(t5_leaveStack.end().plus(new Pose2d(0, 0, CLOCKWISE_135)))
//			 .forward(9)
//			 .build();
//
//	   // back away from junction
//	   Trajectory t7_backAwayFromHighJunction = bot.navigation.trajectoryBuilder(t6_approachHighJunction.end())
//			 .back(10)
//			 .build();



//	   bot.navigation.turn(CLOCKWISE_45);
//	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(750);
//
//	   bot.navigation.followTrajectory(t2_approachHighJunction);
//
//	   bot.lift.setPosition( LIFT_POSITION_BELOW_HIGH); sleep(250);
//	   bot.claw.open(); sleep(250);
//	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(250);
//
//	   bot.navigation.followTrajectory(t3_backAwayFromHighJunction);
//
//	   bot.lift.setPosition( LIFT_POSITION_STACKTOP); //sleep(1000);
//
//	   bot.navigation.turn(COUNTER_CLOCKWISE_135);
//
//	   bot.navigation.followTrajectory(t4_approachStack);
//
//	   bot.claw.close(); sleep(750);
//	   bot.lift.setPosition( LIFT_POSITION_LOW); sleep(500);
//
//	   bot.navigation.followTrajectory(t5_splineToHighJunction);
//	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(500);

	   // ground pound
//	   bot.lift.setPosition( LIFT_POSITION_GROUND); sleep(500);
//
//	   bot.navigation.turn(CLOCKWISE_135);
//
//	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(500);
//
//	   bot.navigation.followTrajectory(t6_approachHighJunction);
//
//	   bot.lift.setPosition( LIFT_POSITION_BELOW_HIGH); sleep(250);
//	   bot.claw.open(); sleep(250);
//	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(500);
//
//	   bot.navigation.followTrajectory(t7_backAwayFromHighJunction);
//	   bot.lift.setPosition( LIFT_POSITION_GROUND); sleep(250);
//	   bot.navigation.turn(CLOCKWISE_45);

	   if (foundTagId < 1) {
		  // really should do something here

	   } else if (foundTagId == 1) {
//
//		  Trajectory t8_backIntoZone1 = bot.navigation.trajectoryBuilder(t7_backAwayFromHighJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_45)))
//				.back(25)
//				.build();
//		  Trajectory t9_forwardWithinZone1 = bot.navigation.trajectoryBuilder(t8_backIntoZone1.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
//				.forward(21)
//				.build();
//
//		  bot.navigation.followTrajectory(t8_backIntoZone1);
//		  bot.navigation.turn(CLOCKWISE_90);
//		  bot.navigation.followTrajectory(t9_forwardWithinZone1);

	   } else if (foundTagId == 2) {
//		  bot.navigation.turn(CLOCKWISE_90);

	   } else if (foundTagId == 3) {
//		  Trajectory t8_forwardIntoZone3 = bot.navigation.trajectoryBuilder(t7_backAwayFromHighJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_45)))
//				.forward(26)
//				.build();
//		  Trajectory t9_forwardWithinZone3 = bot.navigation.trajectoryBuilder(t8_forwardIntoZone3.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
//				.forward(23)
//				.build();
//		  bot.navigation.followTrajectory(t8_forwardIntoZone3);
//		  bot.navigation.turn(CLOCKWISE_90);
//		  bot.navigation.followTrajectory(t9_forwardWithinZone3);
	   }
    }
}