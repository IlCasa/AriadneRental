package it.ariadne.prenotazioni.model.resources;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import it.ariadne.prenotazioni.model.Risorsa;


@Entity
@DiscriminatorValue("Laptop")
public class Laptop extends Risorsa{

	public Laptop() {
		super();
	}
}
