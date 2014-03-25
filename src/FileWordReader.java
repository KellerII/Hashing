import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

//Reads the text file and converting words to lower case and eliminates punctuation
class FileWordReader 
{
    StreamTokenizer token;

    public FileWordReader(String file) throws FileNotFoundException, IOException 
    {
        token = new StreamTokenizer(new BufferedReader(new InputStreamReader(
                new FileInputStream(file))));
        token.eolIsSignificant(false);
        token.lowerCaseMode(true);
        token.wordChars('a', 'z');
        token.wordChars('A', 'Z');
        String punctuation = "!.;:-[],;!?*+-=\\|/(){}\"><'";
        for (int i = 0; i < punctuation.length(); i++) 
        {
            token.wordChars(punctuation.charAt(i), punctuation.charAt(i));
        }
    }

    public String nextWord() throws IOException {
        int type = token.nextToken();
        while (type != StreamTokenizer.TT_WORD && type != StreamTokenizer.TT_EOF) 
        {
            type = token.nextToken();
        }
        if (type == StreamTokenizer.TT_EOF)
        {
            return null;
        }

        String convertedString = token.sval.replaceAll("[^a-z]", "");
        if(convertedString.length() > 0)
        {
            return convertedString;
        }
        
        return nextWord();
    }
}
