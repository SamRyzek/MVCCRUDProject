package com.skilldistillery.MVCCRUDProject;

import java.util.List;

public interface PlanetDAO {
	
	public void addPlanet(Planet planet);
	
	public Planet removePlanet(String string);
	
	public Planet updatePlanet(Planet updatePlanet);
	
	public Planet getPlanet(String planetName);
	
	public List<Planet> getAllPlanets();
	
}
