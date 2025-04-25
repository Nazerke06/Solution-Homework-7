package firstTask.episode;

public class EpisodeView {
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

