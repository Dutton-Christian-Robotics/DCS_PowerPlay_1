package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class ESBClaw extends DefenderBotSystem {

    private Servo clawServo;

    public ESBClaw(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   clawServo = hm.servo.get(ESBConfiguration.CLAW_SERVO_NAME);
    }

// ----------------------------------------

    public void setClawPosition(double p) {
	   clawServo.setPosition(p);
    }

// ----------------------------------------


    public void open() {
	   setClawPosition(ESBConfiguration.CLAW_POSITION_OPEN);
    }

    public void close() {
	   setClawPosition(ESBConfiguration.CLAW_POSITION_CLOSED);

    }


}