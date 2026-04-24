package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.motor;
@Disabled
@TeleOp
public class DCMotorPractice extends OpMode {

    motor bench = new motor(); // permet de lier une classe a celle ci, pour alleger le code,

    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
       bench.setMotorSpeed(0.5); // le moteur tournera ici a 50 %
       telemetry.addData("motor Rev", bench.getMotorRevs()); // permet d'avoir le nombre de révolution du moteur
    }
}
