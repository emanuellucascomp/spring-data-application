package br.com.wb.spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.wb.spring.data.orm.Funcionario;
import br.com.wb.spring.data.repository.FuncionarioRepository;
import br.com.wb.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Digite o nome");
		String nomeString = scanner.next();
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.nome(nomeString)));
		funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
	}

}
