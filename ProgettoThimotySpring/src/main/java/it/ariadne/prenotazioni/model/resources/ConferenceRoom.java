package it.ariadne.prenotazioni.model.resources;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import it.ariadne.prenotazioni.model.Risorsa;

@Entity
@DiscriminatorValue("ConferenceRoom")
public class ConferenceRoom extends Risorsa{

	public ConferenceRoom() {
		super();
	}

}
