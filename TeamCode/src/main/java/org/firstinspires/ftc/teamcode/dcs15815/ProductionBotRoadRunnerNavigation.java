package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class ProductionBotRoadRunnerNavigation extends DefenderBotSystem {

    public SampleMecanumDrive drivetrain;

    ProductionBotRoadRunnerNavigation(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   drivetrain = new SampleMecanumDrive(hm);

    }




}