package engine;

public class Distance {
	private String from;
	private String to ;
	private int distance;
	public Distance(String from , String to , int distance) {
		this.from = from;
		this.to=to;
		this.distance=distance;	
	}
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
	public int getDistance() {
		return distance;
	}
	
	public boolean equals(Object o) {
		Distance d=(Distance)o;
		return this.from.equals(d.from)&&this.to.equals(d.to);
	}
}
