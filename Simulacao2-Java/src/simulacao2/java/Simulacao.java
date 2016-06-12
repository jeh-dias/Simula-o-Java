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
                
                conexoes.add(con);

                // criando 50 pacotes por conexão
                for (int j = 0; j < 50; j++) {
                    pacote = new Pacote();
                    pacote.setTamanho(225.28);
                    tDecorrido = con.getTempoNovaConexao();
                    // tratando chegadas
                    if (tDecorrido == con.getTempoNovaConexao()) {
                        System.out.println(" chegou");
                        if (!roteador.isEmpty()) {
                            tempoServico = pacote.getTamanho() / tamanhoLink;
                        }
                        roteador.add(pacote);
                        // setando tempo de nova conexao
                        con.setTempoNovaConexao(con.poisson(mediaTempoDuracao, 1, mediaConexoes));
                        
                        // setando tempo de duracao de conexao
                        con.setDuracaoConexao(con.exponencial(mediaConexoes) * (-1));       
                        
                        tempoGeraConexao = con.getTempoNovaConexao();
                        System.out.println("tempo gera conexao " + tempoGeraConexao);
                        
                        tempoDuraConexao = con.getDuracaoConexao();
                        System.out.println("tempo dura conexao " + tempoDuraConexao);
                    // tratando saídas    
                    }else{
                            System.out.println(" saiu ");
                            roteador.remove("0");
                            if(roteador.isEmpty()){
                                
                                tDecorrido += (-1.0 / tempoServico) * Math.log(aleatorio());
                                tempoSimulacao++;
                            }else{
                                tDecorrido = 0.0;
                            }

                        
                        utilizacaoCalculada = tempoGeraConexao / tempoServico;
                        System.out.println(" tempo de serviço " + tempoServico);
                        System.out.println(" utilizacao calculada " + utilizacaoCalculada);
                }

            }
         }

        } while (tDecorrido < 10.000);
        System.out.println(" conexoes " + conexoes.size());

        /* inicio trafego web
        do {
            tempoSimulacao = 0.0;
            tempoSimulacao++;
        } while (tempoSimulacao <= 100.000);*/

    }

}
