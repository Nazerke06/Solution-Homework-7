package firstTask.iterator;

import firstTask.episode.Episode;

public  interface EpisodeIterator {
    boolean hasNext();
    Episode next();
}
