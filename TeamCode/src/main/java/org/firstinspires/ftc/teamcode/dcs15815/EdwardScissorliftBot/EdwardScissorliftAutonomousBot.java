package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;

public class EdwardScissorliftAutonomousBot extends DefenderBot {

    public EdwardScissorliftBotClas claw;
    public EdwardScissorliftBotVision vision;
    public EdwardScissorliftBotSensors sensors;
    public EdwardScissorliftBotRoadRunnerNavigation navigation;
    public EdwardScissorliftBotMecanumDrivetrain drivetrain;
    public EdwardScissorliftBotLift lift;


    public EdwardScissorliftAutonomousBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(EdwardScissorliftBotMecanumDrivetrain.class);
	   lift = addSystem(EdwardScissorliftBotLift.class);
	   claw = addSystem(EdwardScissorliftBotClas.class);
	   sensors = addSystem(EdwardScissorliftBotSensors.class);
	   navigation = addSystem(EdwardScissorliftBotRoadRunnerNavigation.class);
	   vision = addSystem(EdwardScissorliftBotVision.class); // is something happening here in vision that is causing invisible crashes?
   }
}