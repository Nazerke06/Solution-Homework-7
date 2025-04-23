// After the Episode class...

class EpisodeView {
    private Episode episode;
    private int startOffset;

    public EpisodeView(Episode episode, int startOffset) {
        this.episode = episode;
        this.startOffset = startOffset;
    }

    public void play() {
        System.out.println("Playing '" + episode.getTitle() + "' starting at " + startOffset + " seconds");
    }

    public Episode getEpisode() {
        return episode;
    }
}

// After other iterator implementations...

// First, modify SkipIntroIterator
class SkipIntroIterator implements EpisodeIterator {
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

// Then fix WatchHistoryIterator
class WatchHistoryIterator implements EpisodeIterator {
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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Episode result = nextEpisode;
        findNext();
        return result;
    }
}