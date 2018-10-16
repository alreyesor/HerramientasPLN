package edu.uam.azc.pln.pesado;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Profesor: Alejandro Reyes
 * @version  1.0
 */
public class PesadoTerminos {


    
    public static void main(String[] args)  {
       
    	
    
    	ArrayList<String> noticia1 = new ArrayList<>(Arrays.asList("acusar", "robar", "transeúnte"));
    	ArrayList<String> noticia2 = new ArrayList<>(Arrays.asList("silvia", "secuestrar", "2017"));
    	ArrayList<String> noticia2 = new ArrayList<>(Arrays.asList("juan", "patricia", "asesinar", "10", "mujeres"));

        ArrayList<ArrayList<String>> noticias = new ArrayList<>(Arrays.asList(noticia1, noticia2, noticia3));
        
        ArrayList<String> dicc = new ArrayList<>(Arrays.asList("acusar", "robar", "transeúnte", "silvia", "secuestrar", "2017", "juan", "patricia", "asesinar", "10", "mujeres"));
    
        PesadoTerminos pesado = new PesadoTerminos();
        
        // Pesado Booleano
        for(ArrayList<String> not: noticias){
        	ArrayList<Boolean> bol= pesado.pesadoBooleano(not, dicc);
        	System.out.println(bol);
        }
        // Pesado TF
        for(ArrayList<String> not: noticias){
        	ArrayList<Integer> tf= pesado.pesadoFT(not, dicc);
        	System.out.println(tf);
        }
        
        //Pesado TFIDF
        for(ArrayList<String> not: noticias){
        ArrayList<Double> tfidf= pesado.pesadoTFIDF(not, dicc,noticias);
             System.out.println(tfidf);
        }

       
    }
    
    /**
     * Calcula el pesado de un documento utilizando la metrica Booleana
     * 
     * @param documento  documento que desea calcular su pesado
     * @param diccionario lexicon de palabras de todos los documentos
     * @return la frecuencia booleana de los terminos 
     */
     public ArrayList<Boolean> pesadoBooleano(ArrayList<String> documento, ArrayList<String> diccionario) {
    	 ArrayList<Boolean> pesosBooleanos = new ArrayList<Boolean>();
         for(String entradaDiccionario:diccionario){
            	 if(documento.contains(entradaDiccionario))
            		 pesosBooleanos.add(true);
                   else
                	   pesosBooleanos.add(false);
  
         }
         return pesosBooleanos;
        }
   
     /**
      * Calcula el pesado de un documento utilizando la metrica FT (Frecuencia absoluta de los términos)
      * 
      * @param documento  documento que desea calcular su pesado
      * @param diccionario lexicon de palabras de todos los documentos
      * @return la frecuencia de los terminos 
      */
     public ArrayList<Integer> pesadoFT(ArrayList<String> documento, ArrayList<String> diccionario){
        ArrayList<Integer> pesosTF = new ArrayList<>();
             
        for(String entradaDiccionario:diccionario){
           int frecuenciaTermino=0; 
           for(String palabraDocumento:documento){
               if(palabraDocumento.equalsIgnoreCase(entradaDiccionario)){
            	   frecuenciaTermino++;
               }
           }
           pesosTF.add(frecuenciaTermino);
        }
        return pesosTF;
       }
     
     
 
     /**
      * Calcula el pesado de un documento utilizando la metrica TF-IDF (Frecuencia inversa de los términos)
      * 
      * @param documento  documento que desea calcular su pesado
      * @param diccionario lexicon de palabras de todos los documentos
      * @param documentos lista de documentos del corpus
      * @return la frecuencia de los terminos 
      */
       public ArrayList<Double> pesadoTFIDF(ArrayList<String> documento, ArrayList<String> diccionario, ArrayList<ArrayList<String>> documentos){
           ArrayList<Double> pesos = new ArrayList<Double>();
           // Calcular TF
           float tf;  
           for(String entradaDiccionario:diccionario){
        	   int frecuenciaTermino=0;
               for(String palabraDocumento:documento){
                   if(entradaDiccionario.equalsIgnoreCase(palabraDocumento)){
                	   frecuenciaTermino++;
                   } 
               } 
                 tf= (float)(frecuenciaTermino);
              
              // Calcular IDF

              int contadorDocumentos=0;
              for(ArrayList<String> doc: documentos){
                   if(doc.contains(entradaDiccionario))
                   {
                	   contadorDocumentos++;
                   }
              }
            
            float D= (float)(documentos.size()); // Cantidad total de documentos
           float idf= (float)(Math.log10(D/(contadorDocumentos))); // # documentos que contienen al termino
            
            
            // Calcular TF*IDF
              int aux = (int)((tf*idf)*100);
              double resultado = aux/100d;
              pesos.add(resultado);
           }
           return pesos;
       }
}
