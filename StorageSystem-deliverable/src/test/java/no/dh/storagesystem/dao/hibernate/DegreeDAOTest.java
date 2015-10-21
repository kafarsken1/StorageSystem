package no.dh.storagesystem.dao.hibernate;

import static org.junit.Assert.*;

import no.dh.storagesystem.dao.DegreeDAO;
import no.dh.storagesystem.model.Degree;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/assignment2/beans.xml"})
@Transactional
public class DegreeDAOTest {
	
	@Autowired
	private DegreeDAO degreeDAO;
	
	private Degree testDegree;
	private int id;
	
	@Before
	public void init(){
		testDegree = new Degree("Tester");
		id = degreeDAO.saveDegree(testDegree);
	}
	
	@Test
	public void testSaveDegree(){
		assertEquals(id, testDegree.getId());
		assertEquals("Tester", testDegree.getType());
	}
	
	@Test
	public void testGetDegree(){
		assertNotNull(degreeDAO.getDegree(testDegree.getId()));
	}
	
	@Test
	public void testGetDegreeByType(){
		assertNotNull(degreeDAO.getDegreeByType(testDegree.getType()));
	}
	
	@Test
	public void testGetAllDegrees(){
		Degree tester1 = new Degree("Tester1");
		Degree tester2 = new Degree("Tester2");
		Degree tester3 = new Degree("Tester3");
		degreeDAO.saveDegree(tester1);
		degreeDAO.saveDegree(tester2);
		degreeDAO.saveDegree(tester3);
		
		assertTrue(degreeDAO.getAllDegrees().contains(tester1));
		assertTrue(degreeDAO.getAllDegrees().contains(tester2));
		assertTrue(degreeDAO.getAllDegrees().contains(tester3));
	}
	
	@Test
	public void testDelDegree(){
		Degree delTest = new Degree("Deleter");
		degreeDAO.saveDegree(delTest);
		degreeDAO.delDegree(delTest);
		assertNull(degreeDAO.getDegree(delTest.getId()));
		assertFalse(degreeDAO.getAllDegrees().contains(delTest));
	}

}
