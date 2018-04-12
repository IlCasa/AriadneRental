package it.ariadne.prenotazioni.controller;


import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ariadne.prenotazioni.model.Prenotazione;
import it.ariadne.prenotazioni.model.Risorsa;
import it.ariadne.prenotazioni.model.Utente;
import it.ariadne.prenotazioni.service.PrenotazioniService;
import it.ariadne.prenotazioni.service.RisorseService;
import it.ariadne.prenotazioni.service.UtentiService;



@Controller
public class MainController {

	@Autowired
	private UtentiService utentiServ;
	@Autowired
	private PrenotazioniService prenotazioniServ;
	@Autowired
	private RisorseService risorseServ;

	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}
	
	@GetMapping("/all-users")
	public String allUsers(HttpServletRequest request) {
		request.setAttribute("users", utentiServ.findAll());
		request.setAttribute("mode", "MODE_FIND_ALL_USERS");
		return "index";
	}
	
	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_FIND_ALL_USERS");
		utentiServ.delete(id);
		request.setAttribute("users", utentiServ.findAll());
		return "index";
	}
	
	@GetMapping("/update-user")
	public String updateUser(@RequestParam String nome, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_UPDATE_USER");
		request.setAttribute("user", utentiServ.findByNome(nome));
		return "index";
	}
	
	@PostMapping("/save-user")
	public String saveUser(@ModelAttribute Utente u, BindingResult br, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_FIND_ALL_USERS");
		utentiServ.save(u);
		request.setAttribute("users", utentiServ.findAll());
		return "index";
	}

	
	//RISORSE -----------------------------------
	
	@GetMapping("/all-resources")
	public String allResources(HttpServletRequest request) {
		request.setAttribute("resources", risorseServ.findAll());
		request.setAttribute("mode", "MODE_FIND_ALL_RESOURCES");
		return "index";
	}

	@GetMapping("/delete-resource")
	public String deleteResource(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_FIND_ALL_RESOURCES");
		risorseServ.delete(id);
		request.setAttribute("resources", risorseServ.findAll());
		return "index";
	}
	
	@GetMapping("/update-resource")
	public String updateTask(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_UPDATE_RESOURCE");
		request.setAttribute("resource", risorseServ.findResource(id));
		return "index";
	}

	// movement -------------------
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "index";
	}
	
	@GetMapping("/new-resource")
	public String newResource(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_NEW_RESOURCE");
		return "index";
	}
	
	@GetMapping("/sign-up")
	public String signed(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_SIGN_UP");
		return "index";
	}
	
	@GetMapping("/new-prenotation")
	public String newPrenotation(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_NEW_PRENOTATION");
		return "index";
	}
	
	@GetMapping("/add-prenotations")
	public String addPrenotations(@RequestParam String nomeUser, @RequestParam int idRis, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_CONFIRM_PRENOTATION");
		Utente activeUser = utentiServ.findByNome(nomeUser);
		Risorsa choosenResource = risorseServ.findResource(idRis);
		System.out.println(choosenResource.toString() +"  UTENTE " +activeUser.toString());
		request.setAttribute("activeUser", activeUser);
		request.setAttribute("choosenRes", choosenResource);
		return "index";
	}
	
	@PostMapping("/checkRisOk")
	public String listRisOk(@RequestParam String tipo,@RequestParam String oraInizio,@RequestParam String oraFine, @RequestParam int limite,@RequestParam Date dataInizio, @RequestParam Date dataFine, HttpServletRequest request) {
		request.setAttribute("mode", "MODE_RISORSE_OK");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String app = dataFine.toString() +" "+ oraFine;
		String app2 = dataInizio.toString() +" "+ oraInizio;
		LocalDateTime dateTimeStart = LocalDateTime.parse(app, formatter);
		LocalDateTime dateTimeEnd = LocalDateTime.parse(app2, formatter);
		List<Risorsa> risorseOk = new ArrayList<>();
		risorseOk = checkSlot(tipo, limite, dateTimeStart, dateTimeEnd);
		request.getSession().setAttribute("dataInizioPar", dateTimeStart);
		request.getSession().setAttribute("dataFinePar", dateTimeEnd);
		request.setAttribute("resources", risorseOk);
		return "index";
	}
	
	private ArrayList<Risorsa> findRisorseOk(String tipo, int limite) {
		ArrayList<Risorsa> tmp = new ArrayList<>();
		for(Risorsa r : (ArrayList<Risorsa>)risorseServ.findAll()) {
			if((r.getRisorsa_type().equals(tipo))&&(r.getLimite()>=limite)){
				tmp.add(r);
			}
		}
		return tmp;
	}
	private List<Risorsa> checkSlot(String tipo, int limite, LocalDateTime start, LocalDateTime end) {
		List<Risorsa> risorseOk = new ArrayList<>();
		List<Risorsa> risorseOkDispo = new ArrayList<>();
		risorseOk = findRisorseOk(tipo, limite);
		boolean flag=true;
		for(Risorsa r: risorseOk) {
			flag = true;
			for(Prenotazione p : (ArrayList<Prenotazione>)prenotazioniServ.findAll()) {
				if(r.getId()==p.getRisorsa().getId()) {
					if(!((start.isAfter(p.getDataFine()))||(end.isBefore(p.getDataInizio())))){
						System.out.println("error, resource not available");
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				risorseOkDispo.add(r);
			}
		}
		return risorseOkDispo;
	}
	
	
	
	
}
