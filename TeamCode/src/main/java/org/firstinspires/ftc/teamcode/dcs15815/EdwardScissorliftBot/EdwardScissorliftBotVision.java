package org.firstinspires.ftc.teamcode.dcs15815.EdwardScissorliftBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;


public class EdwardScissorliftBotVision extends DefenderBotSystem {
    OpenCvWebcam webcam;
    AprilTagDetectionPipeline pipeline;
    private int _foundTagId = -1;
    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;
    int[] signalTagIds = new int[]{1, 2, 3};

    AprilTagDetection tagOfInterest = null;

    public EdwardScissorliftBotVision(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);
	   int cameraMonitorViewId = hm.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hm.appContext.getPackageName());
	   webcam = OpenCvCameraFactory.getInstance().createWebcam(hm.get(WebcamName.class, configString("CAMERA_NAME")), cameraMonitorViewId);
	   pipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
	   webcam.setPipeline(pipeline);
	   webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
	   webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
		  @Override
		  public void onOpened()
		  {
			 webcam.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
		  }

		  @Override
		  public void onError(int errorCode)
		  {

		  }
	   });
	   b.telemetry.setMsTransmissionInterval(50);
    }

    public boolean hasFoundTag() {
	   return _foundTagId > 0;
    }
    public int searchForTags() {
	   ArrayList<AprilTagDetection> currentDetections = pipeline.getLatestDetections();

	   if (currentDetections.size() != 0) {
		  boolean tagFound = false;

		  for (AprilTagDetection detectedTag : currentDetections) {
			 for (int sTagId : signalTagIds) {
				if (detectedTag.id == sTagId) {
				    _foundTagId = sTagId;
				    tagFound = true;
				    break;
				}
			 }
		  }
		  return _foundTagId;

//		  if (tagFound) {
//			 bot.telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
////			 tagToTelemetry(tagOfInterest);
//		  } else {
//			 bot.telemetry.addLine("Don't see tag of interest :(");
//
//			 if(tagOfInterest == null)
//			 {
//				bot.telemetry.addLine("(The tag has never been seen)");
//			 }
//			 else
//			 {
//				bot.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
////				tagToTelemetry(tagOfInterest);
//			 }
//		  }

	   } else {
		  return -1;
//		  bot.telemetry.addLine("Don't see tag of interest :(");
//
//		  if(tagOfInterest == null) {
//			 telemetry.addLine("(The tag has never been seen)");
//		  } else {
//			 telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//			 tagToTelemetry(tagOfInterest);
//		  }

	   }

//	   telemetry.update();
//	   sleep(20);
    }

}