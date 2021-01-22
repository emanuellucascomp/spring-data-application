package br.com.wb.spring.data.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.wb.spring.data.orm.Funcionario;
import br.com.wb.spring.data.orm.FuncionarioProjection;
import br.com.wb.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Selecione ação: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionarios por nome");
			System.out.println("2 - Busca funcionarios por nome, salario e data contratação");
			System.out.println("3 - Busca funcionarios por data contratação");
			System.out.println("4 - Busca funcionarios e salários");

			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				buscarFuncionariosPorNome(scanner);
				break;
			case 2:
				buscaFuncionarioPorNomeSalarioMaiorDataContratacao(scanner);
				break;
			case 3:
				buscaFuncionarioPorDataContratacaoMaior(scanner);
				break;
			case 4:
				buscaFuncionarioESalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscarFuncionariosPorNome(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
	}
	
	private void buscaFuncionarioPorNomeSalarioMaiorDataContratacao(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		System.out.println("Digite a data");
		String data = scanner.next();
		System.out.println("Digite o salário");
		Double salario = scanner.nextDouble();
		LocalDateTime localDate = LocalDateTime.parse(data, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findPorNomeDataContratacaoSalarioMaior(nome, salario, localDate);
		funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
	}
	
	private void buscaFuncionarioPorDataContratacaoMaior(Scanner scanner) {
		System.out.println("Digite a data");
		String data = scanner.next();
		LocalDateTime localDate = LocalDateTime.parse(data, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
		funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
	}
	
	private void buscaFuncionarioESalario() {
		List<FuncionarioProjection> funcionarios = funcionarioRepository.findFuncionariosSalario();
		funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
	}
	
}
