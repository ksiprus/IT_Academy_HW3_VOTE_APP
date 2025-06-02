package by.ksiprus.hw3_sva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        VoteData voteData = VoteData.getInstance();

        // Сортируем исполнителей по голосам
        List<Map.Entry<String, Integer>> sortedArtists = voteData.getArtists().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        // Сортируем жанры по голосам
        List<Map.Entry<String, Integer>> sortedGenres = voteData.getGenres().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        // Сортируем комментарии по дате
        List<VoteData.Comment> sortedComments = voteData.getComments().stream()
                .sorted(Comparator.comparing(VoteData.Comment::getTimestamp).reversed())
                .toList();

        // Выводим результаты
        writer.println("<html><body>");
        writer.println("<h1>Результаты голосования</h1>");

        writer.println("<h2>Лучшие исполнители</h2>");
        writer.println("<ul>");
        for (Map.Entry<String, Integer> entry : sortedArtists) {
            writer.println("<li>" + entry.getKey() + ": " + entry.getValue() + " голосов</li>");
        }
        writer.println("</ul>");

        writer.println("<h2>Любимые жанры</h2>");
        writer.println("<ul>");
        for (Map.Entry<String, Integer> entry : sortedGenres) {
            writer.println("<li>" + entry.getKey() + ": " + entry.getValue() + " голосов</li>");
        }
        writer.println("</ul>");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        writer.println("<h2>Комментарии</h2>");
        writer.println("<ul>");
        for (VoteData.Comment comment : sortedComments) {
            writer.println("<li>" + comment.getText() + " (" + dateFormat.format(comment.getTimestamp()) + ")</li>");
        }
        writer.println("</ul>");
        // Кнопка для возврата к голосованию
        writer.println("<a href='form.html'><button>Спасибо за ваш голос</button></a>");

        writer.println("</body></html>");
    }
}