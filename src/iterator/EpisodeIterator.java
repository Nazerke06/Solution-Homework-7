package iterator;

import episode.Episode;

public interface EpisodeIterator {
    boolean hasNext();
    Episode next();
}
