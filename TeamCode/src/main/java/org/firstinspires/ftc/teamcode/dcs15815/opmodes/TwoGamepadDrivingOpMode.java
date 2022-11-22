package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBotConfiguration;

@TeleOp(name = "Two Gamepad Driving", group = "Competition")
public class TwoGamepadDrivingOpMode extends LinearOpMode
{
    EdwardScissorliftBot bot;
    private DefenderDebouncer liftUpDebouncer, liftDownDebouncer, liftGroundDebouncer, clawDebouncer;
    private DefenderAnalogModifier gamepad2RightStickModifier;
    int currentLiftPositionIndex = 0;
    int[] liftPositions;
    boolean isClawOpen;

    @Override
    public void runOpMode() {
	   bot = new EdwardScissorliftBot(hardwareMap, EdwardScissorliftBotConfiguration.class, telemetry);

	   gamepad2RightStickModifier = new DefenderAnalogModifier(
		  bot.getConfigDouble("GAMEPAD2_RIGHT_STICK_CURVE"),
		  bot.getConfigDouble("GAMEPAD2_RIGHT_STICK_MAX")
	   );

	   liftPositions = new int[]{
		  bot.getConfigInt("LIFT_POSITION_GROUND"),
		  bot.getConfigInt("LIFT_POSITION_LOW"),
		  bot.getConfigInt("LIFT_POSITION_MIDDLE"),
		  bot.getConfigInt("LIFT_POSITION_HIGH")
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
	   clawDebouncer = new DefenderDebouncer(500, () -> {
		  if (isClawOpen) {
			 bot.claw.close();
		  } else {
			 bot.claw.open();
		  }
		  isClawOpen = !isClawOpen;
	   });

	   waitForStart();
	   bot.claw.open();
	   isClawOpen = true;


	   while (opModeIsActive()) {
//		  telemetry.addData("lift-left", bot.lift.leftMotor.getCurrentPosition());
//		  telemetry.addData("lift-right", bot.lift.rightMotor.getCurrentPosition());
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

		  if (gamepad2.right_stick_y > 0) {
			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
		  } else if (gamepad2.right_stick_y < 0) {
			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
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
		  if (gamepad2.right_bumper) {
			 clawDebouncer.run();
		  }
		  telemetry.addData("lift-target", liftPositions[currentLiftPositionIndex]);
		  telemetry.update();

	   }
    }
}