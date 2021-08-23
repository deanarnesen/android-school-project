class TempPlayer implements java.io.Serializable{
    String name;
    String game;
    String color;

    public TempPlayer(String name, String game, String color) {
        this.name = name;
        this.game = game;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public String getColor() {
        return color;
    }
}
