package br.com.wb.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.wb.spring.data.orm.Cargo;
import br.com.wb.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository repository;
	
	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Selecione ação: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Inserir");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch(action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setNome(descricao);
		repository.save(cargo);
		System.out.println("Salvo.");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id do cargo: ");
		Long id = scanner.nextLong();
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Optional<Cargo> cargo = repository.findById(id);
		if(cargo.isPresent()) {
			cargo.get().setNome(descricao);
			repository.save(cargo.get());			
			System.out.println("Salvo.");
		} else {
			System.out.println("Cargo não localizado");
		}
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();
		cargos.forEach(cargo -> System.out.println("Cargo: " + cargo.getNome()));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id do cargo: ");
		Long id = scanner.nextLong();
		Optional<Cargo> cargo = repository.findById(id);
		if(cargo.isPresent()) {
			repository.delete(cargo.get());
			System.out.println("Deletado.");
		} else {
			System.out.println("Cargo não localizado");
		}
	}

}
