package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

public class ESBLift extends DefenderBotSystem {

    public DcMotorEx leftMotor, rightMotor;
    private DefenderPIDController pidController;

    public ESBLift(HardwareMap hm, DefenderBot b) {
	   super(hm, b);
	   pidController = new DefenderPIDController(
			 ESBConfiguration.LIFT_MOTOR_KP,
			 ESBConfiguration.LIFT_MOTOR_KI,
			 ESBConfiguration.LIFT_MOTOR_KD
	   );

	   leftMotor = hm.get(DcMotorEx.class, ESBConfiguration.LIFT_LEFT_MOTOR_NAME);
	   rightMotor = hm.get(DcMotorEx.class, ESBConfiguration.LIFT_RIGHT_MOTOR_NAME);
	   leftMotor.setDirection(ESBConfiguration.LIFT_LEFT_MOTOR_DIRECTION);
	   rightMotor.setDirection(ESBConfiguration.LIFT_RIGHT_MOTOR_DIRECTION);

	   leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
	   rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

	   leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

	   leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPosition(int p) {
	   leftMotor.setPower(0);
	   rightMotor.setPower(0);
	   leftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
	   rightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
	   leftMotor.setTargetPosition(p);
	   rightMotor.setTargetPosition(p);
	   leftMotor.setPower(ESBConfiguration.LIFT_POWER_MAX);
	   rightMotor.setPower(ESBConfiguration.LIFT_POWER_MAX);
    }

    public void gotoPosition(int p) {
	   int tolerance = 5;
	   double power;

	   leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

	   while (Math.abs(p - leftMotor.getCurrentPosition()) > tolerance) {
		  power = pidController.calculatePower(0, leftMotor.getCurrentPosition());
		  leftMotor.setPower(power);
		  rightMotor.setPower(power);
	   }
	   leftMotor.setPower(0);
	   rightMotor.setPower(0);
    }

    public void gotoPosition(int p, Runnable r) {
	   gotoPosition(p);
	   r.run();
    }

    public void setPower(double p) {
	   leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   leftMotor.setPower(p);
	   rightMotor.setPower(p);
    }

    public void stop() {
	   setPower(0);
    }



}