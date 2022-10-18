package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.io.*;
import java.util.*;

//This class implements DVDLibraryDao interface's methods.
public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    //declare and initialize variables.
    private Map<String, DVDLibrary> dvds = new HashMap<>();
    public static final String DVDLIBRARY_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    /*This method is for adding a new DVD to the file. With the loadDVD() function it reads the DVD text file.
    Then, with the put() function it adds the new DVD to the HashMap dvds.
    Finally, with the writeDVD() function it writes the new HashMap dvds on the file.
     */
    @Override
    public DVDLibrary addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary newDVD = dvds.put(title,dvd);
        try{
            writeDVD();
        }catch (Exception e ){
            throw new DVDLibraryDaoException("ERROR");
        }
        return newDVD;
    }

    /*This method is for listing all the DVDs to the user. With the loadDVD() function it reads the DVD text file.
    Then, return as an ArrayList for display.
     */
    @Override
    public List<DVDLibrary> getAllDVDs() throws DVDLibraryDaoException {
        loadDVD();
        return new ArrayList<DVDLibrary>(dvds.values());
    }
    /*This method is for displays a particular DVD to the user. With the loadDVD() function it reads the DVD text file.
    Then, return with get() function in HashMap.
    */
    @Override
    public DVDLibrary getDVDByTitle(String title) throws DVDLibraryDaoException {
        loadDVD();
        return dvds.get(title);
    }

    /*This method is for removing an existing DVD from the file. With the loadDVD() function it reads the DVD text file.
    Then, with the remove() function it removes the existing DVD from the HashMap dvds.
    Finally, with the writeDVD() function it writes the new HashMap on the file.
     */
    @Override
    public DVDLibrary removeDVD(String title) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary removedDVD = dvds.remove(title);
        try{
            writeDVD();
        }catch (Exception e ){
            throw new DVDLibraryDaoException("ERROR");
        }
        return removedDVD;
    }

    /*This method is for editing release date information on an existing DVD from the file.
    With the loadDVD() function it reads the DVD text file.
    Then, with the get() function it gets the particular DVD from the HashMap dvds,
    and with the setReleaseDate() function it sets new value for that DVD.
    Finally, with the writeDVD() function it writes the new HashMap on the file.
     */
    @Override
    public DVDLibrary editReleaseDate(String title, String newReleaseDate) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    /*This method is for editing Mpaa rating information on an existing DVD from the file.
    With the loadDVD() function it reads the DVD text file.
    Then, with the get() function it gets the particular DVD from the HashMap dvds,
    and with the setMpaaRating() function it sets new value for that DVD.
    Finally, with the writeDVD() function it writes the new HashMap on the file.
     */
    @Override
    public DVDLibrary editMPAA(String title, String newMpaaRating) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setMpaaRating(newMpaaRating);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    /*This method is for editing director name information on an existing DVD from the file.
    With the loadDVD() function it reads the DVD text file.
    Then, with the get() function it gets the particular DVD from the HashMap dvds,
    and with the setDirectorName() function it sets new value for that DVD.
    Finally, with the writeDVD() function it writes the new HashMap on the file.
     */
    @Override
    public DVDLibrary editDirectorName(String title, String newDirectorName) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setDirectorName(newDirectorName);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }
    /*This method is for editing studio name information on an existing DVD from the file.
     With the loadDVD() function it reads the DVD text file.
     Then, with the get() function it gets the particular DVD from the HashMap dvds,
     and with the setStudio() function it sets new value for that DVD.
     Finally, with the writeDVD() function it writes the new HashMap on the file.
      */
    @Override
    public DVDLibrary editStudio(String title, String newStudioName) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }
    /*This method is for editing user note information on an existing DVD from the file.
     With the loadDVD() function it reads the DVD text file.
     Then, with the get() function it gets the particular DVD from the HashMap dvds,
     and with the setNote() function it sets new value for that DVD.
     Finally, with the writeDVD() function it writes the new HashMap on the file.
      */
    @Override
    public DVDLibrary editNote(String title, String newUserNote) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setNote(newUserNote);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    /* This method is for unmarshalling a DVD.
    It reads a line from specified text file, and it separates values with a specified delimiter.
    With the set functions, it assigns values of the object.
    Returns the DVDLibrary object.
     */
    public DVDLibrary unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdTitle = dvdTokens[0];
        DVDLibrary dvdFromFile = new DVDLibrary(dvdTitle);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setNote(dvdTokens[5]);

        return dvdFromFile;
    }
    /* This method is for reading from a text file.
    With the Scanner object, Buffered Reader, and File Reader, it opens the file and reads it.
    It uses unmarshallDV() function to get a particular DVD, and then it creates a HashMap contains all DVDs.
     */
    private void loadDVD() throws DVDLibraryDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner( new BufferedReader( new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.");
        }

        String currentLine;
        DVDLibrary currentDVD;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);

        }

        scanner.close();
    }
    /* This method is for marshalling a DVD.
     It creates a line for writing into a file, and it adds values with a specified delimiter.
     With the get functions, it gets values of the object.
     Returns the DVDLibrary object information as a text.
      */
    private String marshallDVD(DVDLibrary dvd){
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getMpaaRating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getNote();

        return dvdAsText;
    }
    /* This method is for writing into a text file.
     With the PrinterWriter, it opens the file and prepare to be written.
     It uses marshallDVD() function to write a particular DVD information. It gets the string from it and with the println()
     function it writes into the file.
     With the flush() function it forces everything to be written to the file.
      */
    private void writeDVD() throws DVDLibraryDaoException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save dvd data.");
        }

        String dvdAsText;
        List<DVDLibrary> dvdList = this.getAllDVDs();
        for (DVDLibrary currentDVD : dvdList) {

            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();

        }
        out.close();
    }
}
