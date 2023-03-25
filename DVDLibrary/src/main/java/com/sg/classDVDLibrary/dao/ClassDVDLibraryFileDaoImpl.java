package com.sg.classDVDLibrary.dao;

import com.sg.classDVDLibrary.dto.DVD;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClassDVDLibraryFileDaoImpl implements ClassDVDLibraryDao {

    public static final String DVDLibrary_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";


    //To look up DVDs by title key in a Hashmap
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        DVD prevDVD = dvds.put(title, dvd);
        writeDVDLibrary();
        return prevDVD;
    }


    @Override
    public List<DVD> getAllDVDs()
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD DVDInfo(String dvdTitle)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        return (dvds.get(dvdTitle));
    }

    @Override
    public DVD EditDVDInfo(String dvdTitle, String newTitle)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        DVD newRecord = dvds.get(dvdTitle);
        newRecord.setTitle(newTitle);
        writeDVDLibrary();
        return newRecord;
    }

    @Override
    public DVD removeDVD(String dvdTitle)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        DVD removedDVD = dvds.remove(dvdTitle);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public ArrayList<String> searchDVD(String searchKeyword)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        ArrayList<String> searchResult = new ArrayList<String>();
        for (String i : dvds.keySet()) {
            if (i.contains(searchKeyword)) {
                searchResult.add(i);
            }
        }
        return searchResult;
    }

    //Find all movies released in the last N years
    @Override
    public List<DVD> searchLastNYearsDVDs(int years)
            throws ClassDVDLibraryDaoException {
        loadDVDLibrary();
        int currentYear = LocalDate.now().getYear();

        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        return listOfDVDs.stream()
                .filter(DVD -> currentYear - DVD.getReleaseDate().getYear() < years)
                .collect(Collectors.toList());

    }


    //Find all the movies with a given MPAA rating
    @Override
    public List<DVD> searchByMpaaDVDs(String mpaaRating) {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());

        return listOfDVDs.stream()
                .filter(DVD -> DVD.getMpaaRating().equals(mpaaRating))
                .collect(Collectors.toList());
    }


    //Find all the movies by a given director
   //sorted into separate data structures by MPAA rating ??
    @Override
    public List<DVD> searchByDirectorDVDs(String directorName) {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());

        return listOfDVDs.stream()
                .filter(DVD -> DVD.getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());

    }


    //Find all the movies released by a particular studio


    @Override
    public List<DVD> searchByStudioDVDs(String studioName) {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        return listOfDVDs.stream()
                .filter(DVD -> DVD.getStudio().equalsIgnoreCase(studioName))
                .collect(Collectors.toList());
    }


    //Find the average age of the movies in the collection
    @Override
    public OptionalDouble averageAgeOfMovies() {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        int currentYear = LocalDate.now().getYear();
        return listOfDVDs.stream()
                .mapToInt(DVD -> currentYear - DVD.getReleaseDate().getYear())
                .average();
    }


    //Find the newest movie in your collection

    public Optional<DVD> newestDVDSearch()  {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        return listOfDVDs.stream()
                .min((DVD1,DVD2)->DVD1.getReleaseDate().compareTo(DVD2.getReleaseDate()));
    }



    //Find the oldest movie in your collection
    @Override
    public Optional<DVD> oldestDVDSearch() {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        return listOfDVDs.stream()
                .max((DVD1,DVD2)->DVD1.getReleaseDate().compareTo(DVD2.getReleaseDate()));
    }



    //Find the average number of notes associated with movies in your collection
    public OptionalDouble averageNotes() {
        List<DVD> listOfDVDs = new ArrayList<DVD>(dvds.values());
        return listOfDVDs.stream()
                .mapToDouble(DVD -> DVD.getUserNote().trim().length() == 0 ? 0 : 1)
                .average();
    }




    // File loading and writing
    private DVD unmarshallDVD(String dvdAsText){
        // DVDAsText is expecting a line read in from our file.
            String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(title);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - ReleaseDate
        LocalDate releaseDate = LocalDate.parse(dvdTokens[1]);
        // remove the line below to convert String to localDate
        //dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - MpaaRating
        dvdFromFile.setMpaaRating(dvdTokens[2]);

        // Index 3 - DirectorName
        dvdFromFile.setDirectorName(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - DirectorName
        dvdFromFile.setUserNote(dvdTokens[5]);

        // We have now created a dvd! Return it!
        return dvdFromFile;
    }


// loadDVDLibrary method
    private void loadDVDLibrary() throws ClassDVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDLibrary_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassDVDLibraryDaoException(
                    "-_- Could not load DVD Library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent DVD unmarshalled
        DVD currentdvd;
        // Go through DVDLibrary_FILE line by line, decoding each line into a
        // DVD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentdvd = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our DVD object.
            // Put currentStudent into the map using DVD title as the key
            dvds.put(currentdvd.getTitle(), currentdvd);
        }
        // close scanner
        scanner.close();
    }


// marshallDVD method
    private String marshallDVD(DVD aDVD){
        // We need to turn a DVD object into a line of text for our file.

        // Start with the dvd title, since that's supposed to be first.
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:

        // ReleaseDate
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;

        // MPAARating
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;

        // Director NAme
        dvdAsText += aDVD.getDirectorName() + DELIMITER;

        // Studio
        dvdAsText += aDVD.getStudio() + DELIMITER;

        // Not
        dvdAsText += aDVD.getUserNote();

        // We have now turned a DVD to text!
        return dvdAsText;
    }


    // Write in file

    /**
     * Writes all DVDs in the DVD Library out to DVDLibrary_FILE.  See loadDVDLibrary
     * for file format.
     *
     * @throws ClassDVDLibraryDaoException if an error occurs writing to the file
     */
    private void writeDVDLibrary() throws ClassDVDLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLibrary_FILE));
        } catch (IOException e) {
            throw new ClassDVDLibraryDaoException(
                    "Could not save student data.", e);
        }

        // Write out the DVD objects to the DVD Library file.
        // NOTE TO THE APPRENTICES: We could just grab the DVD map,
        // get the Collection of DVDS and iterate over them but we've
        // already created a method that gets a List of DVDs so
        // we'll reuse it.
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }


}
