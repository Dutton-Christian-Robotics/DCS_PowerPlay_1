package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class EdwardScissorliftBotClaw extends DefenderBotSystem {

    private Servo clawServo;

    public EdwardScissorliftBotClaw(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   clawServo = hm.servo.get(EdwardScissorliftBotConfiguration.CLAW_SERVO_NAME);
    }

// ----------------------------------------

    public void setClawPosition(double p) {
	   clawServo.setPosition(p);
    }

// ----------------------------------------


    public void open() {
	   setClawPosition(EdwardScissorliftBotConfiguration.CLAW_POSITION_OPEN);
    }

    public void close() {
	   setClawPosition(EdwardScissorliftBotConfiguration.CLAW_POSITION_CLOSED);

    }


}