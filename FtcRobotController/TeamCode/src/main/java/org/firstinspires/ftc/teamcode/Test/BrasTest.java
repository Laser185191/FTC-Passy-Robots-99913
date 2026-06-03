package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.mechanisms.Bras;

@TeleOp
public class BrasTest extends OpMode {

    Bras bras = new Bras();


    @Override
    public void init(){
        bras.init(hardwareMap);
    }
    @Override
    public void loop(){
        if(gamepad1.a){
            bras.loop(1); // position haute
            telemetry.addData("Bras pos : ","haute");
        }
        if(gamepad1.b){
            bras.loop(0);  // position basse
            telemetry.addData("Bras pos : ","Basse");
        }

    }
}
