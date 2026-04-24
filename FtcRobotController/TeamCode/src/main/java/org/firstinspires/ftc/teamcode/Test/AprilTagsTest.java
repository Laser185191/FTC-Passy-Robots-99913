package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.teamcode.mechanisms.AprilTagsWebcam;

@Disabled
@Autonomous
public class AprilTagsTest extends LinearOpMode {
    private DcMotor leftMotor, rightMotor;
    AprilTagsWebcam aprilTagsWebcam = new AprilTagsWebcam();

    double distanceError;
    double forward;
    double targetDistance;
    double turn;
    double distance;
    boolean tagDetected = false;





    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.get(DcMotor.class, "moteur_gauche");
        rightMotor = hardwareMap.get(DcMotor.class, "moteur_droit");
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Inverser si nécessaire
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        aprilTagsWebcam.init(hardwareMap, telemetry);

        waitForStart();



        while (opModeIsActive()) {

            aprilTagsWebcam.update();
            AprilTagDetection tag = aprilTagsWebcam.getTagBySpecificId(20);



            if (tag != null && tag.ftcPose != null) {

                double angle = tag.ftcPose.bearing;     // degrés
                distance = tag.ftcPose.range;    // mm

                // ----------------------
                // Gestion ANGLE
                // ----------------------

                double kP_turn = 0.01;   // ajustable
                turn = angle * kP_turn;

                turn = Range.clip(turn, -0.5, 0.5);

                // Si angle trop grand → on tourne
                if (Math.abs(angle) > 3) {
                    leftMotor.setPower(turn);
                    rightMotor.setPower(-turn);   // changer le - si ca ne tourne pas dans le bon sens
                }

                // ----------------------
                // GESTION DISTANCE
                // ----------------------
                else {

                    targetDistance = 1000;  // 1 m
                    distanceError = distance - targetDistance;

                    double kP_drive = 0.5;  // ajustable, trop lent + , - si ocsille
                    forward = distanceError * kP_drive;

                    forward = Range.clip(forward, -0.5, 0.5);

                    leftMotor.setPower(-forward);
                    rightMotor.setPower(-forward);

                    // Stop précis
                    if (Math.abs(distanceError) < 25) {
                        leftMotor.setPower(0);
                        rightMotor.setPower(0);
                    }
                }

                telemetry.addData("Angle", angle);
                telemetry.addData("Distance (mm)", distance);
                telemetry.addData("Left power", leftMotor.getPower());
                telemetry.addData("Right power", rightMotor.getPower());
                telemetry.addData("Distance raw", distance);
                telemetry.addData("Error Distance", distanceError);
                telemetry.addData("puissance", forward);
                telemetry.update();
            }
            else {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                telemetry.addLine("Tag non detecte");
            }

            telemetry.update();
        }

        aprilTagsWebcam.stop();
    }
}
