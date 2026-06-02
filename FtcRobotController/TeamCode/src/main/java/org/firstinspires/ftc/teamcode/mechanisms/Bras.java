package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bras {
    private Servo servoBras;

    public void init(HardwareMap hwMap){
        servoBras = hwMap.get(Servo.class,"servoBras");
    }
    public void loop(double angle){
        servoBras.setPosition(angle);
    }
}
