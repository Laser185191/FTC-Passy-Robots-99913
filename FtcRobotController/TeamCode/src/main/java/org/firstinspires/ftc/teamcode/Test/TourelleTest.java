package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Tourelle;
@Disabled
@TeleOp
public class TourelleTest extends OpMode {

    Tourelle tourelle = new Tourelle();
    double power = 1;
    int position_90 = 72;
    int position_180 = 288;
    int position_0 = -288;

    @Override
    public void init() {
        tourelle.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Position possible", "90°, 180°, 0°");
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_right) {
            tourelle.loop(position_180, power);
            telemetry.addData("Position", position_90);
        }
        if (gamepad1.dpad_left) {
            tourelle.loop(position_0, power);
            telemetry.addData("Position", position_90);
        }

    }

}

