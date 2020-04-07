package map;

import java.util.HashSet;

public class Room {
	private String name;
	private HashSet<Feature> items = new HashSet<>();
	private HashSet<Feature> exits = new HashSet<>();
	
	public Room(String name){
		this.name = name;
	}
	
	private void addFeatures(HashSet<Feature> featureSet, Feature...features){
		for(Feature f : features){
			featureSet.add(f);
		}
	}
	
	public void addItems(Item...items){
		addFeatures(this.items, items);
	}
	
	public void addExits(Exit...exits){
		addFeatures(this.exits, exits);
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	private String getFeatures(HashSet<Feature> featureSet){
		StringBuffer sb = new StringBuffer();
		for(Feature f : featureSet){
			sb.append(f.toString());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public String getItems(){
		return getFeatures(items);
	}
	
	public String getExits(){
		return getFeatures(exits);
	}
}
