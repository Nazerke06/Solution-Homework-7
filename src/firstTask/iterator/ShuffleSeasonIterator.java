package firstTask.iterator;

import firstTask.episode.Episode;

import java.util.*;

public  class ShuffleSeasonIterator implements EpisodeIterator {
    private List<Episode> episodes;
    private int currentIndex = 0;

    public ShuffleSeasonIterator(List<Episode> episodes) {
        this.episodes = new ArrayList<>(episodes);
        Collections.shuffle(this.episodes, new Random(42));
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
