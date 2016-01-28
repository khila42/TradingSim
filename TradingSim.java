import java.util.Scanner;

public class TradingSim
{
    //varibles to be shared within the program
    public static final int DAYS_IN_A_YEAR = 365;
    private static final Scanner kbd = new Scanner(System.in); //keyboard
    //Scanner
    public static Country location; //Where the user currently is
    public static int goldStock; //how much gold the user has
    public static int teaStock;  //how much tea the user has
    public static int steelStock; //how much steel the user has
    public static int oilStock; //how much oil the user has
    public static int woolStock; //how much wool the user has
    public static int velvetStock; //how much velvet the user has
    public static Country[] countries = new Country[5]; //array for all the
    //countries

    /**
     * A first attempt at creating a game. It allows the user to travel
     * between 5 locations buying and selling different commodities over the
     * course of a year. Every transaction they make as well as everytime they
     * travel will take 1 day. At the end of the day every country has a
     * chance to have either a major or minor or perhaps no random event
     * happening causing a particular commodity in the country to rapidly
     * raise or fall in price.
     * At the end of the 365 days or when the user decides to quit they will
     * be presented with the final amount of money they made during their
     * endevours.
     */
    public static void main(String args[])
    {
        //the 5 countries being created
        countries [0]= new Country("USA", 300, 30, 200, 450, 40, 350, 200,
        50, 300, 150, 500, 20, 1.3, 0.5, 1.33, 2.0, 0.9, 2.2);
        countries [1] = new Country("England", 250, 100, 150, 400, 60,
        375, 150, 200, 150, 120, 400, 33, 1.5, 1.1, 1.0, 1.3, 0.9, 2.5);
        countries [2] = new Country("China", 200, 45, 300, 650, 50, 300,
        250, 200, 600, 750, 700, 12, 1.1, 1.3, 1.67, 1.8, 0.66, 2.0);
        countries [3] = new Country("India", 150, 65, 150, 350, 68, 700,
        300, 160, 200, 500, 600, 100, 0.8, 1.2, 0.6, 1.5, 1.2, 3.0);
        countries [4] = new Country("Russia", 400, 20, 450, 650, 100, 250,
        300, 100, 750, 600, 250, 10, 1.3, 0.8, 1.9, 1.8, 1.1, 2.3);
        location = countries [0];//The user starts in the USA
        int days = DAYS_IN_A_YEAR; //number of days starts at 365 (one year)
        int cash = 10000;//The user starts with $10 000
        /*
        The user begins with no commodities
         */
        goldStock = 0;
        teaStock = 0;
        steelStock = 0;
        oilStock = 0;
        woolStock = 0;
        velvetStock = 0;
        //If the user took an action to end the day (checking stock, prices
        //and commands do not end the day)
        boolean actionTaken;
        //Introduce the program
        System.out.println("\nIn this game you have 1 year to travel around "
        + "the world buying low\nand selling high and making a fortane. " +
        "You have $10 000 to start your enterpise\nwith. Your start " +
        "location will be the United States of America. Every\ntransaction" +
        " you make will count as one day, at the end of the day the" +
        " markets\nwill change and you will get a newsfeed for any large " +
        "\nshifts positive or negative");
        //Continue to loop as long as there's days left
        do
        {
            //Assume they haven't ended the day
            actionTaken = false;
            //Find out their action
            char action = getAction();
            //If they choose the question mark...
            if (action == '?')
            {
                //...Display the options menu
                optionsMenu();
            }
            //If they choose c...
            else if (action == 'c')
            {
                //...Show them the prices of all the commodities in their
                //current location formatted to 2 decimal places
                System.out.printf("Gold:" + " %.2f %n",
                location.getGoldPrice());
                System.out.printf("Tea:" + " %.2f %n",
                location.getTeaPrice());
                System.out.printf("Steel:" + " %.2f %n",
                location.getSteelPrice());
                System.out.printf("Oil:" + " %.2f %n",
                location.getOilPrice());
                System.out.printf("Wool:" + " %.2f %n",
                location.getWoolPrice());
                System.out.printf("Velvet:" + " %.2f %n",
                location.getVelvetPrice());
            }
            //if they choose b...
            else if(action == 'b')
            {
                //...Find out what item they want to buy
                char buyItem = getBuy();
                //If they want gold...
                if(buyItem == 'g')
                {
                    int bought;
                    int cost;
                    do
                    {
                        //Find out how much they want
                        bought = location.buyGold();
                        //And how much it will cost
                        cost = location.costOfGold(cash, bought);
                        //System.out.println(bought);
                        //If it cost's more then they have in cash...
                        if(cost > cash)
                        {
                            //...Tell them
                            System.out.println("Invalid amount: cost is " +
                            "greater than cash");
                            pause();
                        }
                    }
                    while(cost > cash); //Keep looping until they enter a
                    //valid amount
                    System.out.println();
                    //If they bought something...
                    if(cost != 0)
                    {
                        //end the day
                        actionTaken = true;
                        //add the amount bought to their stock
                        goldStock += bought;
                        //and take the cost from their cash
                        cash -= cost;
                    }

                }
                //Same for tea
                else if(buyItem == 't')
                {
                    int bought;
                    int cost;
                    do
                    {
                        bought = location.buyTea();
                        cost = location.costOfTea(cash, bought);
                        if(cost > cash)
                        {
                            System.out.println("Invalid amount: cost is " +
                            "greater"
                            + " than cash");
                            pause();
                        }
                    }
                    while(cost > cash);
                    System.out.println();
                    if(cost != 0)
                    {
                        actionTaken = true;
                        teaStock += bought;
                        cash -= cost;
                    }
                }
                //and steel
                else if(buyItem == 's')
                {
                    int bought;
                    int cost;
                    do
                    {
                        bought = location.buySteel();
                        cost = location.costOfSteel(cash, bought);
                        if(cost > cash)
                        {
                            System.out.println("Invalid amount: cost is " +
                            "greater"
                            + " than cash");
                            pause();
                        }
                    }
                    while(cost > cash);
                    System.out.println();
                    if(cost != 0)
                    {
                        actionTaken  = true;
                        steelStock += bought;
                        cash -= cost;
                    }
                }
                //and oil
                else if(buyItem == 'o')
                {
                    int bought;
                    int cost;
                    do
                    {
                        bought = location.buyOil();
                        cost = location.costOfOil(cash, bought);
                        if(cost > cash)
                        {
                            System.out.println("Invalid amount: cost is " +
                            "greater than cash");
                            pause();
                        }
                    }
                    while(cost > cash);
                    System.out.println();
                    if(cost != 0)
                    {
                        actionTaken = true;
                        oilStock += bought;
                        cash -= cost;
                    }
                }
                //and wool
                else if(buyItem == 'w')
                {
                    int bought;
                    int cost;
                    do
                    {
                        bought = location.buyWool();
                        cost = location.costOfWool(cash, bought);
                        if(cost > cash)
                        {
                            System.out.println("Invalid amount: cost is " +
                            "greater than cash");
                            pause();
                        }
                    }
                    while(cost > cash);
                    System.out.println();
                    if(cost != 0)
                    {
                        actionTaken = true;
                        woolStock += bought;
                        cash -= cost;
                    }
                }
                //and velvet
                else if(buyItem == 'v')
                {
                    int bought;
                    int cost;
                    do
                    {
                        bought = location.buyVelvet();
                        cost = location.costOfVelvet(cash, bought);
                        if(cost > cash)
                        {
                            System.out.println("Invalid amount: cost is " +
                            "greater than cash");
                            pause();
                        }
                    }
                    while(cost > cash);
                    System.out.println();
                    if(cost != 0)
                    {
                        velvetStock += bought;
                        cash -= cost;
                        actionTaken = true;
                    }
                }
            }
            //If they choose o...
            else if (action == 'o')
            {
                //...Show them their current stock
                System.out.println("Gold:\t" + goldStock + "\nTea:\t"
                + teaStock + "\nSteel:\t" + steelStock + "\nOil\t"
                + oilStock + "\nWool:\t" + woolStock + "\nVelvet:\t"
                + velvetStock + "\nCash:\t" + cash);
            }
            //...If they choose s...
            else if (action == 's')
            {
                //...Find out what item they want to sell
                char sellItem = getSellItem();
                //If they want to sell gold....
                if(sellItem == 'g')
                {
                    //Make sure they actually have any gold
                    if(goldStock == 0)
                    {
                        System.out.println("You do not have any gold.");
                        pause();
                    }
                    else
                    {
                        //Find out how many they want to sell
                        int sold = location.sellGold(goldStock);
                        //How much they will Make
                        int profit = location.incomeOfGold(sold);
                        //If they went through with the sale...
                        if(profit != 0)
                        {
                            //Take the stock away
                            goldStock -= sold;
                            //Add the profit to their cash
                            cash += profit;
                            //End the day
                            actionTaken = true;
                        }
                    }
                }
                //See gold
                else if(sellItem == 't')
                {
                    if(teaStock == 0)
                    {
                        System.out.println("You do not have any tea.");
                        pause();
                    }
                    else
                    {
                        int sold = location.sellTea(teaStock);
                        int profit = location.incomeOfTea(sold);
                        if(profit != 0)
                        {
                            actionTaken = true;
                            teaStock -= sold;
                            cash += profit;
                        }
                    }
                }
                //See gold
                else if(sellItem == 's')
                {
                    if(steelStock == 0)
                    {
                        System.out.println("You do not have any steel.");
                        pause();
                    }
                    else
                    {
                        int sold = location.sellSteel(steelStock);
                        int profit = location.incomeOfSteel(sold);
                        if(profit != 0)
                        {
                            actionTaken = true;
                            steelStock -= sold;
                            cash += profit;
                        }
                    }
                }
                //See gold
                if(sellItem == 'o')
                {
                    if(oilStock == 0)
                    {
                        System.out.println("You do not have any oil.");
                        pause();
                    }
                    else
                    {
                        int sold = location.sellOil(oilStock);
                        int profit = location.incomeOfOil(sold);
                        if(profit != 0)
                        {
                            actionTaken = true;
                            oilStock -= sold;
                            cash += profit;
                        }
                    }
                }
                //See gold
                if(sellItem == 'w')
                {
                    if(woolStock == 0)
                    {
                        System.out.println("You do not have any wool.");
                        pause();
                    }
                    else
                    {
                        int sold = location.sellWool(woolStock);
                        int profit = location.incomeOfWool(sold);
                        if(profit!=0)
                        {
                            actionTaken = true;
                            woolStock -= sold;
                            cash += profit;
                        }
                    }
                }
                //See gold
                if(sellItem == 'v')
                {
                    if(velvetStock == 0)
                    {
                        System.out.println("You do not have any velvet.");
                        pause();
                    }
                    else
                    {
                        int sold = location.sellVelvet(velvetStock);
                        int profit = location.incomeOfVelvet(sold);
                        if(profit != 0)
                        {
                            velvetStock -= sold;
                            cash += profit;
                            actionTaken = true;
                        }
                    }
                }
            }
            //If they select move...
            else if (action == 'm')
            {
                //Ask where they want to move to
                System.out.print("\nWhere would you like to go?\n(U)SA" +
                "\n(E)ngland\n(C)hina\n(I)ndia\n(R)ussia ");
                String input = kbd.nextLine();
                //ensure the option was a valid response
                while(!input.equalsIgnoreCase("e") &&
                !input.equalsIgnoreCase("u") &&
                !input.equalsIgnoreCase("c") &&
                !input.equalsIgnoreCase("i") &&
                !input.equalsIgnoreCase("r"))
                {
                    System.out.println("\nInvalid response please try again");
                    pause();
                    System.out.println("\nWhere would you like to go?\n(U)SA"
                    + "\n(E)ngland\n(C)hina\n(I)ndia\n(R)ussia ");
                    input = kbd.nextLine();
                }
                //If they select U and are not already there
                if(input.equalsIgnoreCase("U") && location != countries[0])
                {
                    //Send them to the USA
                    location = countries[0];
                    //end the day
                    actionTaken = true;
                }
                //See u
                else if(input.equalsIgnoreCase("E") &&
                location != countries[1])
                {
                    location = countries[1];
                    actionTaken = true;
                }
                //See u
                else if(input.equalsIgnoreCase("C") &&
                location != countries[2])
                {
                    location = countries[2];
                    actionTaken = true;
                }
                //See u
                else if(input.equalsIgnoreCase("I") &&
                location != countries[3])
                {
                    location = countries[3];
                    actionTaken = true;
                }
                //See u
                else if(input.equalsIgnoreCase("R") &&
                location != countries[4])
                {
                    location = countries[4];
                    actionTaken = true;
                }
                //If they are already in the country they chose...
                else
                {
                    //...Tell them
                    System.out.println("\nYou're already here!");
                    pause();
                }
            }
            //Only other option they could of choses is 'q' so...
            else
            {
                //...Take away the rest of the days and cause the game to end
                days = 0;
            }
            //if they ended the day...
            if(actionTaken)
            {
                //Take away a day
                days--;
                //Tell them how many days they have left
                System.out.println("\nYou have " + days + " left to" +
                " trade.");
                pause();
                //Cause random events to happen
                randomEvents();
            }
        }
        while (days > 0 && cash > 0); // countinue as long as they have days
        //left and some cash.
        //When they ended tell them their final total amount of cash
        System.out.println("\nYour enterpise managed to last with $" + cash +
        " in it's pockets");
    }

    /**
    * Short method that prints out all the commands to the TradingSim class
    */
    public static void optionsMenu()
    {
        System.out.println("\nc\t(C)heck prices\no\tCheck St(o)ck/cash"
        + "\nb\t(B)uy product\nm\t(M)ove location\ns\t(S)ell\nq"
        + "\t(Q)uit program");
    }

    /**
     * Asks the user for an action to take for the TradingSim class
     * @return The users selected action
     */
    public static char getAction()
    {
        char action;//The users action
        String input;//What the user initially inputs
        boolean valid;//If their action is valid
        do
        {
            valid = true;//assume their action to be valid
            //Ask for their action
            System.out.print("\nWhat is you chosen action? (enter \"?\" to " +
            "see possible actions) ");
            input = kbd.nextLine();
            //Make their input lower case to make it easier to test
            input = input.toLowerCase();
            System.out.println();
            //Try to take the first character and make it a char
            try
            {
                action = input.charAt(0);
            }
            //if it falis (should only happen if they input blank space first)
            catch (StringIndexOutOfBoundsException e)
            {
                //Give their action a dummy value that will turn out to be
                //invalid
                action = 'z';
            }
            //If their action 
            if (action != 'c' && action != 'b' &&
            action != 'm' && action != 's' && action != '?' && action != 'q'
            && action != 'o' || input.length() > 1)
            {
                //System.out.println("Somethings wrong");
                System.out.println("Invalid response. Please try again.");
                pause();
                valid = false;
            }
            //System.out.println(valid);
        }
        while(!valid);
        return action;
    }

    public static char getBuy()
    {
        System.out.println("\nThe stock of each product is:\nGold:\t" +
        location.getNumOfGold() + "\nTea:\t" + location.getNumOfTea() +
        "\nSteel:\t" + location.getNumOfSteel() + "\nOil:\t" +
        location.getNumOfOil() + "\nWool:\t" + location.getNumOfWool()
        + "\nVelvet:\t" + location.getNumOfVelvet());
        pause();
        boolean valid;
        char buy;
        do
        {
            valid = true;
            System.out.println("\nWhat would you like to buy? (enter first " +
            "letter of the product)");
            String input = kbd.nextLine();
            input = input.toLowerCase();
            try
            {
                buy = input.charAt(0);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                buy = 'z';
            }
            if(buy != 'g' && buy != 't' && buy != 's' && buy != 'o'
            && buy != 'w' && buy != 'v' || input.length() > 1)
            {
                System.out.println("Error: invalid response");
                valid = false;
                pause();
            }
        }
        while(!valid);
        return buy;
    }

    public static char getSellItem()
    {
        System.out.println("\nYour stock is\nGold:\t" + goldStock + "\nTea:\t"
        + teaStock + "\nSteel:\t" + steelStock + "\nOil\t"
        + oilStock + "\nWool:\t" + woolStock + "\nVelvet:\t"
        + velvetStock);
        boolean valid;
        char sell;
        do
        {
            valid = true;
            System.out.println("What would you like to sell? (enter first " +
            "letter of the product)");
            String input = kbd.nextLine();
            input = input.toLowerCase();
            try
            {
                sell = input.charAt(0);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                sell = 'z';
            }
            if(sell != 'g' && sell != 't' && sell != 's' && sell != 'o'
            && sell != 'w' && sell != 'v' || input.length() > 1)
            {
                System.out.println("Error: invalid response");
                valid = false;
                pause();
            }
        }
        while(!valid);
        return sell;
    }

    public static void randomEvents()
    {
        for (int i = 0; i < countries.length - 1; i++)
        {
            int doIGetIt = (int) (Math.random() * 2) + 1;
            if(doIGetIt == 1)
            {
                int majorOrMinor = (int) (Math.random() * 2) + 1;
                if(majorOrMinor == 1)
                {
                    int product = (int) (Math.random() * 6) + 1;
                    int amount = (int) (Math.random() * 50) + 1;
                    int buyOrSell = (int) (Math.random() * 2) + 1;
                    if(buyOrSell == 1)
                    {
                        countries[i].randomBuy(amount, product);
                    }
                    else
                    {
                        countries[i].randomSell(amount, product);
                    }
                }
                else
                {
                    int product = (int) (Math.random() * 6) + 1;
                    int amount = (int) (Math.random() * 39) + 51;
                    int buyOrSell = (int) (Math.random() * 2) + 1;
                    if(buyOrSell == 1)
                    {
                        countries[i].majourBuy(product);
                        countries[i].randomBuy(amount, product);
                    }
                    else
                    {
                        countries[i].majourSell(product);
                        countries[i].randomSell(amount, product);
                    }
                }
            }
        }
    }

    public static void pause()
    {
        System.out.print("\nPress Enter to continue ... ");
        kbd.nextLine();
    }
}
