package com.ftninformatika.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/restorani")
public class RestoranController {

	private Dostava dostava;

	public RestoranController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("")
	public String getAll(ModelMap request,
			@RequestParam(required = false, defaultValue = "0") long kategorijaId) {
		
		Collection<Restoran> rezultat = new ArrayList<Restoran>();
		for(Restoran itRestoran : dostava.getRestorani().values()) {
			for(Kategorija itKategorija : itRestoran.getKategorije()) {
				if(kategorijaId == 0 || itKategorija.getId() == kategorijaId) {
					rezultat.add(itRestoran);
					break;
				}
			}
		}
		request.addAttribute("restorani", rezultat);
		return "restorani";
	}
	
	@GetMapping("/prikaz") //
	public String get(ModelMap request, 
			@RequestParam long id) {
		request.addAttribute("restoran", dostava.getRestorani().get(id));
		return "restorani-prikaz";
	}
}
