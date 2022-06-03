import java.io.File;
import java.util.*;

import javax.swing.text.html.HTMLDocument.BlockElement;
/*
	Hoja de Trabajo 10
	Programadores:
		Daniel Esteban Morales Urizar - 21785
		Herber Sebastian Silva Muñoz - 21764
		Elías Alberto Alvarado Raxón - 21808
*/
import java.io.FileWriter;
public class Principal {
	File archivo;
	FileWriter fw;
	static Scanner sw;
	
	public static void main(String[] args) {
		try {
			System.out.println("Bienvenido\nAsegurese que su archivo no tenga ningun enter de mas :)")
			Scanner sr = new Scanner (System.in);
			//System.out.println("Ingrese la ruta del archivo (incluya el nombre y la extención)");
			//String ruta = sr.nextLine();
			String ruta = "C:\\Users\\pc\\Documents\\uvg\\sem3\\algoritmos\\Hojas\\Hoja10\\guategrafo.txt";
			File archivo = new File(ruta);
			ArrayList<String> texto = new ArrayList<String>();
			if(!archivo.exists()) {
				System.out.println("Archivo no encontrado, varificar ruta");
                
			}else {
				sw = new Scanner(archivo, "UTF-8");
				while (sw.hasNextLine()) {
					texto.add(sw.nextLine());}
				}
			sw.close();
			Controlador contr = new Controlador();
			
			contr.crear_grafo(texto);

			String menu = "1. Agregar coneccion \n"+
			"2. Buscar ruta mas corta\n"+
			"3. Calcular el centro\n"+
			"4. Salir";
			boolean Cicloprincipal = true;
			while(Cicloprincipal){
				System.out.println("¿Que desea hacer?\n"+menu);
				int opcion = sr.nextInt();
				sr.nextLine();
				switch (opcion) {
					case 1:
						System.out.println("Ingrese nombre de ciudad de salida");
						String ciudad1 = sr.nextLine();
						System.out.println("Ingrese nombre de ciudad de llegada");
						String ciudad2 = sr.nextLine();
						System.out.println("Ingrese la distancia entre ciudades en km");
						String distancia = sr.nextLine();
						String agregar = ciudad1.trim()+" "+ciudad2.trim()+" "+distancia.trim();
						texto.add(agregar);
						contr.crear_grafo(texto);//Se vuelve a crear el grafo con la nueva coneccion
						break;
					case 2:
						//Busca la ruta mas corta
						break;
					case 3:
						//calcula el centro
						break;
					case 4:
						Cicloprincipal = false;
						break;
					default:
						System.out.println("Ingrese una opcion valida");
						break;
				}
			}

		}catch(Exception e) {
			System.out.print(e);
		}
		
	}

}