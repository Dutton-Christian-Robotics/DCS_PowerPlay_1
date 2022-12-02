package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;

@TeleOp(name = "One Gamepad Driving Test", group = "Testing")
public class OneGamepadDrivingTestOpMode extends LinearOpMode
{
    EdwardScissorliftBot bot;
    private DefenderDebouncer liftUpDebouncer, liftDownDebouncer, liftGroundDebouncer;
    int currentLiftPositionIndex = 0;
    int[] liftPositions;

    @Override
    public void runOpMode() {
	   bot = new EdwardScissorliftBot(hardwareMap, ESBConfiguration.class, telemetry);

	   liftPositions = new int[]{
			 ESBConfiguration.LIFT_POSITION_GROUND,
			 ESBConfiguration.LIFT_POSITION_LOW,
			 ESBConfiguration.LIFT_POSITION_MIDDLE,
			 ESBConfiguration.LIFT_POSITION_HIGH
	   };
	   liftUpDebouncer = new DefenderDebouncer(500, () -> {
		  if (currentLiftPositionIndex < (liftPositions.length - 1)) {
			 currentLiftPositionIndex++;
		  }
		  bot.lift.setPosition(liftPositions[currentLiftPositionIndex]);
	   });
	   liftDownDebouncer = new DefenderDebouncer(500, () -> {
		  if (currentLiftPositionIndex > 0) {
			 currentLiftPositionIndex--;
		  }
		  bot.lift.setPosition(liftPositions[currentLiftPositionIndex]);
	   });
	   liftGroundDebouncer = new DefenderDebouncer(500, () -> {
		  bot.lift.setPosition(0);
		  currentLiftPositionIndex = 0;
	   });

	   waitForStart();
	   while (opModeIsActive()) {
		  telemetry.addData("lift-left", bot.lift.leftMotor.getCurrentPosition());
		  telemetry.addData("lift-right", bot.lift.rightMotor.getCurrentPosition());
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

		  if (gamepad1.right_stick_y > 0) {
			 bot.lift.setPower(-1 * gamepad1.right_stick_y);
		  } else if (gamepad1.right_stick_y < 0) {
			 bot.lift.setPower(-1 * gamepad1.right_stick_y);
		  } else if (gamepad1.y) {
			 bot.lift.setPower(0.2);
		  } else if (gamepad1.dpad_up) {
			 liftUpDebouncer.run();
		  } else if (gamepad1.dpad_down) {
			 liftDownDebouncer.run();
		  } else if (gamepad1.x) {
			 liftGroundDebouncer.run();
		  }
		  if (gamepad1.b) {
			 bot.lift.stop();
		  }
		  if (gamepad1.left_bumper) {
			 bot.claw.open();
		  } else if (gamepad1.right_bumper) {
			 bot.claw.close();
		  }
		  telemetry.addData("lift-target", liftPositions[currentLiftPositionIndex]);
		  telemetry.update();

	   }
    }
}