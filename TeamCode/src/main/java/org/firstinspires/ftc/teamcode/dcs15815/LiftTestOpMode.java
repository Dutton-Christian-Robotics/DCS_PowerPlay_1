package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@TeleOp(name = "Lift Test", group = "Testing")
public class LiftTestOpMode extends LinearOpMode
{
    ProductionBot bot;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();

	   while (opModeIsActive()) {
		  bot.lift.gotoPosition(bot.configuration.getInt("LIFT_POSITION_HIGH"),
			 () -> {
				telemetry.addData("lift position", "reached");
				telemetry.update();
			 }
		  );
	   }

    }
}