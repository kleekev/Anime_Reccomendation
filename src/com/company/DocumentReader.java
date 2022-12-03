package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class DocumentReader {

    Scanner sc;

    public DocumentReader(String document) {
        try {
            sc = new Scanner(new File(document));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<String> getInformation() {
        LinkedList<String> s = new LinkedList<>();
        sc.useDelimiter("END");
        while (sc.hasNext()) {
            s.add(sc.next());
        }
        s.removeLast();
        s.removeFirst();
        return s;
    }

    public LinkedList<Anime> getAnimeList(LinkedList<String> information) {
        LinkedList<Anime> animeList = new LinkedList<>();
        for (String s : information) {
            String[] array = s.split("SEPERATE");
            String id = array[0].replace(",", "").replace("\n", "");
            String name = array[1].replace(",", "").replace("\"", "");
            String score = array[2].replace(",", "");
            String genre = array[3].replace(",", "");
            String type = array[4].replace(",", "");
            String ep = array[5].replace(",", "");
            String pre = array[6].replace(",", "");
            String ranked = array[7].replace(",", "");
            String pop = array[8].replace(",", "");
            String fav = array[9].replace(",", "");
            Anime anime = new Anime(id, name, score, genre, type, ep, pre, ranked, pop, fav);
            animeList.add(anime);
        }
        return animeList;
    }

}
