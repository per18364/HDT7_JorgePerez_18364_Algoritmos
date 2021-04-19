// Universidad del Valle de Guatemala
// José Jorge Pérez 18364
// MainHDT7.java

import java.util.*;
import java.io.*;
import java.lang.*;

/**
* <h1> Binary Search Tree Implementation with Associations </h1>
* Crea una diccionario en forma de BST y traduce de ingles a espanol
* <p>
* @author Jorge Perez 18364
* @version 1.0
* @since   2021-15-04
*/

public class MainHDT7 {

	public static void main(String args[]) {
	
		// Variables
		BinaryTree diccionario = new BinaryTree();
		Association<String, String> asoc;
		int cont =0;
		
		String FILENAME = "diccionario.txt";
		
		BufferedReader br = null;
		FileReader fr = null;
		String stringOutput ="";

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			System.out.println("\n>>> Se abrio exitosamente " + FILENAME);
		
			while ((stringOutput = br.readLine()) != null) {
		
                
                int startParenIndex = 1 + stringOutput.indexOf("(");
                int endParenIndex = stringOutput.indexOf(")");
            	int commaIndex = stringOutput.indexOf(",");
            	
            	String key = stringOutput.substring(startParenIndex, commaIndex);
            	String value = stringOutput.substring(2+commaIndex, endParenIndex);
            	
            	asoc = new Association<>(key.toLowerCase(), value.toLowerCase());
            	
            	if (cont == 0) { // la primera vez se crea el ROOT, luego ya son solo los hijos
            		diccionario.setData(asoc);
            		cont++;
            		
            	} else {
            		diccionario.insert(asoc);
            	}
			}
		

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
		System.out.println("\n>>> Se lleno el diccionario exitosamente!");
		
		// Para interpretar el texto.txt de ingles a espanol
		FILENAME = "texto.txt";
		br = null;
		fr = null;
		stringOutput ="";
		String traduction = "";

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			System.out.println("\n>>> Se abrio exitosamente " + FILENAME);
		
			while ((stringOutput = br.readLine()) != null) {
                
                stringOutput = stringOutput.replace(".",""); // to remove all dots
                
                String[] words = stringOutput.split(" ",0);
                
                for (String word : words) {
                	
                	if (diccionario.contains(word.toLowerCase())) {
                		
                		traduction += " " + diccionario.get(word.toLowerCase());
                		
                	} else {
                		
                		traduction += " *" + word + "*";
                	}
                }
                
                traduction += ".\n"; // final de la oracion
				
			}
		

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
		// menu de opciones
		Scanner scan = new Scanner(System.in);
		
		int option = 0;
        String menu = "\n MENU: \n 1. Imprimir el diccionario en in-order \n 2. Traducir a espanol: " + FILENAME + " \n 3. Salir";
        
        while (option != 3) {
            
            System.out.println(menu);
            
            System.out.println("\n>>> Ingrese el numero de opcion que desea realizar: ");
            
            try {
                option = scan.nextInt();
                scan.nextLine();
                
            } catch (InputMismatchException e) {
                
                scan.nextLine();
                System.out.println("<!> No ingreso un numero...");
            }
            
            // defense for number of option
            while (option < 1 || option > 3) {
                
                System.out.println("<!> No ingreso una opcion valida...");
                System.out.println("\n>>> Ingrese el numero de opcion que desea realizar: ");
                
                try {
                    option = scan.nextInt();
                    scan.nextLine();
                    
                } catch (InputMismatchException e) {
                    
                    scan.nextLine();
                    System.out.println("<!> No ingreso un numero...");
                }
            }
            
            if (option == 1) {
            	
            	System.out.println("\n>>> Imprimiendo diccionario en forma In Order...");
            	diccionario.printInOrder();
            	
            } else {
            	
            	System.out.println("\n>>> Traduccion: ");
				System.out.println(traduction);
            	
            }
        }
		
		System.out.println("\n Saliendo...\n");
	}
}
