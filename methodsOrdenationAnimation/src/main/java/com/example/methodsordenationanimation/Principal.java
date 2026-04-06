package com.example.methodsordenationanimation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.Random;

public class Principal extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button[] vet;
    private static final int qtdeElem = 16;

    private Label[] linhasCodigoUI;
    private ScrollPane scrollCodigo;
    private final String[] codigoFonte = {
            "0.  private void executarLogicaBucketSort() throws Exception {",
            "1.      Thread.sleep(500);",
            "2. ",
            "3.      Button[][] bucketsDados = new Button[10][qtdeElem];",
            "4.      int[] qtdNoBalde = new int[10];",
            "5. ",
            "6.      // Distribuir nos baldes os elementos do vetor",
            "7.      for (int i = 0; i < qtdeElem; i++) {",
            "8.          Button btn = vet[i];",
            "9.          int valor = Integer.parseInt(btn.getText());",
            "10.         int indexBalde = valor / 10;",
            "11.         int posicaoInsercao = qtdNoBalde[indexBalde];",
            "12.         bucketsDados[indexBalde][posicaoInsercao] = btn;",
            "13.         qtdNoBalde[indexBalde]++;",
            "14. ",
            "15.         Platform.runLater(() -> {",
            "16.             btn.setStyle(\"-fx-base: #e67e22; -fx-text-fill: white;\");",
            "17.             btn.toFront();",
            "18.         });",
            "19. ",
            "20.         // Faz os calculos para mover em X,Y os elementos",
            "21.         double destinoX = 20 + (indexBalde * 75) + 8;",
            "22.         double destinoY = 330 + (posicaoInsercao * 45);",
            "23. ",
            "24.         moverBotao(btn, destinoX, destinoY);",
            "25.         Thread.sleep(300);",
            "26.     }",
            "27. ",
            "28.     Thread.sleep(800);",
            "29. ",
            "30.     // Ordeno cada balde, ate que todos estejam ordenados",
            "31.     for (int i = 0; i < 10; i++) {",
            "32.         int tamanhoBalde = qtdNoBalde[i];",
            "33. ",
            "34.         if(tamanhoBalde > 1) {",
            "35.             java.util.Arrays.sort(bucketsDados[i], 0, tamanhoBalde, (b1, b2) -> {",
            "36.                 int v1 = Integer.parseInt(b1.getText());",
            "37.                 int v2 = Integer.parseInt(b2.getText());",
            "38.                 return Integer.compare(v1, v2);",
            "39.             });",
            "40. ",
            "41.             for (int j = 0; j < tamanhoBalde; j++) {",
            "42.                 Button btn = bucketsDados[i][j];",
            "43.                 double novoY = 330 + (j * 45);",
            "44.                 moverBotao(btn, btn.getLayoutX(), novoY);",
            "45.             }",
            "46.             Thread.sleep(500);",
            "47.         }",
            "48.     }",
            "49. ",
            "50.     Thread.sleep(800);",
            "51. ",
            "52.     // Aqui faco o gather (coleta de volta os numeros)",
            "53.     int indexAtualVetor = 0;",
            "54. ",
            "55.     for (int i = 0; i < 10; i++) {",
            "56.         int tamanhoBalde = qtdNoBalde[i];",
            "57. ",
            "58.         for (int j = 0; j < tamanhoBalde; j++) {",
            "59.             Button btn = bucketsDados[i][j];",
            "60.             double destinoX = 20 + (indexAtualVetor * 65);",
            "61.             double destinoY = 100;",
            "62. ",
            "63.             Platform.runLater(() -> btn.setStyle(\"-fx-base: #2ecc71; -fx-text-fill: white;\"));",
            "64. ",
            "65.             moverBotao(btn, destinoX, destinoY);",
            "66. ",
            "67.             vet[indexAtualVetor] = btn;",
            "68.             indexAtualVetor++;",
            "69. ",
            "70.             Thread.sleep(250);",
            "71.         }",
            "72.     }",
            "73. ",
            "74.     Platform.runLater(() -> botao_inicio.setDisable(false));",
            "75. }"
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
        painelCodigo.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15;");

        linhasCodigoUI = new Label[codigoFonte.length];
        for (int i = 0; i < codigoFonte.length; i++) {
            linhasCodigoUI[i] = new Label(codigoFonte[i]);
            linhasCodigoUI[i].setFont(Font.font("Monospaced", 13));
            linhasCodigoUI[i].setStyle("-fx-text-fill: #ecf0f1; -fx-padding: 2 5 2 5;");
            linhasCodigoUI[i].setPrefWidth(550);
            linhasCodigoUI[i].setMinWidth(550);

            painelCodigo.getChildren().add(linhasCodigoUI[i]);
        }

        // Aqui cria a barra de rolagem
        scrollCodigo = new ScrollPane();
        scrollCodigo.setContent(painelCodigo);
        scrollCodigo.setLayoutX(1200);
        scrollCodigo.setLayoutY(20);
        scrollCodigo.setPrefSize(580, 980);

        // Remove as bordas feias padrão do ScrollPane e deixa fundo escuro
        scrollCodigo.setStyle("-fx-background: #2c3e50; -fx-border-color: #2c3e50;");
        scrollCodigo.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Tira a rolagem horizontal
        scrollCodigo.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Força a rolagem vertical

        // Adiciona o scrollPane no painel principal em vez do VBox
        pane.getChildren().add(scrollCodigo);

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
        destacarLinhaCodigo(0);
        Thread.sleep(500);

        destacarLinhaCodigo(3);
        Button[][] bucketsDados = new Button[10][qtdeElem];
        int[] qtdNoBalde = new int[10]; // Controla quantos itens existem em cada balde

        destacarLinhaCodigo(4);

        // Distribuir nos baldes os elementos do vetor
        for (int i = 0; i < qtdeElem; i++) {
            destacarLinhaCodigo(9);
            Button btn = vet[i];
            int valor = Integer.parseInt(btn.getText());
            int indexBalde = valor / 10;

            destacarLinhaCodigo(13); // Adicionando o elemento ao balde correto
            int posicaoInsercao = qtdNoBalde[indexBalde];
            bucketsDados[indexBalde][posicaoInsercao] = btn;
            qtdNoBalde[indexBalde]++; // Incrementa a quantidade neste balde

            destacarLinhaCodigo(15);

            // Faz os cálculos para mover em X,Y os elementos em seus respectivos baldes
            double destinoX = 20 + (indexBalde * 75) + 8;
            double destinoY = 330 + (posicaoInsercao * 45);

            destacarLinhaCodigo(25); // Executando a animação de mover
            moverBotao(btn, destinoX, destinoY);
            Thread.sleep(300);
        }

        destacarLinhaCodigo(29);
        Thread.sleep(800);

        // Ordena internamente cada balde
        for (int i = 0; i < 10; i++) {
            destacarLinhaCodigo(32); // Passando por cada balde
            int tamanhoBalde = qtdNoBalde[i];

            if(tamanhoBalde > 1) {
                destacarLinhaCodigo(36); // Lógica de ordenação
                java.util.Arrays.sort(bucketsDados[i], 0, tamanhoBalde, (b1, b2) -> {
                    int v1 = Integer.parseInt(b1.getText());
                    int v2 = Integer.parseInt(b2.getText());
                    return Integer.compare(v1, v2);
                });

                destacarLinhaCodigo(42);
                for (int j = 0; j < tamanhoBalde; j++) {
                    Button btn = bucketsDados[i][j];
                    double novoY = 330 + (j * 45);

                    destacarLinhaCodigo(45); // Movendo o botão na tela
                    moverBotao(btn, btn.getLayoutX(), novoY);
                }
                Thread.sleep(500);
            }
        }

        destacarLinhaCodigo(51);
        Thread.sleep(800);

        // Coleta de volta os números do vetor
        destacarLinhaCodigo(54);
        int indexAtualVetor = 0;

        for (int i = 0; i < 10; i++) {
            destacarLinhaCodigo(56); // For externo percorrendo os 10 baldes
            int tamanhoBalde = qtdNoBalde[i];

            for (int j = 0; j < tamanhoBalde; j++) {
                destacarLinhaCodigo(57); // For interno tirando do balde
                Button btn = bucketsDados[i][j];

                double destinoX = 20 + (indexAtualVetor * 65);
                double destinoY = 100;

                destacarLinhaCodigo(61);
                Platform.runLater(() -> btn.setStyle("-fx-base: #2ecc71; -fx-text-fill: white;"));

                destacarLinhaCodigo(63); // Movendo de volta
                moverBotao(btn, destinoX, destinoY);

                destacarLinhaCodigo(65); // Atualizando o vetor
                vet[indexAtualVetor] = btn;
                indexAtualVetor++;

                Thread.sleep(250);
            }
        }

        destacarLinhaCodigo(72); // Fim da execução e liberação do botão
        Platform.runLater(() -> botao_inicio.setDisable(false));
        Thread.sleep(1000);
        destacarLinhaCodigo(-1);
    }

    // ------------ MÉTODOS AUXILIARES -----------------------
    private void destacarLinhaCodigo(int indice) {
        Platform.runLater(() -> {
            for (int i = 0; i < linhasCodigoUI.length; i++) {
                if (i == indice) {
                    linhasCodigoUI[i].setStyle("-fx-background-color: #f1c40f; -fx-text-fill: #2c3e50; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");

                    // Calcula a posição percentual da linha atual
                    double posicaoRolagem = (double) i / (linhasCodigoUI.length - 1);
                    scrollCodigo.setVvalue(posicaoRolagem); // Move a barra de rolagem para aquela posição

                } else {
                    linhasCodigoUI[i].setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-padding: 2 5 2 5;");
                }
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