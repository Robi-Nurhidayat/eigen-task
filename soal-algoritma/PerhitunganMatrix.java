public class PerhitunganMatrix {
    public static void main(String[] args) {
        task();
    }

    private static void task() {



        int[][] matriks = {{1, 2, 0},{4, 5, 6},{7, 8, 9}};
        int increase = 0;
        int decrease = 2;

        int diagonalPertama = 0;
        int diagonalKedua = 0;

        for(var item: matriks) {

            for (var i = 0; i < item.length; i++) {
                if (increase == i) {
                    diagonalPertama += item[increase];
                }

                if (decrease == i) {
                    diagonalKedua += item[decrease];
                }
            }

            increase++;
            decrease--;
        }

        int result = diagonalPertama - diagonalKedua;

        System.out.println("hasil dari diagonal pertama = " + diagonalPertama);
        System.out.println("hasil dari diagonal kedua = " + diagonalKedua);
        System.out.println("=============================================");
        System.out.println("maka hasil nya adalah " + diagonalPertama + " - " + diagonalKedua + " = " + result);
    }
}
