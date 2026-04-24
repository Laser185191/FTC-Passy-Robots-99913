package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@TeleOp
public class IntakeOpMode extends OpMode {

    Intake intake = new Intake();

    double motorspeedIntake;
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
            motorspeedIntake =-0.5; // besoin du maximum de vitesse pour lancer
            intake.intake(motorspeedIntake);
        }

            else {
                motorspeedIntake = 0;
                intake.intake(motorspeedIntake);
            }

        // Sauvegarde de l'état du bouton
        lastButtonStateIntake = currentButtonStateIntake;

    }
}
