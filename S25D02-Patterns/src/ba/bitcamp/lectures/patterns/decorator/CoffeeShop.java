package ba.bitcamp.lectures.patterns.decorator;

public class CoffeeShop {
	public static void main(String[] args) {
		Beverage bev = new Coffee();
		bev = new BeverageWithMilk(bev);
		bev = new BeverageWithChocolate(bev);
		
		System.out.println(bev.price());
	}
}
