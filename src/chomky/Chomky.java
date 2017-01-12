/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomky;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JairA
 */
public class Chomky {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Metodos met = new Metodos();
       int opc, CantidadReglas,n=0,sele;
        String algo[];
        char minu;
        int a=0;
        

        List<String[]> lista = new ArrayList<String[]>();
        List<Character> alcanzables = new ArrayList<Character>();
        List<Character> unitarios = new ArrayList<Character>();
        List<Character> minus = new ArrayList<Character>();
        List<Character> eliminadas = new ArrayList<Character>();
        List<Character> lamda = new ArrayList<Character>();

       // d.PedirDatos(lista, minus);
        do{
          System.out.print("Ingrese la minuscula: \n");  
          minu= stdin.next().charAt(0);
          minus.add(minu);
          System.out.print("Desea ingresar otra minuscula?: \n" + "1.Si \n" +"2.No");
          sele = stdin.nextInt();
        }while(sele == 1);
    
        do {
            System.out.print("Cuantas reglas tiene su produccion: " + "\n");
            CantidadReglas = stdin.nextInt();
            algo = new String[CantidadReglas +1];
            System.out.print("El nombre de la producción: ");
            algo[0] = stdin.next();
            for (int i = 1; i <= CantidadReglas; i++) {
                System.out.print("Ingrese la regla [" + (i) + "]: ");
                algo[i] = stdin.next();
            }
            lista.add(algo);
                   // System.out.println((lista));
            System.out.print("Desea agregar otra produccion? \n"
                    + "1. Sí \n" + "2. No" + "\n");
            opc = stdin.nextInt();
        } while (opc == 1);
   
//No generadoras
        int j=0;
        do{
        j= minus.size();
            for(int i =0;i<lista.size();i++){
                boolean ban = met.NoGeneradoras(lista.get(i),minus);
               if(ban == true){
                    String[] NmbProdu = lista.get(i);
                    int pos = met.BuscarPosProduccion(NmbProdu[0],minus);
                    if(pos == -1){  
                          minus.add(NmbProdu[0].charAt(0));
                    }
                }
            }
        }while(j != minus.size());
     
//Eiminar producciones No generadoras
       a = lista.size();
       for(int i=0;i<a;i++){
          String [] produccion = lista.get(i);
          boolean elim =  met.EliminarProducciones(lista.get(i),minus,eliminadas);
           if(elim==true){
              lista.remove(i);
              eliminadas.add(produccion[0].charAt(0));
              a--;
              i--;
           }
       }
// Eliminar reglas no generadoras
  met.EliminarReglas(lista,eliminadas); 
   System.out.print("Despues de eliminar no generadores \n");
    met.show(lista);
//Eiminar producciones No alcanzables
    met.NoAlcanzables(lista.get(0),lista,alcanzables);   
    System.out.print("Despues de eliminar no alcanzables \n");
    met.show(lista);
         
//Eliminar producciones lamda      
    met.BuscarProduccionesLamda(lista, lamda);
    System.out.print("Despues de procesar producciones Lamda \n");
      met.show(lista);   
      
 //Producciones unitarias
     met.ProcesarUnitarios(lista,unitarios);
     System.out.print("Despues de procesar producciones Unitarias \n");
      met.show(lista); 
    
    }
}
