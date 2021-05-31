package engine;

import java.io.*;
import java.util.*;
import units.*;

                             

public class Game {
	private Player player;
	private ArrayList<City> availableCities; // to add all the cities in the game
	private ArrayList<Distance> distances; // to add the distance* between each two cities
	private final int maxTurnCount = 30;
	private int currentTurnCount ;

	public Game(String playerName, String playerCity) throws IOException {
<<<<<<< HEAD
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		this.player = new Player(playerName);
		City x = new City(playerCity); // the city the player has chosen to play with, so he is controlling it
		player.getControlledCities().add(x); // add the city to the controlled cities
		loadCitiesAndDistances(); // to fill the above two arrayLists (available cities & distances)

		for (City e : availableCities) { // load the army of any available city except the player's city**
			if (!e.getName().equals(playerCity)) {  // to check that the current city isn't the player's city
				loadArmy(e.getName(), e.getName().toLowerCase() + "_army.csv");
			}
		}
=======
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
		
		
		
>>>>>>> b34c741ffaf19d4b4be9bd787b6128d00ced5ed7


	public void loadArmy(String cityName, String path) throws IOException {
		Army ar = new Army(cityName); // initialize a new army to defend the city
		ArrayList<Unit> b = new ArrayList<Unit>(); // arrayList to fill it with the units given the Csv file  
		String currentLine = "";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[] result = currentLine.split(",");  // Array contains the unit and the level ***
			if (result[0].equals("Archer") && 1 == Integer.parseInt(result[1])) {
				Archer a = new Archer(1, 60, 0.4, 0.5, 0.6); //to initialize a new archer(unit) with its values
				b.add(a); // if this unit is Archer and level 1 we add it to the arrayList of units
				
				// we check the unit and do the same for the whole Excel file(Csv.file)
				
			} else if (result[0].equals("Archer") && 2 == Integer.parseInt(result[1])) {
				Archer a = new Archer(2, 60, 0.4, 0.5, 0.6);
				b.add(a);

			} else if (result[0].equals("Archer") && 3 == Integer.parseInt(result[1])) {
				Archer a = new Archer(3, 70, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals("Infantry") && 1 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(1, 50, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals("Infantry") && 2 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(2, 50, 0.5, 0.6, 0.7);
				b.add(a);

			} else if (result[0].equals("Infantry") && 3 == Integer.parseInt(result[1])) {
				Infantry a = new Infantry(3, 60, 0.6, 0.7, 0.8);
				b.add(a);

			} else if (result[0].equals("Cavalry") && 1 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(1, 40, 0.6, 0.7, 0.75);
				b.add(a);

			} else if (result[0].equals("Cavalry") && 2 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(2, 40, 0.6, 0.7, 0.75);
				b.add(a);

			} else if (result[0].equals("Cavalry") && 3 == Integer.parseInt(result[1])) {
				Cavalry a = new Cavalry(3, 60, 0.7, 0.8, 0.9);
				b.add(a);

			}

		}
		br.close();
		
		// after the loop, we have arrayList contains All the units, so we can use this arrayList 
		//to initialize an Army

		ar.setUnits(b); // We set the arrayList of units to the army
		
		//We loop on the availableCities till we hit the city that we want to set this army for it
		
		for (int i = 0; i < availableCities.size(); i++) {
			if (availableCities.get(i).getName().equals(cityName)) {
				availableCities.get(i).setDefendingArmy(ar); // ****
			}
		}

	}

	private void loadCitiesAndDistances() throws IOException {
		String currentLine = "";
		FileReader fileReader = new FileReader("distances.csv");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[] result = currentLine.split(","); // Array contains (from city 1, to city 2, distance)
			boolean flag = false;
			for (int i = 0; i < this.availableCities.size(); i++) {
				if (availableCities.get(i).getName().equals(result[0])) { //to check if city 1 exists
					                                                      //in the availableCities
					flag = true; // if it exists
				}
			}
			if (!flag)
				this.availableCities.add(new City(result[0])); // if not add city 1
			flag = false;
			for (int i = 0; i < this.availableCities.size(); i++) {
				if (availableCities.get(i).getName().equals(result[1])) {//to check if city 2 exists
                                                                         //in the availableCities
					flag = true; //if it exists
				}
			}
			if (!flag)
				this.availableCities.add(new City(result[1])); // if not add city 2
			
			// add a new Distance to the distances arrayList
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

	public String toString() {
		return this.player.getName() + " is controlling " + this.player.getControlledCities();
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

<<<<<<< HEAD
	public static void main(String[] args) throws Exception {
		Game g = new Game("farghal", "Rome");

		System.out.println(g.getAvailableCities());
	}

=======
>>>>>>> b34c741ffaf19d4b4be9bd787b6128d00ced5ed7
}
// * : the ArrayList distances is of type <Distance> .. meaning to add an element to the arrayList, you 
//     should create a new Distance which takes parameters (from City1, to City 2, int dist)

//** : We loaded the army for the cities except for the player as the player will build his own army so
//     set its value to null

// *** : the array will look like ["Archer","1"], but this array is updated each loop and we use the 
//       values inside it to create a new unit and add it to the arrayList<units>

// **** : We set the defending army for each city to it. Ex-> if we loaded the army using the city name "Sparta"
//        and the Csv.file-> sparta_Army.csv , we will set the army of sparta to sparta and so on
