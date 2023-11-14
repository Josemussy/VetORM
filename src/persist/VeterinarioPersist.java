package persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cliente;
import model.Veterinario;

import java.util.List;
import java.util.Scanner;

public class VeterinarioPersist {


    public static void cadastrarNovoVeterinario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do veterinário:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do veterinário:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o celular do veterinário:");
        String celular = scanner.nextLine();

        System.out.println("Digite o CRMV do veterinário:");
        String crmv = scanner.nextLine();

        Veterinario novoVeterinario = new Veterinario(nome, cpf, celular, crmv);

        try {
            em.getTransaction().begin();
            em.persist(novoVeterinario);
            em.getTransaction().commit();
            System.out.println("Veterinário cadastrado com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao cadastrar o veterinário: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void consultarVeterinario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do veterinário para consulta:");
        String nomeConsulta = scanner.nextLine();

        try {
            String query = "SELECT v FROM Veterinario v WHERE v.nome = :nome";
            List<Veterinario> veterinarios = em.createQuery(query, Veterinario.class)
                    .setParameter("nome", nomeConsulta)
                    .getResultList();

            if (veterinarios.isEmpty()) {
                System.out.println("Nenhum veterinário encontrado com o nome: " + nomeConsulta);
            } else {
                System.out.println("Veterinários encontrados:");
                for (Veterinario veterinario : veterinarios) {
                    System.out.println("ID: " + veterinario.getId() + ", Nome: " + veterinario.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar o veterinário: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void atualizarVeterinario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);


        consultarVeterinario();

        System.out.println("Digite o ID do Veterinário que deseja atualizar:");
        Long idVeterinario = scanner.nextLong();
        scanner.nextLine();

        Veterinario veterinario= em.find(Veterinario.class, idVeterinario);

        if (veterinario == null) {
            System.out.println("Veterinário não encontrado com o ID: " + idVeterinario);
        } else {
            System.out.println("Digite os novos dados do Veterinário:");

            System.out.println("Novo nome do Veterinário:");
            veterinario.setNome(scanner.nextLine());

            System.out.println("Novo celular do Veterinário:");
            veterinario.setCelular(scanner.nextLine());

            System.out.println("Novo CPF do veterinário:");
            veterinario.setCpf(scanner.nextLine());

            System.out.println("Novo CRMV do Veterinário:");
            veterinario.setCrmv(scanner.nextLine());


            try {
                em.getTransaction().begin();
                em.merge(veterinario);
                em.getTransaction().commit();
                System.out.println("Veterinário atualizado com sucesso!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Erro ao atualizar o veterinário: " + e.getMessage());
            }
        }

        em.close();
        emf.close();
    }

    public static void excluirVeterinario() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        consultarVeterinario();

        System.out.println("Digite o ID do Veterinário que deseja excluir:");
        Long idVeterinario = scanner.nextLong();
        scanner.nextLine();

        Veterinario veterinario = em.find(Veterinario.class, idVeterinario);

        if (veterinario == null) {
            System.out.println("Veterinário não encontrado com o ID: " + idVeterinario);
        } else {
            try {
                em.getTransaction().begin();
                em.remove(veterinario);
                em.getTransaction().commit();
                System.out.println("Veterinário excluído com sucesso!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Erro ao excluir o Veterinário: " + e.getMessage());
            }
        }

        em.close();
        emf.close();
    }
}


