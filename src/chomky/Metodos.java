/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomky;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JairA
 */
public class Metodos {
    
public boolean NoGeneradoras(String[] elemento, List<Character> minus) {
        int contador = 0;
        for(int i = 1; i<elemento.length; i++){
                char [] el = elemento[i].toCharArray();
                for(int j = 0; j<el.length;j++){          
                    for(int jj=0; jj<minus.size();jj++){
                        if(el[j]== minus.get(jj)){
                            contador++;
                              if(contador == el.length){
                            //      System.out.print("letra" + elemento[0]);
                                return true;
                            }
                        } 
                    }  
                }
             contador=0;
            }
       return false;
    }
    
public boolean EliminarProducciones(String[] produccion, List<Character> minus, List<Character> eliminadas){
   String prueba ="";
    for(int i=0; i<minus.size();i++){
                 if(Character.isUpperCase(minus.get(i))){
                      prueba = prueba + minus.get(i).toString();               
                    }
                 }
    
    if(prueba.contains(produccion[0])){
       return false; //System.out.print(produccion[0]);
    }else{
      return true;
   }
  }

public void NoAlcanzables(String[] lista, List<String[]> todo, List<Character>alcanzables){
    Metodos o= new Metodos(); 
    for(int i=1; i<lista.length;i++){
        char [] letras = lista[i].toCharArray();
            for(int j=0; j<letras.length; j++){
                if(Character.isUpperCase(letras[j])){
                    String a = ""+letras[j];
                    int pos = o.BuscarPosProduccion(a,alcanzables);
                    if(pos == -1){
                        alcanzables.add(letras[j]);
                    }
                }
            }
    }
    
if(alcanzables.size()>0){
    int iteraciones = 0;
    do{
    iteraciones = alcanzables.size();
      String pr="";
      for(int i=0; i<alcanzables.size();i++){
         pr=pr+alcanzables.get(i);
      }
      for(int i=1; i<todo.size();i++){
          String[] produccion = todo.get(i);
             for(int j=1;j<produccion.length; j++){
                 if(pr.contains(produccion[0])){
                     char[] verif = produccion[j].toCharArray();
                        for(int m=1; m<verif.length;m++){
                            if(Character.isUpperCase(verif[m])){
                                String a = ""+verif[m];
                             int posi = o.BuscarPosProduccion(a, alcanzables);
                                  if(posi == -1){
                                        alcanzables.add(verif[m]);
                                    }
                            }
                        }
                 }        
            }
        } 
    }while(iteraciones != alcanzables.size());
    
    String pru="";
    for(int i=0; i<alcanzables.size();i++){
         pru=pru+alcanzables.get(i);
      }
    
    int con = todo.size();
    for(int i=1; i<con;i++){
            String [] produ= todo.get(i);
            if(pru.contains(produ[0])){
            }else{
                todo.remove(i);
                con--;
                i--;
            }
        }
    
    }    
}
    //funciona
public boolean EliminarReglas(List<String[]> lista, List<Character> eliminadas){
        if(eliminadas.size()>0){
            for(int i=0;i<lista.size();i++){
                String[]pr = lista.get(i);
                    for(int j=1; j<pr.length;j++){
                        for(int jj=0;jj<eliminadas.size();jj++){
                            if(pr[j].contains(eliminadas.get(jj).toString())){
                                pr[j]="";
                            }
                        }
                    }
                }
        }
        
        return true;
    } 
  
public int BuscarPosProduccion(String nombreProduccion,List<Character> minus){
        char n = nombreProduccion.charAt(0);
        if(minus.size() >0){
            for(int i=0; i<minus.size(); i++){
            if(n == minus.get(i)){
                return i;
               }
            }
        }
        return -1;
    }
    
public void BuscarProduccionesLamda(List<String[]> lista, List<Character> lamda){
    Metodos o = new Metodos();   
    for(int i=0;i<lista.size();i++){
            String [] produccion = lista.get(i); 
            for(int j=0;j<produccion.length;j++){
                if("&".equals(produccion[j])){
                    produccion[j]="";
                   // o.lamda(lista, i);
                    o.eliminarProduccionesLamda(lista,lamda,i);
                }
            }
        }
       // return -1;
    }

/*public void lamda(List<String[]> lista, int pos){
    List<String> newe = new ArrayList<String>();
    for(int i=0; i<lista.size(); i++){
        if(i!=pos){
         String n= "";
            String[] produccion = lista.get(i);
                for(int j=1; j<produccion.length;j++){
                    char[] pr = produccion[j].toCharArray();
                        for(int jj=0; jj<pr.length;jj++){
                            String [] prLamda = lista.get(pos);
                            if(pr[jj] == prLamda[0].charAt(0)){
                                for(int jjj=0; jjj<pr.length;jjj++){
                                    if(pr[jj] != prLamda[0].charAt(0)){
                                           n=n+pr[jj];
                                    }
                                }
                            }
                        }
                      newe.add(n);
                      n="";
                }
    String [] nuevaPr = new String[(lista.get(pos).length + newe.size())];
        int s=0;
            String[]l = lista.get(i);
                for(int ii=0; ii<l.length;ii++){
                    nuevaPr[s] = l[ii];
                    s++;
                }
                for(int j=0; j< newe.size();j++){
                    nuevaPr[s-1] = newe.get(j);
                    s++;
                }
            lista.set(i, nuevaPr);
        }
    }
    
    
            
            
            
}*/

public void eliminarProduccionesLamda(List<String[]> lista, List<Character> lamda ,int pos){

      //String [] pr = new String[1];
    int NumeroReglas = lista.get(pos).length;
    int ReglasConMayusculas = 0;
    //String mayusculas= "";
    String [] produccion = lista.get(pos); 
        for(int h=0; h<produccion.length;h++){
          if(produccion[h].length() > 1){
          char[] ProduccionLetras = produccion[h].toCharArray();
             for(int m=0; m<ProduccionLetras.length;m++){
                    if(Character.isUpperCase(ProduccionLetras[m])){
                        //mayusculas = mayusculas + ProduccionLetras[m];
                        lamda.add(ProduccionLetras[m]);
                        ReglasConMayusculas++;
                    }
                }
          }        
       }
        
        
     String [] NProduccion = new String [lista.get(pos).length + ReglasConMayusculas];
     //String[] pro = lista.get(pos);
     int s=0;   
        for(int i=0;i<produccion.length;i++){
            NProduccion[s] = produccion[i]; 
            s++;
        }
        
     String n="";
     String may="";
     int contador=0;
     for(int h=0; h<lamda.size();h++){
         may=may+lamda.get(h);
     }
     
        for(int i=0; i<lamda.size();i++){
            for(int j=1; j<produccion.length;j++){
                if(produccion[j].length()>2){
                    char [] pr = produccion[j].toCharArray();
                    for(int c=0; c<pr.length;c++){
                        String lt = ""+pr[c];
                        if(pr[c] != lamda.get(i) || Character.isLowerCase(pr[c])){
                            n=n+pr[c];
                        }else{
                            if(n.contains(lt)){
                            
                            }else{
                             n = n + pr[c];
                            }
                        }
                    }
                   NProduccion[s-1] = n; 
                   n="";
                   s++;
                }
                
                if(produccion[j].length() == 2){
                    char [] pr = produccion[j].toCharArray();
                    for(int c=0; c<pr.length;c++){
                        String lt = ""+pr[c];
                        if(pr[c] == lamda.get(i)){
                            
                        }else{
                             if(Character.isLowerCase(pr[c])){
                                  contador++;
                             }
                             if(contador!= pr.length){
                                n = n + pr[c];
                             }else{
                                 contador=0;
                                 n="";
                             }
                        }
                    }
                   if(n!=""){
                        NProduccion[s-1] = n; 
                        n="";
                        s++;
                       // contador=0;
                   }
                }
            }
        }
        
       lista.set(pos, NProduccion);
}

public void ProcesarUnitarios(List<String[]> lista, List<Character> unitarios){
    String[] producciones = lista.get(0);
            for(int j=1; j<producciones.length;j++){
                if(producciones[j].length()==1 && (Character.isUpperCase(producciones[j].charAt(0)))){
                    unitarios.add(producciones[j].charAt(0));
                }
            }
    //copiar todas las producciones que contengan la produccion unitaria sin la produccion
    String l="";
    for(int i=1;i<producciones.length;i++){
        for(int j=0; j<unitarios.size();j++){
              l=l+unitarios.get(j);
        }
         if(producciones[i].length() == 1 && l.contains(producciones[i])){
                producciones[i]="";
            }
    }
    
    String[] S = lista.get(0);
    for(int j=1; j<lista.size();j++){
        String[] produccion = lista.get(j);
            for(int i=0; i<unitarios.size();i++){
                  if(produccion[0].charAt(0)==unitarios.get(i)){
                        String[] nuevoS = new String[(produccion.length + S.length)-1];
                            //for(int m=0; m<nuevoS.length;m++){
                        //buscar posicio a posicion las unitarias y agregar el complemento.
                                   int ss=0;
                                    for(int s=0; s<lista.get(0).length;s++){
                                          nuevoS[s]= S[s];
                                            ss++;
                                    }
                                    
                                    for(int p=1; p<produccion.length;p++){
                                        nuevoS[(ss+p)-1] = produccion[p];
                                    }
                            //}
                         lista.set(0, nuevoS);
                    }
            }
        
    }
    
}
    
 public void show(List<String[]> lista){
      for(String elem[]: lista){//Crear una función (Si da tiempo)
           for(String e: elem){
               if( e!= ""){
                        System.out.print(e + "|" );
                }
           }
           System.out.print("\n");
       }
 
   }    
}