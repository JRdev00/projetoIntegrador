/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesMatematica;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class Calculando {
    
    
    
       
    
    
    
    
        public static ArrayList<VendaDia> ArrumarDados(ArrayList<Integer> xiBaguncado ) throws Exception{        
            //Arrumar o xi e ni
            ArrayList<Integer> xi = new ArrayList<Integer>();
            ArrayList<Integer> ni = new ArrayList<Integer>();   
          //  ArrayList<Integer> porcentagem = new ArrayList<Integer>();    
            ArrayList<VendaDia> resultado = new ArrayList<VendaDia>();
            double niTotal = 0.0;
            double nixiTotal = 0.0;
           
            
            //
           /////Coloca meus dados de xiBagunçado e remove os repetidos e joga dentro de xi
            HashSet<Integer> hashSet = new HashSet<Integer>(xiBaguncado);  
            xi.addAll(hashSet);
            
            

            //atribui os valores de ni ao ArrayNi
            
            for (int i = 0; i < xi.size(); i++) {
                Integer contar = 0;
                for (int j = 0; j < xiBaguncado.size(); j++) {
                    if (xi.get(i).equals(xiBaguncado.get(j))) {
                        contar++;
                    }
                }
                ni.add(i, contar);
                
            }
            
            
             
            

            
            //For para calcular o valor total de ni e de ni.xi
            
            for (int i = 0; i < ni.size(); i++) {
                niTotal = ni.get(i) + niTotal;
                
                nixiTotal = nixiTotal + (xi.get(i) * ni.get(i));

            }
            
            System.out.println("NiTotal: "+ niTotal);
            System.out.println("ni.xi Total ="+nixiTotal);
            Double valorMedia = nixiTotal / xiBaguncado.size();
            System.out.println("Media "+ valorMedia);
            
           
            
            
            //Jogar os valores até então dentro do arrey de VendasDia
            
            for (int i = 0; i < xi.size(); i++) {
                VendaDia v = new VendaDia();
                v.setXi(xi.get(i));
                v.setNi(ni.get(i));
                v.setNixi(xi.get(i) * ni.get(i));
                
                double porcentagem = (ni.get(i) / niTotal) * 100;
                DecimalFormat df = new DecimalFormat("0.##");
                String dx = df.format(porcentagem);
                System.out.println("porcentagem "+ dx);
                dx = dx.replaceAll(",", ".");
               
                 System.out.println("porcentagem "+ dx);
                Double percent = Double.parseDouble(dx);
                
                v.setPorcentagem(percent);
                
                
                double contaDoida = Math.pow(xi.get(i) - valorMedia, 2) * ni.get(i);
                String contaDoida2 = df.format(contaDoida);
                contaDoida2 = contaDoida2.replaceAll(",", ".");
                double conta = Double.parseDouble(contaDoida2);
                

                
                v.setConta(conta);
                
                
                System.out.println("xi :" + v.getXi());
                System.out.println("ni :"+ v.getNi());
                System.out.println("ni.xi :"+ v.getNixi());
                System.out.println("porcentagem :"+ v.getPorcentagem());
                System.out.println("(x-X)^2 * ni :"+ v.getConta());
                System.out.println(" ");
                
                resultado.add(v);
                
            }

            return resultado;
        }
        
        
        
        
        public static ArrayList<ValorVendaDia> criarClasses(ArrayList<Double> valores){
            ArrayList<ValorVendaDia> resultado = new ArrayList<>();
            
            Double menorValor = valores.get(0);
            int posicaoMaiorValor = valores.size()- 1;
            Double maiorValor = valores.get(posicaoMaiorValor);
            menorValor = Math.ceil(menorValor);
            maiorValor = Math.ceil(maiorValor);
            Double quantidadeClasses = Math.sqrt(valores.size());
            quantidadeClasses = Math.floor(quantidadeClasses);
            System.out.println("Numero de classes sem filtro com arredondação: "+ quantidadeClasses);
            System.out.println("NUmero de elementos: " + valores.size());
            
            if (quantidadeClasses > 15) {
                quantidadeClasses = 15.0;
            }
            if (quantidadeClasses < 5) {
                quantidadeClasses = 5.0;
            }
            
            System.out.println("Numero de classes com filtro: "+ quantidadeClasses);
            
            Double amplitude = (maiorValor - menorValor) / quantidadeClasses;
            System.out.println("a amplitude : " + amplitude);
            
            Double ClasseInicial = menorValor;
            Double ClasseFinal = menorValor + amplitude;
            
            int niTotal = valores.size();
            
            Double Media = 0.0;
            Double xiniTotal = 0.0;
            Double ContaParaVariancia = 0.0;
           
            

            //Gerando o meu array resultado
            
            for (int i = 0; i < quantidadeClasses; i++) {
                ValorVendaDia classe = new ValorVendaDia();
                DecimalFormat df = new DecimalFormat("0.00");
                String intervaloclasse = df.format(ClasseInicial) + " |--- "+ df.format(ClasseFinal);
                
                classe.setClasses(intervaloclasse);
                
                classe.setXiClasses((ClasseInicial + ClasseFinal)/ 2);
                int contadorNI = 0;
                
                for (int j = 0; j < valores.size(); j++) {
                    if (valores.get(j) >= ClasseInicial && valores.get(j) < ClasseFinal) {
                        contadorNI++;
                    }               
                }
                
                classe.setNiClasses(contadorNI);
                
                classe.setXiniClasses(classe.getXiClasses() * classe.getNiClasses());
                
                double porcentagem = ((double)contadorNI / (double)niTotal) * 100;
                
                classe.setPorcentagemClasses(porcentagem);
                
                resultado.add(classe);

                ClasseInicial = ClasseInicial + amplitude;
                ClasseFinal = ClasseFinal + amplitude;
            }
            
                //Calcular o xiniTotal
            for (int i = 0; i < resultado.size(); i++) {
                xiniTotal = xiniTotal + resultado.get(i).getXiniClasses();                   
            }
            System.out.println("xiniTotal : " + xiniTotal);
            Media = (double)xiniTotal / (double)niTotal;
            System.out.println("Essa é a mediaaa " + Media);
            
            
            //ADICIONAR O CAMPO CONTA AO ARRAY
            
            for (int i = 0; i < resultado.size(); i++) {
                
                double conta = Math.pow(resultado.get(i).xiClasses - Media, 2) * resultado.get(i).niClasses;
                ContaParaVariancia = ContaParaVariancia + (double)conta;
                
                resultado.get(i).setContaClasses(conta);
                
                System.out.println("(x-X^2) == "+  conta);
                
            }
            System.out.println("contgaTotal = " + ContaParaVariancia);
            
            
            
            return resultado;
            
        }
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
