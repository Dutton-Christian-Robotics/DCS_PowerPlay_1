package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import com.qualcomm.robotcore.util.ElapsedTime;

public class ESBNavigation extends DefenderBotSystem {

    private final GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);
    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;

    private MatrixF conversion;

    private DcMotor backLeft, frontLeft, frontRight, backRight;

    private ESBSensors sensors;
    private ESBMecanumDrivetrain drivetrain;

    public ESBNavigation(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   float[] data = {1.0f, 1.0f, 1.0f,
			 1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f, 1.0f};
	   conversion = new GeneralMatrixF(3, 3, data);
	   conversion = conversion.inverted();


	   sensors = (ESBSensors)((EdwardScissorliftBot)bot).sensors;

    }

    public void linkDrivetrain(ESBMecanumDrivetrain dt) {
	   drivetrain = dt;
//	   System.out.println("FLAG");
//	   System.out.println(drivetrain == null ? "null" : "exists");
//	   System.out.println(drivetrain.getClass().getName());

	   backLeft = drivetrain.backLeft;
	   frontLeft = drivetrain.frontLeft;
	   frontRight = drivetrain.frontRight;
	   backRight = drivetrain.backRight;

	   setEncoderOffsets();

//	   backLeft = ((ProductionBotMecanumDrivetrain) bot.drivetrain).backLeft;
//	   frontLeft = ((ProductionBotMecanumDrivetrain) bot.drivetrain).frontLeft;
//	   frontRight = ((ProductionBotMecanumDrivetrain) bot.drivetrain).frontRight;
//	   backRight = ((ProductionBotMecanumDrivetrain) bot.drivetrain).backRight;

    }

    public boolean between(double x, double min, double max) {
	   return (x > min) && (x < max);
    }

    public void comeToHeading(double angle, double maxPower, double tolerance, double timeout) {
	   double difference, absDifference, currentAngle;
	   boolean keepTurning = true;
	   ElapsedTime timer = new ElapsedTime();
	   long sleepLength = 10;
	   double powerCutoffThreshold = 0.01;
	   double targetAngle = angleWrap(angle);

	   //Orientation orientation;

	   DefenderPIDController pid = new DefenderPIDController(
			 ESBConfiguration.NAVIGATION_ROTATION_KP,
			 ESBConfiguration.NAVIGATION_ROTATION_KI,
			 ESBConfiguration.NAVIGATION_ROTATION_KD
	   );
	   double power;
	   do {
		  currentAngle = sensors.getIntegratedHeading();
//		  difference = currentAngle - angle;
//		  absDifference = Math.abs(difference);

		  power = pid.calculatePower(targetAngle, currentAngle);
		  if (power <= powerCutoffThreshold) {
			 break;
		  }
		  drivetrain.drive(0, 0, -1 * power);
		  sleep(sleepLength);

		  if (timer.milliseconds() >= timeout) {
			 keepTurning = false;
		  }
	   } while (keepTurning);

    }

    public void comeToHeading(double angle, double powerRatio) {
	   comeToHeading(angle, powerRatio * ESBConfiguration.NAVIGATION_POWER_DEFAULT, ESBConfiguration.NAVIGATION_TOLERANCE_ROTATION, ESBConfiguration.NAVIGATION_TIMEOUT_DEFAULT);
    }

    public void comeToHeading(double angle) {
	   comeToHeading(angle, ESBConfiguration.NAVIGATION_POWER_DEFAULT, ESBConfiguration.NAVIGATION_TOLERANCE_ROTATION, ESBConfiguration.NAVIGATION_TIMEOUT_DEFAULT);
    }

    double[] getDistanceInches() {
	   double[] distances = {0.0, 0.0};

	   encoderMatrix.put(0, 0, (float) ((frontLeft.getCurrentPosition() - frontLeftOffset) * ESBConfiguration.NAVIGATION_INCHES_PER_TICK));
	   encoderMatrix.put(1, 0, (float) ((frontRight.getCurrentPosition() - frontRightOffset) * ESBConfiguration.NAVIGATION_INCHES_PER_TICK));
	   encoderMatrix.put(2, 0, (float) ((backLeft.getCurrentPosition() - backLeftOffset) * ESBConfiguration.NAVIGATION_INCHES_PER_TICK));

	   MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);
	   distances[0] = distanceMatrix.get(0, 0);
	   distances[1] = distanceMatrix.get(1, 0);

	   return distances;
    }

    public double powerDropoff(double target, double current) {
	   return 1 - Math.pow(1 - ((target - (current - 1)) / Math.abs(target - current)), 14);
    }

    public void setEncoderOffsets() {

	   frontRightOffset = frontRight.getCurrentPosition();
	   frontLeftOffset = frontLeft.getCurrentPosition();
	   backLeftOffset = backLeft.getCurrentPosition();
	   backRightOffset = backRight.getCurrentPosition();
    }

    public void driveToPosition(double x, double y, double heading) {

	   double[] d = getDistanceInches();
	   double deltaY, deltaX, deltaH;
	   double pX, pY, pH;
	   double h = sensors.getIntegratedHeading();
	   double rotation = 0;
	   double averageError = 0;

	   while ((Math.abs(y - d[0]) > ESBConfiguration.NAVIGATION_TOLERANCE_Y) || (Math.abs(x - d[1]) > ESBConfiguration.NAVIGATION_TOLERANCE_X) || (Math.abs(heading - h) > ESBConfiguration.NAVIGATION_TOLERANCE_ROTATION)) {
		  deltaX = x - d[1];
		  deltaY = y - d[0];
		  deltaH = h - heading;
		  bot.telemetry.addData("dX", deltaX);
		  bot.telemetry.addData("dY", deltaY);
		  bot.telemetry.addData("dH", deltaH);
		  bot.telemetry.update();
//		  System.out.println("DRIVE REPORT");
//		  System.out.println(deltaX);
//		  System.out.println(deltaY);
//		  System.out.println(deltaH);


//            bot.telemetry.addData("x", deltaX);
//            bot.telemetry.addData("y", deltaY);
//            bot.telemetry.addData("h", deltaH);
//            bot.telemetry.update();




//            if (h > heading) {
//                rotation = 0.5;
//            } else if (h < heading) {
//                rotation = -0.5;
//            }


		  averageError = (Math.abs(deltaX) + Math.abs(deltaY) + Math.abs(deltaH)) / 3;
//            bot.telemetry.addData("Avg", averageError);
//            pX = powerDropoff(x, d[1]);
//            pY = powerDropoff(y, d[0]);
//            pH = powerDropoff(heading, h);

//            pX = powerDropoff(x, d[1]) * (deltaX / averageError);
//            pY = powerDropoff(y, d[0]) * (deltaY / averageError);
//            pH = powerDropoff(heading, h) * (deltaH / averageError);
		  pX = (deltaX / averageError);
		  pY = (deltaY / averageError);
		  pH = (deltaH / averageError);

//            bot.telemetry.addData("px", pX);
//            bot.telemetry.addData("py", pY);
//            bot.telemetry.addData("ph", pH);


//            bot.telemetry.update();
//            bot.drive(powerDropoff(y, d[0]), powerDropoff(x, d[1]), 0);
		  drivetrain.drive(pY, pX, pH);
		  d = getDistanceInches();
		  h = sensors.getIntegratedHeading();
	   }
	   bot.stopDriving();

    }

    public void driveToPosition(double x, double y) {
	   driveToPosition(x, y, 0.0);
    }

    public void driveToPosition(DefenderBotPosition position) {
	   driveToPosition(position.getX(), position.getY(), position.getHeading());
    }

//    public DefenderBotPosition getCurrentPosition() {
//	   double[] d = getDistanceInches();
//	   return new DefenderBotPosition(d[1], d[0], sensors.getIntegratedHeading());
//    }

//    public void driveToRelativePosition(double dX, double dY) {
//	   driveToPosition(getCurrentPosition().relativePosition(dX, dY));
//    }

    public void driveToRelativePosition(double dX, double dY, double heading) {
//	   driveToPosition(getCurrentPosition().relativePosition(dX, dY), heading);
    }

    public void resetPositionTracking() {
	   drivetrain.resetEncoders();
    }

    public void resetAndDriveToPosition(double x, double y) {
	   resetPositionTracking();
	   driveToPosition(x, y);
    }

    public double angleWrap(double degrees) {
	   double radians = Math.toRadians(degrees);
	   while (radians > Math.PI) {
		  degrees -= 2 * Math.PI;
	   }
	   while (radians < -Math.PI) {
		  radians += 2 * Math.PI;
	   }
	   return Math.toDegrees(radians);
    }


}