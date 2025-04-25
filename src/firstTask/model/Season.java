package firstTask.model;

import firstTask.episode.Episode;
import firstTask.iterator.*;
import java.util.*;

public  class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    @Override
    public Iterator<Episode> iterator() {
        return (Iterator<Episode>) new SeasonIterator(episodes);
    }

    public EpisodeIterator normalIterator() {
        return new SeasonIterator(episodes);
    }

    public EpisodeIterator reverseIterator() {
        return new ReverseSeasonIterator(episodes);
    }

    public EpisodeIterator shuffleIterator() {
        return new ShuffleSeasonIterator(episodes);
    }
}
