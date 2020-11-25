package com.mayab.calidad.DAO;

public class AlumnoDriver {
	
	public static void main(String[] args) {
		AlumnosSQL alumnos= new AlumnosSQL();
		alumnos.AddAlumno(new Alumno(2,"Fredy","Guzman",21,10.0f));
	}
}
