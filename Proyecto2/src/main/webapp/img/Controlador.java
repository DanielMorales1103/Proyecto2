import java.util.ArrayList;


public class Controlador {
	int [][] MatrizAd;
	String[] ciudadesvector;
	public void crear_grafo(ArrayList<String> texto) {
		ArrayList<String> ciudades = new ArrayList<String>();
		try {
			int cont = 0;
			for (int i=0;i<texto.size();i++){
				String fila = texto.get(i);
				String []palabras = fila.split(" ");
				
				if (cont == 0){
					ciudades.add(palabras[0]);
					ciudades.add(palabras[1]);
					cont++;
				}else{
					for (int j =0; j<1;j++){
						boolean bandera=true;
						for (int w = 0; w<ciudades.size();w++){
							String ciudad1 = palabras[j];
							String ciudad2 = ciudades.get(w);
							//System.out.println(ciudad1 +" "+ciudad2);
							if(ciudad1.equals(ciudad2)){
								//System.out.println("si");
								bandera = false;
							}
						}
						if (bandera){
							//System.out.println("Si");
							ciudades.add(palabras[j]);
						}
					}
				}
			}
			//---------------------------------------Array de ciudades creado---------------------------------------------
			int tamano = ciudades.size();
			MatrizAd = new int [tamano][tamano];
			ciudadesvector = new String[tamano];
			for (int i = 0;i<ciudades.size();i++){
				ciudadesvector[i] = ciudades.get(i);
			}
			//relleno de matrz
			for (int i = 0; i<texto.size();i++){
				String fila = texto.get(i);
				String []palabras = fila.split(" ");
				int x = buscar_posicion(palabras[0],ciudadesvector);
				int y = buscar_posicion(palabras[1],ciudadesvector);
				MatrizAd[x][y]=Integer.parseInt(palabras[2]);
				MatrizAd[y][x]=Integer.parseInt(palabras[2]);
			}
			//poner infinitos
			for (int i = 0;i<tamano;i++){
				for (int j = 0; j<tamano;j++){
					if (MatrizAd[i][j]==0)
						MatrizAd[i][j]=9999;
				}
			}
			//poner 0 en la diagonal
			for (int i = 0;i<tamano;i++){
				MatrizAd[i][i]=0;				
			}
			//imprime matriz
			for (int i = 0;i<tamano;i++){
				for (int j = 0; j<tamano;j++){
					System.out.print(MatrizAd[i][j]+"	");
				}
				System.out.print("\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int buscar_posicion(String ciudad, String[]vector){
		int numero = 0;
		for (int i = 0; i<vector.length;i++){
			String ciudad2 = vector[i];
			if(ciudad.equals(ciudad2)){
				numero = i;
			}
		}
		return numero;
	}
}