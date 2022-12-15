package org.firstinspires.ftc.teamcode.dcs15815.opmodes_unused;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions.DefenderActionSequence;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ClawAction;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.DriveAction;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.LiftAction;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.SwitchAction;
/*
	This was an attempt to use the DefenderAction framework to build a version of the opmode
	used at Fruitport. Given the limited time available and some problems encountered in
	trying to use this untested framework, we decided to instead keep with the code that
	we know works.

	While I was able to get some of the actions to sequence properly in the virtual robot
	simulator, when run on the actual robot timings seemed to be ignored and possibly even
	jumbled. I think it has something to do with the way the actions link to each other,
	but that will need more time to test.
 */

@Disabled
@Autonomous(name = "Left 2c Autonomous", group = "Left")
public class Left_2c_AutonomousOpMode extends LinearOpMode {
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

	   DefenderActionSequence mainSequence = DefenderActionSequence.newWithActions(bot,
		  LiftAction.gotoLow(),
		  DriveAction.forward(57).thenTurn(DriveAction.CLOCKWISE_45), // Drive forward and then turn
		  LiftAction.gotoHigh().thenWait(750), // set lift to high junction height
		  DriveAction.forward(7), // Forward to the junction
		  LiftAction.gotoBelowHigh().thenWait(250),
		  ClawAction.open().thenWait(250),
		  LiftAction.gotoHigh().thenWait(250),
		  DriveAction.back(7), // Back away from the junction
		  LiftAction.gotoStackTop(),
		  DriveAction.turn(DriveAction.COUNTER_CLOCKWISE_135), // turn toward the cone stack
		  DriveAction.forward(28), // drive toward the stack
		  ClawAction.close().thenWait(750), // grab the top cone
		  LiftAction.gotoLow().thenWait(500), // lift up just a bit
		  DriveAction.back(24), // back away from the stack
		  LiftAction.gotoGround().thenWait(500), // ground pound to improve grip on cone
		  DriveAction.turn(DriveAction.CLOCKWISE_135), // turn back to the high junction
		  LiftAction.gotoHigh().thenWait(500),
		  DriveAction.forward(9), // drive toward the high junction
		  LiftAction.gotoBelowHigh().thenWait(250),
		  ClawAction.open().thenWait(250),
		  LiftAction.gotoHigh().thenWait(250),
		  DriveAction.back(10), // back away from the junction
		  LiftAction.gotoGround().thenWait(250),
		  DriveAction.turn(DriveAction.CLOCKWISE_45), // turn for better zone driving
		  SwitchAction.on(foundTagId)
			 .when(1, DriveAction.back(25)
				.thenTurn(DriveAction.CLOCKWISE_90)
				.then(DriveAction.forward(21)))
			 .when(2, DriveAction.turn(DriveAction.CLOCKWISE_90))
			 .when(3, DriveAction.forward(26)
				.thenTurn(DriveAction.CLOCKWISE_90)
				.then(DriveAction.forward(23)))

	   );

	   while (!isStopRequested() && !mainSequence.isFinished()) {
		  mainSequence.run();
	   }
    }
}