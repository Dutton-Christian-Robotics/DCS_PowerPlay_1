package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DefenderBot {

    public HardwareMap hardwareMap;
//    public DefenderBotConfiguration configuration;
    public DefenderBotDrivetrain drivetrain;
    public DefenderBotSystem sensors;
    public DefenderBotSystem navigation;

    public ArrayList<DefenderBotSystem> systems = new ArrayList<DefenderBotSystem>();
    public Telemetry telemetry;


    public DefenderBot(HardwareMap hm, Class<? extends DefenderBotConfiguration> configClass, Telemetry t) {
	   hardwareMap = hm;

	   try {
//		  configuration = (DefenderBotConfiguration) configClass.cast(configClass.getDeclaredConstructor().newInstance());
	   } catch (Exception e) {
		  t.addData("Error", "loading config");
		  t.update();
	   }
	   telemetry = t;

	   // In subclassess, use addSystem with the system class.
	   // This will automatically pass along hardwaremap properties, and the bot instance
    }

    // the DefenderBot, by itself, cannot do any driving. This method is intended as a "pass through"
    // to make it easier for one subsystem to request movement. If a drivetrain exists, the call
    // is passed through. This method uses the varargs approach. It can be called with any number
    // of double parameters. Those are collected in an array and passed along to the drivetrain's
    // drive method.
    public void drive(double... arr) {
	   if (drivetrain != null) {
		  drivetrain.drive(arr);
	   }
    }

    // This method is passed-through to the drivetrain, if one exists.
    public void stopDriving() {
	   if (drivetrain != null) {
		  drivetrain.stopDriving();
	   }
    }

    // This method uses generics in order to instantiate and add system classes to the bot. The
    // method works by being called with the class object for a DefenderBotSystem child class.
    // The method then uses the reflection API to find the constructor for the class that can be
    // called with a hardwaremap, a properties file, and a bot instance. It then calls that constructor
    // with the required params. The resulting object is explicitly cast to the desired system
    // class. This is added to the bot's array of systems, and the object returned so that it can
    // be assigned to specific instance variables.

    public <T extends DefenderBotSystem> T addSystem(Class<T> sc) {
	   try {
		  T system = sc.cast(sc.getDeclaredConstructor(HardwareMap.class, DefenderBot.class).newInstance(hardwareMap, this));
		  systems.add(system);
		  return system;

	   } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
		  System.out.println("CRASH!");
		  ex.printStackTrace();
		  System.out.println(ex.getClass().getCanonicalName());
		  System.out.println(ex.getMessage());
		  System.out.println(ex.getCause());

		  //System.exit(-1);
	   }
	   return null;
    }

    public ArrayList<DefenderBotSystem> getSystems() {
	   return systems;
    }


    public void sleep(long milliseconds) {
	   try {
		  Thread.sleep(milliseconds);
	   } catch (Exception e) {
		  //something should happen here
	   }
    }

}