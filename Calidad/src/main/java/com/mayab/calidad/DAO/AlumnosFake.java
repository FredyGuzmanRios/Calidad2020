package com.mayab.calidad.DAO;

import java.util.HashMap;

public class AlumnosFake implements AlumnoDAO {
	private HashMap<Integer, Alumno> Alumnos = new HashMap<Integer, Alumno>();
	public void AddAlumno(Alumno alumno) {
		Alumnos.put(alumno.getId(), alumno);
		
	}

	public void removeAlumno(Alumno alumno) {
		Alumnos.remove(alumno.getId());
		
	}

	public void updatePromedioAlumno(Alumno alumno,float newProm) {
		Alumnos.get(alumno.getId()).setAverage(newProm);
		
	}

	public Alumno getAlumno(int i) {
		return Alumnos.get(i);
	}
	public HashMap<Integer, Alumno> getAlumnos() {
		return Alumnos;
	}

}
