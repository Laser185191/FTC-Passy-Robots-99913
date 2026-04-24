/*--------------------------------------------------------------------------------------------------
                               Autonomous_Cycle_1_RED
--------------------------------------------------------------------------------------------------*/
package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;

@Autonomous
public class Autonomous1 extends OpMode {

    /*----------------------------------------------------------------------------------------------
                                   Information sur l'équipe:
    ----------------------------------------------------------------------------------------------*/
    int teamNumber = 99913;
    String teamName = "Passy-Robots";
    String alliance = "RED";

     /*----------------------------------------------------------------------------------------------
                                   Variables pour le fonctionnement des moteurs
    ----------------------------------------------------------------------------------------------*/
    private DcMotorEx leftmotor; // DcmotorEx, permet l'accès a la fonction velocite
    private DcMotorEx rightmotor;


    /*----------------------------------------------------------------------------------------------
                                    Variables de bases :
    ----------------------------------------------------------------------------------------------*/
    static final int diametre = 90 ; // diamètre des roues du chassis
    static final double rapportReduction = 30.24; // rapport de réduction en sortie moteur
    static final int ticksParTourMoteur = 28; // standards pour ce type de moteur REV


    /*----------------------------------------------------------------------------------------------
                                 Variables utiliser dans le programme:
    ----------------------------------------------------------------------------------------------*/
    static final double circonferenceRoue = diametre * 3.14 ;
    static final double nbTourRoue = ticksParTourMoteur * rapportReduction;
    static final double trRoueParMm = nbTourRoue / circonferenceRoue;


    /*----------------------------------------------------------------------------------------------
                               Variables distances pour target: ( coef * distance = nb ticks cible)
    ----------------------------------------------------------------------------------------------*/
    int distance1 = 1000; // distance en mm
    int target1 = (int) (distance1 * trRoueParMm);

    int rotation1 = 10;
    int spin1 = (int) (rotation1 * trRoueParMm);

    int rotation2 = 15;
    int spin2 = (int) (rotation2 * trRoueParMm);

    int distance2 = 500;
    int target2 = (int) (distance2 * trRoueParMm);

    int rotation3 = 15;
    int spin3 = (int) (rotation3 * trRoueParMm);

    int distance3 = 150;
    int target3 = (int) (distance3 * trRoueParMm);



    /*----------------------------------------------------------------------------------------------
                                 Variables pour la vitesse des moteurs :
    ----------------------------------------------------------------------------------------------*/
    int vitesseMax = 150; // Standars, un moteur rev fait du 300 Tr/ min au max,
    double vitesseMaxSec = vitesseMax / 60;
    double TPS = vitesseMaxSec * trRoueParMm; // meilleur velocité possible

    /*----------------------------------------------------------------------------------------------
                                           Variables Launchers :
    ----------------------------------------------------------------------------------------------*/
    double motorSpeed = 1;
    Launcher launch = new Launcher();

    @Override
    public void init() {
        telemetry.addData("Team Number",teamNumber);
        telemetry.addData("Team Name",teamName);
        telemetry.addData("Alliance :",alliance);
        telemetry.addData("Circonférence de la roue", circonferenceRoue);
        telemetry.addData("nombre de tour de roue",nbTourRoue);
        telemetry.addData("coef de conversion", trRoueParMm);

        telemetry.addData("Target 1 en ticks :", target1);

        gamepad1.setLedColor(255,0,0, Gamepad.LED_DURATION_CONTINUOUS);

        leftmotor = hardwareMap.get(DcMotorEx.class, "moteur_gauche");
        rightmotor = hardwareMap.get(DcMotorEx.class,"moteur_droit");

        rightmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.update();
    }


    /*----------------------------------------------------------------------------------------------
                           Boucles des différents target ou spin :
    ----------------------------------------------------------------------------------------------*/
    public void target1() {
        // Première distance, sortir de la zone de lancement haute vers la basse
        leftmotor.setTargetPosition(target1);
        rightmotor.setTargetPosition(target1);

        leftmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftmotor.setVelocity(TPS);
        rightmotor.setVelocity(TPS);
    }

    public void spin1(){
        leftmotor.setTargetPosition(-spin1);
        rightmotor.setTargetPosition(spin1);

        leftmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftmotor.setVelocity(TPS);
        rightmotor.setVelocity(TPS);
    }

    public void spin2(){
        leftmotor.setTargetPosition(spin2);
        rightmotor.setTargetPosition(-spin2);

        leftmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftmotor.setVelocity(TPS);
        rightmotor.setVelocity(TPS);
    }

    public void target2(){
        leftmotor.setTargetPosition(target2);
        rightmotor.setTargetPosition(target2);

        leftmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftmotor.setVelocity(TPS);
        rightmotor.setVelocity(TPS);
    }


    // ======= Boucles d'actions =======
    public void launch() {

    }


    /*----------------------------------------------------------------------------------------------
                              Boucle de reset des encodeurs entre chaque utilisation:
    ----------------------------------------------------------------------------------------------*/
    public void reset(){
        rightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /*----------------------------------------------------------------------------------------------
                               Boucles d'action effectué physiquement par le robot:
    ----------------------------------------------------------------------------------------------*/
    @Override
    public void start(){
        target1();
        reset();

        spin1();
        reset();

        launch.launch(motorSpeed);

        spin2();
        reset();

        target2();
        reset();


    }

    @Override
    public void stop() {

        super.stop();

        leftmotor.setVelocity(0);   // stop les deplacement du robot de manière sécu
        rightmotor.setVelocity(0);

    }

    @Override
    public void loop() {

    }

}
