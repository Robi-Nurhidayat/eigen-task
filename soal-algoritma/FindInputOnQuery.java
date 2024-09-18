public class FindInputOnQuery {
    public static void main(String[] args) {

        task();
    }

    private static void task() {

        String[] input = {"xc", "dz", "bbb", "dz"};
        String[] query = {"bbb", "ac", "dz"};
        int[] result = new int[3];
        int count = 0;



        for (var i = 0; i < query.length; i++) {

            for (var j = 0; j < input.length; j++) {
                if (query[i] == input[j]) {
                    count += 1;
                }
                result[i] = count;

                if (j == input.length - 1) {
                    count = 0;
                }

            }


        }

        for (var item: result) {
            System.out.println("isi result : " + item);
        }


    }
}
