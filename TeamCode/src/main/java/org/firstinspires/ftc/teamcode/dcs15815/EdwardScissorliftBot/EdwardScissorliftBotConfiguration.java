package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;

import java.lang.reflect.Field;

public class EdwardScissorliftBotConfiguration extends DefenderBotConfiguration {

    public double DRIVETRAIN_POWER_MAX;
    public int DRIVETRAIN_MAX_TICKS_PER_SECOND;

    public String LIFT_LEFT_MOTOR_NAME, LIFT_RIGHT_MOTOR_NAME;
    public DcMotorSimple.Direction LIFT_LEFT_MOTOR_DIRECTION, LIFT_RIGHT_MOTOR_DIRECTION;
    public double LIFT_POWER_MAX;
    public int LIFT_POSITION_MAX, LIFT_POSITION_HIGH, LIFT_POSITION_BELOW_HIGH, LIFT_POSITION_MIDDLE, LIFT_POSITION_LOW, LIFT_POSITION_STACKTOP, LIFT_POSITION_GROUND;
    public static int S_LIFT_POSITION_MAX = 2200;
    public static int S_LIFT_POSITION_HIGH = 2200;
    public static int S_LIFT_POSITION_BELOW_HIGH = 1600;
    public static int S_LIFT_POSITION_MIDDLE = 1500;
    public static int S_LIFT_POSITION_LOW = 1100;
    public static int S_LIFT_POSITION_STACKTOP = 600;
    public static int S_LIFT_POSITION_GROUND = 0;

    public double LIFT_MOTOR_KP, LIFT_MOTOR_KI, LIFT_MOTO_KD;

    public double GAMEPAD2_RIGHT_STICK_CURVE, GAMEPAD2_RIGHT_STICK_MAX;

    public String CLAW_SERVO_NAME = "claw";
    public double CLAW_POSITION_OPEN;
    public double CLAW_POSITION_CLOSED;

    public String IMU_SENSOR_NAME;
    public AxesOrder IMU_AXES_ORDER;

    public double NAVIGATION_POWER_DEFAULT;
    public long NAVIGATION_TIMEOUT_DEFAULT;
    public double NAVIGATION_TOLERANCE_ROTATION;
    public double NAVIGATION_TOLERANCE_X;
    public double NAVIGATION_TOLERANCE_Y;
    public double NAVIGATION_GEAR_RATIO;
    public long NAVIGATION_TICKS_PER_ROTATION;
    public double NAVIGATION_WHEEL_RADIUS;
    public double NAVIGATION_INCHES_PER_TICK;
    public double NAVIGATION_ROTATION_KP = 0;
    public double NAVIGATION_ROTATION_KI = 0;
    public double NAVIGATION_ROTATION_KD = 0;

    // Unused since moving to robot-relative RoadRunner movement instead of absolute
    public Pose2d NAVIGATION_START_RED_LEFT = new Pose2d(-65, 36, 0);
    public Pose2d NAVIGATION_START_RED_RIGHT = new Pose2d(-65, -36, 0);
    public Pose2d NAVIGATION_START_BLUE_LEFT = new Pose2d(65, -36, 0);
    public Pose2d NAVIGATION_START_BLUE_RIGHT = new Pose2d(65, 36, 0);


    public String CAMERA_NAME = "Webcam 1";
    public double VISION_THRESHOLD_DETECTION = 18;

    public EdwardScissorliftBotConfiguration() {
	   super();
	   DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left_motor";
	   DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left_motor";
	   DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right_motor";
	   DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right_motor";
	   DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   DRIVETRAIN_POWER_MAX = 1.0;
	   DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;

	   LIFT_LEFT_MOTOR_NAME = "lift_left_motor";
	   LIFT_RIGHT_MOTOR_NAME = "lift_right_motor";
	   LIFT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   LIFT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   LIFT_POWER_MAX = 1;
	   GAMEPAD2_RIGHT_STICK_CURVE = 2;
	   GAMEPAD2_RIGHT_STICK_MAX = 1;

	   LIFT_POSITION_MAX = 2200;
	   LIFT_POSITION_HIGH = 2200;
	   LIFT_POSITION_BELOW_HIGH = 1600;
	   LIFT_POSITION_MIDDLE = 1500;
	   LIFT_POSITION_LOW = 1100;
	   LIFT_POSITION_STACKTOP = 600;
	   LIFT_POSITION_GROUND = 0;
	   LIFT_MOTOR_KP = 0;
	   LIFT_MOTOR_KI = 0;
	   LIFT_MOTO_KD = 0;

	   // We've changed these several times as we've swapped claws whose
	   // servo positions were reversed
//	   CLAW_POSITION_OPEN = 0.15; // original metal claw
//	   CLAW_POSITION_OPEN = 0; // original metal claw
//	   CLAW_POSITION_CLOSED = 0.8;
	   CLAW_POSITION_OPEN = 0.8;
	   CLAW_POSITION_CLOSED = 0.15;

	   IMU_SENSOR_NAME = "imu";
	   IMU_AXES_ORDER = AxesOrder.XYZ;

	   NAVIGATION_POWER_DEFAULT = 0.65;
	   NAVIGATION_TIMEOUT_DEFAULT = 10000;
	   NAVIGATION_TOLERANCE_ROTATION = 0.3;
	   NAVIGATION_TOLERANCE_X = 1.0;
	   NAVIGATION_TOLERANCE_Y = 1.0;
	   NAVIGATION_ROTATION_KP = 0.05;
	   NAVIGATION_ROTATION_KI = 0;
	   NAVIGATION_ROTATION_KD = 0;

	   // These are constants for the homegrown navigation.
	   // This is unused since implementing RoadRunner
	   NAVIGATION_GEAR_RATIO = 1; //is this right?
	   NAVIGATION_TICKS_PER_ROTATION = 280;
	   NAVIGATION_WHEEL_RADIUS = 2; // 4 inches diameter
	   NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;

	   for (Field f : EdwardScissorliftBotConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }

    }

}