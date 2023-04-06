package generics;

public class GenericDriver {
	
    public static void main(String[] args) {
    	Set<GenericClass<?>> set = new Set();
        SetMenu menu = new SetMenu(set);
        menu.run();
    }
}
