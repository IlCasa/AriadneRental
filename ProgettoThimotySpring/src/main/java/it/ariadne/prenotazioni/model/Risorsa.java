package it.ariadne.prenotazioni.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Risorsa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	protected int id;
	
	@Column(nullable=false)
	protected boolean available;
	
	@JsonInclude(Include.ALWAYS)
	@Transient
	private String risorsa_type;
	
	@Column
	protected int limite;
	
	@OneToMany(mappedBy = "risorsa")
	protected List<Prenotazione> prenotazioni;
	
	public Risorsa() {
		this.risorsa_type = getClass().getSimpleName();
	}

	public Risorsa(int id,int limit) {
		this.id = id;
		this.limite = limit;
	}

	public int getId() {
		return id;
	}

	public String getRisorsa_type() {
		return risorsa_type;
	}

	public void setRisorsa_type(String risorsa_type) {
		this.risorsa_type = risorsa_type;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	} 
	
	
	
}
