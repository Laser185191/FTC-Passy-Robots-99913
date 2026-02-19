package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;

/* programme pour activer le lanceur, a une vitesse de 90% vitesse précise a déterminer.

Une amélioration serait de détecter la distance avec le panier, et avoir la vitesse du moteur
varier en fonction de cela.
Mais cela demande que le robot connaisse sa position exacte.

La V1 de ce code obligait le maintient du bouton, la V2, version actuelle, utilise un toggle.
 */

@TeleOp
public class LaunchOpMode extends OpMode{
    // appel de la fonction
    Launcher launch = new Launcher();

    // Variables globales
    double motorspeed;
    boolean motorOn = false;
    boolean lastButtonState = false;

    @Override
    public void init() {
        launch.init(hardwareMap);
    }

    @Override
    public void loop() {
        // lit l'état du bouton
        boolean currentButtonState = gamepad1.a;
        telemetry.addData("x value :", gamepad1.a);


        if (currentButtonState && !lastButtonState) {
            motorOn = !motorOn;
        }

        // Commande du moteur
        if (motorOn) {
            motorspeed = 0.6;
            launch.launch(motorspeed);
        }

        else {
            motorspeed = 0;
            launch.launch(motorspeed);
        }

        // Sauvegarde de l'état du bouton
        lastButtonState = currentButtonState;
    }
}
            // test github 1


        /*if (gamepad1.crossWasPressed()) {
            motorspeed = 0.9;
            launch.launch(motorspeed);
        }

        else if (gamepad1.crossWasReleased()){
            motorspeed = 0;
            launch.launch(motorspeed);
        }*/








