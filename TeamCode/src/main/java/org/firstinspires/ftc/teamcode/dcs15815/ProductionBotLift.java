package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProductionBotLift extends DefenderBotSystem {

    public DcMotor leftMotor, rightMotor;

    ProductionBotLift(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   leftMotor = hm.dcMotor.get(configString("LIFT_LEFT_MOTOR_NAME"));
	   rightMotor = hm.dcMotor.get(configString("LIFT_RIGHT_MOTOR_NAME"));
	   leftMotor.setDirection(configMotorDirection("LIFT_LEFT_MOTOR_DIRECTION"));
	   rightMotor.setDirection(configMotorDirection("LIFT_RIGHT_MOTOR_DIRECTION"));

	   leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	   rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	   leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPosition(int p) {
	   leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	   rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	   leftMotor.setPower(0);
	   rightMotor.setPower(0);
	   leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   leftMotor.setTargetPosition(p);
	   rightMotor.setTargetPosition(p);
	   leftMotor.setPower(configDouble("LIFT_POWER_MAX"));
	   rightMotor.setPower(configDouble("LIFT_POWER_MAX"));

    }

    public void setPower(double p) {
	   leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   leftMotor.setPower(p);
	   rightMotor.setPower(p);
    }

    public void stop() {
	   setPower(0);
    }



}