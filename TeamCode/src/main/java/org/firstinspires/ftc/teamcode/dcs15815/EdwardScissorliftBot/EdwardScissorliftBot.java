package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;

public class EdwardScissorliftBot extends DefenderBot {

    public ESBClaw claw;
    public ESBVision vision;
    public ESBSensors sensors;
    public ESBNavigation navigation;
    public ESBMecanumDrivetrain drivetrain;
    public ESBLift lift;


    public EdwardScissorliftBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ESBMecanumDrivetrain.class);
	   lift = addSystem(ESBLift.class);
	   claw = addSystem(ESBClaw.class);
	   sensors = addSystem(ESBSensors.class);
	   navigation = addSystem(ESBNavigation.class);
//	   navigation = new ProductionBotNavigation(hm, configuration, this);
	   navigation.linkDrivetrain(drivetrain);
	   vision = addSystem(ESBVision.class); // is something happening here in vision that is causing invisible crashes?



    }
}