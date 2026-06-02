package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Hood;

@TeleOp
public class HoodTest extends OpMode {
    Hood hood = new Hood();
    double posCourt = 0.1;
    double posLong = 0.9;


    @Override
    public void init() {
        hood.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            hood.loop(posCourt);
        }
        if (gamepad1.b) {
            hood.loop(posLong);
        }

    }
}
