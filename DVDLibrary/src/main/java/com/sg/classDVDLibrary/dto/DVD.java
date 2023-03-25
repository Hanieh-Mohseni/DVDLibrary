package com.sg.classDVDLibrary.dto;

import java.time.LocalDate;

public class DVD {
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;

    public DVD(String title) {
        this.title=title;
    }


    //dvdId is a read-only field. It is passed in as a parameter to the constructor,

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getStudio() {
        return studio;
    }


    public String getUserNote() {
        return userNote;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public void setStudio(String studio) {
        this.userNote = userNote;
    }
}
