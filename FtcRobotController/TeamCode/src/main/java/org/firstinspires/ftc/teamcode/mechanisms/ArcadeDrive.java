package org.firstinspires.ftc.teamcode.mechanisms;

// Probleme ici, le moteur droit tourne moins vite que le gauche, que peut on faire ?

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArcadeDrive {
    private DcMotor leftMotor, rigthMotor ;

    double rigthCorrection = 1; // valeur a ajuster avec des test la laisser a 1 pour voir quand on est pleinne balle

    public void init(HardwareMap hwMap){ // Permet de reperer le matériel physique et d'orienter les commandes vers eux
        leftMotor = hwMap.get(DcMotor.class, "moteur_gauche");
        rigthMotor = hwMap.get(DcMotor.class,"moteur_droit");

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rigthMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // permet de les synchroniser a la meme vitesse

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD); // empèche que le robots tourne sur lui meme
        rigthMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void drive(double acceleration, double rotation){
        double leftPower = (acceleration - rotation)*rigthCorrection; // permet d'avancer et tourner en meme temps
        double rightPower = (acceleration + rotation)*rigthCorrection; // meme principe, mais emepeche que le robots tourne sur lui meme
        double largest = Math.max(Math.abs(leftPower),Math.abs(rightPower)); // Permet au deux moteurs de rouler a la meme vitesse
        if (largest > 1.0){
            leftPower /= largest;
            rightPower /= largest;
        }
        leftMotor.setPower(leftPower);
        rigthMotor.setPower(rightPower);

    }
}