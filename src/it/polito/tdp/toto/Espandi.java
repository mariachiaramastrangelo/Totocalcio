package it.polito.tdp.toto;

import java.util.ArrayList;
import java.util.List;

public class Espandi {
	private List<Schedina> soluzioni;
	
	public List<Schedina> espandiPronostico(Pronostico p) {
		Schedina parziale= new Schedina(p.getN());
		soluzioni= new ArrayList<Schedina>();
		
		espandi(p, parziale, 0);
		return soluzioni;
	}
	//il livello della ricorsione è la singola partita 
	//quindi se ho il livello=0 significa che ho una schedina con 0 risultati etc
	
	private void espandi(Pronostico p, Schedina parziale, int livello) {
		//riceve u problema da risolvere già parzialmente risolto 
		//che è il pronostico dell'utente e un livello parziale
		
		//es. se sono al livello 5 ho una schedina che contiene già 4 previsioni  
		//parizale contiene già (livello) valori nelle posizioni da 0 a (livello-1)
		//quindi io devo determinare parziale[livello] cioè livello+1esima riga
		//sulla base del pronostico corrispondente 
		
		//io prendo la riga su ciu devo lavoare che è livello 
		//la classe previsione gestisce un set di valori 
		//quindi i tentativi che posso fare a questo livello sono tutti i valori a questo set
		
		if(livello==p.getN()) {
			//System.out.println(parziale);
			//aggiunta grazie a un metodo clonante
			this.soluzioni.add(new Schedina(parziale));
			return;
		}
		Previsione prev= p.get(livello);
		
		//prova le varie alternative iterando tra i risultati 
		//getvalori mi dà il set e itero su questo set estraendo un risultato per volta 
		
		for(Risultato r: prev.getValori()) {
			//provo ad aggiungere 'r' alla soluzione 
			parziale.add(r);
			
			espandi(p, parziale, livello+1);
			
			//backtrack
			//dopo che ho aggiunto un elemento lo tolgo faccio letteralmente le "prove"
			//alla fine al soluzione parziale era come prima 
			parziale.removeLast();
			
			
		}
		
		//si ferma quando il livello è tale per cui il pronostico è stato esaurito 
	}
	

}
