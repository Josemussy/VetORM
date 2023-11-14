package persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cliente;
import model.Pet;

import java.util.List;
import java.util.Scanner;

public class PetPersist {

    public static void cadastrarNovoPet() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do pet:");
        String nome = scanner.nextLine();

        System.out.println("Digite a idade do pet:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite a espécie do pet:");
        String especie = scanner.nextLine();

        System.out.println("Digite o peso do pet:");
        float peso = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Digite o ID do cliente associado ao pet:");
        Long idCliente = scanner.nextLong();
        scanner.nextLine();

        Cliente cliente = em.find(Cliente.class, idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado com o ID: " + idCliente);
        } else {
            Pet novoPet = new Pet(nome, idade, especie, peso);
            novoPet.setCliente(cliente);

            try {
                em.getTransaction().begin();
                em.persist(novoPet);
                em.getTransaction().commit();
                System.out.println("Pet cadastrado com sucesso!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Erro ao cadastrar o pet: " + e.getMessage());
            }
        }

        em.close();
        emf.close();
    }

    public static void consultarPet() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do pet para consulta:");
        String nomeConsulta = scanner.nextLine();

        try {
            String query = "SELECT p FROM Pet p WHERE p.nome = :nome";
            List<Pet> pets = em.createQuery(query, Pet.class)
                    .setParameter("nome", nomeConsulta)
                    .getResultList();

            if (pets.isEmpty()) {
                System.out.println("Nenhum pet encontrado com o nome: " + nomeConsulta);
            } else {
                System.out.println("Pets encontrados:");
                for (Pet pet : pets) {
                    System.out.println("ID: " + pet.getId() + ", Nome: " + pet.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar o pet: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void atualizarPet() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);


        consultarPet();

        System.out.println("Digite o ID do Pet que deseja atualizar:");
        Long idPet = scanner.nextLong();
        scanner.nextLine();

        Pet pet = em.find(Pet.class, idPet);

        if (pet == null) {
            System.out.println("Pet não encontrado com o ID: " + idPet);
        } else {
            System.out.println("Digite os novos dados do pet:");

            System.out.println("Novo nome do Pet:");
            pet.setNome(scanner.nextLine());

            System.out.println("Nova idade do Pet:");
            pet.setIdade(scanner.nextInt());

            System.out.println("Novo peso do Pet:");
            pet.setPeso(scanner.nextInt());

            System.out.println("Nova especie do pet:");
            pet.setEspecie(scanner.nextLine());


            try {
                em.getTransaction().begin();
                em.merge(pet);
                em.getTransaction().commit();
                System.out.println("Pet atualizado com sucesso!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Erro ao atualizar o pet: " + e.getMessage());
            }
        }

        em.close();
        emf.close();
    }

    public static void excluirPet() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);


        consultarPet();

        System.out.println("Digite o ID do Pet que deseja excluir:");
        Long idPet = scanner.nextLong();
        scanner.nextLine();

        Pet pet = em.find(Pet.class, idPet);

        if (pet == null) {
            System.out.println("Pet não encontrado com o ID: " + idPet);
        } else {
            try {
                em.getTransaction().begin();
                em.remove(pet);
                em.getTransaction().commit();
                System.out.println("Pet excluído com sucesso!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Erro ao excluir o pet: " + e.getMessage());
            }
        }

        em.close();
        emf.close();
    }


}
