package Candidatures;


	import java.io.*;
	import java.util.*;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

import javafx.scene.control.Label;

	/**
	 * In this test I think I'm starting to get a little smarter as far as how Jazzy works.
	 * Here are some of the things I've done:
	 * (1) I've switched from the FileWordTokenizer to the StringWordTokenizer.
	 * (2) I learned that "spellChecker.addSpellCheckListener(this)" can only be run once.
	 * (3) I've also started to abstract/extract some of the functionality out of the constructor and into other methods,
	 * such as the new "populateListOfMisspelledWords" and "printWordsInMisspelledList" methods.
	 */

	public class JazzyTest2 implements SpellCheckListener
	{
		
	  public String getString1() {
			return string1;
		}

		public void setString1(String string1) {
			this.string1 = string1;
		}

		public String getString2() {
			return string2;
		}

		public void setString2(String string2) {
			this.string2 = string2;
		}

	private final String DICTIONARY_FILE = "src/main/resources/en_US/english.0";
	  private  String string1 ;   // first test string
	  private  String string2 ;   // second test string
	  public static String h;
	  private SpellChecker spellChecker;
	  private ArrayList listOfMisspelledWords;

	  public ArrayList getListOfMisspelledWords() {
		return listOfMisspelledWords;
	}

	public void setListOfMisspelledWords(ArrayList listOfMisspelledWords) {
		this.listOfMisspelledWords = listOfMisspelledWords;
	}

	public JazzyTest2(String s1,String s2)
	  {
		string1=s1;
		string2=s2;
	    createDictionary();
	    spellChecker.addSpellCheckListener(this);

	    // run the test on string1
	    StringWordTokenizer texTok = 
	       new StringWordTokenizer(string1, new TeXWordFinder());
	    populateListOfMisspelledWords(texTok);
	    errors=printWordsInMisspelledList();

	    // run a second test, this one on string2
	    texTok = new StringWordTokenizer(string2, new TeXWordFinder());
	    populateListOfMisspelledWords(texTok);
	    errors=printWordsInMisspelledList();
	  }

	  private void createDictionary()
	  {
	    File dict = new File(DICTIONARY_FILE);
	    try
	    {
	      spellChecker = new SpellChecker(new SpellDictionaryHashMap(dict));
	    }
	    catch (FileNotFoundException e)
	    {
	      System.err.println("Dictionary File '" + dict + "' not found! Quitting. " + e);
	      System.exit(1);
	    }
	    catch (IOException ex)
	    {
	      System.err.println("IOException occurred while trying to read the dictionary file: " + ex);
	      System.exit(2);
	    }
	  }

	  private void populateListOfMisspelledWords(StringWordTokenizer texTok)
	  {
	    listOfMisspelledWords = new ArrayList();
	    spellChecker.checkSpelling(texTok);
	  }

	  public  String printWordsInMisspelledList()
	  {
	    Iterator it = listOfMisspelledWords.iterator();
	    h ="";
	    while (it.hasNext())
	    {
	    h=h+ it.next().toString();
	   
	      System.out.println("listOfMisspelledWords: " + it.next());
	    }
	    
	    return h;
	  }

	  public void spellingError(SpellCheckEvent event)
	  {
	    event.ignoreWord(true);
	    listOfMisspelledWords.add(event.getInvalidWord());
	  }

	}

