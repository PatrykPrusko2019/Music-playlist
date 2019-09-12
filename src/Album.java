import java.util.*;

public class Album {
    private String name;
    private ArrayList<Song> listOfSongInAlbum;
    public ArrayList<Album> listOfAlbum;
    private ArrayList<Song> listAllSongsToPlaylist;
    private Scanner sc = new Scanner(System.in);
    public LinkedList<Song> playlistSong;
    private boolean goingForward = false;

    public Album() {
        this.listOfAlbum = new ArrayList<>();
        this.listAllSongsToPlaylist = new ArrayList<>();
        this.playlistSong = new LinkedList<>();
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


        int number;
        boolean flag = false;
        showOptions();
        while (!flag) {
            System.out.println("create new playlist (options show -> 0 ) :");
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
                        printListOfPlaylist();
                        break;
                    }
                    case 5: {
                        if (!ifPlaylistIsEmpty()) { // ! false -> true
                            System.out.println("****** create new my playlist for songs and start *********");
                            createNewPlaylistAndStart();
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
            Album album = null;
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
        System.out.println("\t0. show options \n\t1. displays all songs \n\t2. add new song in playlist " +
                "\n\t3. add album in playlist \n\t4. show add my new songs to playlist \n\t5. create new my playlist for songs and start" +
                "\n\t6. removing album from the playlist  \n\t7. removing a song from the playlist  \n\t8. exit ");
    }

    private void createNewPlaylistAndStart() {


        ListIterator<Song> iterator = playlistSong.listIterator();

        System.out.println("\n\n********* adding a new playlist *********\n\n ");


        System.out.println("My playlist:  ");
        printListOfPlaylist();

        printOptionsForPlaylist();

        boolean flag = false;
        while (!flag) {
            System.out.println("select a number (show options - 1 ) : ");
            boolean isInt = sc.hasNextInt();
            if (isInt) {
                int number = sc.nextInt();
                sc.nextLine();

                switch (number) {
                    case 0: {
                        System.out.println("*********** exit ************");
                        flag = true;
                        break;
                    }

                    case 1: {
                        System.out.println("*********** print Options For Playlist ***************");
                        printOptionsForPlaylist();
                        break;
                    }
                    case 2: {
                        System.out.println("*********** skip forward ************");
                        skipForward(iterator);
                        break;
                    }
                    case 3: {
                        System.out.println("*********** skip backwards ************");
                        skipBackwards(iterator);
                        break;
                    }
                    case 4: {
                        System.out.println("*********** removing song in playlist ************");
                        iterator = removingSongInPlaylist(iterator);
                        break;
                    }
                    case 5: {
                        System.out.println("*********** repeat the current song **************");
                        repeatCurrentSong(iterator);
                        break;
                    }
                    case 6: {
                        System.out.println("************ current song played ************");
                        currentSongPlayed(iterator);
                        break;
                    }
                    case 7: {
                        System.out.println("************ show list songs of my playlist ************");
                        printListOfPlaylist();
                        break;
                    }

                }


            } else {
                System.out.println("wrong value ...");
                sc.nextLine();
            }

        }

    }

    //repeat current song
    private void repeatCurrentSong(ListIterator<Song> iterator) {

        if (goingForward) {
            if (iterator.hasPrevious()) {
                System.out.println("repeat song -> " + iterator.previous().getTitle());
                goingForward = false;
            } else {
                System.out.println("start of list ... ");
            }
        } else {
            if (iterator.hasNext()) {
                System.out.println("repeat song -> " + iterator.next().getTitle());
                goingForward = true;
            } else {
                System.out.println("end of list ...");
            }
        }

    }


    private ListIterator<Song> removingSongInPlaylist(ListIterator<Song> iterator) {

        if (playlistSong.isEmpty()) {
            System.out.println("playlist is empty ....");
        } else {
            boolean isRemovedSong = false;
            while (!isRemovedSong) {

                System.out.println("give me a title of song to be removed : ");
                String nameSong = sc.nextLine();

                for (int i = 0; i < playlistSong.size(); i++) {
                    Song songRemove = playlistSong.get(i);
                    if (songRemove.getTitle().equals(nameSong)) {
                        playlistSong.remove(songRemove); //remove song
                        isRemovedSong = true;

                        iterator = playlistSong.listIterator(); //dodaje nowa liste po usunieciu song
                    }
                }
                if (isRemovedSong) {
                    System.out.println("removed song in playlist : " + nameSong);
                } else {
                    System.out.println(" wrong value a name of song ...");
                }

            }
        }
        return iterator;
    }

    private void skipBackwards(ListIterator<Song> iterator) {

        if (goingForward) {

            if (iterator.hasPrevious()) {
                iterator.previous();
            }
            goingForward = false;
        }

        if (iterator.hasPrevious()) {

            System.out.println(iterator.previous().getTitle());
        } else {
            System.out.println("start list of playlist !!!");
            goingForward = true;
        }
    }

    private void skipForward(ListIterator<Song> iterator) {

        if (!goingForward) { // ! false  -> true

            if (iterator.hasNext()) {
                iterator.next();
                goingForward = true;
            }
        }


        if (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        } else {
            System.out.println("end the list of playlist !!!");
            goingForward = false;
        }
    }

    private void currentSongPlayed(ListIterator<Song> iterator) {

        if (goingForward) {

            if (iterator.hasPrevious()) {
                iterator.previous();
                System.out.println(iterator.next().getTitle() + " -> current song");
            } else {
                System.out.println("start list of playlist !!!");
            }
        } else {
            if(iterator.hasNext()) {
                iterator.next();
                System.out.println(iterator.previous().getTitle() + " -> current song");
            } else {
                System.out.println("end the list of playlist !!!");
            }
        }

    }

    private void printOptionsForPlaylist() {
        //todo options my new playlist 1.Quit 2. Skip forward 3. skip backwards 5.repeat the current song
        System.out.println("\n\t0. Quit \n\t1. print Options For Playlist  \n\t2. Skip forward (next song) \n\t3. skip backwards (previous song)" +
                "\n\t4. removing song in playlist" + " \n\t5. repeat the current song" + " \n\t6. current song played"
                + " \n\t7. show the list of playlist ");
    }

    private void printListOfPlaylist() {
        int i = 1;
        if (!(playlistSong.isEmpty())) {
            for (Song song : playlistSong) {
                System.out.println((i++) + ". song: " + song.getTitle() + ", duration : " + song.getDuration());
            }
        } else {
            System.out.println("the list of playlist is empty ...");
        }
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
