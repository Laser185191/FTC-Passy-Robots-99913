package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    private DcMotor launchMotor ;


    public void init(HardwareMap hwMap){
        launchMotor = hwMap.get(DcMotor.class, "moteur_lanceur");
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Dans notre application au lanceur, on se fiche de la position du moteur
    }

    public void launch(double motorLaunchSpeed) {
        double launchPower = motorLaunchSpeed; // si besoin de faire des maths / nom + reconnaisable plus tard

        launchMotor.setPower(launchPower);
    }
}
