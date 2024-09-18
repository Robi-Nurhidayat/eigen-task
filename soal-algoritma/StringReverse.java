public class StringReverse {
    public static void main(String[] args) {

        task();
    }

    private static void task() {

        String sentence = "NEGIE1";
        String reverseSentence = "";

        int count = sentence.length() - 1;
        System.out.println("awal kalimat : " + sentence );

        for (var i = 0; i < sentence.length(); i++) {

            if (count != sentence.length() - 1){
                reverseSentence = reverseSentence + sentence.charAt(count);
            }
            if(i == sentence.length() - 1) {
                reverseSentence = reverseSentence + sentence.charAt(i);
            }
            count--;
        }

        System.out.println("kalimat reverse : " + reverseSentence);





    }
}
