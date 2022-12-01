package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;


public class EdwardScissorliftBotSensors extends DefenderBotSystem {

    private BNO055IMU imu;
    private double lastAngle = 0;
    private double previousHeading = 0;
    private double integratedHeading = 0;


    public EdwardScissorliftBotSensors(HardwareMap hm, DefenderBot b) {
	   super(hm, b);
	   imu = hm.get(BNO055IMU.class, "imu");
	   BNO055IMU.Parameters params = new BNO055IMU.Parameters();
	   imu.initialize(params);
    }

//    public double currentHeading() {
//        Orientation orientation = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
//        return orientation.thirdAngle;
//    }

//    public double getHeading() {
//        Orientation orientation;
//        orientation = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
//        double roundedAngle = Math.round(100 * orientation.firstAngle) / 100;
//        lastAngle = roundedAngle;
//        return roundedAngle;
//    }

    public double getIntegratedHeading() {
	   double currentHeading = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
	   double deltaHeading = currentHeading - previousHeading;

	   if (deltaHeading < -180) {
		  deltaHeading += 360;
	   } else if (deltaHeading >= 180) {
		  deltaHeading -= 360;
	   }

	   integratedHeading += deltaHeading;
	   previousHeading = currentHeading;

	   return integratedHeading;
    }
}