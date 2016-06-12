/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacao2.java;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author jessica
 */
public class Simulacao {

    public static double aleatorio() {
        double d = Math.random();
        return 1.0 - d;
    }

    public static void main(String[] args) {

        int mediaConexoes = 0;
        double mediaTempoDuracao = 0.0;
        double utilizacaoDesejada = 0.0;
        double utilizacaoCalculada = 0.0;
        double tamanhoLink = 0;
        int taxaDados = 64;
        int intervaloPacotes = 120;
        double tempoSimulacao = 0.0;
        double taxaChegadaPacotesWeb = 0.0;
        double taxaGeracaoPacotesVoip = 0.0;
        double tempoServico = 0.0;
        double tempoGeraConexao = 0.0;
        double tempoDuraConexao = 0.0;
        Pacote pacote = null;
        Conexao con = null;
        double tDecorrido = 0.0;
        LinkedList<Conexao> conexoes = new LinkedList<Conexao>();
        LinkedList<Pacote> roteador = new LinkedList<Pacote>();
        

        Scanner entrada = new Scanner(System.in);
        // exemplo 5 conexões por segundo - valor discreto
        System.out.println("Informe a taxa média de conexões por segundo: ");
        mediaConexoes = entrada.nextInt();

        // 120 segundos - valor continuo
        System.out.println("Informe a taxa média do tempo de duração de cada conexão: ");
        mediaTempoDuracao = entrada.nextDouble();
        // 30
        System.out.println("Informe a utilização desejada: ");
        utilizacaoDesejada = entrada.nextDouble();

        // 1000
        System.out.println("Informe o tamanho do link: ");
        tamanhoLink = entrada.nextDouble();
        
        // inicia-se o trafego voip
        do {
            for (int i = 0; i < 5; i++) {
                con = new Conexao();
                // setando tempo de nova conexao
                con.setTempoNovaConexao(con.poisson(mediaTempoDuracao, 1, mediaConexoes));
                // setando tempo de duracao de conexao
                con.setDuracaoConexao(con.exponencial(mediaConexoes) * (-1));

                conexoes.add(con);
                tDecorrido += tempoServico;

                // criando 50 pacotes por conexão
                for (int j = 0; j < 50; j++) {
                    pacote = new Pacote();
                    pacote.setTamanho(225.28);
                    tempoServico = pacote.getTamanho() / tamanhoLink;
                    tDecorrido = tempoServico;
                    // tratando chegadas
                    if (tDecorrido == tempoServico) {
                        System.out.println(" chegou");
                        if (roteador.isEmpty()) {
                            roteador.add(pacote);
                            
                        }else{
                            tDecorrido += (-1.0 / tempoServico) * Math.log(aleatorio());
                            tempoSimulacao++;
                        }
                        roteador.add(pacote);
                        
                        tempoGeraConexao = con.getTempoNovaConexao();
                        System.out.println("tempo gera conexao " + tempoGeraConexao);
                        
                        tempoDuraConexao = con.getDuracaoConexao();
                        System.out.println("tempo dura conexao " + tempoDuraConexao);
                        
                        utilizacaoCalculada = tempoGeraConexao / tempoServico;
                        System.out.println(" tempo de serviço " + tempoServico);
                        System.out.println(" utilizacao calculada " + utilizacaoCalculada);

                    // tratando saidas    
                    } else {
                        System.out.println(" saiu ");
                        roteador.remove("0");
                        if(roteador.isEmpty()){
                            tDecorrido += (-1.0 / tempoServico) * Math.log(aleatorio());
                        }else{
                            tDecorrido = 0.0;
                        }
                    }

                }

            }

        } while (tempoSimulacao < 10.000);

        //double taxaTotalPacotes = roteador. / 100.000;

        System.out.println(" total roteador " + roteador.size());
        System.out.println(" conexoes " + conexoes.size());

        /* inicio trafego web
        do {
            tempoSimulacao = 0.0;
            tempoSimulacao++;
        } while (tempoSimulacao <= 100.000);*/

    }

}
