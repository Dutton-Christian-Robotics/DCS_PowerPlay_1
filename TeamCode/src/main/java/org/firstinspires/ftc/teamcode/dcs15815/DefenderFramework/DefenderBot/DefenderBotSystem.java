package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

abstract public class DefenderBotSystem {
    public HardwareMap hardwareMap;
    public DefenderBotConfiguration configuration;
    public DefenderBot bot;
    private boolean logging = false;

    // Systems are generally responsible for their own setup. To make that happen, this abstract
    // parent class saves references to the hardwareMap provided by the opmode, the properties
    // file that was used when creating the bot instances, and a reference to the bot itself
    // (useful for things like accessing telemetry)
    public DefenderBotSystem(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   this.hardwareMap = hm;
	   this.configuration = config;
	   this.bot = b;
    }

    public boolean isLogging() {
	   return logging;
    }

    public void setLogging(boolean logging) {
	   this.logging = logging;
    }

    // A convenience method that can be called from any subsystem. Removes the need
    // to have try/catch blocks muddying up code.
    public void sleep(long milliseconds) {
	   bot.sleep(milliseconds);
    }

}
