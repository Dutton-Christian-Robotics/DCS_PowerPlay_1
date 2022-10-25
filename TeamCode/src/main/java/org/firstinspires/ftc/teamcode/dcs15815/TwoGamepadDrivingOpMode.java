package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Two Gamepad Driving", group = "Competition")
public class TwoGamepadDrivingOpMode extends LinearOpMode
{
    ProductionBot bot;
    private DefenderDebouncer liftUpDebouncer, liftDownDebouncer, liftGroundDebouncer;
    int currentLiftPositionIndex = 0;
    int[] liftPositions;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   liftPositions = new int[]{
		  bot.configuration.getInt("LIFT_POSITION_GROUND"),
		  bot.configuration.getInt("LIFT_POSITION_LOW"),
		  bot.configuration.getInt("LIFT_POSITION_MIDDLE"),
		  bot.configuration.getInt("LIFT_POSITION_HIGH")
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
		  bot.lift.gotoPosition(0);
		  currentLiftPositionIndex = 0;
	   });

	   waitForStart();
	   while (opModeIsActive()) {
		  telemetry.addData("lift-left", bot.lift.leftMotor.getCurrentPosition());
		  telemetry.addData("lift-right", bot.lift.rightMotor.getCurrentPosition());
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

		  if (gamepad2.right_stick_y > 0) {
			 bot.lift.setPower(-1 * gamepad2.right_stick_y);
		  } else if (gamepad2.right_stick_y < 0) {
			 bot.lift.setPower(-1 * gamepad2.right_stick_y);
		  } else if (gamepad2.dpad_up) {
			 liftUpDebouncer.run();
		  } else if (gamepad2.dpad_down) {
			 liftDownDebouncer.run();
		  } else if (gamepad2.x) {
			 liftGroundDebouncer.run();
		  }
		  if (gamepad2.b) {
			 bot.lift.stop();
		  }
		  if (gamepad2.left_bumper) {
			 bot.claw.open();
		  } else if (gamepad2.right_bumper) {
			 bot.claw.close();
		  }
		  telemetry.addData("lift-target", liftPositions[currentLiftPositionIndex]);
		  telemetry.update();

	   }
    }
}