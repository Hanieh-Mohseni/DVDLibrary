package com.sg.classDVDLibrary.ui;

import com.sg.classDVDLibrary.dto.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Component
public class ClassDVDLibraryView {

    @Autowired
    private UserIO io;
    public ClassDVDLibraryView(UserIO io) {
        this.io = io;
    }

    //private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. ADD DVD");
        io.print("3. FIND a DVD");
        io.print("4. Remove a DVD");
        io.print("5. EDIT DVD INFO");
        io.print("6. SEARCH DVD");
        io.print("7. Exit");

        return  io.readInt("Please select from the"
                + " above choices.", 1, 6);
    }

    public DVD getNewDVDInfo() {
        io.readString("\n");
        String title = io.readString("Please enter DVD Title");
        String inputDate = io.readString("Please enter Release Date");
        LocalDate releaseDate = dateStringToLocalDate(inputDate);
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter Studio");
        String userNote = io.readString("Please enter Extra Note");

        DVD  currentDVD = new DVD (title);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);

        return currentDVD;
    }

    public void displayAddDVDtBanner() {
        io.print("=== ADD DVD ===");
    }
    public void displayAddSuccessBanner() {
        io.readString(
                "DVD successfully Added.  Please hit enter to continue Adding");
    }


// to continue adding or going back to the main menu
public int getAddContinueGetSelection() {
    io.print("1. Main Menu");
    io.print("2. ADD DVD");
    return  io.readInt("Please select from the"
            + " above choices.", 1, 2);
    }

// to display all the DVDs

    public void displayDVDList(List<DVD> dvdList) {
        io.readString("\n");
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s : %s %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getDirectorName(),
                    currentDVD.getMpaaRating(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getUserNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }


    // to show the Display All DVDs banner

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }


// To find a DVD

    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    public String getDVDTitleChoice() {
        io.readString("\n");
        return io.readString("Please enter the title of the DVD.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirectorName());
            io.print(dvd.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            io.print(dvd.getMpaaRating());
            io.print(dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }


    // To remove a dvd

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    // To have multiple remove in one session
    // to continue removing or going back to the main menu
    public int getRemoveContinueGetSelection() {
        io.print("1. Main Menu");
        io.print("2. Remove DVD");
        return  io.readInt("Please select from the"
                + " above choices.", 1, 2);
    }


    //Search DVD
    public void displaySearchDVDBanner () {
        io.print("=== SEARCH DVD ===");
    }

    public void displaySearchResultBanner() {
        io.readString("All Search Result. Please hit enter to continue");
    }

    public String getSearchKeyword() {
        io.readString("\n");
        return io.readString("Please enter the search keyword for title of the DVD.");
    }

    public int getFullInfoMenuSelection() {
        io.print("1. Main Menu");
        io.print("2. DVD INFO");
        return  io.readInt("Please select from the"
                + " above choices.", 1, 2);
    }

    public String displayEnterKeywordBanner(){
        io.readString("\n");
        String kewWord = io.readString("Please Select one of The DVD Titles to See Full Info");
        return kewWord;
    }



    // Edit title

    public void displayEdiTitleBanner () {
        io.print("=== SEARCH DVD ===");
    }

    public void displayEditResultBanner() {
        io.readString("\n");
        io.readString("The Title is Updated. Please hit enter to continue");
    }

    public String getTitleToEdit() {
        io.readString("\n");
        return io.readString("Please enter the New title for the DVD.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }


    private static LocalDate dateStringToLocalDate(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(inputDate, formatter);
    }



}
