package com.skilldistillery.MVCCRUDProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PlanetDBDAOImpl implements PlanetDAO {

	public PlanetDBDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Planet getPlanet(String planetname) {
	
		
		try {
			Planet planet = new Planet();
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name, diameter, length_of_days, distance_from_sun FROM planets WHERE name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, planetname);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String pname = rs.getString(1);
				String pdiameter = rs.getString(2);
				String plengthofdays = rs.getString(3);
				String pdistancefromsun = rs.getString(4);
				planet.setPlanetName(pname);
				planet.setPlanetDiameter(pdiameter);
				planet.setPlanetLengthOfDays(plengthofdays);
				planet.setPlanetDistanceFromSun(pdistancefromsun);
			}
			rs.close();
			stmt.close();
			conn.close();
			return planet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Planet> getAllPlanets() {
		List<Planet> planet = new ArrayList<>();
		String planetname = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name, diameter, length_of_days, distance_from_sun FROM planets";
			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, planetname);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				String diameter = rs.getString(2);
				String length_of_day = rs.getString(3);
				String distance_from_sun = rs.getString(4);

				planet.add(new Planet(name, diameter, length_of_day, distance_from_sun));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planet;
	}

	@Override
	public void addPlanet(Planet planet) {
		System.out.println("a;sldkfsad;lfjsda");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO planets (name, diameter, length_of_days, distance_from_sun)"
					+ " VALUES (?, ?, ?, ?) ";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, planet.getPlanetName());
			stmt.setString(2, planet.getPlanetDiameter());
			stmt.setString(3, planet.getPlanetLengthOfDays());
			stmt.setString(4, planet.getPlanetDistanceFromSun());

			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount);
			if (updateCount == 1) { // if we add one
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					String newPlanetName = keys.getString(1);
					planet.setPlanetName(newPlanetName);
				}
			} else {
				planet = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting planet " + planet);
		}
	}

	@Override
	public Planet removePlanet(String name) {
		Connection conn = null;
		Planet planet = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM planets WHERE name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM planets WHERE name = ?";
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1, name);
		    updateCount = stmt.executeUpdate();
		    conn.commit();             // COMMIT TRANSACTION
		    conn.close();
		    stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting planet.");
		}
		
		return planet;
	}

	@Override
	public Planet updatePlanet(Planet updatePlanet) {
		System.out.println("a;sldkfsad;lfjsda");

		Connection conn = null;
		try {
			Planet planet = new Planet();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE planets SET name = ?, diameter = ?, length_of_days = ?, distance_from_sun = ? WHERE name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			int updateCount = 0;
			System.out.println(updatePlanet);
			System.out.println(stmt.toString());
			stmt.setString(1, updatePlanet.getPlanetName());
			stmt.setString(2, updatePlanet.getPlanetDiameter());
			stmt.setString(3, updatePlanet.getPlanetLengthOfDays());
			stmt.setString(4, updatePlanet.getPlanetDistanceFromSun());
			stmt.setString(5, updatePlanet.getPlanetName());
//			stmt.setString(3, planet.getPlanetLengthOfDays());
			updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
			return planet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error updating planet.");
		}
	}

}
