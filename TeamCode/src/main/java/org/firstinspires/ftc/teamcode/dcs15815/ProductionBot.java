package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ProductionBot extends DefenderBot {

    public ProductionBotClaw claw;
    public ProductionBotVision vision;
    public ProductionBotSensors sensors;
    public ProductionBotNavigation navigation;
    public ProductionBotMecanumDrivetrain drivetrain;
    public ProductionBotLift lift;


    ProductionBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ProductionBotMecanumDrivetrain.class);
	   lift = addSystem(ProductionBotLift.class);
	   claw = addSystem(ProductionBotClaw.class);
	   sensors = addSystem(ProductionBotSensors.class);
	   navigation = addSystem(ProductionBotNavigation.class);
//	   navigation = new ProductionBotNavigation(hm, configuration, this);
	   navigation.linkDrivetrain(drivetrain);
	   vision = addSystem(ProductionBotVision.class); // is something happening here in vision that is causing invisible crashes?



    }
}