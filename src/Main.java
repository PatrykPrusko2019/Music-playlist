/**
 * 	    // Create a program that implements a playlist for songs
 *         // Create a Song class having Title and Duration for a song.
 *         // The program will have an Album class containing a list of songs.
 *         // The albums will be stored in an ArrayList
 *         // Songs from different albums can be added to the playlist and will appear in the list in the order
 *         // they are added.
 *         // Once the songs have been added to the playlist, create a menu of options to:-
 *         // Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
 *         // List the songs in the playlist
 *         // A song must exist in an album before it can be added to the playlist (so you can only play songs that
 *         // you own).
 *         // Hint:  To replay a song, consider what happened when we went back and forth from a city before we
 *         // started tracking the direction we were going.
 *         // As an optional extra, provide an option to remove the current song from the playlist
 *         // (hint: listiterator.remove()
 */
public class Main {
    public static void main(String[] args) {

        Album albumToAll = new Album();

        Album firstAlbum = new Album ("Iron Maiden");
        firstAlbum.addNewSongOfList("go to hell", 7, albumToAll);
        firstAlbum.addNewSongOfList("weekend", 4, albumToAll);

        Album secondAlbum = new Album("Korn");
        secondAlbum.addNewSongOfList("libido" , 8, albumToAll);
        secondAlbum.addNewSongOfList("alibaca", 6, albumToAll);

        Album thirdAlbum = new Album("Off spring");
        thirdAlbum.addNewSongOfList("I go back" , 9, albumToAll);
        thirdAlbum.addNewSongOfList("I looking for", 3, albumToAll);

        albumToAll.addNewAlbumToList(firstAlbum);
        albumToAll.addNewAlbumToList(secondAlbum);
        albumToAll.addNewAlbumToList(thirdAlbum);

        albumToAll.startApplication();

    }
}
