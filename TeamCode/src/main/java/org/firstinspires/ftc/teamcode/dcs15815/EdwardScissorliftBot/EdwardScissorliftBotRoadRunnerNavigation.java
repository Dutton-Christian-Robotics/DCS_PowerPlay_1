package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

// TODO: after tuning, take guts of SampleMecanumDrive and make them the internals here
public class EdwardScissorliftBotRoadRunnerNavigation extends DefenderBotSystem {

    public SampleMecanumDrive roadrunner;

    EdwardScissorliftBotRoadRunnerNavigation(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   roadrunner = new SampleMecanumDrive(hm);

    }

    public void followTrajectory(Trajectory trajectory) {
	   roadrunner.followTrajectory(trajectory);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
	   return roadrunner.trajectoryBuilder(startPose);
    }

    public void turn(double angle) {
	   roadrunner.turn(angle);
    }





}