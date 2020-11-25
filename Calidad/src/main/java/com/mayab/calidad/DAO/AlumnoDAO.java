package com.mayab.calidad.DAO;


import java.util.HashMap;

public interface AlumnoDAO {

	void AddAlumno(Alumno alumno);
	void removeAlumno(Alumno alumno);
	void updatePromedioAlumno(Alumno alumno,float newProm);
	Alumno getAlumno(int i);
}
