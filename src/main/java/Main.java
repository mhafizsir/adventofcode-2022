import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<Integer, Integer> rations = new HashMap<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("adventofcode.com_2022_day_1_input.txt")) {
            if (inputStream != null) {
                String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                String[] splittedRations = text.split("\n");
                int numOfElves = 0;
                for (String splittedRation : splittedRations) {
                    if (splittedRation != null && !splittedRation.isEmpty() && !splittedRation.isBlank()) {
                        Integer ration = rations.get(numOfElves);
                        if (ration == null) {
                            rations.put(numOfElves, Integer.valueOf(splittedRation));
                        } else {
                            ration = ration + Integer.parseInt(splittedRation);
                            rations.put(numOfElves, ration);
                        }
                    } else {
                        numOfElves++;
                    }
                }
            }
            Optional<Map.Entry<Integer, Integer>> maxEntryOptional = rations.entrySet().stream().max(Map.Entry.comparingByValue());
            if (maxEntryOptional.isEmpty()) {
                System.out.println("Check algorithm");
            } else {
                Map.Entry<Integer, Integer> maxEntry = maxEntryOptional.get();
                System.out.printf("Elves %s with %s ration%n", maxEntry.getKey(), maxEntry.getValue());
                List<Integer> elfRations = rations.values().stream().sorted(Collections.reverseOrder()).toList();
                int top3Rations = 0;
                for (int i = 0; i < 3; i++) {
                    top3Rations = top3Rations + elfRations.get(i);
                }
                System.out.printf("Top 3 Rations = %s", top3Rations);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
