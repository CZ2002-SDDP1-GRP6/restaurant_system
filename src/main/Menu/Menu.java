package main.Menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a menu that contains a list of menu items and promotions
 */
public class Menu {
    /** List of menu items or promotions within this menu */
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    /** Name of this menu */
    private String menuName;

    /**
     * Public constructor for Menu class, takes in a String parameter to store as
     * the name of this menu
     * 
     * @param menuName The name of the menu
     */
    public Menu(String menuName) {
        this.menuName = menuName;
    }

    /**
     * Gets name of this menu
     * 
     * @return name
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * Sets name of this menu using String parameter
     * 
     * @param menuName The name of the menu
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * Gets number of menu items and promotions (in total) within this menu
     * 
     * @return size
     */
    public int getMenuSize() {
        return this.menuItems.size();
    }

    /**
     * Returns an ArrayList of MenuItems which are the menu items within this menu
     * 
     * @return menuItems
     */
    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Prints all menu items within a menu
     */
    public void printItems() {
        if (menuItems.size() == 0)
            System.out.println("Menu is empty.");
        else {
            System.out.printf("%s Menu:\n", this.menuName);
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("Item No. %d -> ", i + 1);
                if (menuItems.get(i) instanceof Promotion) {
                    Promotion castedPromo = (Promotion) menuItems.get(i);
                    castedPromo.printInfo();
                } else
                    menuItems.get(i).printInfo();
            }
        }
    }

    /**
     * Adds menu items to this menu
     * 
     * @param item The MenuItem object
     */
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    /**
     * Updates fields like name, price, type, description, and (only for Promotions)
     * included items of menu items within this menu
     */
    public void updateItem() {
        Scanner scan = new Scanner(System.in);
        this.printItems();
        System.out.println("Which item in this menu would you like to update? Please enter its Item No.");
        int userInput = main.ErrorApp.safeInteger() - 1;
        if (userInput < menuItems.size() && menuItems.size() > 0) {
            if (menuItems.get(userInput) instanceof Promotion) {
                Promotion castedPromo = (Promotion) menuItems.get(userInput);
                int promoChoice = 0;
                do {
                    castedPromo.printInfo();
                    System.out.println("Which part of this promotion would you like to update?");
                    System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                            + "(5) Included Items\n" + "Enter -1 to stop updating");
                    promoChoice = main.ErrorApp.safeInteger();
                    switch (promoChoice) {
                    case 1:
                        System.out.println("What would you want to change this promo's name to?");
                        String inputName = scan.nextLine();
                        castedPromo.setName(inputName);
                        System.out.printf("Successfully changed, promo's new name is: %s\n", castedPromo.getName());
                        break;
                    case 2:
                        System.out.println("What would you want to change this promo's price to?");
                        double inputPrice = main.ErrorApp.safeDouble();
                        castedPromo.setPrice(inputPrice);
                        System.out.printf("Successfully changed, promo's new price is: %f\n", castedPromo.getPrice());
                        break;
                    case 3:
                        System.out.println("What would you want to change this promo's type to?");
                        String inputType = scan.nextLine();
                        castedPromo.setType(inputType);
                        System.out.printf("Successfully changed, promo's new type is: %s\n", castedPromo.getType());
                        break;
                    case 4:
                        System.out.println("What would you want to change this promo's description to?");
                        String inputDesc = scan.nextLine();
                        castedPromo.setDescription(inputDesc);
                        System.out.printf("Successfully changed, promo's new description is: %s\n",
                                castedPromo.getDescription());
                        break;
                    case 5:
                        int itemChoice = 0;
                        do {
                            System.out.println(
                                    "Would you like to add or remove items from this promo? Enter -1 to exit.");
                            System.out.println("(1) Add\n" + "(2) Remove");
                            itemChoice = main.ErrorApp.safeInteger();
                            switch (itemChoice) {
                            case 1:
                                System.out.println("What item would you like to add to this promo?");
                                String name = scan.nextLine();
                                castedPromo.addItem(name);
                                System.out.println("Updated promo item list:");
                                castedPromo.printItems();
                                break;
                            case 2:
                                System.out.println("What item would you like to remove from this promo?");
                                castedPromo.printItems();
                                int removeChoice = main.ErrorApp.safeInteger() - 1;
                                castedPromo.removeItem(removeChoice);
                                System.out.println("Updated promo item list:");
                                castedPromo.printItems();
                                break;
                            default:
                                System.out.println("All changes to promo item list completed.");
                                break;
                            }
                        } while (itemChoice != -1);
                        break;
                    default:
                        break;
                    }
                } while (promoChoice != -1);
            } else {
                int normalChoice = 0;
                do {
                    menuItems.get(userInput).printInfo();
                    System.out.println("Which part of this menu item would you like to update?");
                    System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                            + "Enter -1 to stop updating");
                    normalChoice = main.ErrorApp.safeInteger();
                    switch (normalChoice) {
                    case 1:
                        System.out.println("What would you want to change this item's name to?");
                        String inputName = scan.nextLine();
                        menuItems.get(userInput).setName(inputName);
                        System.out.printf("Successfully changed, item's new name is: %s\n",
                                menuItems.get(userInput).getName());
                        break;
                    case 2:
                        System.out.println("What would you want to change this item's price to?");
                        double inputPrice = main.ErrorApp.safeDouble();
                        menuItems.get(userInput).setPrice(inputPrice);
                        System.out.printf("Successfully changed, item's new price is: %f\n",
                                menuItems.get(userInput).getPrice());
                        break;
                    case 3:
                        System.out.println("What would you want to change this item's type to?");
                        String inputType = scan.nextLine();
                        menuItems.get(userInput).setType(inputType);
                        System.out.printf("Successfully changed, item's new type is: %s\n",
                                menuItems.get(userInput).getType());
                        break;
                    case 4:
                        System.out.println("What would you want to change this item's description to?");
                        String inputDesc = scan.nextLine();
                        menuItems.get(userInput).setDescription(inputDesc);
                        System.out.printf("Successfully changed, item's new description is: %s\n",
                                menuItems.get(userInput).getDescription());
                        break;
                    default:
                        break;
                    }
                } while (normalChoice != -1);
            }
        }
    }

    /**
     * Removes a menu item from this menu using an integer index parameter
     * 
     * @param index The array index of the menu item to be removed
     */
    public void removeItem(int index) {
        if (index >= 0 && index < menuItems.size())
            menuItems.remove(index);
        else
            System.out.println("Invalid Item No.");
    }

    /**
     * Allows adding menu items to orders
     * 
     * @param itemChoice The array index of the menu to find the requested item
     * @return itemToFind or null
     */
    public MenuItem findItem(int itemChoice) {
        MenuItem itemToFind = null;

        if (itemChoice >= 0 && itemChoice < menuItems.size()) {
            itemToFind = menuItems.get(itemChoice);
            return itemToFind;
        } else if (itemChoice >= menuItems.size()) {
            System.out.println("Invalid input.");
            return null;
        }
        return null;
    }
}
