package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
/*

ABANDONED: Scores pre-loaded cone on high junction that heads to stack and places three stack cones
on nearest low junction, doing so by approaching low junction at 90 degree angle.
Only test a couple of times, but scored all three cones..

Also, scoring four cones used up almost all time. Could not get to park in correct location. Will
try again once we install odometry pods.
 */

@Disabled
@Autonomous(name = "Left 2b4 Autonomous", group = "Left")
public class Left_2b4_AutonomousOpMode extends LinearOpMode {
    EdwardScissorliftAutonomousBot bot;
    int foundTagId = -1;
    double CLOCKWISE_45 = Math.toRadians(-45);
    double COUNTER_CLOCKWISE_45 = Math.toRadians(45);

    double CLOCKWISE_90 = Math.toRadians(-90);
    double COUNTER_CLOCKWISE_90 = Math.toRadians(90);

    double CLOCKWISE_113 = Math.toRadians(-113);
    double COUNTER_CLOCKWISE_113 = Math.toRadians(113);

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

	   Trajectory t1_initialForward = bot.navigation.trajectoryBuilder(startingPose)
			 .forward(57)
			 .build();
	   Trajectory t2_approachHighJunction = bot.navigation.trajectoryBuilder(t1_initialForward.end().plus(new Pose2d(0, 0, CLOCKWISE_45)))
			 .forward(7)
			 .build();
	   Trajectory t3_backAwayFromHighJunction = bot.navigation.trajectoryBuilder(t2_approachHighJunction.end())
			 .back(7)
			 .build();
	   Trajectory t4_approachStack = bot.navigation.trajectoryBuilder(t3_backAwayFromHighJunction.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_135)))
			 .forward(28)
			 .build();
	   Trajectory t5_leaveStack = bot.navigation.trajectoryBuilder(t4_approachStack.end())
			 .back(12)
			 .build();
	   Trajectory t6_approachLowJunction = bot.navigation.trajectoryBuilder(t5_leaveStack.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_90)))
			 .forward(3)
			 .build();
	   Trajectory t7_leaveLowJunction = bot.navigation.trajectoryBuilder(t6_approachLowJunction.end())
			 .back(3)
			 .build();
	   Trajectory t8_approachStack = bot.navigation.trajectoryBuilder(t7_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
			 .forward(12)
			 .build();
	   Trajectory t9_leaveStack = bot.navigation.trajectoryBuilder(t8_approachStack.end())
			 .back(12)
			 .build();
	   Trajectory t10_approachLowJunction = bot.navigation.trajectoryBuilder(t9_leaveStack.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_90)))
			 .forward(4)
			 .build();
	   Trajectory t11_leaveLowJunction = bot.navigation.trajectoryBuilder(t10_approachLowJunction.end())
			 .back(4)
			 .build();
	   Trajectory t12_approachStack = bot.navigation.trajectoryBuilder(t11_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
			 .forward(12)
			 .build();
	   Trajectory t13_leaveStack = bot.navigation.trajectoryBuilder(t12_approachStack.end())
			 .back(12)
			 .build();
	   Trajectory t14_approachLowJunction = bot.navigation.trajectoryBuilder(t13_leaveStack.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_90)))
			 .forward(4)
			 .build();



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

	   // Drive forward
	   bot.navigation.followTrajectory(t1_initialForward);
	   bot.navigation.turn(CLOCKWISE_45);
	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(750);


	   // Forward to the junction after turn
	   bot.navigation.followTrajectory(t2_approachHighJunction);
	   bot.lift.setPosition( LIFT_POSITION_BELOW_HIGH); sleep(250);
	   bot.claw.open(); sleep(250);
	   bot.lift.setPosition( LIFT_POSITION_HIGH); sleep(250);


	   // back away from junction
	   bot.navigation.followTrajectory(t3_backAwayFromHighJunction);
	   bot.lift.setPosition( LIFT_POSITION_STACKTOP); //sleep(1000);
	   bot.navigation.turn(COUNTER_CLOCKWISE_135);

	   // drive toward stack after turn
	   bot.navigation.followTrajectory(t4_approachStack);
	   bot.claw.close(); sleep(250);
	   bot.lift.setPosition( LIFT_POSITION_LOW);

	    // drive away from stack
	   bot.navigation.followTrajectory(t5_leaveStack);
	   bot.navigation.turn(COUNTER_CLOCKWISE_90);

	   bot.navigation.followTrajectory(t6_approachLowJunction);
	   bot.lift.setPosition(LIFT_POSITION_GROUND);sleep(200);
	   bot.claw.open();
	   bot.lift.setPosition(LIFT_POSITION_LOW);

	   bot.navigation.followTrajectory(t7_leaveLowJunction);
	   bot.navigation.turn(CLOCKWISE_90);

	   bot.lift.setPosition( LIFT_POSITION_STACKTOP);
	   bot.navigation.followTrajectory(t8_approachStack);
	   bot.claw.close(); sleep(250);
	   bot.lift.setPosition( LIFT_POSITION_LOW);

	   // drive away from stack
	   bot.navigation.followTrajectory(t9_leaveStack);
	   bot.navigation.turn(COUNTER_CLOCKWISE_90);

	   bot.navigation.followTrajectory(t10_approachLowJunction);
	   bot.lift.setPosition(LIFT_POSITION_GROUND);sleep(200);
	   bot.claw.open();
	   bot.lift.setPosition(LIFT_POSITION_LOW);

	   bot.navigation.followTrajectory(t11_leaveLowJunction);
	   bot.navigation.turn(CLOCKWISE_90);

	   bot.lift.setPosition( LIFT_POSITION_STACKTOP); sleep(150);
	   bot.navigation.followTrajectory(t12_approachStack);
	   bot.claw.close(); sleep(250);
	   bot.lift.setPosition( LIFT_POSITION_LOW);

	   // drive away from stack
	   bot.navigation.followTrajectory(t13_leaveStack);
	   bot.navigation.turn(COUNTER_CLOCKWISE_90);

	   bot.navigation.followTrajectory(t14_approachLowJunction);
	   bot.lift.setPosition(LIFT_POSITION_GROUND);sleep(200);
	   bot.claw.open();
	   bot.lift.setPosition(LIFT_POSITION_LOW);



//	   // drive toward junction
//	   Trajectory t6_approachHighJunction = bot.navigation.trajectoryBuilder(t5_leaveStack.end().plus(new Pose2d(0, 0, CLOCKWISE_135)))
//			 .forward(9)
//			 .build();
//
//	   // back away from junction
//	   Trajectory t7_backAwayFromHighJunction = bot.navigation.trajectoryBuilder(t6_approachHighJunction.end())
//			 .back(10)
//			 .build();











//	   // ground pound
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