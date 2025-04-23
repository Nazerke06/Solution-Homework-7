package iterator;

import episode.Episode;

import java.util.*;

public class SeasonIterator implements EpisodeIterator {
    private List<Episode> episodes;
    private int currentIndex = 0;

    public SeasonIterator(List<Episode> episodes) {
        this.episodes = new ArrayList<>(episodes);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < episodes.size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        return episodes.get(currentIndex++);
    }
}
