package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;

public class EdwardScissorliftAutonomousBot extends DefenderBot {

    public ESBClaw claw;
    public ESBVision vision;
    public ESBSensors sensors;
    public ESBRoadRunnerNavigation navigation;
    public ESBMecanumDrivetrain drivetrain;
    public ESBLift lift;


    public EdwardScissorliftAutonomousBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ESBMecanumDrivetrain.class);
	   lift = addSystem(ESBLift.class);
	   claw = addSystem(ESBClaw.class);
	   sensors = addSystem(ESBSensors.class);
	   navigation = addSystem(ESBRoadRunnerNavigation.class);
	   vision = addSystem(ESBVision.class); // is something happening here in vision that is causing invisible crashes?
   }
}