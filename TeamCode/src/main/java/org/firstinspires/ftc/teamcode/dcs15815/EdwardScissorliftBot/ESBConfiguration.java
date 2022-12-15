package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;

public class ESBConfiguration extends DefenderBotConfiguration {

    public static String DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left_motor";
    public static String DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left_motor";
    public static String DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right_motor";
    public static String DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right_motor";
    public static DcMotorSimple.Direction DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;


    public static double DRIVETRAIN_POWER_MAX = 1.0;
    public static int DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;

    public static String LIFT_LEFT_MOTOR_NAME = "lift_left_motor";
    public static String LIFT_RIGHT_MOTOR_NAME = "lift_right_motor";
    public static DcMotorSimple.Direction LIFT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;;
    public static DcMotorSimple.Direction LIFT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static double LIFT_POWER_MAX = 1;
    public static int LIFT_POSITION_MAX = 2200;
    public static int LIFT_POSITION_HIGH = 2200;
    public static int LIFT_POSITION_BELOW_HIGH = 1600;
    public static int LIFT_POSITION_MIDDLE = 1500;
    public static int LIFT_POSITION_LOW = 1100;
    public static int LIFT_POSITION_STACKTOP = 680;
    public static int LIFT_POSITION_STACK3 = 500;
    public static int LIFT_POSITION_GROUND = 0;

    public static double LIFT_MOTOR_KP = 0;
    public static double LIFT_MOTOR_KI = 0;
    public static double LIFT_MOTOR_KD = 0;

    public static double GAMEPAD2_RIGHT_STICK_CURVE = 2;
    public static double GAMEPAD2_RIGHT_STICK_MAX = 1;

    public static double GAMEPAD1_LEFT_STICK_Y_CURVE = 2.5;
    public static double GAMEPAD1_LEFT_STICK_Y_MAX = 1;

    public static double GAMEPAD1_LEFT_STICK_X_CURVE = 2;
    public static double GAMEPAD1_LEFT_STICK_X_MAX = 1;


    public static String CLAW_SERVO_NAME = "claw";
    // We've changed these several times as we've swapped claws whose
    // servo positions were reversed
    //	   CLAW_POSITION_OPEN = 0.15; // original metal claw
    //	   CLAW_POSITION_OPEN = 0; // original metal claw

    public static double CLAW_POSITION_OPEN = 0.35;
    public static double CLAW_POSITION_CLOSED = 0.15;

    public static String IMU_SENSOR_NAME = "imu";
    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;

    // These are constants for the homegrown navigation.
    // This is unused since implementing RoadRunner
    public static double NAVIGATION_POWER_DEFAULT = 0.65;
    public static long NAVIGATION_TIMEOUT_DEFAULT = 10000;
    public static double NAVIGATION_TOLERANCE_ROTATION = 0.3;
    public static double NAVIGATION_TOLERANCE_X = 1.0;
    public static double NAVIGATION_TOLERANCE_Y = 1.0;
    public static double NAVIGATION_GEAR_RATIO = 1;
    public static long NAVIGATION_TICKS_PER_ROTATION = 280;
    public static double NAVIGATION_WHEEL_RADIUS = 2;
    public static double NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;;
    public static double NAVIGATION_ROTATION_KP = 0.05;
    public static double NAVIGATION_ROTATION_KI = 0;
    public static double NAVIGATION_ROTATION_KD = 0;

    // Unused since moving to robot-relative RoadRunner movement instead of absolute
    public static Pose2d NAVIGATION_START_RED_LEFT = new Pose2d(-65, 36, 0);
    public static Pose2d NAVIGATION_START_RED_RIGHT = new Pose2d(-65, -36, 0);
    public static Pose2d NAVIGATION_START_BLUE_LEFT = new Pose2d(65, -36, 0);
    public static Pose2d NAVIGATION_START_BLUE_RIGHT = new Pose2d(65, 36, 0);


    public static String CAMERA_NAME = "Webcam 1";
    public static double VISION_THRESHOLD_DETECTION = 18;

    public ESBConfiguration() {
	   super();
    }

}