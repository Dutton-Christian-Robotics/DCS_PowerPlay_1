package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.ESBConfiguration;

@Disabled
@TeleOp(name = "Lift Test", group = "Testing")
public class LiftTestOpMode extends LinearOpMode
{
    EdwardScissorliftBot bot;

    @Override
    public void runOpMode() {
	   bot = new EdwardScissorliftBot(hardwareMap, ESBConfiguration.class, telemetry);

	   waitForStart();

	   while (opModeIsActive()) {
//		  bot.lift.gotoPosition(bot.configuration.getInt("LIFT_POSITION_HIGH"),
//			 () -> {
//				telemetry.addData("lift position", "reached");
//				telemetry.update();
//			 }
//		  );
	   }

    }
}