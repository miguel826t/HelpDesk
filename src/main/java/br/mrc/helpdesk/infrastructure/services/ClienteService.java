package br.mrc.helpdesk.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mrc.helpdesk.domain.entities.Cliente;
import br.mrc.helpdesk.domain.entities.Pessoa;
import br.mrc.helpdesk.domain.repositories.ClienteRepository;
import br.mrc.helpdesk.domain.repositories.PessoaRepository;
import br.mrc.helpdesk.infrastructure.services.exceptions.DataIntegrityViolationException;
import br.mrc.helpdesk.infrastructure.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService{

	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private PessoaRepository pessoasRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clientes.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não cadastrado. Id: " + id));
	}
	
	public List<Cliente> findAll(){
		return clientes.findAll();
	}
	
	public Cliente create(Cliente cliente) {
		validaPorCpfeEmail(cliente);
		return clientes.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteOld = findById(cliente.getId());
		validaPorCpfeEmail(cliente);
		BeanUtils.copyProperties(cliente, clienteOld);
		return clientes.save(cliente);
	}
	
	public void delete(Integer id) {
		Cliente cliente = findById(id);
		if(cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de servico vinculadas e não pode ser deletado!");
		}
		clientes.deleteById(id);
	}
	
	private void validaPorCpfeEmail(Cliente cliente) {
		Optional<Pessoa> obj = pessoasRepository.findByCpf(cliente.getCpf());
		if(obj.isPresent() && obj.get().getId() != cliente.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoasRepository.findByEmail(cliente.getEmail());
		if(obj.isPresent() && obj.get().getId() != cliente.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
	
}
