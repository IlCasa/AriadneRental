package it.ariadne.prenotazioni.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.ariadne.prenotazioni.model.Prenotazione;
import it.ariadne.prenotazioni.model.ResourceManager;
import it.ariadne.prenotazioni.model.Risorsa;
import it.ariadne.prenotazioni.model.Utente;
import it.ariadne.prenotazioni.service.PrenotazioniService;
import it.ariadne.prenotazioni.service.RisorseService;
import it.ariadne.prenotazioni.service.UtentiService;

@RestController
public class ControllerJQ {
	
		
		@Autowired
		private UtentiService utentiServ;
		@Autowired
		private RisorseService risorseServ;
		@Autowired
		private PrenotazioniService prenotazioniServ;
		

		@RequestMapping(value="/newRisJQ", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Risorsa addResource(@RequestBody String jsonBody) {
			Gson gson = new GsonBuilder().registerTypeAdapter(Risorsa.class, new ResourceManager()).create();
			Risorsa r = gson.fromJson(jsonBody, Risorsa.class);
			return risorseServ.save(r);
		}
		
		@RequestMapping(value="/newUserJQ", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Utente newUserJQ(@RequestBody Utente u) {
			return utentiServ.save(u);
		}
		
		@RequestMapping(value="/newPrenJQ", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Prenotazione newPrenJQ(@RequestBody Prenotazione p) {
			return prenotazioniServ.save(p);
		}
		
		
		
		@RequestMapping(value="/signUpJQ", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public void addUser(@RequestBody Utente u) {
			System.out.println(u.toString());
			utentiServ.registerNewUserAccount(u.getPassword(), u.getNome(), u.getMail());
		}
		
//		@RequestMapping(value="/checkRisOkJQ", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//		@ResponseBody
//		public Prenotazione addPrenotation(@RequestBody Prenotazione p) {
//			System.out.println(p.toString());
//			return p;
//		}
		
		
	

}
