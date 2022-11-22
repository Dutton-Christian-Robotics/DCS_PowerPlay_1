package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBot;
import org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot.EdwardScissorliftBotConfiguration;

@Disabled
@TeleOp(name = "Wheel Test", group = "Testing")
public class WheelTestOpMode extends LinearOpMode
{
    EdwardScissorliftBot bot;

    @Override
    public void runOpMode() {
	   bot = new EdwardScissorliftBot(hardwareMap, EdwardScissorliftBotConfiguration.class, telemetry);

	   waitForStart();

	   bot.drivetrain.backLeft.setPower(1);
	   sleep(3000);
	   bot.drivetrain.backLeft.setPower(0);
	   bot.drivetrain.frontLeft.setPower(1);
	   sleep(3000);
	   bot.drivetrain.frontLeft.setPower(0);
	   bot.drivetrain.frontRight.setPower(1);
	   sleep(3000);
	   bot.drivetrain.frontRight.setPower(0);
	   bot.drivetrain.backRight.setPower(1);
	   sleep(3000);
	   bot.drivetrain.backRight.setPower(0);

    }
}