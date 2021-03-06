package edu.uminho.biosynth.core.data.io.dao.biodb.kegg;

import org.junit.Before;

import pt.uminho.sysbio.biosynthframework.biodb.kegg.KeggDrugMetaboliteCrossreferenceEntity;
import pt.uminho.sysbio.biosynthframework.biodb.kegg.KeggDrugMetaboliteEntity;
import pt.uminho.sysbio.biosynthframework.core.data.io.factory.KeggDaoFactory;
import pt.uminho.sysbio.data.test.mother.HbmKeggDrugMetaboliteMother;
import pt.uminho.sysbio.data.test.rule.SessionFactoryRule;
import edu.uminho.biosynth.core.data.io.dao.biodb.AbstractTestHbmMetaboliteDaoTemplate;

public class TestHbmKeggDrugMetaboliteDaoImpl
extends AbstractTestHbmMetaboliteDaoTemplate<KeggDrugMetaboliteEntity>{

	private HbmKeggDrugMetaboliteMother hbmKeggDrugMetaboliteMother;
	
	@Before
	public void setUp() throws Exception {
		this.metaboliteDao = new KeggDaoFactory()
			.withSessionFactory(this.getSessionFactory())
			.buildHbmKeggDrugMetaboliteDao();
		
		hbmKeggDrugMetaboliteMother = new HbmKeggDrugMetaboliteMother(
				this.getSessionFactory().getCurrentSession());
		this.hbmObjectMother = hbmKeggDrugMetaboliteMother;
		
		this.entry = this.hbmKeggDrugMetaboliteMother.getEntry();
	}
	
	public TestHbmKeggDrugMetaboliteDaoImpl() {
		super(new SessionFactoryRule(
				KeggDrugMetaboliteEntity.class,
				KeggDrugMetaboliteCrossreferenceEntity.class));
	}
}
