public class FindLongSentece {
    public static void main(String[] args) {

        task();

    }

    private static void task() {
        var sentence = "Saya sangat senang mengerjakan soal algoritma";


        String[] myArray = sentence.split(" ");

        String findSentence = "";
        int sentenceLength = 0;
        int finalLengthSentence = 0;
        for (var i = 0; i < myArray.length; i++) {

            sentenceLength = myArray[i].length();


            for (var j = 0; j < myArray.length; j++) {
                if (sentenceLength < myArray[j].length()) {
                    findSentence = myArray[j];
                    finalLengthSentence = myArray[j].length();
                }
            }
        }


        System.out.println(findSentence);
        System.out.println(finalLengthSentence);

    }
}
