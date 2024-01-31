
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		if(str.length() == 1)
		{
			return "";
		}
		else 
		{
			return str.substring(1);
		}
		
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		int edit_distance;
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if(word2.length() == 0)
		{
			edit_distance = word1.length();
			return edit_distance;
		}
		if (word1.length() == 0)
		{
			edit_distance = word2.length();
			return edit_distance;
		}
		if(word1.charAt(0)== word2.charAt(0))
		{
			return levenshtein(tail(word1), tail(word2));
		}
		else 
		{
			int temp_1 = levenshtein(tail(word1), word2);
			int temp_2 = levenshtein(word1, tail(word2));
			int temp_3 = levenshtein(tail(word1), tail(word2));
			edit_distance = 1 + Math.min(Math.min(temp_1, temp_2), temp_3);
			return edit_distance;
		}
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i<dictionary.length; i++)
		{
			dictionary[i] = in.readLine();
		}

		return dictionary;

	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int smallest_distance = Integer.MAX_VALUE;
		int current_distance;
		int index = 0;

		for(int i =0; i<dictionary.length; i++)
		{
			current_distance = levenshtein(dictionary[i], word);
			if(current_distance<smallest_distance)
			{
				smallest_distance = current_distance;
				index = i;
			}
		}
		if(smallest_distance<= threshold)
		{
			return dictionary[index];
		}
		else
		{
			return word;
		}

	}

}
