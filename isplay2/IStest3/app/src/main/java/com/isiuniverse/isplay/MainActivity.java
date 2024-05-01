package com.isiuniverse.isplay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.*;

import java.util.Date;
import java.lang.String;

import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    //criando as variaveis responsaveis por conectar-se com os items da Activity
    //declarando os atributos onde vamos ligar com os campos de entrada de dados
    EditText PtUm, PtDois, PtTres, PtQuatro, PtCinco, PtSeis;
    //--------------------------------------------------------------------------------------------------
    //declarando os atributos onde ligaremos com a saída(exibir) os dados
    TextView TvLista;
    TextView TvR, TvN, TvR_2, TvN_2, TvR_3, TvN_3, TvR_4, TvN_4, TvR_5, TvN_5, TvR_6, TvN_6,
            TvR_7, TvN_7, TvR_8, TvN_8, TvR_9, TvN_9, TvR_10, TvN_10, TvR_11, TvN_11, TvR_12, TvN_12,
            TvR_13, TvN_13, TvR_14, TvN_14, TvR_15, TvN_15, tvTexte, tvTexte2;
    //--------------------------------------------------------------------------------------------------
    //declarando os atributos necessários para armazenar e manipular os dados de entrada e saída
    //Array responsavel por armazenar todos os números e relacionalos as vezer que ele repetiu
    int Numerosfixo[][] = new int[61][2];
    //Array responsavel por armazenar quantas vezes esse número foi inserido(ele repetiu)
    int[] Contador = new int[61];
    //Array responsavel por armazenar os números inseridos pelo usuários
    int[] NumerosInseridos = new int[6];
    //Atributos declarados para receber diratamente o que foi digitado pelo usuário e depois passar para o Array(NumerosInseridos)
    int n1, n2, n3, n4, n5, n6;
    //Array responsavel por ordenar crescente e exibir os números de repetições dos números de contagem de repetição
    int[] c = new int[15];
    //Array responsavel por ordenar os números que estão entrelados aos números que tem menos repetições
    int[] n = new int[15];

    int NumerosFixosOrdenados[][] = new int[61][2];
    int NFO2[] = new int[61];
    int ArrayBanco[] = new int[61];

    SQLiteDatabase bd = null;


//--------------------------------------------------------------------------------------------------

    //Método que è responsavel por criar os atrbutos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ligando os atributos aos seu devidos individuos na Activity
        //ligando o atributo PtUm a individuo que será responavel por receber o rimeiro Número
        PtUm = findViewById(R.id.PtUm);
        //ligando o atributo ptdois ao individo qeu receberá o segundo número
        PtDois = (EditText) findViewById(R.id.PtDois);
        //ligando o atributo pttres ao individuo que receberá o treseiro número
        PtTres = (EditText) findViewById(R.id.PtTres);
        //ligando o atributo ptquatro ao individuo que receberá o quarto número digitado
        PtQuatro = (EditText) findViewById(R.id.PtQuatro);
        //ligando o atributo ptcinco ao indiniduo que receberá o quinto número
        PtCinco = (EditText) findViewById(R.id.PtCinco);
        //ligando o atributo ptseis ao individuo que receberá o sexto número
        PtSeis = (EditText) findViewById(R.id.PtSeis);

        //liganso o atributo tvteste ao individuo tvtexte
        tvTexte = findViewById(R.id.tvTexte);
        //ligando o atributo tvtexte2 ao individuo tvtexte2
        tvTexte2 = findViewById(R.id.tvTexte2);

        //ligando o atributo tvlista ao individuo tvlista
        TvLista = findViewById(R.id.TvInformativo);

        //ligando todos os atributos ao seus respctivos individuos na activity
        TvR = findViewById(R.id.Tv1);
        TvN = findViewById(R.id.Tv2);
        TvR_2 = findViewById(R.id.Tv1_2);
        TvN_2 = findViewById(R.id.Tv2_2);
        TvR_3 = findViewById(R.id.Tv1_3);
        TvN_3 = findViewById(R.id.Tv2_3);
        TvR_4 = findViewById(R.id.Tv1_4);
        TvN_4 = findViewById(R.id.Tv2_4);
        TvR_5 = findViewById(R.id.Tv1_5);
        TvN_5 = findViewById(R.id.Tv2_5);
        TvR_6 = findViewById(R.id.Tv1_6);
        TvN_6 = findViewById(R.id.Tv2_6);
        TvR_7 = findViewById(R.id.Tv1_7);
        TvN_7 = findViewById(R.id.Tv2_7);
        TvR_8 = findViewById(R.id.Tv1_8);
        TvN_8 = findViewById(R.id.Tv2_8);
        TvR_9 = findViewById(R.id.Tv1_9);
        TvN_9 = findViewById(R.id.Tv2_9);
        TvR_10 = findViewById(R.id.Tv1_10);
        TvN_10 = findViewById(R.id.Tv2_10);
        TvR_11 = findViewById(R.id.Tv1_11);
        TvN_11 = findViewById(R.id.Tv2_11);
        TvR_12 = findViewById(R.id.Tv1_12);
        TvN_12 = findViewById(R.id.Tv2_12);
        TvR_13 = findViewById(R.id.Tv1_13);
        TvN_13 = findViewById(R.id.Tv2_13);
        TvR_14 = findViewById(R.id.Tv1_14);
        TvN_14 = findViewById(R.id.Tv2_14);
        TvR_15 = findViewById(R.id.Tv1_15);
        TvN_15 = findViewById(R.id.Tv2_15);

        CriarOuAbrirBanco();
        CriarOuAbirTabela();
        Fechar();


    }

    //--------------------------------------------------------------------------------------------------
/*
    //metodo responsavel por incrementar os numeros do array de 1 a 60 e chama o método vevificar...
    public void IncrementarNumeros (View v) {
        int a = 0;
        for (int i = 0; i < Numerosfixo.length; i++) {
            a++;
            Numerosfixo[i] = Numerosfixo[i] + a;
             //tvTexte2.setText(tvTexte.getText().toString() +"-"+ String.valueOf(Numeros[i]));
        }
        verificar();

        for(int i = 0; i < Numerosfixo.length ; i++){
            tvTexte2.setText(String.valueOf(tvTexte2.getText().toString() +" - "+Numerosfixo[i]));
        }
    }
*/
//--------------------------------------------------------------------------------------------------
    //Método responsavel por
    public void Inserir(View v) {

        //declarando os atributos responsaveis por receber os números digitados pelo usuário
        String PtUms, PtDoiss, PtTress, PtQuatros, PtCincos, PtSeiss;

        //passando os valores digitados pelo usuário para cada atributo correspondente e os convertendo para String
        PtUms = PtUm.getText().toString();
        PtDoiss = PtDois.getText().toString();
        PtTress = PtTres.getText().toString();
        PtQuatros = PtQuatro.getText().toString();
        PtCincos = PtCinco.getText().toString();
        PtSeiss = PtSeis.getText().toString();

        //passando os valores digitados para os atributos responsáseis por esse número e os convertendo para int
        n1 = Integer.parseInt(PtUms.toString());
        n2 = Integer.parseInt(PtDoiss.toString());
        n3 = Integer.parseInt(PtTress.toString());
        n4 = Integer.parseInt(PtQuatros.toString());
        n5 = Integer.parseInt(PtCincos.toString());
        n6 = Integer.parseInt(PtSeiss.toString());

        //passando os valores dos atributos responsávies por conter os números digitados pelo usuários a cada vetor do array correspontente
        NumerosInseridos[0] = n1;
        NumerosInseridos[1] = n2;
        NumerosInseridos[2] = n3;
        NumerosInseridos[3] = n4;
        NumerosInseridos[4] = n5;
        NumerosInseridos[5] = n6;

        // int controle = 1;
        //   tvTexte.setText("");
        //tvTexte2.setText("");
        //Esse loop verificará se o número que estão em cada vetor do array responsável por conter os números digitados pelo usuário
        //já exitem e se exitirem carecentaremos um a eles
        //repita emquanto j for menor que o tamnha de NI e some mais 1 a caca execução
        //esse loop vai rodar 6 vezes uma para cada posção do array NI


        limpar();
        Preenxer();
        int conta = 0;

        for (int i = 0; i < NumerosInseridos.length; i++) {
            for (int j = 0; j < Numerosfixo.length; j++) {
                if (NumerosInseridos[i] == Numerosfixo[j][0]) {
                    Numerosfixo[j][1]++;
                    conta++;
                }



           /* //esse loop vai rodar 60 vezes uma para cada linha do coluna o no array NF
            for (int i = 1; i < Contador.length; i++) {

                //se o  numero no array NI na posição j(Numero inderido pelo usuario)
                // for igual ao número do array FN na da linha no valor i (que é o valor da repetição do loop )da coluna 0(coluna fixa )
                if (NumerosInseridos[j] == Numerosfixo[i][0]) {
                    //caso ele seja igual vamso somar em 1 o valor da mesma linha mas da segunda coluna
                    Numerosfixo[i][1] = controle++;
                }
                //caso o numero no array NI na posição j(numero inserido pelo usuario) seja diferendo do numero da linha na posição i  colula 0
                if(NumerosInseridos[j] != Numerosfixo[i][1]){
                    Numerosfixo[i][0] = NumerosInseridos[j];
                }
            }*/
            /*if ( NumerosInseridos[j] == j ) {
                if (Numerosfixo[j] == NumerosInseridos[j]) {
                    Numerosfixo[j] = n1;
                }
                Contador[j]++;
                tvTexte.setText(String.valueOf(Contador[j]));
            }*//*
            if (n2 == j) {
                if (Numeros[j] != n2) {
                    Numeros[j] = n2;
                }
                Contador[j]++;


            }
            if (n3 == j) {
                if (Numeros[j] != n3) {
                    Numeros[j] = n3;
                }
                Contador[j]++;

            }
            if (n4 == j) {
                if (Numeros[j] != n4) {
                    Numeros[j] = n4;
                }
                Contador[j]++;

            }
            if (n5 == j) {
                if (Numeros[j] != n5) {
                    Numeros[j] = n5;
                }
                Contador[j]++;

            }
            if (n6 == j) {
                if (Numeros[j] != n6) {
                    Numeros[j] = n6;
                }
                Contador[j]++;

            }*/
            }

        }

        /*for (int i = 0; i < Contador.length; i++) {
            tvTexte.setText(tvTexte.getText().toString() + Numerosfixo[i][0]);
        }
        for (int i = 0; i < Contador.length; i++) {
            tvTexte2.setText(tvTexte2.getText().toString() + Numerosfixo[i][1] + " - ");
        }*/

        for (int i = 0; i < Numerosfixo.length; i++) {
            tvTexte2.setText(tvTexte2.getText().toString() + " - " + Numerosfixo[i][1]);
        }


        Exibir();
    }

    public void Preenxer() {
        tvTexte.setText("");
        int conta2 = 0;

        //Numerosfixo[17][0] = 17;
        for (int i = 0; i < Numerosfixo.length; i++) {
            Numerosfixo[i][0] = i;
            //tvTexte.setText(tvTexte.getText().toString() + " - " + Numerosfixo[i][0]);
        }


    }


    public void limpar() {
        tvTexte2.setText("");

    }

    public void Exibir() {

        Ordenar();

        TvR.setText(String.valueOf(NFO2[1]));
        TvR_2.setText(String.valueOf(NFO2[2]));
        TvR_3.setText(String.valueOf(NFO2[3]));
        TvR_4.setText(String.valueOf(NFO2[4]));
        TvR_5.setText(String.valueOf(NFO2[5]));
        TvR_6.setText(String.valueOf(NFO2[6]));
        TvR_7.setText(String.valueOf(NFO2[7]));
        TvR_8.setText(String.valueOf(NFO2[8]));
        TvR_9.setText(String.valueOf(NFO2[9]));
        TvR_10.setText(String.valueOf(NFO2[10]));
        TvR_11.setText(String.valueOf(NFO2[11]));
        TvR_12.setText(String.valueOf(NFO2[12]));
        TvR_13.setText(String.valueOf(NFO2[13]));
        TvR_14.setText(String.valueOf(NFO2[14]));
        TvR_15.setText(String.valueOf(NFO2[15]));

        for (int i = 1; i < Numerosfixo.length; i++) {
            if (NFO2[1] == Numerosfixo[i][1]) {
                TvN.setText(String.valueOf(Numerosfixo[i][0]));

                for (int j = 1; j < Numerosfixo.length; j++) {
                    if (NFO2[2] == Numerosfixo[j][1] && TvN.getText().toString() == String.valueOf(Numerosfixo[i][0])) {

                        for (int k = j + 1; k < Numerosfixo.length; k++) {
                            if (NFO2[2] == Numerosfixo[k][1]) {
                                TvN_2.setText(String.valueOf(Numerosfixo[k][0]));

                                for (int l = 1; l < Numerosfixo.length; l++) {
                                    if (NFO2[3] == Numerosfixo[l][1] && TvN_2.getText().toString() == String.valueOf(Numerosfixo[k][0])) {

                                        l = k;

                                        for (int m = l + 1; m < Numerosfixo.length; m++) {
                                            if (NFO2[3] == Numerosfixo[m][1]) {
                                                TvN_3.setText(String.valueOf(Numerosfixo[m][0]));


                                                for (int n = 1; n < Numerosfixo.length; n++) {
                                                    if (NFO2[4] == Numerosfixo[n][1] && TvN_3.getText().toString() == String.valueOf(Numerosfixo[m][0])) {

                                                        n = m;

                                                        for (int o = n + 1; o < Numerosfixo.length; o++) {
                                                            if (NFO2[4] == Numerosfixo[o][1]) {
                                                                TvN_4.setText(String.valueOf(Numerosfixo[o][0]));

                                                                for (int p = 1; p < Numerosfixo.length; p++) {
                                                                    if (NFO2[5] == Numerosfixo[p][1] && TvN_4.getText().toString() == String.valueOf(Numerosfixo[o][0])) {

                                                                        p = o;
                                                                        for (int q = p + 1; q < Numerosfixo.length; q++) {
                                                                            if (NFO2[5] == Numerosfixo[q][1]) {
                                                                                TvN_5.setText(String.valueOf(Numerosfixo[q][0]));

                                                                                for (int r = 1; r < Numerosfixo.length; r++) {
                                                                                    if (NFO2[6] == Numerosfixo[r][1] && TvN_5.getText().toString() == String.valueOf(Numerosfixo[q][0])) {

                                                                                        r = q;
                                                                                        for (int s = r + 1; s < Numerosfixo.length; s++) {
                                                                                            if (NFO2[6] == Numerosfixo[s][1]) {
                                                                                                TvN_6.setText(String.valueOf(Numerosfixo[s][0]));

                                                                                                for (int t = 1; t < Numerosfixo.length; t++) {
                                                                                                    if (NFO2[7] == Numerosfixo[t][1] && TvN_6.getText().toString() == String.valueOf(Numerosfixo[s][0])) {

                                                                                                        t = s;
                                                                                                        for (int u = t + 1; u < Numerosfixo.length; u++) {
                                                                                                            if (NFO2[7] == Numerosfixo[u][1]) {

                                                                                                                TvN_7.setText(String.valueOf(Numerosfixo[u][0]));

                                                                                                                for (int v = 1; v < Numerosfixo.length; v++) {
                                                                                                                    if (NFO2[8] == Numerosfixo[v][1] && TvN_7.getText().toString() == String.valueOf(Numerosfixo[u][0])) {

                                                                                                                        v = u;

                                                                                                                        for (int w = v + 1; w < Numerosfixo.length; w++) {
                                                                                                                            if (NFO2[8] == Numerosfixo[w][1]) {
                                                                                                                                TvN_8.setText(String.valueOf(Numerosfixo[w][0]));

                                                                                                                                for (int x = 1; x < Numerosfixo.length; x++) {
                                                                                                                                    if (NFO2[9] == Numerosfixo[x][1] && TvN_8.getText().toString() == String.valueOf(Numerosfixo[w][0])) {

                                                                                                                                        x = w;
                                                                                                                                        for (int y = x + 1; y < Numerosfixo.length; y++) {
                                                                                                                                            if (NFO2[9] == Numerosfixo[y][1]) {
                                                                                                                                                TvN_9.setText(String.valueOf(Numerosfixo[y][0]));

                                                                                                                                                for (int z = 1; z < Numerosfixo.length; z++) {
                                                                                                                                                    if (NFO2[10] == Numerosfixo[z][1] && TvN_9.getText().toString() == String.valueOf(Numerosfixo[y][0])) {

                                                                                                                                                        z = y;
                                                                                                                                                        for (int a = z + 1; a < Numerosfixo.length; a++) {

                                                                                                                                                            if (NFO2[10] == Numerosfixo[a][1]) {

                                                                                                                                                                TvN_10.setText(String.valueOf(Numerosfixo[a][0]));

                                                                                                                                                                for (int b = 1; b < Numerosfixo.length; b++) {
                                                                                                                                                                    if (NFO2[11] == Numerosfixo[b][1] && TvN_10.getText().toString() == String.valueOf(Numerosfixo[a][0])) {

                                                                                                                                                                        b = a;
                                                                                                                                                                        for (int c = b + 1; c < Numerosfixo.length; c++) {
                                                                                                                                                                            if (NFO2[11] == Numerosfixo[c][1]) {

                                                                                                                                                                                TvN_11.setText(String.valueOf(Numerosfixo[c][0]));

                                                                                                                                                                                for (int d = 1; d < Numerosfixo.length; d++) {
                                                                                                                                                                                    if (NFO2[12] == Numerosfixo[d][1] && TvN_11.getText().toString() == String.valueOf(Numerosfixo[c][0])) {

                                                                                                                                                                                        d = c;

                                                                                                                                                                                        for (int e = d + 1; e < Numerosfixo.length; e++) {
                                                                                                                                                                                            if (NFO2[12] == Numerosfixo[e][1]) {

                                                                                                                                                                                                TvN_12.setText(String.valueOf(Numerosfixo[e][0]));

                                                                                                                                                                                                for (int f = 1; f < Numerosfixo.length; f++) {
                                                                                                                                                                                                    if (NFO2[13] == Numerosfixo[f][1] && TvN_12.getText().toString() == String.valueOf(Numerosfixo[e][0])) {

                                                                                                                                                                                                        f = e;
                                                                                                                                                                                                        for (int g = f + 1; g < Numerosfixo.length; g++) {
                                                                                                                                                                                                            if (NFO2[13] == Numerosfixo[g][1]) {

                                                                                                                                                                                                                TvN_13.setText(String.valueOf(Numerosfixo[g][0]));

                                                                                                                                                                                                                for (int h = 1; h < Numerosfixo.length; h++) {
                                                                                                                                                                                                                    if (NFO2[14] == Numerosfixo[h][1] && TvN_13.getText().toString() == String.valueOf(Numerosfixo[g][0])) {

                                                                                                                                                                                                                        h = g;

                                                                                                                                                                                                                        for (int ia = h + 1; ia < Numerosfixo.length; ia++) {
                                                                                                                                                                                                                            if (NFO2[14] == Numerosfixo[ia][1]) {

                                                                                                                                                                                                                                TvN_14.setText(String.valueOf(Numerosfixo[ia][0]));

                                                                                                                                                                                                                                for (int ja = 1; ja < Numerosfixo.length; ja++) {
                                                                                                                                                                                                                                    if (NFO2[15] == Numerosfixo[ja][1] && TvN_14.getText().toString() == String.valueOf(Numerosfixo[ia][0])) {

                                                                                                                                                                                                                                        ja = ia;
                                                                                                                                                                                                                                        for (int ka = ja + 1; ka < Numerosfixo.length; ka++) {
                                                                                                                                                                                                                                            if (NFO2[15] == Numerosfixo[ka][1]) {

                                                                                                                                                                                                                                                TvN_15.setText(String.valueOf(Numerosfixo[ka][0]));

                                                                                                                                                                                                                                                ka = 70;
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }


                                                                                                                                                                                                                                        ja = 70;
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                ia = 70;
                                                                                                                                                                                                                            }


                                                                                                                                                                                                                        }

                                                                                                                                                                                                                        h = 70;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }

                                                                                                                                                                                                                g = 70;
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }

                                                                                                                                                                                                        f = 70;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }

                                                                                                                                                                                                e = 70;
                                                                                                                                                                                            }

                                                                                                                                                                                        }

                                                                                                                                                                                        d = 70;
                                                                                                                                                                                    }
                                                                                                                                                                                }

                                                                                                                                                                                c = 70;
                                                                                                                                                                            }
                                                                                                                                                                        }

                                                                                                                                                                        b = 70;
                                                                                                                                                                    }
                                                                                                                                                                }

                                                                                                                                                                a = 70;
                                                                                                                                                            }

                                                                                                                                                        }

                                                                                                                                                        z = 70;
                                                                                                                                                    }
                                                                                                                                                }

                                                                                                                                                y = 70;
                                                                                                                                            }
                                                                                                                                        }

                                                                                                                                        x = 70;
                                                                                                                                    }
                                                                                                                                }

                                                                                                                                w = 70;
                                                                                                                            }
                                                                                                                        }

                                                                                                                        v = 70;

                                                                                                                    }
                                                                                                                }

                                                                                                                //parando o for
                                                                                                                u = 70;
                                                                                                            }
                                                                                                        }

                                                                                                        //parando o for
                                                                                                        t = 70;
                                                                                                    }
                                                                                                }

                                                                                                s = 70;
                                                                                            }
                                                                                        }

                                                                                        r = 70;
                                                                                    }
                                                                                }

                                                                                q = 70;
                                                                            }


                                                                        }

                                                                        p = 70;

                                                                    }


                                                                }

                                                                o = 70;
                                                            }
                                                            n = 70;
                                                        }
                                                    }
                                                }

                                                m = 70;
                                            }
                                        }

                                        l = 70;
                                    }

                                }


                                k = 70;
                            }


                        }

                        j = 70;
                    }

                }

                i = 70;
            }
        }






        /*TvR_2.setText(String.valueOf(NFO2[59]));
        TvN_2.setText(String.valueOf(Numerosfixo[2][0]));
        TvR_3.setText(String.valueOf(NumerosFixosOrdenados[3][1]));
        TvN_3.setText(String.valueOf(NumerosFixosOrdenados[3][0]));
        TvR_4.setText(String.valueOf(NumerosFixosOrdenados[4][1]));
        TvN_4.setText(String.valueOf(NumerosFixosOrdenados[4][0]));
        TvR_5.setText(String.valueOf(NumerosFixosOrdenados[5][1]));
        TvN_5.setText(String.valueOf(NumerosFixosOrdenados[5][0]));
        TvR_6.setText(String.valueOf(NumerosFixosOrdenados[6][1]));
        TvN_6.setText(String.valueOf(NumerosFixosOrdenados[6][0]));
        TvR_7.setText(String.valueOf(NumerosFixosOrdenados[7][1]));
        TvN_7.setText(String.valueOf(NumerosFixosOrdenados[7][0]));
        TvR_8.setText(String.valueOf(NumerosFixosOrdenados[8][1]));
        TvN_8.setText(String.valueOf(NumerosFixosOrdenados[8][0]));
        TvR_9.setText(String.valueOf(NumerosFixosOrdenados[9][1]));
        TvN_9.setText(String.valueOf(NumerosFixosOrdenados[9][0]));
        TvR_10.setText(String.valueOf(NumerosFixosOrdenados[10][1]));
        TvN_10.setText(String.valueOf(NumerosFixosOrdenados[10][0]));
        TvR_11.setText(String.valueOf(NumerosFixosOrdenados[11][1]));
        TvN_11.setText(String.valueOf(NumerosFixosOrdenados[11][0]));
        TvR_12.setText(String.valueOf(NumerosFixosOrdenados[12][1]));
        TvN_12.setText(String.valueOf(NumerosFixosOrdenados[12][0]));
        TvR_13.setText(String.valueOf(NumerosFixosOrdenados[13][1]));
        TvN_13.setText(String.valueOf(NumerosFixosOrdenados[13][0]));
        TvR_14.setText(String.valueOf(NumerosFixosOrdenados[14][1]));
        TvN_14.setText(String.valueOf(NumerosFixosOrdenados[14][0]));
        TvR_15.setText(String.valueOf(NumerosFixosOrdenados[15][1]));
        TvN_15.setText(String.valueOf(NumerosFixosOrdenados[15][0]));*/

    }


    public void Ordenar() {

        NumerosFixosOrdenados = Numerosfixo;


        for (int i = 0; i < NumerosFixosOrdenados.length; i++) {
            NFO2[i] = NumerosFixosOrdenados[i][1];

        }

        Arrays.sort(NFO2);


        for (int i = 0; i < NFO2.length; i++) {
            tvTexte.setText(tvTexte.getText().toString() + "-" + NFO2[i]);
        }
    }

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /////////////////////////////////////////                    ////////////////////////////////////////
    /////////////////////////////////////////                    ////////////////////////////////////////
    /////////////////////////////////////////                    ////////////////////////////////////////
    /////////////////////////////////////////   BANCO DE DADOS   ////////////////////////////////////////
    /////////////////////////////////////////                    ////////////////////////////////////////
    /////////////////////////////////////////                    ////////////////////////////////////////
    /////////////////////////////////////////                    ////////////////////////////////////////

    //Primeiro vamso criar um metodo para abriri o banco ou criar o banco
    public void CriarOuAbrirBanco() {
        try {
            bd = openOrCreateDatabase("numeros", MODE_PRIVATE, null);
        } catch (Exception ex) {
            mensagens("Ouve um erro ao criar o banco " + ex.getMessage().toString());
        } finally {
            mensagens("Banco ciado com sucesso !!!! ");
        }
    }

    //Criando ou abrindo a tabela
    public void CriarOuAbirTabela() {
        try {
            //criando a tabela no bd
            bd.execSQL("CREATE TABLE IF NOT EXISTS tabelaNumeros (id INTEGER PRIMARY KEY,vesesRepetidas TEXT, numero TEXT)");
        } catch (Exception ex) {
            mensagens("erro ao criar a tebela");
        } finally {
            mensagens("tabela criada com sucesso");
        }
        
    }

    //metodo para fechar o banco
    public void Fechar() { bd.close(); mensagens("banco fechado (; (; (; ");}

    //criando o metodo que vai inserir os dados no banco
    public void InserirRegistros(){
        //criando o arrya que vai se ralcionar com o banco
        ArrayBanco = NFO2;
        //Abrindo o banco
        CriarOuAbrirBanco();
        //trantando qualquer erro
        try{
            bd.execSQL("INSERT INTO tabelaNumeros (vesesRepetidas,numero) VALUES ( )");
        }catch(Exception ex){

        }finally {

        }
    }




    //----------------------------------------------------------------------------------------------------------
    //Mensagens ou alertas

    //metodp para mensagens
    public void mensagens(String txt) {
        //criando o alerta na tela
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        //passando a mensagem que vai aparecer
        msg.setMessage(txt);
        //colcando o nome no botao da mensagem
        msg.setNeutralButton("Entendido", null);
        //pedindo para mostrar na tela
        msg.show();

    }


}


//--------------------------------------------------------------------------------------------------

   /* public void limpar(View v) {
        tvTexte.setText("");
        tvTexte2.setText("");
    }*/

//--------------------------------------------------------------------------------------------------

    /*public void organizarc(View v) {
        Arrays.sort(Contador);//Ordenando do menor para o maior
        for (int i = 0; i < 14; i++) {
            c[i] = Contador[i];
        }

        for(int i = 0; i < Contador.length; i++){
            tvTexte.setText(tvTexte.getText() +" - "+String.valueOf(Contador[i]));
        }
    }*/

   /* public void organizarn(View v) {
        Arrays.sort(Numerosfixo);//Ordenando do menor para o maior
        for (int i = 0; i < 14; i++) {
            n[i] = Numerosfixo[i];
        }
    }*/


//--------------------------------------------------------------------------------------------------

       /* public void Exibir (View v){
            TvR.setText(String.valueOf(Numerosfixo[0]);
            TvN.setText(String.valueOf(n[0]));
            TvR_2.setText(String.valueOf(c[1]));
            TvN_2.setText(String.valueOf(n[1]));
            TvR_3.setText(String.valueOf(c[2]));
            TvN_3.setText(String.valueOf(n[2]));
            TvR_4.setText(String.valueOf(c[3]));
            TvN_4.setText(String.valueOf(n[3]));
            TvR_5.setText(String.valueOf(c[4]));
            TvN_5.setText(String.valueOf(n[4]));
            TvR_6.setText(String.valueOf(c[5]));
            TvN_6.setText(String.valueOf(n[5]));


        }
    }*/

       /*

///////////////////////// codigos sobresablente /////////////////////////////////////

// tvTexte.setText(String.valueOf(n1 + " - " + n2 + "- " + n3 + " - " + n4 + " - " + n5 + " - " + " - " + n6 + "\r\n"));
*         Arrays.sort(Numeros);//Ordenando do menor para o maior
        for (int k = 0; k < Numeros.length; k++) {
            n[k] = Numeros[k];
        }
    }


* */


/*
 * pensamentos
 * para fucionar precisamso declarar um arrar qeu contará quantas vezes esse número já entrou no sistema e entrelçalo ao número que esta
 * sendo repetido
 *
 * vou ter qeufazer um arrya com duas coluna onde uma vai registrar quantas vezes esse número repetiu e na segunda que número esta repetindo
 * se na coluna um posição na pusicao 0 estiver o número 9 na segunda coluna tem que estar o n´rumeo de vezes que ele esta repetindo
 *
 *se no array a estiver a quantidade de veses que um número repetiu e no array b o número que repetiu
 *
 * Array esquemaLogico
 *      Nf  NC
 *      c0  c1
 *  l0  1   -
 *  l1  2   -
 *  l2  3   -
 *  l3  4   -
 *  l3  5   -
 *
 *o usuario digitou o numero 7 quatro vezes como eu relaciona os dois
 * se o usuarios digitar 4 {
 * vamso adicionar um a linda 3 na coluna 1
 * }
 *
 * */