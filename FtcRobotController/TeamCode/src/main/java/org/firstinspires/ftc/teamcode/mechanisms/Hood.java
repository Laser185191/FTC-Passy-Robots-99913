package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hood {
    private Servo hoodMotor;

    public void init(HardwareMap hwMap){
        hoodMotor = hwMap.get(Servo.class, "hoodMotor");
    }

    public void loop(double angleHood){
        hoodMotor.setPosition(angleHood);
    }
}
