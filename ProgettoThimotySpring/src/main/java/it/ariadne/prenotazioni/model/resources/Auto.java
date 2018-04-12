package it.ariadne.prenotazioni.model.resources;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import it.ariadne.prenotazioni.model.Risorsa;


@Entity
@DiscriminatorValue("Auto")
public class Auto extends Risorsa {
	
	public Auto() {
		super();
	}	
	
}
