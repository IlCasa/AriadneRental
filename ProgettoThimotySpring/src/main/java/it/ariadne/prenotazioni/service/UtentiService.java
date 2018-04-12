package it.ariadne.prenotazioni.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.ariadne.prenotazioni.dao.UtentiRepository;
import it.ariadne.prenotazioni.model.Utente;

@Service
@Transactional
public class UtentiService implements UserDetailsService {
	
	
	@Autowired
	UtentiRepository utentiRepo;

	public UtentiService() {
		
	}

	public List<Utente> findAll(){
		List<Utente> utenti = new ArrayList<>();
		for(Utente u : utentiRepo.findAll()) {
			utenti.add(u);
		}
		return utenti;
	}
	
	public Utente findUser(int id) {
		return utentiRepo.findOne(id);
	}
	
	public Utente save(Utente u) {
		return utentiRepo.save(u);
	}
	
	public void delete(int id) {
		utentiRepo.delete(id);
	}
	
	public Utente findByNome(String nome) {
		Utente u = utentiRepo.findByNome(nome);
		return u;
	}
	
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Utente user = utentiRepo.findByNome(nome);

		if(user == null) {
			throw new UsernameNotFoundException("username not found");
		}

		return new org.springframework.security.core.userdetails.User(
				nome, 
				user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority("nome")));
	}
	
	
	public Utente registerNewUserAccount(String pwd, String nome, String mail) {
	  
	    Utente user = new Utente();
	    user.setMail(mail);
	    user.setNome(nome);
	    user.setPassword(pwd);
	     //to check if needed for a crypted pwd
	    return utentiRepo.save(user);
	}
	
	
	
	
}
