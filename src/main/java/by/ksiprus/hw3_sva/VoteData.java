package by.ksiprus.hw3_sva;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Синглтон-класс, отвечающий за управление данными голосования.
 * Хранит голоса за исполнителей, жанры и пользовательские комментарии в потокобезопасном режиме.
 */
public class VoteData {
    private static final VoteData INSTANCE = new VoteData();

    private final Map<String, Integer> artists = new ConcurrentHashMap<>();
    private final Map<String, Integer> genres = new ConcurrentHashMap<>();
    private final List<Comment> comments = Collections.synchronizedList(new ArrayList<>());

    private VoteData() {
    }

    public static VoteData getInstance() {
        return INSTANCE;
    }

    public void addVote(String artist, List<String> genresList, String commentText) {
        // Обновляем голоса исполнителей
        artists.merge(artist, 1, Integer::sum);

        // Обновляем голоса жанров
        for (String genre : genresList) {
            this.genres.merge(genre, 1, Integer::sum);
        }

        // Добавляем комментарий
        comments.add(new Comment(commentText, new Date()));
    }

    public Map<String, Integer> getArtists() {
        return artists;
    }

    public Map<String, Integer> getGenres() {
        return genres;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public static class Comment {
        private final String text;
        private final Date timestamp;

        public Comment(String text, Date timestamp) {
            this.text = text;
            this.timestamp = timestamp;
        }

        public String getText() {
            return text;
        }

        public Date getTimestamp() {
            return timestamp;
        }
    }
}