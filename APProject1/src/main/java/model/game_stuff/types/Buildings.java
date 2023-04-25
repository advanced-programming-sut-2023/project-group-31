package model.game_stuff.types;

public enum Buildings {
    ;
    double hp;
    double rockNeeded;
    double woodNeeded;
    double workerNeeded;
    double ironNeeded;

    Buildings(double rockNeeded, double woodNeeded, double workerNeeded, double ironNeeded,double hp) {
        this.rockNeeded = rockNeeded;
        this.woodNeeded = woodNeeded;
        this.workerNeeded = workerNeeded;
        this.ironNeeded = ironNeeded;
        this.hp=hp;
    }

    public void setRockNeeded(double rockNeeded) {
        this.rockNeeded = rockNeeded;
    }

    public void setWoodNeeded(double woodNeeded) {
        this.woodNeeded = woodNeeded;
    }

    public void setWorkerNeeded(double workerNeeded) {
        this.workerNeeded = workerNeeded;
    }

    public void setIronNeeded(double ironNeeded) {
        this.ironNeeded = ironNeeded;
    }
}
