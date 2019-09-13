import java.util.*;

public class Album extends Playlist {
    private String name;
    ChooseFromCreatedPlaylists choosePlaylists;
    private ArrayList<Song> listOfSongInAlbum;
    public ArrayList<Album> listOfAlbum;
    private ArrayList<Song> listAllSongsToPlaylist;
    private Scanner sc = new Scanner(System.in);
    public LinkedList<Song> playlistSong;

    public Album() {
        this.listOfAlbum = new ArrayList<>();
        this.listAllSongsToPlaylist = new ArrayList<>();
        this.playlistSong = new LinkedList<>();
        this.choosePlaylists = new ChooseFromCreatedPlaylists();
    }

    public Album(String name) {
        this.name = name;
        this.listOfSongInAlbum = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // add new album
    public void addNewAlbumToList(Album album) {
        listOfAlbum.add(album); //add new album
    }


    // add new song to list of songs
    public void addNewSongOfList(String nameSong, int duration, Album albumToAll) {
        Song newSong = new Song(nameSong, duration);
        listOfSongInAlbum.add(newSong);
        albumToAll.listAllSongsToPlaylist.add(newSong); //add new song to playlist maybe
    }


    public void printListOfSongsInAlbum() {
        System.out.println("****** name Album: " + this.name + " *********");
        int i = 1;
        for (Song song : this.listOfSongInAlbum) {
            System.out.println((i++) + ". song : " + song.getTitle() + ", duration : " + song.getDuration());
        }

    }

    public void printListOfAlbums() {
        int i = 1;
        for (Album album : this.listOfAlbum) {
            System.out.println((i++) + ". name album : " + album.getName());
        }
    }


    public void startApplication() {

        System.out.println("start application :\n******** Playlist for songs ********");
        showOptions();
        int number;
        boolean flag = false;
        while (!flag) {
            System.out.println("\nenter the number (options show -> 0 ) : ");
            boolean isInt = sc.hasNextInt();

            if (isInt) {
                number = sc.nextInt();
                sc.nextLine();

                switch (number) {
                    case 0: {
                        showOptions();
                        break;
                    }

                    case 1: {
                        System.out.println("****** displays all songs *******");
                        printAllSongs();
                        break;
                    }
                    case 2: {
                        System.out.println("****** add new song in playlist *******");
                        addNewSongInPlaylist();
                        break;
                    }
                    case 3: {
                        System.out.println("****** add album in playlist *******");
                        addAlbumToPlaylist();
                        break;
                    }
                    case 4: {
                        System.out.println("****** display of new songs added to the playlist ********");
                        printListOfPlaylistToCreate();
                        break;
                    }
                    case 5: {
                        if (!ifPlaylistIsEmpty()) { // ! false -> true
                            System.out.println("****** new playlist added to list *********");
                            Playlist playlist = new Playlist(playlistSong);
                            choosePlaylists.addNewPlaylistInList(playlist);
                            playlist.playlistAndStart();
                            if(playlist.getNewSongPlaylist().isEmpty()) {
                                System.out.println("deleted empty playlist\n");
                                choosePlaylists.removesPlaylistInList(playlist);  //deletes an empty playlist
                            }
                            playlistSong.removeAll(playlistSong); // removes all songs in list of playlist
                            showOptions();
                        } else {
                            System.out.println("add new song in playlist ...");
                        }
                        break;
                    }
                    case 6: {
                        if (!ifPlaylistIsEmpty()) { // ! false -> true
                            System.out.println("****** removing album from the playlist *********");
                            removeAlbumInPlaylist();
                        } else {
                            System.out.println("the album cannot be deleted of playlist ...");
                        }
                        break;
                    }
                    case 7: {

                        if (!ifPlaylistIsEmpty()) { // ! false -> true
                            System.out.println("****** removing a song from the playlist *********");
                            removeSongInPlaylist();
                        } else {
                            System.out.println("the song cannot be deleted of playlist ...");
                        }
                        break;
                    }
                    case 8: {
                        if ( ! (choosePlaylists.getListOfPlaylists().isEmpty()) ) { // if empty list of playlists
                            System.out.println("****** show all playlists created and start !!! ******");

                            Playlist playlistChoice = choosePlaylists.choosePlaylist();
                            if(playlistChoice != null) { // if not null start playing songs
                                 System.out.println("****** start ******");
                                 playlistChoice.playlistAndStart();
                                if(playlistChoice.getNewSongPlaylist().isEmpty()) { //deletes an empty playlist
                                    choosePlaylists.removesPlaylistInList(playlistChoice);
                                    System.out.println("deleted empty playlist\n");
                                }
                             }
                            showOptions();
                        } else {
                            System.out.println("no playlists in list... ");
                        }
                        break;
                    }
                    case 9: {
                        System.out.println("****** exit bye my master !!! ******");
                        flag = true;
                        break;
                    }
                    default: {
                        System.out.println("wrong value ....");
                    }
                }

            } else {
                System.out.println("wrong value ...");
                sc.nextLine();
            }
        }
    }

    public void printListOfPlaylistToCreate() {
        int i = 1;
        if (!(playlistSong.isEmpty())) {
            for (Song song : playlistSong) {
                System.out.println((i++) + ". song: " + song.getTitle() + ", duration : " + song.getDuration());
            }
        } else {
            System.out.println("the list of playlist is empty ...");
        }
    }

    private boolean ifPlaylistIsEmpty() {
        if (playlistSong.isEmpty()) {
            System.out.println("playlist is empty ...");
            return true;
        } else {
            return false;
        }
    }

    private void removeSongInPlaylist() {
        boolean flag = false;
        while (!flag) {
            System.out.println("give me the name of the song: ");
            String nameOfSongToBeRemoved = sc.nextLine();
            int indexSongToBeRemoved = isThereSongInPlaylist(nameOfSongToBeRemoved);

            if (indexSongToBeRemoved >= 0) {
                System.out.println("removing the song : " + playlistSong.get(indexSongToBeRemoved).getTitle());
                playlistSong.remove(indexSongToBeRemoved); // removed song in the playlist

                flag = true;
            } else {
                System.out.println("wrong name of song ...");
            }
        }

    }

    private int isThereSongInPlaylist(String nameOfSong) {
        for (int i = 0; i < playlistSong.size(); i++) {
            Song songInPlaylist = playlistSong.get(i);

            if (nameOfSong.equals(songInPlaylist.getTitle())) {
                return i;
            }
        }
        return -1;
    }

    private void showOptions() {
        System.out.println("\n********* CREATE NEW PLAYLIST, MAIN MENU *********\n\t0. show options \n\t1. displays all songs \n\t2. add new song in playlist " +
                "\n\t3. add album in playlist \n\t4. show add my new songs to playlist \n\t5. create new my playlist for songs and start playing" +
                "\n\t6. removing album from the playlist  \n\t7. removing a song from the playlist  \n\t8. show all playlists created and start " +
                "  \n\t9. exit ");
    }

    private void addAlbumToPlaylist() {
        boolean flag = false;
        while (!flag) {
            System.out.println("give me a name of album: ");
            String nameAlbum = sc.nextLine();

            int indexAlbum = isThereAlbumInList(nameAlbum);

            if (indexAlbum >= 0) {
                Album album = listOfAlbum.get(indexAlbum);
                Song song = null;
                boolean isThereAlbumInPlaylist = songOrAlbumIsThereOnPlaylist(album, song);
                boolean isDuplicateInPlaylist = false;

                if (!isThereAlbumInPlaylist) { // ! false -> true
                    for (int i = 0; i < album.listOfSongInAlbum.size(); i++) {
                        Song songAddToPlaylist = album.listOfSongInAlbum.get(i);
                        for (Song songInPlaylist : playlistSong) {

                            if (songAddToPlaylist.getTitle().equals(songInPlaylist.getTitle())) { // true
                                isDuplicateInPlaylist = true;
                            }
                        }

                        if (!isDuplicateInPlaylist) { // ! false -> true
                            playlistSong.add(songAddToPlaylist); // add new songs in all albums
                            System.out.println("add song " + songAddToPlaylist.getTitle());
                        } else {
                            isDuplicateInPlaylist = false;
                        }
                    }
                    flag = true;
                    System.out.println("add album to Playlist : " + album.getName());
                } else {
                    System.out.println(" the album has already been added to the playlist ...");
                    flag = true;
                }
            } else {
                System.out.println("wrong name of album ...");
            }

        }


    }

    private boolean songOrAlbumIsThereOnPlaylist(Album album, Song song) {
        int counter = 0;
        if (album != null) {
            for (int i = 0; i < album.listOfSongInAlbum.size(); i++) {
                Song songInAlbum = album.listOfSongInAlbum.get(i);
                for (Song songInPlaylist : playlistSong) {
                    if (songInAlbum.getTitle().equals(songInPlaylist.getTitle())) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    return false;
                } else {
                    counter = 0; // resets the variable
                }
            }

            return true;

        } else if (song != null) {
            for (Song songInPlaylist : playlistSong) {
                if (song.getTitle().equals(songInPlaylist.getTitle())) {
                    return true;
                }
            }

        }
        return false;
    }

    //remove album
    private void removeAlbumInPlaylist() {
        boolean flag = false;
        int count = 1;

        while (!flag) {
            System.out.println("give me the name of the album: ");
            String nameOfAlbumToBeRemoved = sc.nextLine();

            int indexAlbumToBeRemoved = isThereAlbumInList(nameOfAlbumToBeRemoved);

            if (indexAlbumToBeRemoved >= 0) {
            Album removeAlbum = listOfAlbum.get(indexAlbumToBeRemoved); // assigns the album to be deleted

                for (int i = 0; i <= removeAlbum.listOfSongInAlbum.size() - 1; i++) {

                    Song songInAlbum = removeAlbum.listOfSongInAlbum.get(i); // assigns the given song from the album to be deleted

                    for(int j = 0; j < playlistSong.size(); j++) {
                        Song songInPlaylist = playlistSong.get(j);
                        if( songInAlbum.getTitle().equals(songInPlaylist.getTitle() ) ) {
                            System.out.println("removing the album : " + removeAlbum.getName()  + "\nremoving the song : " + (count++) + ". " + songInPlaylist.getTitle() );
                            playlistSong.remove(songInPlaylist); // removed song in the playlist of Album
                            flag = true;
                        }
                    }

                }
            } else {
                System.out.println("wrong name of song ...");
            }
        }

    }

    private int isThereAlbumInList(String nameAlbum) {

        for(int i = 0; i < listOfAlbum.size(); i++) {
            Album album = listOfAlbum.get(i);
            if(album.getName().equals(nameAlbum)) {
                return i;
            }
        }
        return -1; // not found
    }

    private void addNewSongInPlaylist() {

        boolean flag = false;
        while (!flag) {
            System.out.println("give me a name of song: ");
            String nameSong = sc.nextLine();

            int indexValueOfSong = isThereSongInList(nameSong);
            if (indexValueOfSong >= 0) {
                Album album = null;
                Song songToAddedOnPLaylist = listAllSongsToPlaylist.get(indexValueOfSong);
                boolean isThereSongInPlaylist = songOrAlbumIsThereOnPlaylist(album, songToAddedOnPLaylist);

                if ( ! isThereSongInPlaylist) { // ! false -> true

                    playlistSong.add(songToAddedOnPLaylist);
                    System.out.println("add song to Playlist : " + songToAddedOnPLaylist.getTitle());
                    flag = true;
                } else {
                    System.out.println("the song has already been added to the playlist ...");
                    flag = true;
                }
            } else {
                System.out.println("wrong value a name of song ...");
            }

        }
    }

    private int isThereSongInList(String nameSong) {

        for(int i = 0; i < listAllSongsToPlaylist.size(); i++) {
            Song songInList = listAllSongsToPlaylist.get(i);
            if(songInList.getTitle().equals(nameSong)) {
                return i;
            }
        }
        return -1;
    }

    private void printAllSongs() {
        for(int i = 0; i < this.listOfAlbum.size(); i++) {
            Album album = listOfAlbum.get(i);
            System.out.println((i+1) + " album:");
                album.printListOfSongsInAlbum();

            }
    }

}
