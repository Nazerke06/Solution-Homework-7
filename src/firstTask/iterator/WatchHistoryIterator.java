package firstTask.iterator;

import firstTask.episode.Episode;

import java.util.*;

public  class WatchHistoryIterator implements EpisodeIterator {
    private EpisodeIterator baseIterator;
    private Set<String> watchedEpisodes;
    private Episode nextEpisode;

    public WatchHistoryIterator(EpisodeIterator baseIterator) {
        this.baseIterator = baseIterator;
        this.watchedEpisodes = new HashSet<>();
        findNext();
    }

    public void markAsWatched(String episodeTitle) {
        watchedEpisodes.add(episodeTitle);
        if (nextEpisode != null && episodeTitle.equals(nextEpisode.getTitle())) {
            findNext();
        }
    }

    private void findNext() {
        nextEpisode = null;
        while (baseIterator.hasNext()) {
            Episode candidate = baseIterator.next();
            if (!watchedEpisodes.contains(candidate.getTitle())) {
                nextEpisode = candidate;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextEpisode != null;
    }

    @Override
    public Episode next() {
        if (!hasNext()) throw new NoSuchElementException();
        Episode result = nextEpisode;
        findNext();
        return result;
    }
}
