package com.mayab.calidad.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;

public class TestAlumnos extends DBTestCase{
	IDatabaseConnection conn;
	@Override
	protected IDataSet getDataSet() throws Exception {
		InputStream xmlFile = getClass().getResourceAsStream("/alumnos.xml");
		return new FlatXmlDataSetBuilder().build(xmlFile);
	}
	
	public TestAlumnos(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@127.0.0.1:1522:XE"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "fredy");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "Guzman11");
	}
	@Before
	public void setUp() throws Exception {
		//conn =getConnection();
		 conn = getConnection();
		//conn = DriverManager.getConnection(
				//"jdbc:oracle:thin:@127.0.0.1:1522:XE", "fredy", "Guzman11");
		
		try {
			
	            if (conn != null) {
	                System.out.println("Connected to the database!");
	            } else {
	                System.out.println("Failed to make connection!");
	            }
				//DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
		}finally {
			conn.close();
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testInsert() throws Exception {
		conn = getConnection();
		Alumno alumno = new Alumno(2,"Fredy", "Guzman", 20, 10.0f);
		AlumnosSQL alumnos = new AlumnosSQL();
		alumnos.AddAlumno(alumno);
		IDataSet conexion = getConnection().createDataSet();
		 ITable realTable = conexion.getTable("alumnos");
		 InputStream xmlFile = getClass().getResourceAsStream("/expected.xml");
		 IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
		 ITable expectedTable = expectedDataSet.getTable("alumnos");
		 Assertion.assertEquals(expectedTable, realTable);
		conn.close();
	}
	@Test
	public void testDelete() throws Exception{
		IDatabaseConnection conn = getConnection();
		Alumno alumno = new Alumno(2,"Fredy","Guzman",20, 10.0f);
		Alumno al = new Alumno();
		AlumnosSQL alumnos = new AlumnosSQL();
		alumnos.AddAlumno(alumno);
		alumnos.AddAlumno(al);
		alumnos.removeAlumno(al);
		IDataSet conexion = getConnection().createDataSet();
		 ITable realTable = conexion.getTable("alumnos");
		 InputStream xmlFile = getClass().getResourceAsStream("/expected.xml");
		 IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
		 ITable expectedTable = expectedDataSet.getTable("alumnos");
		 Assertion.assertEquals(expectedTable, realTable);
		conn.close();
	}
	@Test
	public void testUpdateAverage() throws Exception{
		//Creacion del alumno
		 Alumno alumno = new Alumno(2, "Fredy", "Guzman",20, 8.0f);
		 AlumnosSQL alumnos = new AlumnosSQL();
	     alumnos.updatePromedioAlumno(alumno,10.f);
		 //Conexión
		 IDataSet conexion = getConnection().createDataSet();
		 ITable realTable = conexion.getTable("alumnos");
		 InputStream xmlFile = getClass().getResourceAsStream("/expected.xml");
		 IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
		 ITable expectedTable = expectedDataSet.getTable("alumnos");
		 Assertion.assertEquals(expectedTable, realTable);
		 conn.close();
	}
	@Test
	public void testGetAll()throws Exception{
		// :(
		/*
		IDataSet conexion = getConnection().createDataSet();
		ITable realTable = conexion.getTable("alumnos");
		InputStream xmlFile = getClass().getResourceAsStream("/expected.xml");
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
		ITable expectedTable = expectedDataSet.getTable("alumnos");
		Assertion.assertEquals(expectedTable, realTable);
		*/
		}
}
