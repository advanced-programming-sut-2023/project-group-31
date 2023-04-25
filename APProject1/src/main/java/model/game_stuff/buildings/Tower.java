package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.buildings.enums.Towers;

public class Tower extends Building {
        private Towers type;
        private double hp;


        public Tower(Towers type) {
                this.type = type;
                this.hp = type.getHp();
        }

        public Towers getType() {
                return type;
        }

        public double getHp() {
                return hp;
        }
}