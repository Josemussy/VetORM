package persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClientePersist {


	public static void cadastrarNovoCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
		EntityManager em = emf.createEntityManager();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do cliente:");
		String nome = scanner.nextLine();

		System.out.println("Digite o email do cliente:");
		String email = scanner.nextLine();

		System.out.println("Digite o CPF do cliente:");
		String cpf = scanner.nextLine();

		System.out.println("Digite o celular do cliente:");
		String celular = scanner.nextLine();

		System.out.println("Digite o endereço do cliente:");
		String endereco = scanner.nextLine();

		Cliente novoCliente = new Cliente(nome, email, cpf, celular, endereco);

		try {
			em.getTransaction().begin();
			em.persist(novoCliente);
			em.getTransaction().commit();
			System.out.println("Cliente cadastrado com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao cadastrar o cliente: " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
	}

	public static void consultarCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
		EntityManager em = emf.createEntityManager();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do cliente para consulta:");
		String nomeConsulta = scanner.nextLine();

		try {
			String query = "SELECT c FROM Cliente c WHERE c.nome = :nome";
			List<Cliente> clientes = em.createQuery(query, Cliente.class)
					.setParameter("nome", nomeConsulta)
					.getResultList();

			if (clientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado com o nome: " + nomeConsulta);
			} else {
				System.out.println("Clientes encontrados:");
				for (Cliente cliente : clientes) {
					System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome());

				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao consultar o cliente: " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}
	}

	public static void atualizarCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
		EntityManager em = emf.createEntityManager();

		Scanner scanner = new Scanner(System.in);


		consultarCliente();

		System.out.println("Digite o ID do cliente que deseja atualizar:");
		Long idCliente = scanner.nextLong();
		scanner.nextLine();

		Cliente cliente = em.find(Cliente.class, idCliente);

		if (cliente == null) {
			System.out.println("Cliente não encontrado com o ID: " + idCliente);
		} else {
			System.out.println("Digite os novos dados do cliente:");

			System.out.println("Novo nome do cliente:");
			cliente.setNome(scanner.nextLine());

			System.out.println("Novo email do cliente:");
			cliente.setEmail(scanner.nextLine());

			System.out.println("Novo CPF do cliente:");
			cliente.setCpf(scanner.nextLine());

			System.out.println("Novo celular do cliente:");
			cliente.setCelular(scanner.nextLine());

			System.out.println("Novo endereço do cliente:");
			cliente.setEndereco(scanner.nextLine());

			try {
				em.getTransaction().begin();
				em.merge(cliente);
				em.getTransaction().commit();
				System.out.println("Cliente atualizado com sucesso!");
			} catch (Exception e) {
				em.getTransaction().rollback();
				System.out.println("Erro ao atualizar o cliente: " + e.getMessage());
			}
		}

		em.close();
		emf.close();
	}

	public static void excluirCliente() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaVeterinariaPU");
		EntityManager em = emf.createEntityManager();

		Scanner scanner = new Scanner(System.in);

		// Consultar cliente pelo nome
		consultarCliente();

		System.out.println("Digite o ID do cliente que deseja excluir:");
		Long idCliente = scanner.nextLong();
		scanner.nextLine();

		Cliente cliente = em.find(Cliente.class, idCliente);

		if (cliente == null) {
			System.out.println("Cliente não encontrado com o ID: " + idCliente);
		} else {
			try {
				em.getTransaction().begin();
				em.remove(cliente);
				em.getTransaction().commit();
				System.out.println("Cliente excluído com sucesso!");
			} catch (Exception e) {
				em.getTransaction().rollback();
				System.out.println("Erro ao excluir o cliente: " + e.getMessage());
			}
		}

		em.close();
		emf.close();
	}



}
