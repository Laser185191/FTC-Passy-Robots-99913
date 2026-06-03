package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class GamePadPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        // tourne environs 50x par secondes
        double speedForward = -gamepad1.left_stick_y / 2.0 ;
        double leftx = gamepad1.left_stick_x;

        telemetry.addData("x", gamepad1.left_stick_x);
        telemetry.addData("y", speedForward);
        telemetry.addData("a button", gamepad1.a); // verifier le nplan d'attribution des touches, car la c'est la touche arrière M2

        if (leftx > 0){
            telemetry.addData("Left stick", "est en avant");
        }
        else {
            telemetry.addData("Left Stick", "est au nul");
        }
        telemetry.addData("Left stick statut", leftx);
    }
}
/*
AND - &&
OR - ||
NOT !
 */