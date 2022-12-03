package com.company;

public class Anime {

    String MAL_ID;
    String name;
    double score;
    String genre;
    String type;
    int episodes;
    String premiered;
    double ranked;
    double popularity;
    int favorites;
    double matchingGenre;

    public Anime(String id, String name, String score, String genre, String type, String ep,
    String p, String ranked, String pop, String fav) {
        this.MAL_ID = id;
        this.name = name;
        if (score.equals("Unknown")) {
            this.score = 0.0;
        } else {
            this.score = Double.parseDouble(score) / 10;
        }
        this.type = type;
        if (ep.equals("Unknown")) {
            this.episodes = Integer.MAX_VALUE;
        } else {
            this.episodes = Integer.parseInt(ep);
        }
        this.premiered = p;
        if (ranked.equals("Unknown")) {
            this.ranked = 0;
        } else {
            this.ranked = 1 / Double.parseDouble(ranked);
        }
        this.popularity = 1 / Double.parseDouble(pop);
        this.favorites = Integer.parseInt(fav);
        this.genre = genre.replace("\"", "");
        this.matchingGenre = 0;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public void addGenreScore() {
        matchingGenre++;
    }

    public double averageGenreScore(int i) {
        return matchingGenre / i;
    }

    public double getScore() {
        return matchingGenre;
    }

    public double getScoreOutOfOne() {
        return score;
    }

    public String getType() {
        return type;
    }

    public double getRanked()  {
        return ranked;
    }

    public double getPopularity() {
        return popularity;
    }

}
