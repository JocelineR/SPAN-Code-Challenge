import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Insert the results: ");
		
		List <String> results = new ArrayList<>();
		while (scn.hasNextLine()) {
			String line = scn.nextLine();
		    if (line.isEmpty()) break; 
			results.add(line);
		}
		scn.close();
		
		Map<String, Integer> teamRes = new HashMap<>();
		
		for (String result : results) {
			String[] teams = result.split(", ");
			String[] team1 = teams[0].split(" (?=\\d+$)");
			String[] team2 = teams[1].split(" (?=\\d+$)");
			
			String team1Name = team1[0];
			int team1Result = Integer.parseInt(team1[1]); 
			String team2Name = team2[0];
			int team2Result = Integer.parseInt(team2[1]);
			
			if(!teamRes.containsKey(team1Name)){
				teamRes.put(team1Name, 0);
			}
			
			if(!teamRes.containsKey(team2Name)){
				teamRes.put(team2Name, 0);
			}
			
			
			if(team1Result > team2Result) {
				teamRes.put(team1Name, teamRes.get(team1Name) + 3);
			}
			else if(team1Result < team2Result) {
				teamRes.put(team2Name, teamRes.get(team2Name) + 3);

			}
			else {
				teamRes.put(team1Name, teamRes.get(team1Name) + 1);
				teamRes.put(team2Name, teamRes.get(team2Name) + 1);

			}
						
		}
		
		List<Map.Entry<String, Integer>> sortedList = teamRes.entrySet().stream()
				.sorted((a, b) -> {
			      int pCompare = b.getValue().compareTo(a.getValue());
			      return pCompare != 0 ? pCompare : a.getKey().compareTo(b.getKey());
		})
        .collect(Collectors.toList());
		
		int rank=0;
		int lastScore=0;
		for(Map.Entry<String, Integer> entry : sortedList) {
			
			if(entry.getValue() != lastScore) {
				rank ++;
			}
			
			System.out.println(rank +". " + entry.getKey() +", " + entry.getValue() + ((entry.getValue() == 1)  ? "pt" : "pts") );
			
			lastScore = entry.getValue();
		}
		
		
	}

}
