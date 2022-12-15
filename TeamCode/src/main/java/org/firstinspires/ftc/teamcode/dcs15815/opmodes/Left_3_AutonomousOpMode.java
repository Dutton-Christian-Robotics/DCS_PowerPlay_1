package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
/*

 OBSOLETE: This was the opmode that I was planning on until I built 4. Given that 4 seems to work
 	as reliably and does more, I'm disabling this one.

 Alliance Color: either
 Starting Position: touching wall, contained within tile F2 (red) or A5 (blue); camera pointed at signal
 Actions:
 	1) determine signal zone from team-supplied signal sleeve
 	2) drive to angled position near midfield left tall junction
 	3) deliver preloaded cone
 	4) turn towards and approach cone stack
 	5) grab top cone
 	6) reverse and turn 90deg to nearby low junction
 	7) deliver cone
 	8) grab and deliver another two cones from the stack
    10) deliver final cone.
    		a) If signal zone is 1, one more cone is delivered and then Eddy parks
    		b) If signal zone is 2 or 3, the robot drives to the zone without delivering another cone

 */

@Disabled
@Autonomous(name = "Left 3 Autonomous", group = "Left")
public class Left_3_AutonomousOpMode extends LinearOpMode {
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
			 .forward(27)
			 .build();
	   Trajectory t5_leaveStack = bot.navigation.trajectoryBuilder(t4_approachStack.end())
			 .back(12)
			 .build();
	   Trajectory t6_approachLowJunction = bot.navigation.trajectoryBuilder(t5_leaveStack.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_90)))
			 .forward(4)
			 .build();
	   Trajectory t7_leaveLowJunction = bot.navigation.trajectoryBuilder(t6_approachLowJunction.end())
			 .back(4)
			 .build();
	   Trajectory t8_approachStack = bot.navigation.trajectoryBuilder(t7_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
			 .forward(11)
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
	   bot.lift.setPosition( LIFT_POSITION_LOW);sleep(250);

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
	   bot.lift.setPosition( LIFT_POSITION_LOW);sleep(250);

	   // drive away from stack
	   bot.navigation.followTrajectory(t9_leaveStack);
	   bot.navigation.turn(COUNTER_CLOCKWISE_90);

	   bot.navigation.followTrajectory(t10_approachLowJunction);
	   bot.lift.setPosition(LIFT_POSITION_GROUND);sleep(200);
	   bot.claw.open();
	   bot.lift.setPosition(LIFT_POSITION_LOW);

	   bot.navigation.followTrajectory(t11_leaveLowJunction);
	   bot.navigation.turn(CLOCKWISE_90);






	   if (foundTagId < 1) {
		  // really should do something here

	   } else if (foundTagId == 1) {

		  Trajectory t12_approachStack = bot.navigation.trajectoryBuilder(t11_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
			 .forward(12)
			 .build();
		  Trajectory t13_leaveStack = bot.navigation.trajectoryBuilder(t12_approachStack.end())
			 .back(12)
			 .build();
		  Trajectory t14_approachLowJunction = bot.navigation.trajectoryBuilder(t13_leaveStack.end().plus(new Pose2d(0, 0, COUNTER_CLOCKWISE_90)))
			 .forward(4)
			 .build();
		  Trajectory t15_leaveLowJunction = bot.navigation.trajectoryBuilder(t14_approachLowJunction.end())
				.back(4)
				.build();



		  bot.lift.setPosition( LIFT_POSITION_STACKTOP);
		  bot.navigation.followTrajectory(t12_approachStack);
		  bot.claw.close(); sleep(250);
		  bot.lift.setPosition( LIFT_POSITION_LOW);sleep(250);
		  bot.navigation.followTrajectory(t13_leaveStack);
		  bot.navigation.turn(COUNTER_CLOCKWISE_90);

		  bot.navigation.followTrajectory(t14_approachLowJunction);
		  bot.lift.setPosition(LIFT_POSITION_GROUND);sleep(200);
		  bot.claw.open();
		  bot.lift.setPosition(LIFT_POSITION_LOW);

		  bot.navigation.followTrajectory(t15_leaveLowJunction);
		  bot.navigation.turn(CLOCKWISE_90);




		  Trajectory t20_intoZone1 = bot.navigation.trajectoryBuilder(t15_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
				.forward(9)
				.build();

		  bot.navigation.followTrajectory(t20_intoZone1);


	   } else if (foundTagId == 2) {
		  Trajectory t20_intoZone2 = bot.navigation.trajectoryBuilder(t11_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
				.back(17)
				.build();
		  bot.navigation.followTrajectory(t20_intoZone2);


	   } else if (foundTagId == 3) {
		  Trajectory t20_intoZone3 = bot.navigation.trajectoryBuilder(t11_leaveLowJunction.end().plus(new Pose2d(0, 0, CLOCKWISE_90)))
				.back(41)
				.build();
		  bot.navigation.followTrajectory(t20_intoZone3);
	   }
    }
}