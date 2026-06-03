package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@TeleOp
public class IntakeOpMode extends OpMode {
    Intake intake = new Intake();
    double speedIntake;
    boolean motorOnIntake = false;
    boolean lastButtonStateIntake = false;

    @Override
    public void init(){
        intake.init(hardwareMap);

    }

    @Override
    public void loop(){
        // lit l'état du bouton
        boolean currentButtonStateIntake = gamepad1.left_bumper;
        telemetry.addData("gachette gauche:",gamepad1.left_bumper);



        if (currentButtonStateIntake && !lastButtonStateIntake) {
            motorOnIntake = !motorOnIntake;
        }
        // Commande du moteur
        if (motorOnIntake) {
            speedIntake = 0.9; // besoin du maximum de vitesse pour lancer
            intake.intake(speedIntake);
        }
            else {
                speedIntake = 0;
                intake.intake(speedIntake);
            }
        // Sauvegarde de l'état du bouton
        lastButtonStateIntake = currentButtonStateIntake;

    }
}
