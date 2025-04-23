package iterator;

import episode.Episode;

public class SkipIntroIterator implements EpisodeIterator {
    private EpisodeIterator baseIterator;
    private int skipSeconds;

    public SkipIntroIterator(EpisodeIterator baseIterator, int skipSeconds) {
        this.baseIterator = baseIterator;
        this.skipSeconds = skipSeconds;
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public Episode next() {
        final Episode original = baseIterator.next();
        return new Episode(original.getTitle(), original.getRuntimeSec()) {
            @Override
            public void play() {
                System.out.println("Playing '" + getTitle() + "' starting at " + skipSeconds + " seconds");
            }
        };
    }
}
