package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;

@Disabled
@TeleOp(name = "Claw Test", group = "Testing")
public class ClawTestOpMode extends LinearOpMode
{
    EdwardScissorliftBot bot;

    @Override
    public void runOpMode() {
	   bot = new EdwardScissorliftBot(hardwareMap, ESBConfiguration.class, telemetry);

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