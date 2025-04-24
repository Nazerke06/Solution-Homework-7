package model;

import iterator.BingeIterator;
import iterator.EpisodeIterator;

import java.util.*;

public class Series {
    private List<Season> seasons;

    public Series() {
        this.seasons = new ArrayList<>();
    }

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public EpisodeIterator getBingeIterator() {
        return new BingeIterator(seasons);
    }
}
