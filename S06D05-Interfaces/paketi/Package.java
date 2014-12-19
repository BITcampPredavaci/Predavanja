package paketi;

import vic.Uporedivi;

public class Package implements Uporedivi {
	protected double width;
	protected double height;
	protected double length;
	protected double weight;
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return getWeight() * 3;
	}
	
	@Override
	public String toString() {	
		FormatPackage fp = new FormatPackage();
		return fp.format();
	}

	@Override
	public int uporedi(Uporedivi other) {
		Package otherPackage = (Package)other;
		
		if (getWeight() < otherPackage.getWeight()) {
			return -1;
		} else if (getWeight() == otherPackage.getWeight()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public class FormatPackage {
		
		public String format() {
			String output = "Paket (";
			output += getWidth() + "x";
			output += getHeight() + "x";
			output += getLength();
			output += ") ";
			
			output += "[ ";
			output += "težina=" + getWeight() + ", ";
			output += "cijena=" + getPrice();
			output += " ]";
			
			return output;
		}
	}
}
