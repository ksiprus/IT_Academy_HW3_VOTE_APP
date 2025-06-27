package by.ksiprus.storage;

import by.ksiprus.dto.Vote;
import by.ksiprus.storage.api.IVoteStorage;
import by.ksiprus.utils.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
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
        List<Vote> votesResult = new ArrayList<>();
        String sql = "SELECT dt_create, artist, genres, about FROM vote_app.vote";

        try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                String artist = resultSet.getString("artist");
                String[] genres = (String[]) resultSet.getArray("genres").getArray();
                String about = resultSet.getString("about");


                Vote vote = new Vote();
                vote.setDtCreate(dtCreate);
                vote.setArtist(artist);
                vote.setGenres(Arrays.asList(genres));
                vote.setAbout(about);
                votesResult.add(vote);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка голосов", e);
        }
        return votesResult;
    }
}
