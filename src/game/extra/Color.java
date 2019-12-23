package game.extra;

public enum Color {
    RED, BLUE;

    public String toChar() {
        String string = "";
        switch (this) {
            case RED: string = "R"; break;
            case BLUE: string = "B"; break;
        }
        return string;
    }

    public String toString(){
        String string = "";
        switch (this) {
            case RED: string = "Red"; break;
            case BLUE: string = "Blue"; break;
        }
        return string;
    }

}
