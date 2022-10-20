package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ProductionBotClaw extends DefenderBotSystem {

    private Servo clawServo;

    ProductionBotClaw(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   clawServo = hm.servo.get(configString("CLAW_SERVO_NAME"));
    }

// ----------------------------------------

    public void setClawPosition(double p) {
	   clawServo.setPosition(p);
    }

// ----------------------------------------


    public void open() {
	   setClawPosition(configDouble("CLAW_POSITION_OPEN"));
    }

    public void close() {
	   setClawPosition(configDouble("CLAW_POSITION_CLOSED"));

    }


}