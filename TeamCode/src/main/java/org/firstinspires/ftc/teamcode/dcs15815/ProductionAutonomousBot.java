package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ProductionAutonomousBot extends DefenderBot {

    public ProductionBotClaw claw;
    public ProductionBotVision vision;
    public ProductionBotSensors sensors;
    public ProductionBotRoadRunnerNavigation navigation;
    public ProductionBotMecanumDrivetrain drivetrain;
    public ProductionBotLift lift;


    public ProductionAutonomousBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ProductionBotMecanumDrivetrain.class);
	   lift = addSystem(ProductionBotLift.class);
	   claw = addSystem(ProductionBotClaw.class);
	   sensors = addSystem(ProductionBotSensors.class);
	   navigation = addSystem(ProductionBotRoadRunnerNavigation.class);
	   vision = addSystem(ProductionBotVision.class); // is something happening here in vision that is causing invisible crashes?
   }
}