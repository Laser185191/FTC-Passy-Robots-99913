package org.firstinspires.ftc.teamcode.mechanisms;



import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake  {

    private DcMotorSimple intakeMotor;


    public void init(HardwareMap hwMapIntake) {
        intakeMotor = hwMapIntake.get(DcMotorSimple.class,"moteurIntake");
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake(double intakeSpeed) {
        intakeMotor.setPower(intakeSpeed);
    }
}
