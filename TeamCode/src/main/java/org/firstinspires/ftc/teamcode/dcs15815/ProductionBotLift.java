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
    }

    public void setPower(double p) {
	   leftMotor.setPower(p);
	   rightMotor.setPower(p);
    }

    public void stop() {
	   setPower(0);
    }



}