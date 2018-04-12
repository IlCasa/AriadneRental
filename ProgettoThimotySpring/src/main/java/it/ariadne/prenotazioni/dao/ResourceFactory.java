//package it.ariadne.prenotazioni.dao;
//
//import it.ariadne.prenotazioni.model.Risorsa;
//import it.ariadne.prenotazioni.model.resources.Auto;
//import it.ariadne.prenotazioni.model.resources.Laptop;
//
//public class ResourceFactory {
//
//	public static Risorsa getRisorsa(String type, int limite){
//		if("auto".equalsIgnoreCase(type)) 
//			return new Auto(-1, type, limite);
//		else if("laptop".equalsIgnoreCase(type)) 
//			return new Laptop(-1, type, limite);
//		return null;
//	}
//}