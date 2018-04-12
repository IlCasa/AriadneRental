package it.ariadne.prenotazioni.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Utente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(unique = true, nullable = false)
	private String mail;
	@Column(unique = true, nullable = false)
	private String nome;
	@Column(unique = true, nullable = false)
	private String password;
	@OneToMany(mappedBy = "utente")
	private List<Prenotazione> prenotazioni;
	
	public Utente() {
		super();
	}

	public Utente(int id, String email, String password) {
		this.id = id;
		this.mail = email;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Email utente: " + mail + "\n");
		sb.append("Password: " + password + "\n");
		return sb.toString();
	}
	
}
