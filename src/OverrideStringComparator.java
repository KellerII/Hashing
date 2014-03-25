//Override comparator for string
public class OverrideStringComparator implements OverrideComparator<String>
{
    //Compares 2 strings
    public int compare(String string1, String string2) 
    {
            int length = string1.length();
            if (length > string2.length()) 
            {
                length = string2.length();
            }
            for (int i = 0; i < length; i++) 
            {
                int ec1 = (int) string1.charAt(i);
                int ec2 = (int) string2.charAt(i);
                if (ec1 != ec2)
                {
                    return ec1 - ec2;
                }
            }
            return string1.length() - string2.length();
    }
}