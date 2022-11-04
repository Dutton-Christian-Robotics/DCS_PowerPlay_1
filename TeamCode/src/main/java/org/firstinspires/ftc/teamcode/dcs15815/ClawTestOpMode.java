package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "Claw Test", group = "Testing")
public class ClawTestOpMode extends LinearOpMode
{
    ProductionBot bot;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();

	   while (!isStopRequested()) {

		  if (gamepad1.a) {
			 bot.claw.setClawPosition(0);
		  } else if (gamepad1.b) {
			 bot.claw.setClawPosition(1);
		  }
	   }

    }
}