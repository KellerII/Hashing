public class CountDemo 
{
    public static void main(String[] args) 
    {
        System.out.println("Word Count Demo: ");
        Count.wordCount("SteveJobsCommencementSpeech.txt");
        System.out.println("");
        Count.wordCount("Koran.txt");
        System.out.println("\nWord Count Analysis: ");
        System.out.println("--------------------------------------------");
        System.out.println("Average time in milliseconds after 10 runs: "); 
        System.out.println("Steve's Job's Commencement with 2,290 Total Words:");
        Count.averageWordCount("SteveJobsCommencementSpeech.txt");
        System.out.println("The Koran with 168,106 Total Words");
        Count.averageWordCount("Koran.txt");
        System.out.println("Wikipedia Snippet with 1,061 Total Words:");
        Count.averageWordCount("computer.txt");
    }
}
