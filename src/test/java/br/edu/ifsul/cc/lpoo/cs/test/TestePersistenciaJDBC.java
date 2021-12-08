
package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.cs.model.Patente;
import br.edu.ifsul.cc.lpoo.cs.model.dao.PersistenciaJDBC;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author brener
 */
public class TestePersistenciaJDBC {
    
    // atividade assíncrona: 02/12/2021  implementar o test para a recuperacao de registro em tb_patente.
    //@Test
    public void testListPersistenciaPatente() throws Exception {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            List<Patente> lista = persistencia.listPatentes();
            
            if(!lista.isEmpty()){
            
                for(Patente e : lista){

                    System.out.println("Nome: "+e.getNome()+" Cor: "+e.getCor());
                    persistencia.remover(e);
                }
            }else{
                
                System.out.println("Não encontrou a patente");
                
                Patente end = new Patente();
                end.setNome("Fulano");
                end.setCor("nenhum");                                                
                persistencia.persist(end); //insert na tabela.                
                System.out.println("Cadastrou a patente "+end.getId());
                
                
                end = new Patente();//reset com a nova instancia que é gerada aqui.
                end.setNome("Ciclano");
                end.setCor("Vermelho");
                
                persistencia.persist(end); //insert na tabela.
                System.out.println("Cadastrou a patente "+end.getId());
                
            }
            
            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
    
    @Test
    public void testPersistenciaPatente() throws Exception {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JDBC");                        
            
            //casting (modelo o objet retornado pelo find em Patente)            
            Patente end = (Patente) persistencia.find(Patente.class, 2);
            
            if(end == null){
                
                System.out.println("Não encontrou a patente");
                
                end = new Patente();                
                end.setNome("Beltrano");
                end.setCor("Azul");
                
                System.out.println("Patente : "+end.getId());                                
                
                persistencia.persist(end); //insert na tabela.
                
                System.out.println("Cadastrou a patente: "+end.getId());                                
                
            }else{
                
                System.out.println("Encontrou a patente: "+end.getId());
                
                end.setCor("Amarelo");
                persistencia.persist(end);//upate.
                
                System.out.println("Alterou a patente: "+end.getId());
                
                //persistencia.remover(end);
                //System.out.println("Removeu a patente: "+end.getId());
            }                        
            
            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
        
    }
        
}
