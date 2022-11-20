package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.dcs15815.ProductionAutonomousBot;
import org.firstinspires.ftc.teamcode.dcs15815.ProductionBotConfiguration;

@Disabled
@TeleOp(name = "Field Position Test", group = "Testing")
public class FieldPositionTestOpMode extends LinearOpMode
{
    ProductionAutonomousBot bot;


    @Override
    public void runOpMode() {

	   bot = new ProductionAutonomousBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();

	    Trajectory traj = bot.navigation.trajectoryBuilder(new Pose2d())
		  .splineTo(new Vector2d(12, 12), 0)
		  .build();
	   bot.navigation.followTrajectory(traj);


    }
}