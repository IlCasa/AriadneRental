package it.ariadne.prenotazioni.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ariadne.prenotazioni.dao.PrenotazioniRepository;
import it.ariadne.prenotazioni.model.Prenotazione;


@Service
@Transactional
public class PrenotazioniService {

	
	@Autowired
	private final PrenotazioniRepository prenotazioniRepo;
	
	public PrenotazioniService(PrenotazioniRepository pr) {
		super();
		this.prenotazioniRepo = pr;
	}

	public List<Prenotazione> findAll(){
		List<Prenotazione> prenotazioni = new ArrayList<>();
		for(Prenotazione p : prenotazioniRepo.findAll()) {
			prenotazioni.add(p);
		}
		return prenotazioni;
	}
	
	public Prenotazione findUser(int id) {
		return prenotazioniRepo.findOne(id);
	}
	
	public Prenotazione save(Prenotazione p) {
		return prenotazioniRepo.save(p);
	}
	
	public void delete(int id) {
		prenotazioniRepo.delete(id);
	}
	
	
	
}
