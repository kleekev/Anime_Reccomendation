package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Recommend {
    HashMap<String, LinkedList<Anime>> genreMap;
    HashMap<Anime, Double> recommendMap;
    String[] genreList = new String[] {"Action", "Adventure",
            "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance",
            "Sci-Fi", "Slice of Life", "Sports", "Supernatural"};
    LinkedList<Anime> animeList;
    HashMap<String, Anime> animeMap;

    public Recommend(LinkedList<Anime> animeList) {
        genreMap = new HashMap<>();
        recommendMap = new HashMap<>();
        animeMap = new HashMap<>();
        this.animeList = animeList;
        for (String genre : genreList) {
            genreMap.put(genre, new LinkedList<>());
        }
        for (Anime anime : animeList) {
            putIntoMap(anime, genreList);
            animeMap.put(anime.getName(), anime);
        }
    }

    public void putIntoMap(Anime anime, String[] genreList) {
        String genre = anime.getGenre();
        for (String g : genreList) {
            if (genre.contains(g)) {
                LinkedList<Anime> animeList = genreMap.get(g);
                animeList.add(anime);
                genreMap.put(g, animeList);
            }
        }
    }

    public HashMap<String, LinkedList<Anime>> getGenreMap() {
        return genreMap;
    }

    public void scoreGenre(int[] userGenre) {
        LinkedList<String> wantedGenres = new LinkedList<>();
        int index = 0;
        for (int i : userGenre) {
            if (i == 1) {
                wantedGenres.add(genreList[index]);
            }
            index++;
        }
        for (String genre : wantedGenres) {
            LinkedList<Anime> list = genreMap.get(genre);
            for (Anime a : list) {
                a.addGenreScore();
            }
        }

        for (Anime a : animeList) {
            a.averageGenreScore(wantedGenres.size());
            recommendMap.put(a, a.averageGenreScore(wantedGenres.size()) * a.getScoreOutOfOne() * a.getRanked() * a.getPopularity());
        }
    }

    public Anime getRecommendation(String type) {
        Anime mostRecommend = null;
        double highestScore = 0;
        Set<Anime> set = recommendMap.keySet();
        for (Anime a : set) {
           double score = recommendMap.get(a);
           if (a.getType().equals(type)) {
               if (score > highestScore) {
                   highestScore = score;
                   mostRecommend = a;
               }
           }
        }
        return mostRecommend;
    }

    public Anime getAnimeRecommendation(String s) {
        Anime a = animeMap.get(s);
        String genre = a.getGenre();
        Set<String> genreList = genreMap.keySet();
        for (String g : genreList) {
            if (genre.contains(g)) {
                LinkedList<Anime> list = genreMap.get(g);
                for (Anime anime : list) {
                    double score = recommendMap.get(anime);
                    score++;
                    recommendMap.put(anime, score);
                }
            }
        }
        animeList.remove(a);
        double highestScore = 0;
        Anime mostRecommend = null;
        for (Anime anime : animeList) {
            if (recommendMap.get(anime) > highestScore) {
                mostRecommend = anime;
                highestScore = recommendMap.get(anime);
            }
        }
        return mostRecommend;
    }
}
