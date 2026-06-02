package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Tourelle {
/*--------------------------------------------Variables PID---------------------------------------*/
    private DcMotor tourelleMotor;
    private int RotationTourelle;



    public void init(HardwareMap hwMap){
        tourelleMotor = hwMap.get(DcMotor.class,"tourelleMotor");
        tourelleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tourelleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tourelleMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop(int position, double power){
        tourelleMotor.setTargetPosition(position);
        tourelleMotor.setPower(power);
        tourelleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
