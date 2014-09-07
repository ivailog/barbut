package barbut.game;

import java.util.ArrayList;
import java.util.List;


public class Util {
	
	private static void generatePermutationsRec(List<Integer> elements, int[] branch, int level, boolean[] visited, List<List<Integer>> generatedPerms){
	    if (level >= elements.size()-1){
	        List<Integer> tmpBranch = new ArrayList<Integer>();
	        for (int i : branch) tmpBranch.add(i);
	    	generatedPerms.add(tmpBranch);
	        return;
	    }
	    
	    for (int i = 0; i < elements.size(); i++){
	        if (!visited[i]){
	            branch[++level] = elements.get(i);
	            visited[i] = true;
	            generatePermutationsRec(elements, branch, level, visited, generatedPerms);
	            visited[i] = false;
	            level--;
	        }
	    }
	}
	
	public static List<List<Integer>> generatePermutations(List<Integer> elements) {
		int[] branch = new int[elements.size()];
		boolean[] visited = new boolean[elements.size()];
		List<List<Integer>> result = new ArrayList<List<Integer>>();
 		generatePermutationsRec(elements, branch, -1, visited, result);
		return result;
	}	

}
