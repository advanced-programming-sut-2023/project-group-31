package model.game_stuff;

import model.User;

import java.util.ArrayList;

public class Trade {
    private String id;
    private ArrayList<Good> providedGoods;//hashmap beshe
    private ArrayList<Good> askedGoods;
    private User owner;
    private User asked;
    private String message;
}
