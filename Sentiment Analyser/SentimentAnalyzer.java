import java.util.*;

public class SentimentAnalyzer {
    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
	public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
			String[] negOpinionWords) {
		int[] featureOpinions = new int[featureSet.length]; // output
 		review = review.toLowerCase();
 		outer:
        for (int i=0;i<featureSet.length;++i)
        {
        	for (int j=0;j<featureSet[i].length;++j)
        	{
        		featureOpinions[i] = getOpinionOnFeature(review, featureSet[i][j], posOpinionWords, negOpinionWords);
        		if (featureOpinions[i] == 1 || featureOpinions[i] == -1)
        			continue outer;
        	}
        }
 
		return featureOpinions;
	}

	// First invoke checkForWasPhrasePattern and 
	// if it cannot find an opinion only then invoke checkForOpinionFirstPattern
	private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
		int result = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
		if (result == 1 || result == -1)
			return result;	
		result = checkForOpinionFirstPattern (review, feature,posOpinionWords,negOpinionWords);
		return result;
	}	

	// Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
	// Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
	// You can first look for positive opinion. If not found, only then you can look for negative opinion
	private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
		int opinion = 0;
		String pattern = feature + " was ";

		if (review.indexOf(pattern) != -1)
		{
			int patternIndex = review.indexOf(pattern);
			int patternLength = patternIndex + pattern.length();
			String opinionExtracted= "";
			for (int i=patternLength;i<review.length();++i)
			{
				char v = review.charAt(i);
				if (v == ' ' || !((int)v >= 97 && (int)v<=122))
				{
					opinionExtracted = review.substring(patternLength,i);
					break;
				}
			}
			List<String> posList = Arrays.asList(posOpinionWords);
			if (posList.contains(opinionExtracted))
				return 1;
			List<String> negList = Arrays.asList(negOpinionWords);
			if (negList.contains(opinionExtracted))
				return -1;
		}

		return opinion; 	
	}
	
	// You can first look for positive opinion. If not found, only then you can look for negative opinion
	private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
			String[] negOpinionWords) {
		// Extract sentences as feature might appear multiple times. 
		// split() takes a regular expression and "." is a special character 
		// for regular expression. So, escape it to make it work!!
		
		int opinion = 0;
		
		if (review.indexOf(feature) != -1)
		{
			String opinionExtracted = "";
			for (int i = review.indexOf(feature)-2;i>=0;--i)
			{
				char v = review.charAt(i);
				if (v == ' ' || !((int)v >= 97 && (int)v<=122))
				{
					opinionExtracted = review.substring(i+1,review.indexOf(feature)-1);
					break;
				}
			}
			List<String> posList = Arrays.asList(posOpinionWords);
			if (posList.contains(opinionExtracted))
				return 1;
			List<String> negList = Arrays.asList(negOpinionWords);
			if(negList.contains(opinionExtracted))
				return -1;
		}

		return opinion;
	}

	public static void main(String[] args) {
		String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
		
		//String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
		
		String[][] featureSet = { 
		        { "ambiance", "ambience", "atmosphere", "decor" },
				{ "dessert", "ice cream", "desert" }, 
				{ "food" }, 
				{ "soup" },
				{ "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
		String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
				"delicious" };
		String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

		int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
		System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
	}
}