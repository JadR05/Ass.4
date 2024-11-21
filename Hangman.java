import java.io.*; // for File
import java.util.*; // for Scanner

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        run();
    }

    public static void run() throws FileNotFoundException {
        intro();
        int gamesplayed = 1;
        int gamesWon = 0;
        int bestgame = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dictionary filename? ");
        String filename = scanner.next();
        while (true) {
            String secretWord = getRandomWord(filename);
            int guessesleft = playOneGame(secretWord);
            if (guessesleft > 0) {
                gamesWon++;
                bestgame =Math.max(bestgame, guessesleft);
            }
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.print("Play again (Y/N)?");
                String answer = in.next();
                if (answer.equalsIgnoreCase("n")) {
                    stats(gamesplayed, gamesWon, bestgame);
                    return;
                } else if (!answer.equalsIgnoreCase("y")) {
                    System.out.println("Illegal boolean format");
                    continue;
                } else {
                    break;
                }
            }
            gamesplayed++;
        }
    }

    private static void intro() {
        System.out.println(
                "CMPS201 Hangman!\nI will think of a random word. You'll try to guess its\nletters.Every time you guess a letter that isn't in my\nword, a new body part of the hanging man appears.\nGuess correctly to avoid the gallows!");
        System.out.println();
    }

    private static int playOneGame(String secretWord) throws FileNotFoundException {

        String guessedLetters = "";
        int guessesleft = 8;
        String hint = "";
        for (int i = 0; i < secretWord.length(); i++) {
            hint += "-";
        }
        while (guessesleft > 0 && !hint.equals(secretWord)) {
            displayHangman(guessesleft);
            System.out.println("Secret word: " + hint);
            System.out.println("your guesses: " + guessedLetters);
            System.out.println("Guesses left: " + guessesleft);
            String guess = readGuess(guessedLetters) + "";
            if (secretWord.contains(guess)) {
                hint = createHint(secretWord, guessedLetters + guess);
                System.out.println("Correct!");
            } else {
                guessesleft--;
                System.out.println("Incorrect.");
            }
            guessedLetters += guess;
        }
        if (hint.equals(secretWord)) {
            System.out.println("You win! My word was " + secretWord + ".");
            return guessesleft;
        } else {
            displayHangman(0);
            System.out.println("You lose. My word was " + secretWord + ".");
            return 0;
        }

    }

    private static String createHint(String secretWord, String guessedLetters) {

        String hint = "";
        for (int i = 0; i < secretWord.length(); i++) {
            hint += "-";
        }
        for (int i = 0; i < secretWord.length(); i++) {
            String c = secretWord.charAt(i) + "";
            if (guessedLetters.contains(c)) {
                hint = hint.substring(0, i) + c + hint.substring(i + 1);
            }
        }
        return hint;
    }

    private static char readGuess(String guessedLetters) {
        Scanner input = new Scanner(System.in);
        System.out.print("Your guesses? ");
        String guess = input.next();
        guess = guess.toUpperCase();
        if (guessedLetters.contains(guess)) {
            System.out.println("you already guessed that letter.");
            System.out.print("Your guesses? ");
            guess = input.next();
            guess = guess.toUpperCase();
        } else if (guess.length() > 1) {
            System.out.println("Type a single letter from A-Z.");
            System.out.print("Your guesses? ");
            guess = input.next();
        }
        return guess.charAt(0);
    }

    private static void displayHangman(int guessCount) throws FileNotFoundException {
        if (guessCount == 8) {
            Scanner file8 = new Scanner(new File("res/display8.txt"));
            while (file8.hasNextLine()) {
                String n8 = file8.nextLine();
                System.out.println(n8);
            }
            file8.close();
        } else if (guessCount == 7) {
            Scanner file7 = new Scanner(new File("res/display7.txt"));
            while (file7.hasNextLine()) {
                String n7 = file7.nextLine();
                System.out.println(n7);
            }
            file7.close();
        } else if (guessCount == 6) {
            Scanner file6 = new Scanner(new File("res/display6.txt"));
            while (file6.hasNextLine()) {
                String n6 = file6.nextLine();
                System.out.println(n6);
            }
            file6.close();
        } else if (guessCount == 5) {
            Scanner file5 = new Scanner(new File("res/display5.txt"));
            while (file5.hasNextLine()) {
                String n5 = file5.nextLine();
                System.out.println(n5);
            }
            file5.close();
        } else if (guessCount == 4) {
            Scanner file4 = new Scanner(new File("res/display4.txt"));
            while (file4.hasNextLine()) {
                String n4 = file4.nextLine();
                System.out.println(n4);
            }
            file4.close();
        } else if (guessCount == 3) {
            Scanner file3 = new Scanner(new File("res/display3.txt"));
            while (file3.hasNextLine()) {
                String n3 = file3.nextLine();
                System.out.println(n3);
            }
            file3.close();
        } else if (guessCount == 2) {
            Scanner file2 = new Scanner(new File("res/display2.txt"));
            while (file2.hasNextLine()) {
                String n2 = file2.nextLine();
                System.out.println(n2);
            }
            file2.close();
        } else if (guessCount == 1) {
            Scanner file1 = new Scanner(new File("res/display1.txt"));
            while (file1.hasNextLine()) {
                String n1 = file1.nextLine();
                System.out.println(n1);
            }
            file1.close();
        } else {
            Scanner file0 = new Scanner(new File("res/display0.txt"));
            while (file0.hasNextLine()) {
                String n0 = file0.nextLine();
                System.out.println(n0);
            }
            file0.close();
        }

    }

    private static void stats(int gamesCount, int gamesWon, int best) {
        double winpercent = (gamesWon * 1.0 / gamesCount) * 100;
        System.out.println();
        System.out.println("Overall statistics:");
        System.out.println("Games played: " + gamesCount);
        System.out.println("Games won: " + gamesWon);
        System.out.println("Win percent: " + winpercent + "%");
        System.out.println("Best game: " + best + " guess (es) remaining");
        System.out.println("Thanks for playing!");
    }

    private static String getRandomWord(String filename) throws FileNotFoundException {
        String chosenfile1 = "res/" + filename;
        Scanner scan = new Scanner(new File(chosenfile1));
        int linecount = 0;
        String word = "";
        while (scan.hasNext()) {
            scan.next();
            linecount++;
        }
        int num = 0;
        Random rand = new Random();
        if (linecount == 2) {
            num = 2;
        } else {
            num = rand.nextInt(2, linecount + 1);
        }
        int count = 0;
        scan.close();
        scan = new Scanner(new File(chosenfile1));
        while (scan.hasNext()) {
            count++;
            String word1 = scan.next();
            if (count == num) {
                word = word1;
            }
        }

        return word;
    }
}
