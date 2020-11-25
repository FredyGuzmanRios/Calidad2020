package com.mayab.calidad.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class AlumnosSQL implements AlumnoDAO{
	public void AddAlumno(Alumno alumno) {
		try {
			//Crear la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:Oracle://127.0.0.1:1522/XE"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fredy", "Guzman11");
			String query = "INSERT INTO alumnos(id, firstName, lastName, age, average)values (?, ?, ?, ?, ?);";
			//Declaración parametrizada
			PreparedStatement PS = conn.prepareStatement(query);
			PS.setInt(1, alumno.getId());
			PS.setString(2, alumno.getName());
			PS.setString(3, alumno.getLastName());
			PS.setInt(4, alumno.getAge());
			PS.setFloat(5, alumno.getAverage());
			PS.execute();
			//Cerrar conexión
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void removeAlumno(Alumno alumno) {
		try {
			//Crear la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:Oracle://127.0.0.1:1522/XE"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fredy", "Guzman11");
			String query = "DELETE FROM alumnos WHERE id = ?;";
			PreparedStatement PS = conn.prepareStatement(query);
			PS.setInt(1, alumno.getId());
			PS.execute();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updatePromedioAlumno(Alumno alumno,float newProm) {
		try {
			//Crear la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:Oracle://127.0.0.1:1522/XE"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fredy", "Guzman11");
			String query = "UPDATE alumnos SET average WHERE id = ?;";
			PreparedStatement PS = conn.prepareStatement(query);
			PS.setFloat(1, newProm);
			PS.setInt (2, alumno.getId());
			PS.execute();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Alumno> getAll() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {
			//Crear la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:Oracle://127.0.0.1:1522/XE"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fredy", "Guzman11");
			String query = "SELECT * FROM alumnos";
			PreparedStatement PS = conn.prepareStatement(query);
			ResultSet rs = PS.executeQuery(query);
			rs.last();
			int nRows = rs.getRow();
			rs.beforeFirst();
			for(int i=0;i<nRows;i++) {
				if(rs.next()) {
					alumnos.add(new Alumno(rs.getInt("ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),
							rs.getInt("AGE"),rs.getFloat("AVERAGE")));
				}
			}
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return alumnos;
	}


	public Alumno getAlumno(int i) {
		/*
		try {
			//Crear la conexión
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:Oracle://127.0.0.1:1522/XE"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "fredy", "Guzman11");
			String query = "SELECT * FROM alumnos WHERE id =" + i;
			PreparedStatement PS = conn.prepareStatement(query);
			ResultSet rs = PS.executeQuery(query);
			rs.beforeFirst();
			while(rs.next()) {
				
			}
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		*/
		return new Alumno();
	}

	

}
