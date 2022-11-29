package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import com.qualcomm.robotcore.hardware.HardwareMap;


// A robot can have any of a number of different drivetrains. This abstract class represents
// a drivetrain without any specific implementation. To be used, this needs to be subclassed.
// Only two methods are defined--both of which are "pass through" methods from the DefenderBot class
public abstract class DefenderBotDrivetrain extends DefenderBotSystem {

    public DefenderBotDrivetrain(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);
    }

    public abstract void stopDriving();

    public abstract void drive(double args[]);

    public abstract void drive(double forward, double strafe, double rotate);


}