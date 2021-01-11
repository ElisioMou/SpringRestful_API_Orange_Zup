package br.com.orange.bank;

import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootApplication
public class BankApplication {

    private Session session;

    public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
	@Bean
     CommandLineRunner init(ClienteRepository repository, Session Conexao) {
	    return  args -> {
	        repository.deleteAll ();
            LongStream.range (1,3)
                      .mapToObj (i -> {
                      Session s = Conexao.getSessionFactory().getCurrentSession();
                      try {
                            s.beginTransaction ();
                            Cliente c = new Cliente ();
                            c.setNome (c.getNome ());
                            c.setEmail (c.getEmail ());
                            c.setCPF (c.getCPF ());
                            c.setDataNasc (c.getDataNasc ());
                            return c;

                            String checkCPF = "from Clientes c where c.cpf = :cpf";
                            List result = session.createQuery(checkCPF).setParameter("cpf", c.getCPF()).list();
                            if (result.size() > 0) {
                                throw new RuntimeException("CPF já existe");
                            }
                            s.save(c);
                            s.getTransaction().commit();
                            }

                      catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            s.getTransaction().rollback();
                            }


                          return null;
                      });
            };
     }
}

