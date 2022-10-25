package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProductionBotLift extends DefenderBotSystem {

    public DcMotorEx leftMotor, rightMotor;
    private DefenderPIDController pidController;

    ProductionBotLift(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);
	   pidController = new DefenderPIDController(
			 configDouble("LIFT_MOTOR_KP"),
			 configDouble("LIFT_MOTOR_KI"),
			 configDouble("LIFT_MOTOR_KD")
	   );

	   leftMotor = hm.get(DcMotorEx.class, configString("LIFT_LEFT_MOTOR_NAME"));
	   rightMotor = hm.get(DcMotorEx.class, configString("LIFT_RIGHT_MOTOR_NAME"));
	   leftMotor.setDirection(configMotorDirection("LIFT_LEFT_MOTOR_DIRECTION"));
	   rightMotor.setDirection(configMotorDirection("LIFT_RIGHT_MOTOR_DIRECTION"));

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
	   leftMotor.setPower(configDouble("LIFT_POWER_MAX"));
	   rightMotor.setPower(configDouble("LIFT_POWER_MAX"));
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