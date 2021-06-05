package engine;

import java.io.*;
import java.util.*;

import org.junit.Ignore;

import exceptions.*;
import units.*;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount ;

	public Game(String playerName, String playerCity) throws IOException {
		currentTurnCount = 1;
		availableCities= new ArrayList<City>();
		distances = new ArrayList<Distance>();
		this.player = new Player(playerName);
		City x=new City(playerCity);
		x.setDefendingArmy(null);
		player.getControlledCities().add(x);
		loadCitiesAndDistances();
		for (int i = 0; i < availableCities.size(); i++) {
			if(availableCities.get(i).getName().equals(playerCity)) {
				availableCities.get(i).setDefendingArmy(null);
			}
		}
		for (City c : availableCities) {
			if (!(c.getName().equals(playerCity)))
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");
		}
	}
		
		
		


	public void loadArmy(String cityName, String path) throws IOException {
		Army ar = new Army(cityName);
		ArrayList<Unit>b=new ArrayList<Unit>();
		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[]result = currentLine.split(",");
			if (result[0].equals("Archer") && 1 == Integer.parseInt(result[1])) {
				Archer a = new Archer(1, 60, 0.4, 0.5, 0.6);
				a.setParentArmy(ar);
				b.add(a);
			} else if (result[0].equals("Archer") && 2 == Integer.parseInt(result[1])) {
				Archer a = new Archer(2, 60, 0.4, 0.5, 0.6);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals("Archer") && 3 == Integer.parseInt(result[1])) {
				Archer a = new Archer(3, 70, 0.5, 0.6, 0.7);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 1 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(1, 50, 0.5, 0.6, 0.7);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 2 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(2, 50, 0.5, 0.6, 0.7);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 3 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(3, 60, 0.6, 0.7, 0.8);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals( "Cavalry") && 1 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(1, 40, 0.6, 0.7, 0.75);
				a.setParentArmy(ar);
				b.add(a);

			} else if (result[0].equals( "Cavalry")&& 2 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(2, 40, 0.6, 0.7, 0.75);
				a.setParentArmy(ar);
				b.add(a);
				

			} else if (result[0].equals( "Cavalry") && 3 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(3, 60, 0.7, 0.8, 0.9);
				a.setParentArmy(ar);
				b.add(a);

			}

		}
		br.close();
		
		ar.setUnits(b);
		for (int i = 0; i < availableCities.size(); i++) {
			if(availableCities.get(i).getName().equals(cityName)) {
				availableCities.get(i).setDefendingArmy(ar);
			}
		}
		
	}
	
	private void loadCitiesAndDistances() throws IOException {
		String currentLine = "";
		FileReader fileReader = new FileReader("distances.csv");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[] result = currentLine.split(",");
			boolean flag=false;
			for (int i = 0; i < this.availableCities.size(); i++) {
				if(availableCities.get(i).getName().equals(result[0])) {
					flag=true;
				}
			}
			if(!flag)this.availableCities.add(new City(result[0]));
			flag=false;
			for (int i = 0; i < this.availableCities.size(); i++) {
				if(availableCities.get(i).getName().equals(result[1])) {
					flag=true;
				}
			}
			if(!flag)this.availableCities.add(new City(result[1]));
			this.distances.add(new Distance(result[0], result[1], Integer.parseInt(result[2])));
		}
	}
	
	public void targetCity(Army army, String targetName) {
		
	}
	
	public void endTurn() {
		
	}
	
	public void occupy(Army a,String cityName) {
		City c = new City(cityName);
		c.setDefendingArmy(a);
		c.setTurnsUnderSiege(0);
		this.player.getControlledCities().add(c);
	
	}
	
	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException{
		if(attacker.getUnits().size()==0 || defender.getUnits().size()==0) {
			if (defender.getUnits().size()==0) {
				this.occupy(defender, defender.getCurrentLocation());
			}
			return;
		}
		int turn=0;
		Random rn = new Random();
		int randomA = rn.nextInt(attacker.getUnits().size());
		int randomB = rn.nextInt(defender.getUnits().size());
		Unit a = attacker.getUnits().get(randomA);
		Unit b = defender.getUnits().get(randomB);
		if (turn %2==0) {
			a.attack(b);	
		}
		else {
			b.attack(a);
			
		}
		autoResolve(attacker, defender);
		
	}
	
	public boolean isGameOver() {
		return true;
	}
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

}
