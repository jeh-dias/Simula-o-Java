/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacao2.java;

/**
 *
 * @author jessica
 */
public class Conexao {

    private double tempoNovaConexao;
    private double duracaoConexao;
    private static double E = 2.7182818284590452354;

    public void setTempoNovaConexao(double tempoNovaConexao) {
        this.tempoNovaConexao = tempoNovaConexao;
    }

    public double getTempoNovaConexao() {
        return tempoNovaConexao;
    }

    public double getDuracaoConexao() {
        return duracaoConexao;
    }

    public void setDuracaoConexao(double duracaoConexao) {
        this.duracaoConexao = duracaoConexao;
    }
    
    double minimo(double a, double b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    int fatorial(int num) {
        int fat = 1;
        int i;
        for (i = 2; i < num; i++) {
            fat *= i;
        }
        return fat;
    }

    double poisson(double t, int k, double lambda) {
        double p = 0.0;
        int fat = fatorial(k);
        p = Math.pow(E, lambda * t) * Math.pow(lambda * t, k) / fat;
        return p;
    }

    double exponencial(double lambda) {
        double p = 0.0;
        p = 1 - lambda;
        return p;
    }

}
