package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.acmerobotics.roadrunner.drive.Drive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderActions.DefenderAction;

public class DriveAction extends DefenderAction {
    public Trajectory trajectory;
    public double postTrajectoryAngle;
    public double distance = 0;
    public Pose2d startingPose;
    public boolean isForward = false;
    public boolean isBack = false;
    public boolean hasPostTurn = false;

    public static double CLOCKWISE_45 = Math.toRadians(-45);
    public static double COUNTER_CLOCKWISE_45 = Math.toRadians(45);
    public static double CLOCKWISE_90 = Math.toRadians(-90);
    public static double COUNTER_CLOCKWISE_90 = Math.toRadians(90);
    public static double CLOCKWISE_135 = Math.toRadians(-135);
    public static double COUNTER_CLOCKWISE_135 = Math.toRadians(135);

    public DriveAction() {

    }

    public static DriveAction forward(double i) {
	   DriveAction da = new DriveAction();
	   da.isForward = true;
	   da.distance = i;
	   return da;
    }

    public static DriveAction back(double i) {
	   DriveAction da = new DriveAction();
	   da.isBack = true;
	   da.distance = i;
	   return da;
    }

    public DriveAction thenTurn(double a) {
	   hasPostTurn = true;
	   postTrajectoryAngle = a;
	   return this;
    }

    public static DriveAction turn(double a) {
	   DriveAction da = new DriveAction();

	   da.hasPostTurn = true;
	   da.postTrajectoryAngle = a;
	   return da;
    }

    public Pose2d endingPose() {
	   Pose2d pose;
	   if (distance > 0) {
		  pose = trajectory.end();
	   } else {
		  pose = startingPose;
	   }
	   if (hasPostTurn) {
		  pose.plus(new Pose2d(0, 0, postTrajectoryAngle));
	   }
	   return pose;
    }

    @Override
    public void run() {
	   EdwardScissorliftAutonomousBot bot = (EdwardScissorliftAutonomousBot)actionSequence.bot;
	   startingPose = actionSequence.currentPose;
	   if (distance > 0) {
		  TrajectoryBuilder tb = bot.navigation.trajectoryBuilder(startingPose);
		  if (isForward) {
			 tb.forward(distance);
		  } else if (isBack) {
			 tb.back(distance);
		  }

		  trajectory = tb.build();

		  bot.navigation.followTrajectory(trajectory);
	   }
	   if (hasPostTurn) {
		  bot.navigation.turn(postTrajectoryAngle);
	   }

	   actionSequence.currentPose = endingPose();
	   isFinished = true;
    }

}
