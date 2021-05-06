package engine;

import java.io.*;
import java.util.*;

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
		//load the army of any available city
		for (City e:availableCities) {
			if(!e.getName().equals(playerCity)) {
				loadArmy(e.getName(),e.getName().toLowerCase()+"_army.csv" );
			}
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
				b.add(a);
			} else if (result[0].equals("Archer") && 2 == Integer.parseInt(result[1])) {
				Archer a = new Archer(2, 60, 0.4, 0.5, 0.6);
				b.add(a);

			} else if (result[0].equals("Archer") && 3 == Integer.parseInt(result[1])) {
				Archer a = new Archer(3, 70, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 1 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(1, 50, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 2 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(2, 50, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals( "Infantry") && 3 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(3, 60, 0.6, 0.7, 0.8);
				b.add(a);

			} else if (result[0].equals( "Cavalry") && 1 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(1, 40, 0.6, 0.7, 0.75);
				b.add(a);

			} else if (result[0].equals( "Cavalry")&& 2 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(2, 40, 0.6, 0.7, 0.75);
				b.add(a);
				

			} else if (result[0].equals( "Cavalry") && 3 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(3, 60, 0.7, 0.8, 0.9);
				b.add(a);

			}

		}
		
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
