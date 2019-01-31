package owlapi.tutorial.msc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class OWLAPIFirst {
	public static void main(String[] args) throws OWLOntologyStorageException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology o = null;
		int nindividuals=0;
		ArrayList<String> IDCategory = new ArrayList<String>(); //2 liste per fare l'associazione tra ID e Classe
		ArrayList<String> Classes = new ArrayList<String>();
		IDCategory.add("15687");
		Classes.add("MensTshirts");
		IDCategory.add("53159");
		Classes.add("WomensShirtsOrBlouses");
		IDCategory.add("137084");
		Classes.add("MensAthleticWear");
		IDCategory.add("57988");
		Classes.add("MensCoatsOrJackets");
		IDCategory.add("3001");
		Classes.add("MensSuits");
		IDCategory.add("53159");
		Classes.add("WomensShirtsOrBlouses");
		IDCategory.add("11483");
		Classes.add("MensSlacksOrTrousersOrShorts");
		IDCategory.add("11484");
		Classes.add("MensSweaters");
		IDCategory.add("11510");
		Classes.add("MensPajamasOrNightshirtsOrRobes");
		IDCategory.add("57989");
		Classes.add("MensSlacksOrTrousersOrShorts");
		IDCategory.add("93427");
		Classes.add("MensShoes");
		IDCategory.add("15690");
		Classes.add("Swimwear");
		IDCategory.add("93427");
		Classes.add("MensShoes");
		IDCategory.add("63862");
		Classes.add("WomenCoatsOrJacket");
		IDCategory.add("51933");
		Classes.add("BoysCoatsOrJacket");
		IDCategory.add("51580");
		Classes.add("GirlsCoatsOrJacket");
		IDCategory.add("51582");
		Classes.add("GirlsSweaters");
		IDCategory.add("51946");
		Classes.add("BoysSweaters");
		IDCategory.add("175528");
		Classes.add("GirlSuits");
		IDCategory.add("147337");
		Classes.add("InfantSuits");
		IDCategory.add("11510");
		Classes.add("MenPajamasOrNightshirtsOrRobes");
		IDCategory.add("84544");
		Classes.add("BoysPajamasOrNightshirtsOrRobes");
		IDCategory.add("99735");
		Classes.add("GirlsPajamasOrNightshirtsOrRobes");
		IDCategory.add("63853");
		Classes.add("Brassieres");
		IDCategory.add("63854");
		Classes.add("Slips");
		IDCategory.add("11507");
		Classes.add("Slips");
		IDCategory.add("15689");
		Classes.add("MenSlacksTrousersOrShorts");
		IDCategory.add("11555");
		Classes.add("WomenSlacksTrousersOrShorts");
		IDCategory.add("15615");
		Classes.add("BoysSlacksTrousersOrShorts");
		IDCategory.add("15648");
		Classes.add("GirlsSlacksTrousersOrShorts");
		IDCategory.add("11511");
		Classes.add("Socks");
		IDCategory.add("11524");
		Classes.add("Socks");
		IDCategory.add("153797");
		Classes.add("Socks");
		IDCategory.add("153564");
		Classes.add("Socks");
		IDCategory.add("163220");
		Classes.add("Socks");
		IDCategory.add("147284");
		Classes.add("Socks");
		IDCategory.add("57749");
		Classes.add("Socks");
		
		System.out.println(IDCategory.get(0));

		File file = new File("C:/Users/valle/Desktop/unspsc1.owl");//per ora usiamo solo unspsc 
		try {//carichiamo l'ontologia da file per lavorarci su
			o=man.loadOntologyFromOntologyDocument(file);
			System.out.println(o); //stampa alcune assertion assertion
	        IRI documentIRI = man.getOntologyDocumentIRI(o);
	        System.out.println("    from: " + documentIRI);
	        //System.out.println("RDF/XML: "); questo stampa tutta l'ontologia in RDF
	        //man.saveOntology(o, new StreamDocumentTarget(System.out));
		}
		catch (OWLOntologyCreationException e) {
			e.printStackTrace();
			
		}
		System.out.println(man.getOntologies().size());
		
		//xml document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByCategory&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=Giuseppe-GetUserP-PRD-7393cab4a-1cbd1da2&RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD&categoryId=11450&paginationInput.entriesPerPage=200";
		Document doc = null;
		//ottiene un documento da quella stringa url
		try {
			doc = db.parse(new URL(url).openStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("item");
		Clothes vestito[] = new Clothes[200];
		System.out.println("oh");
		for (int temp = 0; temp < nList.getLength(); temp++) {//questa è la fase di parsing del file xml
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("title : "+ eElement.getElementsByTagName("title").item(0).getTextContent());
               System.out.println("categoryName : "+eElement.getElementsByTagName("categoryName").item(0).getTextContent());
               System.out.println("IDcategory : "+eElement.getElementsByTagName("categoryId").item(0).getTextContent());
               vestito[temp]=new Clothes(eElement.getElementsByTagName("categoryName").item(0).getTextContent(),eElement.getElementsByTagName("title").item(0).getTextContent(),"null",eElement.getElementsByTagName("categoryId").item(0).getTextContent());
            }
            //if ((vestito[temp].getTitle().toLowerCase().indexOf(searchM.toLowerCase()) != -1) && (vestito[temp].getCategory()=="" ))
            
            for(int j=0;j<IDCategory.size();j++) { //controllo se il codice è presente nella lista (e quindi esiste un associazione tra IDCategory e le classi della nostra ontologia
            	if (Integer.parseInt(vestito[temp].getIDcategory())==Integer.parseInt(IDCategory.get(j))) {
            		System.out.println("I?MIN");
	     		   vestito[temp].setOWLClass(Classes.get(j));
	     		}
	       }
		}
		
		
	    OWLDataFactory factory = man.getOWLDataFactory();
	    OWLClass cls;
	    for (int i=0; i<nList.getLength(); i++) {
	    	System.out.println(vestito[i].getOWLClass());
	    	OWLIndividual ind = factory.getOWLNamedIndividual(IRI.create("C:/Users/valle/Desktop/unspsc1.owl#" + vestito[i].getTitle()));
	    if(vestito[i].getOWLClass()!= "null") {  //carico nell'ontologia solo se ho trovato una corrispondenza della classe
	    	nindividuals++;
	    	cls = factory.getOWLClass(IRI.create("http://www.daml.org/2004/05/unspsc/unspsc.owl#" + vestito[i].getOWLClass()));
		    OWLAxiom axiom = factory.getOWLClassAssertionAxiom(cls, ind);//un assioma associa classe e individuo
		    AddAxiom addAxion = new AddAxiom(o, axiom);
		     man.applyChange(addAxion);
			 }
        }
        File newfile = new File("C:/Users/valle/Desktop/unspsc1new.owl");//per salvare la nuova ontologia ne creo uno nuova in realtà e la salvo
        System.out.println("Numero individui caricati: "+nindividuals);
        man.saveOntology(o , IRI.create(newfile.toURI()));
        //System.out.println("RDF/XML: "); //questo stampa tutta l'ontologia in RDF
        //man.saveOntology(o, new StreamDocumentTarget(System.out));
	}
}
