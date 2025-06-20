package by.ksiprus.dto;

import java.util.Objects;

public class VoteStat {
    private String title;
    private int votes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotes() {
        return votes;
    }

    public void add(int value) {
        this.votes += value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteStat voteStat = (VoteStat) o;
        return Objects.equals(title, voteStat.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}



