package it.ariadne.prenotazioni.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ariadne.prenotazioni.dao.RisorseRepository;
import it.ariadne.prenotazioni.model.Risorsa;

@Service
@Transactional
public class RisorseService {

	@Autowired
	private final RisorseRepository risorseRepo;
	
	
	public RisorseService(RisorseRepository rp) {
		this.risorseRepo = rp;
	}

	public List<Risorsa> findAll(){
		List<Risorsa> risorse = new ArrayList<>();
		for(Risorsa r : risorseRepo.findAll()) {
			risorse.add(r);
		}
		return risorse;
	}
	
	public Risorsa findResource(int id) {
		return risorseRepo.findOne(id);
	}
	
	public Risorsa save(Risorsa r) {
		return risorseRepo.save(r);
	}
	
	public void delete(int id) {
		risorseRepo.delete(id);
	}
	
	
}
