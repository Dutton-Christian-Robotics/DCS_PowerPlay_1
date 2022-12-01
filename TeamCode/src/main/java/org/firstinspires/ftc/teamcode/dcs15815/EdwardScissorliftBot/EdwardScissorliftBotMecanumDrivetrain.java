package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotDrivetrain;


public class EdwardScissorliftBotMecanumDrivetrain extends DefenderBotDrivetrain {

    public DcMotorEx backLeft, frontLeft, frontRight, backRight;


    public EdwardScissorliftBotMecanumDrivetrain(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   backLeft = hm.get(DcMotorEx.class, EdwardScissorliftBotConfiguration.DRIVETRAIN_BACKLEFT_MOTOR_NAME);
	   frontLeft = hm.get(DcMotorEx.class, EdwardScissorliftBotConfiguration.DRIVETRAIN_FRONTLEFT_MOTOR_NAME);
	   frontRight = hm.get(DcMotorEx.class, EdwardScissorliftBotConfiguration.DRIVETRAIN_FRONTRIGHT_MOTOR_NAME);
	   backRight = hm.get(DcMotorEx.class, EdwardScissorliftBotConfiguration.DRIVETRAIN_BACKRIGHT_MOTOR_NAME);

	   backLeft.setDirection(EdwardScissorliftBotConfiguration.DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION);
	   frontLeft.setDirection(EdwardScissorliftBotConfiguration.DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION);
	   frontRight.setDirection(EdwardScissorliftBotConfiguration.DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION);
	   backRight.setDirection(EdwardScissorliftBotConfiguration.DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION);

	   resetEncoders();
    }

    public void resetEncoders() {
	   backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

	   backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double bl, double fl, double fr, double br) {
	   backLeft.setPower(bl);
	   frontLeft.setPower(fl);
	   frontRight.setPower(fr);
	   backRight.setPower(br);
    }

    public void setPower(double p) {
	   setPower(p, p, p, p);
    }

    public void setMotorDirection(DcMotorSimple.Direction bl, DcMotorSimple.Direction fl,
						    DcMotorSimple.Direction fr, DcMotorSimple.Direction br) {
	   backLeft.setDirection(bl);
	   frontLeft.setDirection(fl);
	   frontRight.setDirection(fr);
	   backRight.setDirection(br);
    }

    public void setMotorDirection(DcMotorSimple.Direction left, DcMotorSimple.Direction right) {
	   setMotorDirection(left, left, right, right);
    }

    private void setProportionalPower(double bl, double fl, double fr, double br) {
	   double largest = EdwardScissorliftBotConfiguration.DRIVETRAIN_POWER_MAX;
	   largest = Math.max(largest, Math.abs(bl));
	   largest = Math.max(largest, Math.abs(fl));
	   largest = Math.max(largest, Math.abs(fr));
	   largest = Math.max(largest, Math.abs(br));

	   backLeft.setPower(bl / largest);
	   frontLeft.setPower(fl / largest);
	   frontRight.setPower(fr / largest);
	   backRight.setPower(br / largest);
    }

    @Override
    public void stopDriving() {
	   setPower(0);
    }

    public void drive(double arr[]) {
	   drive(arr[0], arr[1], arr[2]);
    }

    public void drive(double forward, double strafe, double rotate) {
//	   setMotorDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD,
//			 DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);

	   double backLeftPower = forward - strafe + rotate;
	   double frontLeftPower = forward + strafe + rotate;
	   double frontRightPower = forward - strafe - rotate;
	   double backRightPower = forward + strafe - rotate;

	   System.out.println("DRIVE REPORT");
	   System.out.println(backLeftPower);
	   System.out.println(frontLeftPower);
	   System.out.println(frontRightPower);
	   System.out.println(backRightPower);

	   setProportionalPower(backLeftPower, frontLeftPower, frontRightPower, backRightPower);
    }

    public void driveByVelocity(double forward, double strafe, double rotate) {
//	   setMotorDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD,
//			 DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);

	   backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
	   frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
	   frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
	   backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

	   double backLeftPower = forward - strafe + rotate;
	   double frontLeftPower = forward + strafe + rotate;
	   double frontRightPower = forward - strafe - rotate;
	   double backRightPower = forward + strafe - rotate;

	   double largest = 1;
	   largest = Math.max(largest, Math.abs(backLeftPower));
	   largest = Math.max(largest, Math.abs(frontLeftPower));
	   largest = Math.max(largest, Math.abs(frontRightPower));
	   largest = Math.max(largest, Math.abs(backRightPower));

	   double maxTicksSecond = EdwardScissorliftBotConfiguration.DRIVETRAIN_MAX_TICKS_PER_SECOND;

	   backLeft.setVelocity(backLeftPower / largest * maxTicksSecond);
	   frontLeft.setVelocity(frontLeftPower / largest * maxTicksSecond);
	   frontRight.setVelocity(frontRightPower / largest * maxTicksSecond);
	   backRight.setVelocity(backRightPower / largest * maxTicksSecond);
    }



}