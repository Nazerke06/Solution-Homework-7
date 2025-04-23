package iterator;

import episode.Episode;

import java.util.*;

public class ReverseSeasonIterator implements EpisodeIterator {
    private List<Episode> episodes;
    private int currentIndex;

    public ReverseSeasonIterator(List<Episode> episodes) {
        this.episodes = new ArrayList<>(episodes);
        this.currentIndex = episodes.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return episodes.get(currentIndex--);
    }
}
