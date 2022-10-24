package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "One Gamepad Driving Test", group = "Testing")
public class OneGamepadDrivingTestOpMode extends LinearOpMode
{
    ProductionBot bot;
    private DefenderDebouncer liftUpDebouncer, liftDownDebouncer;
    int currentLiftPositionIndex = 0;
    int[] liftPositions;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	    liftPositions = new int[]{
		  bot.configuration.getInt("LIFT_POSITION_REST"),
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

	   waitForStart();
	   while (opModeIsActive()) {
		  telemetry.addData("lift-left", bot.lift.leftMotor.getCurrentPosition());
		  telemetry.addData("lift-right", bot.lift.rightMotor.getCurrentPosition());
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

		  if (gamepad1.dpad_right) {
			 bot.lift.setPower(1);
		  } else if (gamepad1.dpad_left) {
			 bot.lift.setPower(-0.25);
		  } else if (gamepad1.y) {
			 bot.lift.setPower(0.2);
		  } else if (gamepad1.dpad_up) {
			 liftUpDebouncer.run();
		  } else if (gamepad1.dpad_down) {
			 liftDownDebouncer.run();
		  }
		  if (gamepad1.x) {
			 bot.lift.stop();
		  }
		  if (gamepad1.a) {
			 bot.claw.open();
		  } else if (gamepad1.b) {
			 bot.claw.close();
		  }
		  telemetry.addData("lift-target", liftPositions[currentLiftPositionIndex]);
		  telemetry.update();

	   }
    }
}