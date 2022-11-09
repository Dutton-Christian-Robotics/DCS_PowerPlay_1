package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.util.ElapsedTime;

public class DefenderPIDController {
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double integralSum = 0;
    private double lastError = 0;
    ElapsedTime timer = new ElapsedTime();

    public DefenderPIDController(double kP, double kI, double kD) {
	   this.kP = kP;
	   this.kI = kI;
	   this.kD = kD;
    }

    public double calculatePower(double referencePosition, double currentPosition) {
		double error = referencePosition - currentPosition;
		integralSum += error + timer.seconds();
		double derivative = (error - lastError) / timer.seconds();
		lastError = error;

		timer.reset();
		double outputPower = (error * kP) + (derivative * kD) + (integralSum * kI);
		return outputPower;
    }
}
