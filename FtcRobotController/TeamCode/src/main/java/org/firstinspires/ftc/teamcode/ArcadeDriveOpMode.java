// A tester Mercredi
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ArcadeDrive;
import org.opencv.core.Mat;

@TeleOp
public class ArcadeDriveOpMode extends OpMode {

    ArcadeDrive drive = new ArcadeDrive(); // appeler une instance, mais globalement on appelle une classe faite avant
    double acceleration, rotation;

    // Réglages :
    final double zoneMorte = 0.08; // Trouve en ligne, depends de la sensation que l'on veux mieux décris dans le rapport
    final double tauxRotation = 0.5;

    @Override
    public void init() { // méthode
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {

        // lecture du joysticks
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;

        // Zone Morte :
        if(Math.abs(x) < zoneMorte){
            x = 0;
        }
        if (Math.abs(y) < zoneMorte){
            y = 0;
        }

        // Circularisation :
        double magnitude = Math.sqrt(x*x+y*y);
        if (magnitude > 1.0){
            x /= magnitude;
            y /= magnitude;
        }

        // Réduction de la rotation :
        x *= tauxRotation;

        acceleration = y;
        rotation = x;

        drive.drive(acceleration,rotation);
        /*------------------------------------------------------------------------------------------
                                    Mode lent si appuie sur O
        ------------------------------------------------------------------------------------------*/
        if (gamepad1.circle){

            acceleration = y / 2;
            rotation = x / 2;

            drive.drive(acceleration,rotation);
        }
    }
}