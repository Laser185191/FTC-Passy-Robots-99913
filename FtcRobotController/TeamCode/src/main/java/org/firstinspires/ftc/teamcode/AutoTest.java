package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;


@Autonomous
public class AutoTest extends LinearOpMode { // permet de faire tourner le code une seule fois
    int teamNumber = 99913;
    String teamName = "Passy-Robots";
    String alliance = "RED";

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

    private DcMotor leftmotor; // DcmotorEx, permet l'accès a la fonction velocite
    private DcMotor rightmotor;

    private DcMotor launchMotor;

    private int leftPos ;
    private int rightPos ;

    /*----------------------------------------------------------------------------------------------
                                 Importation des classes pour lancer, et ou prendre les balles :
    ----------------------------------------------------------------------------------------------*/



    @Override
    public void runOpMode() {

        /*==========================================================================================

                Iniatilisation et Information

        ==========================================================================================*/
        telemetry.addData("Team Number",teamNumber);
        telemetry.addData("Team Name",teamName);
        telemetry.addData("Alliance :",alliance);
        telemetry.addData("Circonférence de la roue", circonferenceRoue);
        telemetry.addData("nombre de tour de roue",nbTourRoue);
        telemetry.addData("coef de conversion", trRoueParMm);


        gamepad1.setLedColor(255,0,0, Gamepad.LED_DURATION_CONTINUOUS);

        leftmotor = hardwareMap.get(DcMotor.class, "moteur_gauche");
        rightmotor = hardwareMap.get(DcMotor.class,"moteur_droit");
        launchMotor = hardwareMap.get(DcMotor.class,"moteur-lanceur");

        rightmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftPos = 0;
        rightPos = 0;

        telemetry.update();

        waitForStart();

    /*==============================================================================================

                                    Action du robot :

    ==============================================================================================*/
        drive(1000,1000, 0.5);// on rentre ici la distance gauche, la droite et la vitesse :
        drive(720, -720, 0.45); // devrait faire tourner a gauche

        


    }

    /*==============================================================================================

                            Fonction de déplacement du robots

    ==============================================================================================*/
    private void drive(int leftDistance, int rightDistance, double power) {

        leftPos += (int) (leftDistance * trRoueParMm);
        rightPos += (int) (rightDistance * trRoueParMm);  // permet déviter la rénitialisation des encodeurs a pres chaque manip... en ajoutant ainsi, la position actuelle a la précédente.

        leftmotor.setTargetPosition(leftPos);
        rightmotor.setTargetPosition(rightPos);

        leftmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftmotor.setPower(power);
        rightmotor.setPower(power);


        while(opModeIsActive() && leftmotor.isBusy() && rightmotor.isBusy()) {
            idle();
            telemetry.addData("distance moteur gauche mm : ",leftDistance); // si probleme enlever ces 2 lignes
            telemetry.addData("distance moteur droit mm", rightDistance);
        }
    }

// attention renvoie une erreure a la fin

    private void launch(double motorLaunchSpeed, double angleLancer){



        launchMotor.setPower(motorLaunchSpeed);


        while (opModeIsActive() && launchMotor.isBusy()){
            idle();
        }
    }

}

