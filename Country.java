import java.util.Scanner;

public class Country
{
    public final Scanner kbd = new Scanner(System.in);
    private String name;
    private double baseGold;
    private double baseTea;
    private double baseSteel;
    private double baseOil;
    private double baseWool;
    private double baseVelvet;
    private double numOfGold;
    private double numOfTea;
    private double numOfSteel;
    private double numOfOil;
    private double numOfWool;
    private double numOfVelvet;
    private double goldMultiplier;
    private double teaMultiplier;
    private double steelMultiplier;
    private double oilMultiplier;
    private double woolMultiplier;
    private double velvetMultiplier;
    private double goldPrice;
    private double teaPrice;
    private double steelPrice;
    private double oilPrice;
    private double woolPrice;
    private double velvetPrice;

    Country(String name, double baseGold, double baseTea, double baseSteel,
    double baseOil, double baseWool, double baseVelvet,
    double numOfGold, double numOfTea, double numOfSteel,
    double numOfOil, double numOfWool, double numOfVelvet,
    double goldMultiplier, double teaMultiplier,
    double steelMultiplier, double oilMultiplier,
    double woolMultiplier, double velvetMultiplier)
    {
        this.name = name;
        this.baseGold = baseGold;
        this.baseTea = baseTea;
        this.baseSteel = baseSteel;
        this.baseOil = baseOil;
        this.baseWool = baseWool;
        this.baseVelvet = baseVelvet;
        this.numOfGold = numOfGold;
        this.numOfTea = numOfTea;
        this.numOfSteel = numOfSteel;
        this.numOfOil = numOfOil;
        this.numOfWool = numOfWool;
        this.numOfVelvet = numOfVelvet;
        this.goldMultiplier = goldMultiplier;
        this.teaMultiplier = teaMultiplier;
        this.steelMultiplier = steelMultiplier;
        this.oilMultiplier = oilMultiplier;
        this.woolMultiplier = woolMultiplier;
        this.velvetMultiplier = velvetMultiplier;

        goldPrice = baseGold * (100 / numOfGold) * goldMultiplier;
        teaPrice = baseTea * (100 / numOfTea) * teaMultiplier;
        steelPrice = (baseSteel * ((100 / numOfSteel) * steelMultiplier));
        oilPrice = (baseOil * ((100 / numOfOil) * oilMultiplier));
        woolPrice = (baseWool * ((100 / numOfWool) * woolMultiplier));
        velvetPrice = (baseVelvet * ((100 / numOfVelvet) * velvetMultiplier));
    }

    public String getName()
    {
        return name;
    }

    public double getGoldPrice()
    {
        return goldPrice;
    }


    public int getNumOfGold()
    {
        return (int)numOfGold;
    }

    public int buyGold()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            numBought = 0;
            System.out.println("How much gold would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfGold)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfGold(int cash, int numBought)
    {
        String input;
        int cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateGoldPrice();
            numOfGold--;
            //System.out.println("price: " + getGoldPrice());
            cost += getGoldPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfGold += numBought;
            updateGoldPrice();
            return 2000000000;
        }
        else
        {
            System.out.println("\n" + numBought + " gold will cost $"  + cost
            + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " gold will cost $" + cost +
                " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfGold += numBought;
                updateGoldPrice();
                return 0;
            }
        }
    }

    public int sellGold(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do{
            valid = true;
            System.out.println("How much gold would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }


    public int incomeOfGold(int numSold)
    {
        String input;
        int income;

        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfGold++;
            updateGoldPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getGoldPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " gold sold gives you an " +
        "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " gold sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfGold -= numSold;
            updateGoldPrice();
            return 0;
        }
    }

    public void updateGoldPrice()
    {
        goldPrice = baseGold * (100 / numOfGold) * goldMultiplier;
    }

    public double getTeaPrice()
    {
        return teaPrice;
    }

    public int getNumOfTea()
    {
        return (int)numOfTea;
    }

    public int buyTea()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            numBought = 0;
            System.out.println("How much tea would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfTea)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfTea(int cash, int numBought)
    {
        int cost;
        String input;
        cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateTeaPrice();
            numOfTea--;
            //System.out.println("price: " + getGoldPrice());
            cost += getTeaPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfTea += numBought;
            updateTeaPrice();
            return 2000000000;
        }
        else{
            System.out.println("\n" + numBought + " tea will cost $"
            + cost + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " tea will cost $" + cost +
                " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfTea += numBought;
                updateTeaPrice();
                return 0;
            }
        }
    }

    public int sellTea(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do{
            valid = true;
            System.out.println("How much tea would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }


    public int incomeOfTea(int numSold)
    {
        String input;
        int income;
        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfTea++;
            updateTeaPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getTeaPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " tea sold gives you an " +
        "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " tea sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfTea -= numSold;
            updateTeaPrice();
            return 0;
        }
    }

    public void updateTeaPrice()
    {
        teaPrice = baseTea * (100 / numOfTea) * teaMultiplier;
    }

    public double getSteelPrice()
    {
        return steelPrice;
    }

    public int getNumOfSteel()
    {
        return (int)numOfSteel;
    }

    public int buySteel()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            System.out.println("How much Steel would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfSteel)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfSteel(int cash, int numBought)
    {
        int cost;
        String input;
        cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateSteelPrice();
            numOfSteel--;
            //System.out.println("price: " + getGoldPrice());
            cost += getSteelPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfSteel += numBought;
            updateSteelPrice();
            return 2000000000;
        }
        else
        {
            System.out.println("\n" + numBought + " steel will cost $"
            + cost + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " steel will cost $" + cost +
                " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfSteel += numBought;
                updateSteelPrice();
                return 0;
            }
        }
    }

    public int sellSteel(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do
        {
            valid = true;
            System.out.println("How much steel would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }

    public int incomeOfSteel(int numSold)
    {
        String input;
        int income;
        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfSteel++;
            updateSteelPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getSteelPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " Steel sold gives you an " +
        "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " steel sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfSteel -= numSold;
            updateSteelPrice();
            return 0;
        }
    }

    public void updateSteelPrice()
    {
        steelPrice = baseSteel * (100 / numOfSteel) * steelMultiplier;
    }

    public double getOilPrice()
    {
        return oilPrice;
    }

    public int getNumOfOil()
    {
        return (int)numOfOil;
    }

    public int buyOil()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            System.out.println("How much oil would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfOil)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfOil(int cash, int numBought)
    {
        int cost;
        String input;
        cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateOilPrice();
            numOfOil--;
            //System.out.println("price: " + getGoldPrice());
            cost += getOilPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfOil += numBought;
            updateOilPrice();
            return 2000000000;
        }
        else
        {
            System.out.println("\n" + numBought + " oil will cost $"
            + cost + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " oil will cost $" + cost +
                " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfOil += numBought;
                updateOilPrice();
                return 0;
            }
        }
    }

    public int sellOil(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do{
            valid = true;
            System.out.println("How much oil would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }


    public int incomeOfOil(int numSold)
    {
        String input;
        int income;

        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfOil++;
            updateOilPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getOilPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " oil sold gives you an " +
        "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " oil sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfOil -= numSold;
            updateOilPrice();
            return 0;
        }
    }

    public void updateOilPrice()
    {
        oilPrice = baseOil * (100 / numOfOil) * oilMultiplier;
    }

    public double getWoolPrice()
    {
        return woolPrice;
    }

    public int getNumOfWool()
    {
        return (int)numOfWool;
    }

    public int buyWool()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            System.out.println("How much wool would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfWool)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfWool(int cash, int numBought)
    {
        int cost;
        String input;
        cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateWoolPrice();
            numOfWool--;
            //System.out.println("price: " + getGoldPrice());
            cost += getWoolPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfWool += numBought;
            updateWoolPrice();
            return 2000000000;
        }
        else
        {
            System.out.println("\n" + numBought + " wool will cost $"
            + cost + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " wool will cost $" +
                cost + " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfWool += numBought;
                updateWoolPrice();
                return 0;
            }
        }
    }

    public int sellWool(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do{
            valid = true;
            System.out.println("How much wool would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }


    public int incomeOfWool(int numSold)
    {
        String input;
        int income;

        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfWool++;
            updateWoolPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getWoolPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " wool sold gives you an " +
        "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " wool sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfWool -= numSold;
            updateWoolPrice();
            return 0;
        }
    }

    public void updateWoolPrice()
    {
        woolPrice = baseWool * (100 / numOfWool) * woolMultiplier;
    }

    public double getVelvetPrice()
    {
        return velvetPrice;
    }

    public int getNumOfVelvet()
    {
        return (int)numOfVelvet;
    }

    public int buyVelvet()
    {
        boolean valid;
        int numBought;
        String input;
        do{
            valid = true;
            System.out.println("How much velvet would you like to buy?");
            input = kbd.nextLine();
            try
            {
                numBought = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numBought = 200000000;
            }
            if(numBought > numOfVelvet)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to the stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numBought;
    }

    public int costOfVelvet(int cash, int numBought)
    {
        int cost;
        String input;
        cost = 0;
        for (int i = 0; i < numBought; i++)
        {
            updateVelvetPrice();
            numOfVelvet--;
            //System.out.println("price: " + getGoldPrice());
            cost += getVelvetPrice();
            //System.out.println("cost: " + cost);
        }
        if(cost > cash)
        {
            numOfVelvet += numBought;
            updateVelvetPrice();
            return 2000000000;
        }
        else
        {
            System.out.println("\n" + numBought + " velvet will cost $"
            + cost + " proceed with the purchase? (Y/N)");
            input = kbd.nextLine();
            while(!input.equalsIgnoreCase("y") &&
            !input.equalsIgnoreCase("n"))
            {
                System.out.println("Invalid response please try again");
                TradingSim.pause();
                System.out.println(numBought + " velvet will cost $" + cost +
                " proceed with the purchase? (Y/N)");
                input = kbd.nextLine();
            }
            if(input.equalsIgnoreCase("y"))
            {
                return cost;
            }
            else
            {
                numOfVelvet += numBought;
                updateVelvetPrice();
                return 0;
            }
        }
    }

    public int sellVelvet(int stock)
    {
        boolean valid;
        int numSold;
        String input;
        do{
            valid = true;
            System.out.println("How much velvet would you like to sell?");
            input = kbd.nextLine();
            try
            {
                numSold = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                numSold = 200000000;
            }
            if(numSold > stock)
            {
                System.out.println("Invalid response (enure the number "
                + "is less or equal to your stock)");
                TradingSim.pause();
                valid = false;
            }
        } while(!valid);
        return numSold;
    }


    public int incomeOfVelvet(int numSold)
    {
        String input;
        int income;

        income = 0;
        for (int i = 0; i < numSold; i++)
        {
            numOfVelvet++;
            updateVelvetPrice();
            //System.out.println("price: " + getGoldPrice());
            income += getVelvetPrice();
            //System.out.println("cost: " + cost);
        }
        System.out.println("\n" + numSold + " velvet sold gives you an "
        + "income of $"  + income + " proceed with the transaction? (Y/N)");
        input = kbd.nextLine();
        while(!input.equalsIgnoreCase("y") &&
        !input.equalsIgnoreCase("n"))
        {
            System.out.println("Invalid response please try again");
            TradingSim.pause();
            System.out.println("\n" + numSold + " velvet sold gives " +
            "you an income of $"  + income +
            " proceed with the transaction? (Y/N)");
            input = kbd.nextLine();
        }
        if(input.equalsIgnoreCase("y"))
        {
            return income;
        }
        else
        {
            numOfVelvet -= numSold;
            updateVelvetPrice();
            return 0;
        }
    }

    public void updateVelvetPrice()
    {
        velvetPrice = baseVelvet * (100 / numOfVelvet)
        * velvetMultiplier;
    }

    public void randomBuy (int amount, int product)
    {
        if(product == 1)
        {
            numOfGold -=  ((amount/100) * numOfGold);
            updateGoldPrice();
        }
        else if(product == 2)
        {
            numOfTea -= ((amount/100) * numOfTea);
            updateTeaPrice();
        }
        else if(product == 3)
        {
            numOfSteel -= ((amount/100) * numOfSteel);
            updateSteelPrice();
        }
        else if(product == 4)
        {
            numOfOil -= ((amount/100) * numOfOil);
            updateOilPrice();
        }
        else if(product == 5)
        {
            numOfWool -= ((amount/100) * numOfWool);
            updateWoolPrice();
        }
        else if(product == 6)
        {
            numOfVelvet -= ((amount/100) * numOfVelvet);
            updateVelvetPrice();
        }
    }

    public void majourBuy(int product)
    {
        System.out.println("\nBREAKING NEWS: there has been a mass purchase "
        + "of " + productToString(product) + " in " + name + "\nprices are " +
        "expected to skyrocket with the increased demand");
        TradingSim.pause();
    }

    public String productToString(int product)
    {
        if(product == 1)
        {
            return "gold";
        }
        else if(product == 2)
        {
            return "tea";
        }
        else if(product == 3)
        {
            return "steel";
        }
        else if(product == 4)
        {
            return "oil";
        }
        else if(product == 5)
        {
            return "wool";
        }
        else
        {
            return "velvet";
        }
    }

    public void randomSell (int amount, int product)
    {
        if(product == 1)
        {
            numOfGold += amount;
            updateGoldPrice();
        }
        else if(product == 2)
        {
            numOfTea += amount;
            updateTeaPrice();
        }
        else if(product == 3)
        {
            numOfSteel += amount;
            updateSteelPrice();
        }
        else if(product == 4)
        {
            numOfOil += amount;
            updateOilPrice();
        }
        else if(product == 5)
        {
            numOfWool += amount;
            updateWoolPrice();
        }
        else if(product == 6)
        {
            numOfVelvet += amount;
            updateVelvetPrice();
        }
    }

    public void majourSell(int product)
    {
        System.out.println("\nBREAKING NEWS: there has been a mass selling " +
        "of " + productToString(product) + " in " + name + "\nprices are " +
        "expected to plummet with the increased supply");
        TradingSim.pause();
    }

}
