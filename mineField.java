import java.util.Random;

/**
 * mineField.
 * 
 * @author Ivan Vnukov
 * @id 2303663
 * @author Maria Haritonova
 * @id 2331705
 */
public class MineField {

    int length;
    int height;
    int[][] field;
    int mineCount = 10;


    /*
     * Create a minefield of size length * height and populate it with zeroes
     */
    public MineField(int length, int height) {
        this.length = length;
        this.height = height;
        this.field = new int[length][height];
        for (int[] i: this.field) {
            for (int k: i) {
                k = 0;
            }
        }
        this.populate(mineCount);
    }

    private int[][] populate (int mineCount) {

        int[][] field = this.field;

        Random random = new Random();
        
        int height = field.length; //height of the field
        int width = field[0].length; //length of the field

        for (int x = 0; x < mineCount; x++) { //generaten mineCount mines on the field
            int a = random.nextInt(height - 1); //x-index of the potential mine
            int b = random.nextInt(width - 1); //y-index of the potential mine

            if (field[a][b] != 1) {
                field[a][b] = 1;
            } else {
                x--;
            }

        }

        return field;
    }

    private void print() {
        for (int[] x: field) {
            for (int y : x) {
                System.out.print(y);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }

}
