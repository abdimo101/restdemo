/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;
import entities.Movie;

import java.util.ArrayList;
import java.util.List;


public class MovieDTO {
    private int year;
    private String title;
    private List<String> actors = new ArrayList<>();


    public MovieDTO(String title, int year, List<String> actors) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public MovieDTO(Movie movie) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
