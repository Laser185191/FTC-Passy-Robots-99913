package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ServoTourelle;

@TeleOp
public class ServoTest extends OpMode {

    ServoTourelle test = new ServoTourelle();

    @Override
    public void init (){
        test.init(hardwareMap);
    }
    @Override
    public void loop(){
        if (gamepad1.a){
            test.setServoPos(0);
        }
        if (gamepad1.b){
            test.setServoPos(1);
        }
    }
}
