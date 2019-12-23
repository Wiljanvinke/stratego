package game.extra;

public enum Rank {
    FLAG, SPY, SCOUT, MINER, SERGEANT, LIEUTENANT, CAPTAIN, MAJOR, COLONEL, GENERAL, MARSHALL, BOMB;

    public int toInt() {
        int i = -1;
        switch (this) {
            case FLAG: i = 0; break;
            case SPY: i = 1; break;
            case SCOUT: i = 2; break;
            case MINER: i = 3; break;
            case SERGEANT: i = 4; break;
            case LIEUTENANT: i = 5; break;
            case CAPTAIN: i = 6; break;
            case MAJOR: i = 7; break;
            case COLONEL: i = 8; break;
            case GENERAL: i = 9; break;
            case MARSHALL: i = 10; break;
            case BOMB: i = 11; break;
        }
        return i;
    }

    public static Rank toEnum(int i) {
        Rank rank = null;
        switch (i) {
            case 0: rank = FLAG; break;
            case 1: rank = SPY; break;
            case 2: rank = SCOUT; break;
            case 3: rank = MINER; break;
            case 4: rank = SERGEANT; break;
            case 5: rank = LIEUTENANT; break;
            case 6: rank = CAPTAIN; break;
            case 7: rank = MAJOR; break;
            case 8: rank = COLONEL; break;
            case 9: rank = GENERAL; break;
            case 10: rank = MARSHALL; break;
            case 11: rank = BOMB; break;
        }
        return rank;
    }

    public String toString(){
        String string = "";
        switch (this) {
            case FLAG: string = "Flag"; break;
            case SPY: string = "Spy"; break;
            case SCOUT: string = "Scout"; break;
            case MINER: string = "Miner"; break;
            case SERGEANT: string = "Sergeant"; break;
            case LIEUTENANT: string = "Lieutenant"; break;
            case CAPTAIN: string = "Captain"; break;
            case MAJOR: string = "Major"; break;
            case COLONEL: string = "Colonel"; break;
            case GENERAL: string = "General"; break;
            case MARSHALL: string = "Marshall"; break;
            case BOMB: string = "Bomb"; break;
        }
        return string;
    }
}
