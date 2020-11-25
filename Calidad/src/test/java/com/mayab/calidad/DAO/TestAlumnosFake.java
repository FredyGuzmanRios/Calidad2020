package com.mayab.calidad.DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

class TestAlumnosFake {
	Alumno alumno;
	AlumnosFake alumnos;
	@Before
	void setUp() throws Exception {
		alumno=new Alumno();
		alumnos =new AlumnosFake();
	}

	@After
	void tearDown() throws Exception {
	}

	@Test
	public void AddTest(){
		int n= alumnos.getAlumnos().size();
		alumnos.AddAlumno(alumno);
		assertThat((n+1),is(alumnos.getAlumnos().size()));
		
	}
	@Test
	public void DeleteTest() {
		int n= alumnos.getAlumnos().size();
		if(n==0) {
			alumnos.AddAlumno(alumno);
			n++;
		}
		alumnos.removeAlumno(alumno);
		assertThat((n-1),is(alumnos.getAlumnos().size()));
	}
	@Test 
	public void UpdateTest() {
		int n= alumnos.getAlumnos().size();
		if(n==0) {
			alumnos.AddAlumno(alumno);
		}
		alumnos.getAlumno(alumno.getId()).setAverage(9.8f);;
		assertThat((9.8f),is(alumnos.getAlumno(alumno.getId()).getAverage()));
	}
}
