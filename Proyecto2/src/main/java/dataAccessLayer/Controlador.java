/**
 * 
 */


package dataAccessLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author ealva
 *
 */
public class Controlador
{
	public LinkedList<String> recomendacion(LinkedList<String> artistas)
	{
		Map<String, Integer> conteo = new HashMap<>();
		ArrayList<String> nombres = new ArrayList<>();
		
		LinkedList<String> temporal = new LinkedList<String>();
		for(int indice = 0; indice < artistas.size(); indice++)
		{
			int repeticiones = Collections.frequency(artistas, artistas.get(indice));
			conteo.put(artistas.get(indice), repeticiones);
		}
		
		List<Entry<String, Integer>> list = new ArrayList<>(conteo.entrySet());
		list.sort(Entry.comparingByValue());
		list.forEach(System.out::println);
		
		
		for(String nombre: conteo.keySet())
		{
			nombres.add(nombre);
		}
		
		
		for(int i = list.size() - 1; i >= 0; i--)
		{
			temporal.add(list.get(i).getKey());
		}
		
		
		LinkedList<String> artistas_recomendados = new LinkedList<String>();
		if(temporal.size() <= 5)
		{
			return temporal;
		}
		else
		{
			for(int i = 0; i < 5; i++)
			{
				artistas_recomendados.add(temporal.get(i));
			}
			return artistas_recomendados;
		}
		
	}
}
