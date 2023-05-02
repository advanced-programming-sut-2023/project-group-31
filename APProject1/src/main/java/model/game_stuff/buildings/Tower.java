package model.game_stuff.buildings;

import model.game_stuff.Building;
import model.game_stuff.Government;
import model.game_stuff.buildings.enums.TowerTypes;

public class Tower extends Building {
        private TowerTypes type;
        private boolean hasStairs;
        public Tower(TowerTypes type, Government government) {
                super(government);
                this.type = type;
                this.hp = type.getHp();
        }

        public boolean hasStairs() {
                return hasStairs;
        }
        public void setStairs(boolean hasStairs) {
                this.hasStairs = hasStairs;
        }

        public TowerTypes getType() {
                return type;
        }
        public int getFireRange() {
                return type.getFireRange();
        }
        public int getDefendRange() {
                return type.getDefendRange();
        }

        @Override
        public String getName() {
                return type.getName();
        }
}
