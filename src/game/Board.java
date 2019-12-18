package game;

public class Board {
    private Field[][] playFields;

    public Board(){
        playFields = new Field[10][10];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                playFields[i][j] = new Field();
            }
        }
        playFields[4][2].setPlayable(false);
        playFields[4][3].setPlayable(false);
        playFields[4][6].setPlayable(false);
        playFields[4][7].setPlayable(false);
        playFields[5][2].setPlayable(false);
        playFields[5][3].setPlayable(false);
        playFields[5][6].setPlayable(false);
        playFields[5][7].setPlayable(false);
    }

    public Field[][] getPlayFields() {
        return playFields;
    }

    public void setPlayFields(Field[][] playFields) {
        this.playFields = playFields;
    }

    public void printBoardPlayable(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(playFields[i][j].isPlayable());
            }
            System.out.println();
        }
    }

    public void printBoardPieces(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(playFields[i][j].getPiece()!=null) {
                    System.out.print(playFields[i][j].getPiece().toString());
                }
            }
            System.out.println();
        }
    }
}
