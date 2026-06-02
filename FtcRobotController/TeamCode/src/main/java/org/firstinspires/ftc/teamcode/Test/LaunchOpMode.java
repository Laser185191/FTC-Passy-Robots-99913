package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;

/* programme pour activer le lanceur, a une vitesse de 100% vitesse

Une amélioration serait de détecter la distance avec le panier, et avoir la vitesse du moteur
varier en fonction de cela.
Mais cela demande que le robot connaisse sa position exacte.

La V1 de ce code obligait le maintient du bouton, la V2.1, version actuelle, utilise un toggle.

ici on utilise la touche X de la télécommande, la V2.2 passera sur la gachette droite.

V3 : on a abandonner l'idée de régler la vitesse en téléop, ce sera toujours la meme, ce qui
     changera sera l'angle du hood.
 */

@TeleOp
public class LaunchOpMode extends OpMode{
    // appel de la fonction
    Launcher launch = new Launcher();

    // Variables globales
    double motorSpeedLaunch;
    boolean motorOnLaunch = false;
    boolean lastButtonStateLaunch = false;

    @Override
    public void init() {
        launch.init(hardwareMap);
    }

    @Override
    public void loop() {
        // lit l'état du bouton
        boolean currentButtonStateLaunch = gamepad1.right_bumper;
        telemetry.addData("x value :", gamepad1.right_bumper);


        if (currentButtonStateLaunch && !lastButtonStateLaunch) {
            motorOnLaunch = !motorOnLaunch;
        }

        // Commande du moteur
        if (motorOnLaunch) {
            motorSpeedLaunch = 0.6 ; // besoin du maximum de vitesse pour lancer
            launch.launch(motorSpeedLaunch);
        }

        else {
            motorSpeedLaunch = 0;
            launch.launch(motorSpeedLaunch);
        }

        // Sauvegarde de l'état du bouton
        lastButtonStateLaunch = currentButtonStateLaunch;
    }
}









