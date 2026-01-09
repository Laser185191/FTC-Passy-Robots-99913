package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class motor {
    private DcMotor motor;
    private double ticksParRevolution; // révolution du moteur


    public void init (HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksParRevolution = motor.getMotorType().getTicksPerRev();
    }
    public void setMotorSpeed(double speed){
        // acceppte des valeurs de -1,0 to 1,0
        motor.setPower(speed);
    }

    public double getMotorRevs(){
        return motor.getCurrentPosition() / ticksParRevolution ; // normalise les ticks en révolutioin (2:1 ne pas oublier de multiplier par le reducteur x 2 ici, si il y a un réducteur )

    }
}
