package pt.uminho.sysbio.biosynthframework.biodb.kegg.crawller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uminho.sysbio.biosynthframework.core.data.io.dao.biodb.kegg.RestKeggGenesDaoImpl;
import pt.uminho.sysbio.biosynthframework.core.data.io.dao.biodb.kegg.RestKeggGenomeDaoImpl;

public class keggGenomeAndGenesRunnable implements Runnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(keggGenomeAndGenesRunnable.class);
	String genome;
	RestKeggGenesDaoImpl genes;
	RestKeggGenomeDaoImpl genomeDao;
	int id;
	
	public keggGenomeAndGenesRunnable(int id, String folder, String genome) {
		this.genome = genome;
		genes = new RestKeggGenesDaoImpl();
		genes.setLocalStorage(folder);
		genes.setSaveLocalStorage(true);
		genes.setUseLocalStorage(true);
		genes.createFolder();
		
		genomeDao = new RestKeggGenomeDaoImpl();
		genomeDao.setLocalStorage(folder);
		genomeDao.setSaveLocalStorage(true);
		genomeDao.setUseLocalStorage(true);
		genomeDao.createFolder();
		this.id = id;
	}
	
	public void run() {
		
		try {
			genomeDao.getGenomeByEntry(genome);
			Set<String> geneIds = genes.getAllGenesEntries(genome);
			for(String gId : geneIds){
				genes.getGeneByEntry(gId);
			}
			
			LOGGER.info("Thread {} genome " + genome + "\tcompleted\t"+geneIds.size(), id );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}
}