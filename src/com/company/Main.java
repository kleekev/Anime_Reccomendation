package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is an Anime Recommender Program");
        System.out.println("Please answer the following questions");
        String answer = "";
        String[] genreList = new String[] {"Action", "Adventure",
                "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance",
                "Sci-Fi", "Slice of Life", "Sports", "Supernatural"};
        LinkedList<String> genre = new LinkedList<>(Arrays.stream(genreList).toList());
        int[] userGenre = new int[genre.size()];
        String type = "";
        Anime mostRecommended = null;
        System.out.println("You will choose the genres you prefer");
        System.out.println("For every genre type \"yes\" if you want it " +
                "or \"no\" if you do not want it");
        String reply = "";
        int index = 0;
        for (String g : genre) {
            System.out.println(g);
            while (!reply.equals("yes") && !reply.equals("no")) {
                reply = scanner.nextLine();
                if (!reply.equals("yes") && !reply.equals("no")) {
                    System.out.println("Type again");
                }
            }
            if (reply.equals("yes")) {
                userGenre[index] = 1;
            }
            reply = "";
            index++;
        }

        System.out.println("Would you prefer a movie or a show?");
        while (!reply.equals("movie") && !reply.equals("show")) {
                reply = scanner.nextLine();
                if (!reply.equals("movie") && !reply.equals("show")) {
                    System.out.println("Type again");
                }
            }
            if (reply.equals("movie")) {
                type = "Movie";
            } else {
                type = "TV";
            }
            System.out.println("Processing...");
            DocumentReader dr = new DocumentReader("anime.csv");
            LinkedList<String> information = dr.getInformation();
            LinkedList<Anime> animeList = dr.getAnimeList(information);
            Recommend r = new Recommend(animeList);
            r.scoreGenre(userGenre);
            mostRecommended = r.getRecommendation(type);
        System.out.println("The anime that is most recommended for you is " + mostRecommended.getName());;
    }

}
