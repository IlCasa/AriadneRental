package it.ariadne.prenotazioni.dao;

import org.springframework.data.repository.CrudRepository;

import it.ariadne.prenotazioni.model.Prenotazione;



public interface PrenotazioniRepository extends CrudRepository<Prenotazione, Integer>{

}
