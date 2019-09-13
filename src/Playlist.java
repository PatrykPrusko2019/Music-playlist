import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Playlist {

    private String namePlaylist;
    private Scanner sc = new Scanner(System.in);;
    private boolean goingForward = false;
    private LinkedList<Song> newSongPlaylist;

    public Playlist() {}

    public Playlist(LinkedList<Song> playlistSong) {
        this.newSongPlaylist = new LinkedList<>();
        addSongsToPlaylist(playlistSong);
        boolean isNotTrue = false;
        System.out.println("**************************\nplease name of new create playlist: ");

        while(!isNotTrue) {
            String temporaryName = sc.nextLine();
            if( ! (temporaryName.isEmpty()) ) {
                this.namePlaylist = temporaryName;
                isNotTrue = true;
                System.out.println("adds new name of playlist: " + this.namePlaylist);
                System.out.println("**************************");
            } else {
                System.out.println("wrong value ... please again");
            }
        }
    }
    private void addSongsToPlaylist(LinkedList<Song> playlistSong) {
        for(int i = 0; i < playlistSong.size(); i++) {
            Song addSong = playlistSong.get(i);
            this.newSongPlaylist.add(addSong); //adds new song in list
        }
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }
    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }
    public boolean isGoingForward() {
        return goingForward;
    }
    public void setGoingForward(boolean goingForward) {
        this.goingForward = goingForward;
    }
    public LinkedList<Song> getNewSongPlaylist() {
        return newSongPlaylist;
    }
    public void setNewSongPlaylist(LinkedList<Song> newSongPlaylist) {
        this.newSongPlaylist = newSongPlaylist;
    }

    public void playlistAndStart() {

        ListIterator<Song> iterator = this.newSongPlaylist.listIterator();

        System.out.println("\n*********************** \nstart playing from playlist \n***********************\n ");


        System.out.println("My playlist: " + namePlaylist);
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
                        System.out.println("*********** exit to main menu ************");
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
                        System.out.println("************ show list songs of playlist : " + namePlaylist + " ************");
                        printListOfPlaylist();
                        break;
                    }
                    default: {
                        System.out.println("wrong value, range 0 - 7");
                        break;
                    }

                }


            } else {
                System.out.println("wrong value ...");
                sc.nextLine();
            }

        }
    }

        private ListIterator<Song> removingSongInPlaylist(ListIterator<Song> iterator) {

            if (newSongPlaylist.isEmpty()) {
                System.out.println("playlist is empty ....");
            } else {
                boolean isRemovedSong = false;
                while (!isRemovedSong) {

                    System.out.println("give me a title of song to be removed : ");
                    String nameSong = sc.nextLine();

                    for (int i = 0; i < newSongPlaylist.size(); i++) {
                        Song songRemove = newSongPlaylist.get(i);
                        if (songRemove.getTitle().equals(nameSong)) {
                            newSongPlaylist.remove(songRemove); //remove song
                            isRemovedSong = true;

                            iterator = newSongPlaylist.listIterator(); //dodaje nowa liste po usunieciu song
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


    public void printListOfPlaylist() {
        int i = 1;
        if (!(newSongPlaylist.isEmpty())) {
            for (Song song : newSongPlaylist) {
                System.out.println((i++) + ". song: " + song.getTitle() + ", duration : " + song.getDuration());
            }
        } else {
            System.out.println("the list of playlist is empty ...");
        }
    }

    private void printOptionsForPlaylist() {
        //todo options my new playlist 1.Quit 2. Skip forward 3. skip backwards 5.repeat the current song
        System.out.println("\n\t0. Return to main menu \n\t1. print Options For Playlist  \n\t2. Skip forward (next song) \n\t3. skip backwards (previous song)" +
                "\n\t4. removing song in playlist" + " \n\t5. repeat the current song" + " \n\t6. current song played"
                + " \n\t7. show list songs of playlist ");
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

}
