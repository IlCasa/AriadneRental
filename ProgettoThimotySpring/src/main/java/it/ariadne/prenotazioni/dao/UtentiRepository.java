package it.ariadne.prenotazioni.dao;

import org.springframework.data.repository.CrudRepository;

import it.ariadne.prenotazioni.model.Utente;


public interface UtentiRepository extends CrudRepository<Utente, Integer>{
	public Utente findByNome(String nome);
}
