package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ProductionBotMecanumDrivetrain extends DefenderBotDrivetrain {

    public DcMotor backLeft, frontLeft, frontRight, backRight;


    ProductionBotMecanumDrivetrain(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   backLeft = hm.dcMotor.get(configString("DRIVETRAIN_BACKLEFT_MOTOR_NAME"));
	   frontLeft = hm.dcMotor.get(configString("DRIVETRAIN_FRONTLEFT_MOTOR_NAME"));
	   frontRight = hm.dcMotor.get(configString("DRIVETRAIN_FRONTRIGHT_MOTOR_NAME"));
	   backRight = hm.dcMotor.get(configString("DRIVETRAIN_BACKRIGHT_MOTOR_NAME"));

	   backLeft.setDirection(configMotorDirection("DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION"));
	   frontLeft.setDirection(configMotorDirection("DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION"));
	   frontRight.setDirection(configMotorDirection("DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION"));
	   backRight.setDirection(configMotorDirection("DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION"));

	   resetEncoders();
    }

    public void resetEncoders() {
	   backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	   frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	   frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	   backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	   backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	   backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
	   double largest = configDouble("DRIVETRAIN_POWER_MAX");
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


}