package com.skilldistillery.MVCCRUDProject;

public class Planet {

	private String planetName;
	private String planetDiameter;
	private String planetLengthOfDays;
	private String planetDistanceFromSun;
	
	//getters and setters
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	public String getPlanetDiameter() {
		return planetDiameter;
	}
	public void setPlanetDiameter(String planetDiameter) {
		this.planetDiameter = planetDiameter;
	}
	public String getPlanetLengthOfDays() {
		return planetLengthOfDays;
	}
	public void setPlanetLengthOfDays(String planetLengthOfDays) {
		this.planetLengthOfDays = planetLengthOfDays;
	}
	public String getPlanetDistanceFromSun() {
		return planetDistanceFromSun;
	}
	public void setPlanetDistanceFromSun(String planetDistanceFromSun) {
		this.planetDistanceFromSun = planetDistanceFromSun;
	}
	
	//constructors
	public Planet() {
		super();
	}
	
	public Planet(String planetName, String planetDiameter, String planetLengthOfDays,
			String planetDistanceFromSun) {
		super();
		this.planetName = planetName;
		this.planetDiameter = planetDiameter;
		this.planetLengthOfDays = planetLengthOfDays;
		this.planetDistanceFromSun = planetDistanceFromSun;
	}
	//toString
	@Override
	public String toString() {
//		return "Planet [planetName=" + planetName + ", planetDiameter=" + planetDiameter + ", planetLengthOfDays="
//				+ planetLengthOfDays + ", planetDistanceFromSun=" + planetDistanceFromSun;
		return planetName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planetDiameter == null) ? 0 : planetDiameter.hashCode());
		result = prime * result + ((planetDistanceFromSun == null) ? 0 : planetDistanceFromSun.hashCode());
		result = prime * result + ((planetLengthOfDays == null) ? 0 : planetLengthOfDays.hashCode());
		result = prime * result + ((planetName == null) ? 0 : planetName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (planetDiameter == null) {
			if (other.planetDiameter != null)
				return false;
		} else if (!planetDiameter.equals(other.planetDiameter))
			return false;
		if (planetDistanceFromSun == null) {
			if (other.planetDistanceFromSun != null)
				return false;
		} else if (!planetDistanceFromSun.equals(other.planetDistanceFromSun))
			return false;
		if (planetLengthOfDays == null) {
			if (other.planetLengthOfDays != null)
				return false;
		} else if (!planetLengthOfDays.equals(other.planetLengthOfDays))
			return false;
		if (planetName == null) {
			if (other.planetName != null)
				return false;
		} else if (!planetName.equals(other.planetName))
			return false;
		return true;
	}
	
}
