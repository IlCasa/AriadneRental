package it.ariadne.prenotazioni.model;

import java.time.LocalDateTime;

public class RisorseOkWrapper {
	private String tipo;
	private int limite;
	//private LocalDateTime dataInizio, dataFine;
	
	
	public RisorseOkWrapper(String tipo, int limite) {
		this.tipo = tipo;
		this.limite = limite;
//		this.dataInizio = dataInizio;
//		this.dataFine = dataFine;
	}

	public RisorseOkWrapper() {

	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getLimite() {
		return limite;
	}
	public void setLimite(int limite) {
		this.limite = limite;
	}
//	public LocalDateTime getDataInizio() {
//		return dataInizio;
//	}
//	public void setDataInizio(LocalDateTime dataInizio) {
//		this.dataInizio = dataInizio;
//	}
//	public LocalDateTime getDataFine() {
//		return dataFine;
//	}
//	public void setDataFine(LocalDateTime dataFine) {
//		this.dataFine = dataFine;
//	}
	
	
	
}
