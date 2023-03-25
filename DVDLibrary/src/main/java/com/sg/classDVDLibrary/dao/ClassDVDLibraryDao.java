package com.sg.classDVDLibrary.dao;

import com.sg.classDVDLibrary.dto.DVD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface ClassDVDLibraryDao {

    // Add DVD
    DVD addDVD(String dvdTitle, DVD dvd)
            throws ClassDVDLibraryDaoException;

    //List DVDs
    List<DVD> getAllDVDs()
            throws ClassDVDLibraryDaoException;

    //Find a DVD by title
    DVD getDVD(String title)
            throws ClassDVDLibraryDaoException;

    //Edit DVD Info
    public DVD EditDVDInfo(String dvdTitle, String newTitle)
            throws ClassDVDLibraryDaoException;

    //Remove DVD
    DVD removeDVD(String dvdTitle)
            throws ClassDVDLibraryDaoException;

    DVD DVDInfo(String dvdTitle)
            throws ClassDVDLibraryDaoException;

    ArrayList<String> searchDVD(String searchKeyword)
            throws ClassDVDLibraryDaoException;

    List<DVD> searchLastNYearsDVDs(int years)
            throws ClassDVDLibraryDaoException;

     List<DVD> searchByMpaaDVDs(String mpaa);

    List<DVD> searchByDirectorDVDs(String directorName);

    List<DVD> searchByStudioDVDs(String studioName);

    public OptionalDouble averageAgeOfMovies();

     Optional<DVD> newestDVDSearch();

    Optional<DVD> oldestDVDSearch();

    public OptionalDouble averageNotes();






    }
