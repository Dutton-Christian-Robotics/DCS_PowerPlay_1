package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.lang.reflect.Field;
import java.util.Hashtable;

abstract public class DefenderBotConfiguration {
    public static String DRIVETRAIN_BACKLEFT_MOTOR_NAME = null;
    public static String DRIVETRAIN_FRONTLEFT_MOTOR_NAME = null;
    public static String DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = null;
    public static String DRIVETRAIN_BACKRIGHT_MOTOR_NAME = null;

    public static DcMotorSimple.Direction DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION = null;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION = null;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION = null;
    public static DcMotorSimple.Direction DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION = null;


    public DefenderBotConfiguration() {
    }

}