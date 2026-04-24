package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagsWebcam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AprilTagsWebcamExample extends OpMode {

    AprilTagsWebcam aprilTagsWebcam = new AprilTagsWebcam();

    @Override
    public void init() {
        aprilTagsWebcam.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        // actualise le vision portail
        aprilTagsWebcam.update();
        AprilTagDetection id20 = aprilTagsWebcam.getTagBySpecificId(20);
        aprilTagsWebcam.displayDetectionTelemetry(id20);
    }
}
