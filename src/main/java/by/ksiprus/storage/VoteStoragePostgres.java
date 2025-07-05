package by.ksiprus.storage;

import by.ksiprus.dto.Vote;
import by.ksiprus.storage.api.IVoteStorage;
import by.ksiprus.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteStoragePostgres implements IVoteStorage {

    @Override
    public void add(Vote vote) {

        List<String> genres = vote.getGenres();
        if (genres == null || genres.size() < 3 || genres.size() > 5) {
            throw new IllegalArgumentException("Количество жанров должно быть не менее 3 и не более 5");
        }

        String sql = "INSERT INTO vote_app.vote(dt_create, artist, genres, about) VALUES (now(), ?, ?, ?)";

        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, vote.getArtist());
            statement.setArray(2, connection.createArrayOf("varchar", genres.toArray()));
            statement.setString(3, vote.getAbout());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении голоса", e);
        }
    }

    @Override
    public List<Vote> getAll() {
        List<Vote> votes = new ArrayList<>();
        String sql = "SELECT dt_create, artist, genres, about FROM vote_app.vote";

        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vote vote = new Vote();
                vote.setDtCreate(resultSet.getTimestamp("dt_create").toLocalDateTime());
                vote.setArtist(resultSet.getString("artist"));
                vote.setGenres(Arrays.asList((String[]) resultSet.getArray("genres").getArray()));
                vote.setAbout(resultSet.getString("about"));
                votes.add(vote);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении голосов", e);
        }
        return votes;
    }
}
