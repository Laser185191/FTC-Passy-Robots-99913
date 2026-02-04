package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ServoMotor;

@TeleOp

public class ServoMotorPractice extends OpMode {

    ServoMotor test = new ServoMotor();
    double leftTrigger;
    double rightTrigger;

    @Override
    public void init() {
        test.init(hardwareMap);
        leftTrigger = 0.0;
        rightTrigger = 0.0;
    }

    @Override
    public void loop() {

        leftTrigger = gamepad1.left_trigger;
        rightTrigger = gamepad1.right_trigger;

        test.setServoRot(rightTrigger);
        test.setServoPos(leftTrigger);
    }
}
