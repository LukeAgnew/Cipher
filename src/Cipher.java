import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Random;

public class Cipher {
	public static final char[] ALPHABET_LETTERS_AND_SPACE_CHAR = {'a','b','c','d','e','f','g','h','i','j',
										'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
	
	public static void main(String[] args) {
		try
		{
			String userInput = JOptionPane.showInputDialog("Please enter your plain text phrase:");
			Scanner inputScanner = new Scanner(userInput);
			
			String plainText = inputScanner.nextLine();
			plainText = plainText.toLowerCase();
			
			inputScanner.close();
			
			char[] plainTextChars = plainText.toCharArray();
			
			char[] lettersAndSpace = ALPHABET_LETTERS_AND_SPACE_CHAR;
			
			char[] mapping = createCipher(lettersAndSpace);
			
			String cipherText = encrypt(plainTextChars, mapping, lettersAndSpace);
			
			JOptionPane.showMessageDialog(null, "The cipher text phrase is: " + cipherText);
			
			char[] cipherTextChars = cipherText.toCharArray();
			String restoredPlainText = decrypt(cipherTextChars, mapping, lettersAndSpace);
			
			JOptionPane.showMessageDialog(null, "The restored plain text phrase is: " + restoredPlainText);
		}
		catch (NullPointerException exception)
		{
			JOptionPane.showMessageDialog(null, "You must enter a valid phrase.");
		}
		catch (java.util.NoSuchElementException exception)
		{
			JOptionPane.showMessageDialog(null, "You must enter a valid phrase." );
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			JOptionPane.showMessageDialog(null, "You have entered one or more invalid elements in your phrase, "
																	+ "only letters and spaces are accepted.");
		}
	}
	
	public static char[] createCipher(char[] lettersAndSpace) 
	{
		char[] mapping = new char[lettersAndSpace.length];
		System.arraycopy(lettersAndSpace, 0, mapping, 0, lettersAndSpace.length);
		
		Random generator = new Random();
			
		for (int index = 0; index <mapping.length; index++)
		{
			int otherIndex = generator.nextInt(mapping.length);
			
			char temp = mapping[index];
			
			mapping[index] = mapping[otherIndex];
			mapping[otherIndex] = temp;					
		}
		return mapping;
	}
	
	public static String encrypt(char[] plainTextChars, char[] mapping, char[] lettersAndSpace) throws ArrayIndexOutOfBoundsException
	{
		char[] cipherTextChars = new char[plainTextChars.length];		
		
		for (int index = 0; index < cipherTextChars.length; index++)
		{
			int count = 0;
			while (plainTextChars[index] != lettersAndSpace[count])
			{
				count++;
				if (count > 26)
				{
					throw new ArrayIndexOutOfBoundsException();
				}
			}					
			cipherTextChars[index] = mapping[count];
		}	
		String cipherText = new String(cipherTextChars);
		return cipherText;		
	}
	
	public static String decrypt(char[] cipherTextChars, char[] mapping, char[] lettersAndSpace) throws ArrayIndexOutOfBoundsException
	{
		char[] restoredPlainTextChars = new char[cipherTextChars.length];
		
		for (int index = 0; index < restoredPlainTextChars.length; index++)
		{
			int count = 0;
			while (cipherTextChars[index] != mapping[count])
			{
				count++;
				
				if (count > 26)
				{
					throw new ArrayIndexOutOfBoundsException();
				}
			}
			restoredPlainTextChars[index] = lettersAndSpace[count];
		}		
		String restoredPlainText = new String(restoredPlainTextChars);
		return restoredPlainText;			
	}
	
}
