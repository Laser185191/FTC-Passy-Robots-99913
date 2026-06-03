package org.firstinspires.ftc.teamcode.Total;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Bras;
import org.firstinspires.ftc.teamcode.mechanisms.Hood;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Tourelle;

@TeleOp
public class Teleop1 extends OpMode {
    // Réglages d'équipes :
    int teamNumber = 99913;
    String teamName = "Passy-Robots";
/*__________________________________________________________________________________________________
                            Appel des différentes fonction nécessaire:
Dans l'ordre on a :
    - le chassis
    - le lanceur
    - l'intake
    - le hood
    - la tourelle
    - la caméra dans un futur lointain.
__________________________________________________________________________________________________*/
    ArcadeDrive drive = new ArcadeDrive();
    /* On a appelé une autre instance, mais globalement, c'est comme si, on avait créé un fonction autre part et ici on la rappelle */
    Launcher launch = new Launcher();
    Intake intake = new Intake();
    Hood hood = new Hood();
    Tourelle tourelle = new Tourelle();
    Bras bras = new Bras();
/*__________________________________________________________________________________________________
                            Définition des différentes Variables :
_________________________________________________________________________________________________ */
    // Chassis :
    double acceleration, rotation;
    final double zoneMorte = 0.08; // Trouve en ligne, depends de la sensation que l'on veux mieux décris dans le rapport
    final double tauxRotation = 0.5;

    // Lanceur :
    double motorSpeedLaunch;
    boolean motorOnLaunch = false;
    boolean lastButtonStateLaunch = false;

    // Intake :
    double motorspeedIntake;
    boolean motorOnIntake = false;
    boolean lastButtonStateIntake = false;

    // Tourelle :
    double power = 1;
    int position_90 = 72;
    int position_180 = 144;
    int position_0 = 0;

    // Hood :
    double posCourt = 0.1;
    double posLong = 0.9;

    @Override
    public void init() {
        telemetry.addData("Team Name :", teamName);
        telemetry.addData("Team Number :",teamNumber);
        drive.init(hardwareMap); // initialise les moteurs comme définit dans la fonction
        intake.init(hardwareMap);
        launch.init(hardwareMap);
        tourelle.init(hardwareMap);
        hood.init(hardwareMap);
        telemetry.addData("Statut : ", "Initialized");
    }

    @Override
    public void loop() {

    /*______________________________________________________________________________________________
                                    Partie Chassis :                        Joystick gauche
    ______________________________________________________________________________________________*/
        // lecture du joysticks
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;

        // Zone Morte :
        if (Math.abs(x) < zoneMorte) {
            x = 0;
        }
        if (Math.abs(y) < zoneMorte) {
            y = 0;
        }

        // Circularisation :
        double magnitude = Math.sqrt(x * x + y * y);
        if (magnitude > 1.0) {
            x /= magnitude;
            y /= magnitude;
        }

        // Réduction de la rotation :
        x *= tauxRotation;

        acceleration = y;
        rotation = x;

        drive.drive(acceleration, rotation);

    /*______________________________________________________________________________________________
                                       Partie Intake :
    ______________________________________________________________________________________________*/

        // lit l'état du bouton
        boolean currentButtonStateIntake = gamepad1.left_bumper;
        telemetry.addData("gachette gauche:", gamepad1.left_bumper);


        if (currentButtonStateIntake && !lastButtonStateIntake) {
            motorOnIntake = !motorOnIntake;
        }

        // Commande du moteur
        if (motorOnIntake) {
            motorspeedIntake = 0.5; // besoin du maximum de vitesse pour lancer
            intake.intake(motorspeedIntake);
        } else {
            motorspeedIntake = 0;
            intake.intake(motorspeedIntake);
        }

        // Sauvegarde de l'état du bouton
        lastButtonStateIntake = currentButtonStateIntake;


    /*______________________________________________________________________________________________
                                        Partie Lanceur :
    ______________________________________________________________________________________________*/

        // lit l'état du bouton
        boolean currentButtonStateLaunch = gamepad1.right_bumper;
        telemetry.addData("x value :", gamepad1.right_bumper);


        if (currentButtonStateLaunch && !lastButtonStateLaunch) {
            motorOnLaunch = !motorOnLaunch;
        }

        // Commande du moteur
        if (motorOnLaunch) {
            motorSpeedLaunch = 0.6; // besoin du maximum de vitesse pour lancer
            launch.launch(motorSpeedLaunch);
        } else {
            motorSpeedLaunch = 0;
            launch.launch(motorSpeedLaunch);
        }

        // Sauvegarde de l'état du bouton
        lastButtonStateLaunch = currentButtonStateLaunch;

    /*______________________________________________________________________________________________
                                    Partie Tourelle :                       Joystik droit
     _____________________________________________________________________________________________*/

        if (gamepad1.right_bumper) {
            tourelle.loop(position_90, power);
            telemetry.addData("Position", position_90);
        }
        if (gamepad1.dpad_up) {
            tourelle.loop(position_0, power);
            telemetry.addData("Position", position_0);
        }
        if (gamepad1.dpad_down){
            tourelle.loop(position_180, power);
            telemetry.addData("Position", position_180);
        }

    /*______________________________________________________________________________________________
                                    Partie Hood :
     _____________________________________________________________________________________________*/
        if (gamepad1.a) {
            hood.loop(posCourt);
        }
        if (gamepad1.b) {
            hood.loop(posLong);
        }

        if (gamepad1.x) {
            bras.loop(0.5);
        }
    }
}
