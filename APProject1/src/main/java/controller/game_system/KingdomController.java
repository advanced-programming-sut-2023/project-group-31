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
        int setPopularity=current.getFoodRate()+current.getFearRate()+current.getReligionRate()+current.getTaxRate();
        current.setPopularity(setPopularity);
        checkForPopulation(current);
     }
     public static void checkForPopulation(Government current){
        if(current.getPopulation()<30&&currentGame.getTurn()%2==0){
            //current.getWorkLessPeople().set(x-1)

            return;
        }
        else if(current.getPopularity()>70&&currentGame.getTurn()%2==0){
            //current.getWorkLessPeople().set(x+1)
            return;
        }
        else if(current.getPopularity()<50&&currentGame.getTurn()%4==0){
            //current.getWorkLessPeople().set(x-1)
            return;
        }
        else if(current.getPopularity()>=50&&currentGame.getTurn()%4==0){
            //current.getWorkLessPeople.set(x+1)
            return;
        }
     }
 }