/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    public LinkedList<String> getArtistasPorGenero(String g1, String g2,String g3)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                	Result result1 = tx.run( "MATCH (people:Person)-[Canta]-(:Genero {title:'" + g1 + "'}) RETURN people.name");
                	Result result2 = tx.run( "MATCH (people:Person)-[Canta]-(:Genero {title:'" + g2 + "'}) RETURN people.name");
                	Result result3 = tx.run( "MATCH (people:Person)-[Canta]-(:Genero {title:'" + g3 + "'}) RETURN people.name");
                    LinkedList<String> artistas = new LinkedList<String>();
                    List<Record> registros = result1.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 artistas.add(registros.get(i).get("people.name").asString());
                    }
                    registros = result2.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 artistas.add(registros.get(i).get("people.name").asString());
                    }
                    registros = result3.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 artistas.add(registros.get(i).get("people.name").asString());
                    }
                    
                    Controlador controlador = new Controlador();
                    artistas = controlador.recomendacion(artistas);
                    
                    return artistas;
                }
            } );
            
            return actors;
        }
   }

}
