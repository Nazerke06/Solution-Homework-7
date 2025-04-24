package iterator;

import episode.Episode;
import model.Season;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private List<Season> seasons;
    private int currentSeason = 0;
    private EpisodeIterator currentIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasons = new ArrayList<>(seasons);
        if (!seasons.isEmpty()) {
            currentIterator = seasons.get(0).normalIterator();
        }
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null) return false;
        while (!currentIterator.hasNext() && currentSeason < seasons.size() - 1) {
            currentSeason++;
            currentIterator = seasons.get(currentSeason).normalIterator();
        }
        return currentIterator.hasNext();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return currentIterator.next();
    }
}
