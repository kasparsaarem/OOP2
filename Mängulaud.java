package Rühmatöö2;


// kivi-paber-käärid

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Random;

public class Mängulaud extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Esimese akna sündmused

        FlowPane pane = new FlowPane(10, 10);
        pane.setAlignment(Pos.CENTER);

        Label alustamine = new Label("Tere! Kas soovid hakata mängima või vaadata skoore?");
        alustamine.setFont(new Font(15.0));
        Button mäng = new Button("MÄNGIMA");
        Button skoor = new Button("SKOORE VAATAMA");
        pane.getChildren().addAll(mäng, skoor);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(alustamine, pane);

        Scene peastseen = new Scene(vbox);
        primaryStage.setTitle("Kivi-paber-käärid");
        primaryStage.setScene(peastseen);


        //Aknasündmus kui klikitakse x (exit).

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Stage lõpetamine = new Stage();
                Label headaega = new Label("Olgu siis nii! Järgmise korrani!");
                headaega.setFont(new Font(15.0));
                Button ok = new Button("OK!");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        lõpetamine.hide();
                    }
                });

                VBox lõpp = new VBox(10);
                lõpp.setAlignment(Pos.CENTER);
                lõpp.getChildren().addAll(headaega, ok);
                Scene lõpustseen = new Scene(lõpp);
                lõpetamine.setScene(lõpustseen);
                lõpetamine.setTitle("Kivi-paber-käärid");

                lõpetamine.show();
            }
        });

        //Aknasündmus kui tahetakse trips-traps-trulli mängida. (Klikitakse MÄNGIMA)
        mäng.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // Esmalt küsitakse mängija täisnimi. Selle abil saab pidada skoori kohta statistikat.

                Stage vaheküsimus = new Stage();
                VBox küsimuseAken = new VBox(10);
                küsimuseAken.setAlignment(Pos.CENTER);
                Label instruktsioon = new Label("Palun sisesta oma ees- ja perekonnanimi ning seejärel vajuta ENTER.");
                instruktsioon.setFont(new Font(15.0));
                TextField nimi = new TextField();
                küsimuseAken.getChildren().addAll(instruktsioon, nimi);

                // Mängija nimi ehk sisestatud nimi. Selle abil pannakse faili tulemus või kui nimi on juba failis, siis uuendatakse tulemust.
                String mängijaNimi = nimi.getText();



                vaheküsimus.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        vaheküsimus.hide();
                    }
                });

                nimi.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {

                        // Kui peale nime sisestamist vajutab kasutaja ENTER, siis saab mängima hakata. Muidu ei saa.
                        // Kui nime lahter jäetakse tühjaks, siis võtab programm seda kui tundmatut mängijat ja kirja pannakse tundmatu mängija win streak.


                        if (event.getCode().equals(KeyCode.ENTER)) {

                            Stage mängulaud = new Stage();
                            Label juhised = new Label("Alustame mänguga! Tee oma valik!");
                            juhised.setFont(new Font(15.0));
                            Button kivi = new Button("KIVI");
                            Button paber = new Button("PABER");
                            Button käärid = new Button("KÄÄRID");
                            FlowPane lauaNupud = new FlowPane(10, 10);
                            lauaNupud.setAlignment(Pos.CENTER);
                            lauaNupud.getChildren().addAll(kivi, paber, käärid);

                            VBox laud = new VBox(10);
                            laud.setAlignment(Pos.CENTER);
                            laud.getChildren().addAll(juhised, lauaNupud);


                            // Kasutaja valikutele reageerimine


                            // kivi valiku puhul

                            kivi.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    // Kasutaja mängib arvuti vastu. Arvuti valib ka kolmest variandist ühe.

                                    String[] variandid = {"KIVI", "PABER", "KÄÄRID"};
                                    Random suvaline = new Random();
                                    int valik = suvaline.nextInt(variandid.length);
                                    String arvutiValik = variandid[valik];

                                    Stage tulemus = new Stage();
                                    Button ok = new Button("OK");
                                    Label võit = new Label("Arvuti valis käärid. Palju õnne! Seekord võitsid!");
                                    võit.setFont(new Font(15.0));
                                    Label viik = new Label("Arvuti valis kivi. Jäite seekord viiki!");
                                    viik.setFont(new Font(15.0));
                                    Label kaotus = new Label("Arvuti valis paberi. Seekord kaotasid. Ehk õnnestub järgmine kord..");
                                    kaotus.setFont(new Font(15.0));
                                    VBox vastus = new VBox(10);
                                    vastus.setAlignment(Pos.CENTER);
                                    if (arvutiValik.equals("KÄÄRID")) {
                                        vastus.getChildren().addAll(võit, ok);
                                    }
                                    if (arvutiValik.equals("KIVI")) {
                                        vastus.getChildren().addAll(viik, ok);
                                    }
                                    if (arvutiValik.equals("PABER")) {
                                        vastus.getChildren().addAll(kaotus, ok);
                                    }

                                    // ok või exit klikates saab alustada uuesti mängimist, seni kuni mängija sulgeb akna x-ist.

                                    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            tulemus.hide();

                                        }
                                    });

                                    tulemus.setOnHiding(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            tulemus.hide();
                                            vaheküsimus.hide();

                                        }
                                    });

                                    Scene tulemuseStseen = new Scene(vastus);
                                    tulemus.setTitle("Kivi-paber-käärid");
                                    tulemus.setScene(tulemuseStseen);
                                    tulemus.show();
                                }
                            });

                            // paberi valiku puhul

                            paber.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    // Kasutaja mängib arvuti vastu. Arvuti valib ka kolmest variandist ühe.

                                    String[] variandid = {"KIVI", "PABER", "KÄÄRID"};
                                    Random suvaline = new Random();
                                    int valik = suvaline.nextInt(variandid.length);
                                    String arvutiValik = variandid[valik];

                                    Stage tulemus = new Stage();
                                    Button ok = new Button("OK");
                                    Label võit = new Label("Arvuti valis kivi. Palju õnne! Seekord võitsid!");
                                    võit.setFont(new Font(15.0));
                                    Label viik = new Label("Arvuti valis paberi. Jäite seekord viiki!");
                                    viik.setFont(new Font(15.0));
                                    Label kaotus = new Label("Arvuti valis käärid. Seekord kaotasid. Ehk õnnestub järgmine kord..");
                                    kaotus.setFont(new Font(15.0));
                                    VBox vastus = new VBox(10);
                                    vastus.setAlignment(Pos.CENTER);
                                    if (arvutiValik.equals("KÄÄRID")) {
                                        vastus.getChildren().addAll(kaotus, ok);

                                    }
                                    if (arvutiValik.equals("KIVI")) {
                                        vastus.getChildren().addAll(võit, ok);
                                    }
                                    if (arvutiValik.equals("PABER")) {
                                        vastus.getChildren().addAll(viik, ok);
                                    }

                                    // ok või exit klikates saab alustada uuesti mängimist, seni kuni mängija sulgeb akna x-ist.

                                    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            tulemus.hide();

                                        }
                                    });

                                    tulemus.setOnHiding(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            tulemus.hide();
                                            vaheküsimus.hide();
                                        }
                                    });

                                    Scene tulemuseStseen = new Scene(vastus);
                                    tulemus.setTitle("Kivi-paber-käärid");
                                    tulemus.setScene(tulemuseStseen);
                                    tulemus.show();
                                }
                            });

                            //kääride valiku puhul

                            käärid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    // Kasutaja mängib arvuti vastu. Arvuti valib ka kolmest variandist ühe.

                                    String[] variandid = {"KIVI", "PABER", "KÄÄRID"};
                                    Random suvaline = new Random();
                                    int valik = suvaline.nextInt(variandid.length);
                                    String arvutiValik = variandid[valik];

                                    Stage tulemus = new Stage();
                                    Button ok = new Button("OK");
                                    Label võit = new Label("Arvuti valis paberi. Palju õnne! Seekord võitsid!");
                                    võit.setFont(new Font(15.0));
                                    Label viik = new Label("Arvuti valis käärid. Jäite seekord viiki!");
                                    viik.setFont(new Font(15.0));
                                    Label kaotus = new Label("Arvuti valis kivi. Seekord kaotasid. Ehk õnnestub järgmine kord..");
                                    kaotus.setFont(new Font(15.0));
                                    VBox vastus = new VBox(10);
                                    vastus.setAlignment(Pos.CENTER);
                                    if (arvutiValik.equals("KÄÄRID")) {
                                        vastus.getChildren().addAll(viik, ok);

                                    }
                                    if (arvutiValik.equals("KIVI")) {
                                        vastus.getChildren().addAll(kaotus, ok);
                                    }
                                    if (arvutiValik.equals("PABER")) {
                                        vastus.getChildren().addAll(võit, ok);
                                    }

                                    // ok või exit klikates saab alustada uuesti mängimist, seni kuni mängija sulgeb akna x-ist.

                                    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            tulemus.hide();

                                        }
                                    });

                                    tulemus.setOnHiding(new EventHandler<WindowEvent>() {
                                        @Override
                                        public void handle(WindowEvent event) {
                                            tulemus.hide();
                                            vaheküsimus.hide();
                                        }
                                    });

                                    Scene tulemuseStseen = new Scene(vastus);
                                    tulemus.setTitle("Kivi-paber-käärid");
                                    tulemus.setScene(tulemuseStseen);
                                    tulemus.show();
                                }
                            });


                            Scene lauaStseen = new Scene(laud);
                            mängulaud.setScene(lauaStseen);
                            mängulaud.setTitle("Kivi-paber-käärid");
                            mängulaud.show();
                        }
                    }
                });

                Scene vaheStseen = new Scene(küsimuseAken);
                vaheküsimus.setScene(vaheStseen);
                vaheküsimus.setTitle("Kivi-paber-käärid");
                vaheküsimus.show();
            }

        });


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
