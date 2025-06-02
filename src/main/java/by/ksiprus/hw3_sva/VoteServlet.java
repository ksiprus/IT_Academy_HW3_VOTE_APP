package by.ksiprus.hw3_sva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genresArray = req.getParameterValues("genres");
        String comment = req.getParameter("comment");

        // Проверка количества жанров
        if (genresArray == null || genresArray.length < 3 || genresArray.length > 5) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Выберите от 3 до 5 жанров.");
            return;
        }

        List<String> genres = Arrays.asList(genresArray);

        // Сохраняем голос
        VoteData.getInstance().addVote(artist, genres, comment);

        // Перенаправляем на страницу результатов
        resp.sendRedirect("results");
    }
}
