package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBotSystem;

public class EdwardScissorliftBotClas extends DefenderBotSystem {

    private Servo clawServo;

    EdwardScissorliftBotClas(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
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