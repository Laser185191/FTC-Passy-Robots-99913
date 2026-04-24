package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class VariablePractice extends OpMode {

    @Override
    public void init() {
        int teamNumber = 99913;         // Une variable commence toujours par une minuscule
        double motorSpeed = 0.75;
        boolean clawClosed = true;
        String teamName = "Passy-Robots";

        telemetry.addData("Team number",teamNumber);
        telemetry.addData("motorSpeed", motorSpeed);
        telemetry.addData("claw cloed", clawClosed);
        telemetry.addData("name", teamName);
    }

    @Override
    public void loop() {

    }
}
