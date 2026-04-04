package com.example.methodsordenationanimation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Principal extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button[] vet;
    private static final int qtdeElem = 16;

    // Arrays para o depurador de código
    private Label[] linhasCodigoUI;
    private final String[] codigoFonte = {
            "0. public void bucketSort(int[] vet) {",
            "1.     List<List<Integer>> buckets = new ArrayList<>();",
            "2.     for (int i = 0; i < 10; i++) buckets.add(new ArrayList<>());",
            "3. ",
            "4.     // Espalhar os numeros em seus baldes",
            "5.     for (int i = 0; i < vet.length; i++) {",
            "6.         int indexBalde = vet[i] / 10;",
            "7.         buckets.get(indexBalde).add(vet[i]);",
            "8.     }",
            "9. ",
            "10.    // Ordenar internamente nos baldes",
            "11.    for (int i = 0; i < 10; i++) {",
            "12.        Collections.sort(buckets.get(i));",
            "13.    }",
            "14. ",
            "15.    // Volta os numeros para o vetor ordenado",
            "16.    int k = 0;",
            "17.    for (int i = 0; i < 10; i++) {",
            "18.        for (int j = 0; j < buckets.get(i).size(); j++) {",
            "19.            vet[k++] = buckets.get(i).get(j);",
            "20.        }",
            "21.    }",
            "22. }"
    };

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao - Bucket Sort Visual");
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #f4f4f4;");

        botao_inicio = new Button("Iniciar");
        botao_inicio.setLayoutX(10);
        botao_inicio.setLayoutY(20);
        botao_inicio.setStyle("-fx-base: #2ecc71;");
        botao_inicio.setOnAction(e -> {
            botao_inicio.setDisable(true);
            iniciaBucketSort();
        });

        pane.getChildren().add(botao_inicio);

        // Criação Visual dos Baldes
        for (int i = 0; i < 10; i++) {
            VBox bucketBg = new VBox(5);
            bucketBg.setLayoutX(20 + (i * 75));
            bucketBg.setLayoutY(300);
            bucketBg.setMinWidth(60);
            bucketBg.setMinHeight(220);
            bucketBg.setStyle("-fx-border-color: #7f8c8d; -fx-border-width: 2; -fx-background-color: #ecf0f1; -fx-padding: 5;");

            Label labelBalde = new Label("Balde " + i);
            labelBalde.setFont(new Font("System Bold", 11));
            bucketBg.getChildren().add(labelBalde);

            pane.getChildren().add(bucketBg);
        }

        // Geração aleatória e criação dos botões
        vet = new Button[qtdeElem];
        Random rand = new Random();

        for (int i = 0; i < qtdeElem; i++) {
            int valor = rand.nextInt(100);
            vet[i] = new Button(String.valueOf(valor));
            vet[i].setLayoutX(20 + (i * 65));
            vet[i].setLayoutY(100);
            vet[i].setMinHeight(40);
            vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            vet[i].setStyle("-fx-base: #3498db; -fx-text-fill: white;");
            pane.getChildren().add(vet[i]);
        }

        //Montagem do Painel de Código, famoso debug
        VBox painelCodigo = new VBox(2);
        painelCodigo.setLayoutX(1200);
        painelCodigo.setLayoutY(50);
        painelCodigo.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15; -fx-background-radius: 5;");

        linhasCodigoUI = new Label[codigoFonte.length];
        for (int i = 0; i < codigoFonte.length; i++) {
            linhasCodigoUI[i] = new Label(codigoFonte[i]);
            linhasCodigoUI[i].setFont(Font.font("Monospaced", 13));
            linhasCodigoUI[i].setStyle("-fx-text-fill: #ecf0f1; -fx-padding: 2 5 2 5;");
            linhasCodigoUI[i].setPrefWidth(550);
            linhasCodigoUI[i].setMinWidth(550);

            painelCodigo.getChildren().add(linhasCodigoUI[i]);
        }
        pane.getChildren().add(painelCodigo);

        Scene scene = new Scene(pane, 1400, 600); // Tamanho da interface
        stage.setScene(scene);
        stage.show();
    }

    public void iniciaBucketSort() {
        Thread thread = new Thread(() -> {
            try {
                executarLogicaBucketSort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    // Função para a animação do bucket sort
    private void executarLogicaBucketSort() throws Exception {
        destacarLinhaCodigo(1);
        Thread.sleep(500);
        destacarLinhaCodigo(2);

        List<List<Button>> bucketsDados = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketsDados.add(new ArrayList<>());
        }

        // Distribuir nos baldes os elementos do vetor
        destacarLinhaCodigo(5);
        for (int i = 0; i < qtdeElem; i++) {
            Button btn = vet[i];
            int valor = Integer.parseInt(btn.getText());

            destacarLinhaCodigo(6);
            int indexBalde = valor / 10;

            destacarLinhaCodigo(7);
            bucketsDados.get(indexBalde).add(btn);

            Platform.runLater(() -> {
                btn.setStyle("-fx-base: #e67e22; -fx-text-fill: white;");
                btn.toFront();
            });

            // Faz os cálculos apra mover em X,Y os elementos em seus respctivos baldes
            double destinoX = 20 + (indexBalde * 75) + 8;
            int qtdNoBalde = bucketsDados.get(indexBalde).size() - 1;
            double destinoY = 330 + (qtdNoBalde * 45);

            moverBotao(btn, destinoX, destinoY);
            Thread.sleep(300);
            destacarLinhaCodigo(5);
        }

        Thread.sleep(800);

        // Ordeno cada balde, ate que todos estejam ordenados
        destacarLinhaCodigo(11);
        for (int i = 0; i < 10; i++) {
            List<Button> balde = bucketsDados.get(i);

            if(balde.size() > 1) {
                destacarLinhaCodigo(12);
                balde.sort((b1, b2) -> {
                    int v1 = Integer.parseInt(b1.getText());
                    int v2 = Integer.parseInt(b2.getText());
                    return Integer.compare(v1, v2);
                });

                for (int j = 0; j < balde.size(); j++) {
                    Button btn = balde.get(j);
                    double novoY = 330 + (j * 45);
                    moverBotao(btn, btn.getLayoutX(), novoY);
                }
                Thread.sleep(500);
            }
            destacarLinhaCodigo(11);
        }

        Thread.sleep(800);

        // Aqui faço o gather, o qual, coleta de volta os números do vetor para o vetor ordenado
        destacarLinhaCodigo(16);
        int indexAtualVetor = 0;

        destacarLinhaCodigo(17);
        for (int i = 0; i < 10; i++) {

            if (!bucketsDados.get(i).isEmpty())
                destacarLinhaCodigo(18);

            for (Button btn : bucketsDados.get(i)) {
                destacarLinhaCodigo(19);
                double destinoX = 20 + (indexAtualVetor * 65);
                double destinoY = 100;

                Platform.runLater(() -> btn.setStyle("-fx-base: #2ecc71; -fx-text-fill: white;"));

                moverBotao(btn, destinoX, destinoY);

                vet[indexAtualVetor] = btn;
                indexAtualVetor++;

                Thread.sleep(250);
            }
        }

        destacarLinhaCodigo(-1);
        Platform.runLater(() -> botao_inicio.setDisable(false));
    }

    // ------------ MÉTODOS AUXILIARES -----------------------
    private void destacarLinhaCodigo(int indice) {
        Platform.runLater(() -> {
            for (int i = 0; i < linhasCodigoUI.length; i++) {
                if (i == indice)
                    linhasCodigoUI[i].setStyle("-fx-background-color: #f1c40f; -fx-text-fill: #2c3e50; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");

                else
                    linhasCodigoUI[i].setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-padding: 2 5 2 5;");
            }
        });
    }

    private void moverBotao(Button btn, double destinoX, double destinoY) throws InterruptedException {
        double inicioX = btn.getLayoutX();
        double inicioY = btn.getLayoutY();

        int passos = 20;
        double passoX = (destinoX - inicioX) / passos;
        double passoY = (destinoY - inicioY) / passos;

        for (int i = 0; i < passos; i++) {
            Platform.runLater(() -> {
                btn.setLayoutX(btn.getLayoutX() + passoX);
                btn.setLayoutY(btn.getLayoutY() + passoY);
            });
            Thread.sleep(15);
        }

        Platform.runLater(() -> {
            btn.setLayoutX(destinoX);
            btn.setLayoutY(destinoY);
        });
    }
}