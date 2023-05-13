package model.game_stuff.enums;

public enum TreeTypes {
    OLIVE("olive"),
    DATE("date"),
    CHERRY("cherry");

    private String name;

    TreeTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TreeTypes getTreeType(String name){
        for(TreeTypes treeType:values()){
            if(treeType.name.equals(name)){
                return treeType;
            }
        }
        return null;
    }
}
