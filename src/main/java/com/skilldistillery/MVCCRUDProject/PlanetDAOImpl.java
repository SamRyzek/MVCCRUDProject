package com.skilldistillery.MVCCRUDProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class PlanetDAOImpl implements PlanetDAO {
	private static final String FILE_NAME = "/WEB-INF/planet.csv";
	private List<Planet> planets = new ArrayList<>();
	/*
	 * Use Autowired to have Spring inject an instance of a WebApplicationContext
	 * into this object after creation. We will use the WebApplicationContext to
	 * retrieve an ServletContext so we can read from a file.
	 */
	@Autowired
	private WebApplicationContext wac;

	/*
	 * The @PostConstruct method is called by Spring after object creation and
	 * dependency injection
	 */
	@PostConstruct
	public void init() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String planetName = tokens[0].trim();
				String planetDiameter = tokens[1].trim();
				String planetLengthOfDay = tokens[2].trim();
				String planetDistanceFromSun = tokens[3].trim();
				Planet p = new Planet(planetName, planetDiameter, planetLengthOfDay, planetDistanceFromSun);
				planets.add(p);
				System.out.println(planets);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@Override
	public Planet getPlanet(String name) {
		
		Planet p = null;
		// Find the state by it's name in the 'planets' array
		// If found, assign the planet to 'p';
		for (Planet planet : planets) {
			if (name != null && name.equalsIgnoreCase(planet.getPlanetName())) { //checks null and then name match
				p = planet;
				break;
			}
		}
		return p;
	}

	@Override
	public void addPlanet(Planet planetO) {
	System.out.println("planet array size in addPlanet " + planets.size());
		System.out.println(planets);
		if (planets.indexOf(planetO) != 0) {
			planets.add(planetO);
			System.out.println("PLANET WAS NOT INDEX 0. ADDED!");
		}
		else {
			System.out.println("PLANET WAS INDEX 0!");
		}
		System.out.println("Planet added to index "+ planets.indexOf(planetO));
		System.out.println("planet array size after adding Planet " + planets.size());
		System.out.println(planets);
	}

	@Override
	public Planet removePlanet(String string) {
		Planet p = null;
		System.out.println("DAO remove: " + string);
		for (int i = 0; i < planets.size(); i++) {
			if (planets.get(i).getPlanetName().equals(string)) {
				p = planets.remove(i);
				break;
			}
		}
		return p;
	}

	@Override
	public List<Planet> getAllPlanets() {
	
		return new ArrayList<>(planets);
	}

	@Override
	public Planet updatePlanet(Planet planet) {
		System.out.println("DAO update: " + planet);
		for (int i = 0; i < planets.size(); i++) {
			if (planets.get(i).getPlanetName().equals(planet.getPlanetName())) {
				planets.set(i, planet);
				// set updated planet fields to the planet passed in
				// e.g. updatedPlanet.setName(planet.getName());
				
				return planet;
			}
		}
		return null;
	}
}
