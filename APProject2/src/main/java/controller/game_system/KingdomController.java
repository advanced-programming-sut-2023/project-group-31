package controller.game_system;

import controller.ControllerUtils;
import model.User;
import model.game_stuff.Government;
import model.game_stuff.buildings.Storage;
import model.game_stuff.enums.Items;
import view.game_system.messages.KingdomMessages;
import view.user_system.messages.UserMessages;

import java.util.Map;

public class KingdomController extends ControllerUtils {
    protected User owner;

    public static KingdomMessages foodRate() {
        if (!inputs.get("rateNumber").matches("^-?([0]{1}\\.{1}[0-9]+|[1-9]{1}[0-9]*\\.{1}[0-9]+|[0-9]+|0)$")){
        return KingdomMessages.INVALID_COMMAND;
    }
        int rate=Integer.parseInt(inputs.get("rateNumber"));
        if(rate>5||rate<-5){
            return KingdomMessages.INVALID_RATE_NUMBER;
        }
        currentPlayer.setFoodRate(rate);
        if(rate>0){
            return KingdomMessages.POSITIVE;
        }
        else if(rate<0){
            return KingdomMessages.NEGATIVE;
        }
        return KingdomMessages.ZERO;
    }

    public static KingdomMessages taxRate() {
        if (!inputs.get("rateNumber").matches("^-?([0]{1}\\.{1}[0-9]+|[1-9]{1}[0-9]*\\.{1}[0-9]+|[0-9]+|0)$")){
            return KingdomMessages.INVALID_COMMAND;
        }
        int rate=Integer.parseInt(inputs.get("rateNumber"));
        if(rate>5||rate<-5){
            return KingdomMessages.INVALID_RATE_NUMBER;
        }
        currentPlayer.setTaxRate(rate);
        if(rate>0){
            return KingdomMessages.POSITIVE;
        }
        else if(rate<0){
            return KingdomMessages.NEGATIVE;
        }
        return KingdomMessages.ZERO;
    }

    public static KingdomMessages fearRate() {
     if (!inputs.get("rateNumber").matches("^-?([0]{1}\\.{1}[0-9]+|[1-9]{1}[0-9]*\\.{1}[0-9]+|[0-9]+|0)$")){
         return KingdomMessages.INVALID_COMMAND;
     }
     int rate=Integer.parseInt(inputs.get("rateNumber"));
     if(rate>5||rate<-5){
         return KingdomMessages.INVALID_RATE_NUMBER;
     }
     currentPlayer.setFearRate(rate);
     if(rate>0){
       return KingdomMessages.POSITIVE;
     }
     else if(rate<0){
         return KingdomMessages.NEGATIVE;
     }
     return KingdomMessages.ZERO;
    }

    public static String showTaxRate() {
        return String.valueOf(currentPlayer.getTaxRate());
    }

    public static String showFoodList() {
        int amountOfCheese=findAmount(Items.CHEESE);
        int  amountOfBread=findAmount(Items.BREAD);
        int amountOfApple=findAmount(Items.APPLE);
        int amountOfMeat=findAmount(Items.MEAT);
        StringBuilder result=new StringBuilder();
        result.append("Bread amount = ").append(amountOfBread).append('\n');
        result.append("Meat amount = ").append(amountOfMeat).append('\n');
        result.append("Apple amount = ").append(amountOfApple).append('\n');
        result.append("Cheese amount = ").append(amountOfCheese);
        return result.toString();
    }

    public static String showFoodRate() {
              return String.valueOf(currentPlayer.getFoodRate());
    }

    public static String showPopularityFactors(){
        StringBuilder result=new StringBuilder();
        result.append("food rate: ").append(currentPlayer.getFoodRate()).append('\n');
        result.append("tax rate: ").append(currentPlayer.getTaxRate()).append('\n');
        result.append("religion rate: ").append(currentPlayer.getReligionRate()).append('\n');
        result.append("fear rate: ").append(currentPlayer.getFearRate());
        return result.toString();
     }

    public static String showPopularity() {
        return String.valueOf(currentPlayer.getPopularity());
    }
     public static int findAmount(Items item){
        int targetNum=0;
         for (Storage granary : currentPlayer.getGranaries()) {
             for (Map.Entry i: granary.getProperties().entrySet()){
                 if(i.getKey().equals(item)){
                     targetNum=targetNum+(int)i.getValue();
                 }
             }
         }
         return targetNum;
     }
     public static void nextTurnForGovernment(Government current){
        //TODO ADD ARRAYS OF BUILDINGS
        //if(currentPlayer.getBuildings.contains(FactorRiser)){
         // currentPlayer.setReligionRate(2)}
         int addToPopularityFoods=0;
         int addToPopularityBuildings=0;
         if(!current.getBuildings().isEmpty()){
             addToPopularityBuildings=1;
         }
         addToPopularityFoods= current.getPossession().getItem(Items.MEAT)+current.getPossession().getItem(Items.BREAD)+current.getPossession().getItem(Items.CHEESE)+current.getPossession().getItem(Items.APPLE)-1;
         current.setPopularity(current.getPopularity()+addToPopularityFoods+addToPopularityBuildings);
         if(current.getPopularity()>100){
             current.setPopularity(100);
         }
         if(current.getPopularity()<0){
             current.setPopularity(0);
         }
        checkForPopulation(current);
        checkForTax();
        checkForFood();
        checkForFear();
     }

    private static void checkForFear() {
        switch (currentPlayer.getFearRate()){
            case 5:
                currentPlayer.setEfficiency(0.75);
                break;
            case 4:
                currentPlayer.setEfficiency(0.80);
                break;
            case 3:
                currentPlayer.setEfficiency(0.85);
                break;
            case 2:
                currentPlayer.setEfficiency(0.90);
                break;
            case 1:
                currentPlayer.setEfficiency(0.95);
                break;
            case 0:
                currentPlayer.setEfficiency(1);
                break;
            case -1 :
                currentPlayer.setEfficiency(1.05);
                break;
            case -2:
                currentPlayer.setEfficiency(1.1);
                break;
            case -3:
                currentPlayer.setEfficiency(1.15);
                break;
            case -4:
                currentPlayer.setEfficiency(1.2);
                break;
            case -5:
                currentPlayer.setEfficiency(1.25);
                break;
            default:
                break;
        }
    }

    private static void checkForFood() {
        switch (currentPlayer.getFoodRate()){
            case -2:
                foodToPeople(0,-8);
                break;
            case -1:
                foodToPeople(0.5,-4);
                break;
            case 0:
                foodToPeople(1,0);
                break;
            case 1:
                foodToPeople(1.5,4);
                break;
            case 2:
                foodToPeople(2,8);
                break;
            default:
                break;
        }
    }

    private static void foodToPeople(double foodToDonate, int popularityRate) {
        double toDecreaseFromStorage=Math.ceil(foodToDonate*currentPlayer.getPopulation());
        /*currentPlayer.setPopularity(currentPlayer.getPopularity()+popularityRate);
        if(currentPlayer.getPopularity()>100){
            currentPlayer.setPopularity(100);
        }
        else if(currentPlayer.getPopularity()<0){
            currentPlayer.setPopularity(0);
        }*/
        while (toDecreaseFromStorage>0){
            for (Storage granary : currentPlayer.getGranaries()) {
                if(currentPlayer.getPossession().getItem(Items.CHEESE)==0&&currentPlayer.getPossession().getItem(Items.BREAD)==0
                &&currentPlayer.getPossession().getItem(Items.APPLE)==0&&currentPlayer.getPossession().getItem(Items.MEAT)==0){
                    break;
                }
                if(granary.getProperties().get(Items.CHEESE)!=0) {
                    granary.removeProduct(Items.CHEESE, 1);
                    currentPlayer.getPossession().setItem(Items.CHEESE,-1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if(granary.getProperties().get(Items.BREAD)!=0){
                    granary.removeProduct(Items.BREAD,1);
                    currentPlayer.getPossession().setItem(Items.BREAD,-1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if(granary.getProperties().get(Items.APPLE)!=0){
                    granary.removeProduct(Items.APPLE,1);
                    currentPlayer.getPossession().setItem(Items.APPLE,-1);
                    toDecreaseFromStorage--;
                    continue;
                }
                if(granary.getProperties().get(Items.MEAT)!=0){
                    granary.removeProduct(Items.MEAT,1);
                    currentPlayer.getPossession().setItem(Items.MEAT,-1);
                    toDecreaseFromStorage--;
                    continue;
                }
            }
        }
        if(toDecreaseFromStorage!=0){
            currentPlayer.setFoodRate(-2);
        }
    }

    private static void checkForTax() {
        switch (currentPlayer.getTaxRate()){
            case -3:
                getTax(-1,7);
            case -2:
                getTax(-0.8,5);
            case -1:
                getTax(-0.6,3);
            case 0:
                getTax(0,1);
                break;
            case 1:
                getTax(0.6,-2);
                break;
            case 2:
                getTax(0.8,-4);
                break;
            case 3:
                getTax(1,-6);
                break;
            case 4:
                getTax(1.2,-8);
                break;
            case 5:
                getTax(1.4,-12);
                break;
            case 6:
                getTax(1.6,-16);
                break;
            case 7:
                getTax(1.8,-20);
                break;
            case 8:
                getTax(2,-24);
                break;
            default:
                break;
        }
    }

    private static void getTax(double gold, int toChange) {
        if (currentPlayer.getPossession().getGold()==0 && currentPlayer.getTaxRate() < 0) {
            currentPlayer.setTaxRate(0);
            return;
        }
        /*currentPlayer.setPopularity(currentPlayer.getPopularity()+toChange);
        if(currentPlayer.getPopularity()>100){
            currentPlayer.setPopularity(100);
        }
        else if(currentPlayer.getPopularity()<0){
            currentPlayer.setPopularity(0);
        }*/
        double toDecrease=Math.ceil(currentPlayer.getPopulation()*gold);
        currentPlayer.getPossession().setGold(currentPlayer.getPossession().getGold()+(int) toDecrease);
        if(currentPlayer.getPossession().getGold()<0){
            currentPlayer.getPossession().setGold(0);
        }
    }

    public static void checkForPopulation(Government current){
        if(current.getPopulation()<30&&currentGame.getTurn()%2==0){
            current.getPossession().setPeasant(current.getPossession().getPeasant()-1);
            return;
        }
        else if(current.getPopularity()>70&&currentGame.getTurn()%2==0){
            current.getPossession().setPeasant(current.getPossession().getPeasant()+1);
            return;
        }
        else if(current.getPopularity()<50&&currentGame.getTurn()%4==0){
            current.getPossession().setPeasant(current.getPossession().getPeasant()-1);
            return;
        }
        else if(current.getPopularity()>=50&&currentGame.getTurn()%4==0){
            current.getPossession().setPeasant(current.getPossession().getPeasant()+1);
            return;
        }
     }
 }