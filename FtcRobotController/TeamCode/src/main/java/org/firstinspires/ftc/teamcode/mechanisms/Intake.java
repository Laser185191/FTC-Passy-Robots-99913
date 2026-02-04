package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake  {

    private DcMotor intakeMotor;


    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotor.class,"moteur_intake");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intake(double intakeSpeed) {
        double intakePower = intakeSpeed;
        intakeMotor.setPower(intakePower);
    }
}
