package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "One Gamepad Driving Test", group = "Testing")
public class OneGamepadDrivingTestOpMode extends LinearOpMode
{
    ProductionBot bot;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
	   waitForStart();
	   while (opModeIsActive()) {
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

		  if (gamepad1.dpad_up) {
			 bot.lift.setPower(1);
		  } else if (gamepad1.dpad_down) {
			 bot.lift.setPower(-0.25);
		  } else if (gamepad1.y) {
			 bot.lift.setPower(0.2);
		  }
		  if (gamepad1.x) {
			 bot.lift.stop();
		  }
		  if (gamepad1.a) {
			 bot.claw.open();
		  } else if (gamepad1.b) {
			 bot.claw.close();
		  }

	   }
    }
}