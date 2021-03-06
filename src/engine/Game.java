package engine;

import java.io.*;
import java.util.*;

import org.junit.Ignore;

import exceptions.*;
import units.*;
import buildings.*;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 50;
	private int currentTurnCount ;
	private String autoevent;
	public Game(String playerName, String playerCity) throws IOException {
		autoevent="";
		player = new Player(playerName);
		player.setTreasury(5000);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);

			else
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
		String from = army.getCurrentLocation();
		if (army.getCurrentLocation().equals("onRoad"))
			from = army.getTarget();
		for (Distance d : distances) {
			if ((d.getFrom().equals(from) || d.getFrom().equals(targetName))
					&& (d.getTo().equals(from) || d.getTo().equals(targetName))) {
				army.setTarget(targetName);
				int distance = d.getDistance();
				if (army.getCurrentLocation().equals("onRoad"))
					distance += army.getDistancetoTarget();
				army.setDistancetoTarget(distance);
			}
		}
	}
	
	public void endTurn() {
		currentTurnCount++;
		ArrayList<City>c=player.getControlledCities();
		ArrayList<City>x=availableCities;
		for(City curr:x) {
			if(curr.isUnderSiege()) {
				if(curr.getTurnsUnderSiege()>=3) {
					curr.setUnderSiege(false);
				}else {
					curr.setTurnsUnderSiege(curr.getTurnsUnderSiege()+1);
					Army a=curr.getDefendingArmy();
					for (int j = 0; j <a.getUnits().size(); j++) {
						Unit cc=a.getUnits().get(j);
						cc.setCurrentSoldierCount((cc.getCurrentSoldierCount()-(int)(cc.getCurrentSoldierCount()*0.1)));
					}
				}	
			}
		}
		double foodNeeded=0;
		for (int i = 0; i < c.size();i++) {
			City curr=c.get(i);
			foodNeeded+=curr.getDefendingArmy().foodNeeded();
			for (int j = 0; j < curr.getEconomicalBuildings().size(); j++) {
				EconomicBuilding b=curr.getEconomicalBuildings().get(j);
				if(b instanceof Farm) {
					player.setFood(player.getFood()+b.harvest());
				}else {
					player.setTreasury(player.getTreasury()+b.harvest());
				}
				b.setCoolDown(false);
			}
			for (int j = 0; j < curr.getMilitaryBuildings().size(); j++) {
				MilitaryBuilding b=curr.getMilitaryBuildings().get(j);
				b.setCurrentRecruit(0);
				b.setCoolDown(false);
			}
		}
		ArrayList<Army>a=player.getControlledArmies();
		for (int i = 0; i <a.size(); i++) {
			Army curr=a.get(i);
			if (!curr.getTarget() .equals("") && curr.getCurrentStatus() == Status.IDLE) {
				curr.setCurrentStatus(Status.MARCHING);
				curr.setCurrentLocation("onRoad");
			}
			if(curr.getDistancetoTarget()>0 &&!curr.getTarget().equals("")) {
				curr.setDistancetoTarget(curr.getDistancetoTarget() - 1);
				if (curr.getDistancetoTarget() == 0) {
					curr.setCurrentLocation(curr.getTarget());
					curr.setTarget("");
					curr.setCurrentStatus(Status.IDLE);
				}
			}
			foodNeeded+=curr.foodNeeded();
		}
		if(foodNeeded>player.getFood()) {
			player.setFood(0);
			for (int i = 0; i < a.size(); i++) {
				Army curr=a.get(i);
				for (int j = 0; j <curr.getUnits().size(); j++) {
					Unit cc=curr.getUnits().get(j);
					cc.setCurrentSoldierCount( (cc.getCurrentSoldierCount()-(int)(cc.getCurrentSoldierCount()*0.1)));
				}
			}
		}else {
			player.setFood(player.getFood()-foodNeeded);
		}
	}
	
	public void occupy(Army a,String cityName) {
		for (City c : availableCities) {
			if (c.getName().equals(cityName)) 
			{
				c.setDefendingArmy(a);
				player.getControlledArmies().remove(a);
				a.setCurrentStatus(Status.IDLE);
				c.setUnderSiege(false);
				c.setTurnsUnderSiege(-1);
				this.player.getControlledCities().add(c);
			}
		}
	}
	static int turn=0;
	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException{
		if (this.player.getControlledArmies().contains(defender)) {
			throw new FriendlyFireException();
		}
		if(attacker.getUnits().size()==0 || defender.getUnits().size()==0) {
			if (defender.getUnits().size()==0) {
				this.occupy(attacker, defender.getCurrentLocation());
			}
			turn = 0;
			return;
		}
		
		Random rn = new Random();
		int randomA = rn.nextInt(attacker.getUnits().size());
		int randomB = rn.nextInt(defender.getUnits().size());
		Unit a = attacker.getUnits().get(randomA);
		Unit b = defender.getUnits().get(randomB);
		String w;
		String t;
		if (a instanceof Archer)w="Archer";
		 else if (a instanceof Cavalry)w= "Cavalry";
		 else w= "Infantry";
			 if (b instanceof Archer)t="Archer";
			 else if (b instanceof Cavalry)t= "Cavalry";
			 else t= "Infantry";
		if (turn %2==0) {
			a.attack(b);
			autoevent += this.getPlayer().getName()+"\n"+"-" + w +" "+"attacked"+"  "+ t + "\n" ;
			autoevent += "Remaining soldiers : "+ " "+ b.getCurrentSoldierCount()+"\n";
		}
		else {
			b.attack(a);
			autoevent += "PC"+"\n"+"-" + t +" "+"attacked"+"  "+ w + "\n" ;
			autoevent += "Remaining soldiers : "+ " "+ a.getCurrentSoldierCount()+"\n";
			
		}
		turn++;
		autoResolve(attacker, defender);
		
	}
	
	public boolean isGameOver() {
		if(currentTurnCount>maxTurnCount||availableCities.size()==player.getControlledCities().size()) {
			return true;
		}else {
			return false;
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





	public String getAutoevent() {
		return autoevent;
	}





	public void setAutoevent(String autoevent) {
		this.autoevent = autoevent;
	}
}
