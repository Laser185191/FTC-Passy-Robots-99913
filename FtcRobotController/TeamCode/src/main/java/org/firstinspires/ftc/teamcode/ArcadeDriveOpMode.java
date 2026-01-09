// A absolument tester mercredi prochain

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ArcadeDrive;

@TeleOp
public class ArcadeDriveOpMode extends OpMode {

    ArcadeDrive drive = new ArcadeDrive(); // appeler une instance, mais globalement on appelle une classe faite avant
    double acceleration, rotation;


    @Override
    public void init() { // méthode
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        acceleration = -gamepad1.left_stick_y;
        rotation = gamepad1.left_stick_x;

        drive.drive(acceleration,rotation);
    }
}
