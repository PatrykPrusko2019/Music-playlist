import java.util.ArrayList;
import java.util.Scanner;

public class ChooseFromCreatedPlaylists {

    private ArrayList<Playlist> listOfPlaylists;
    private Scanner sc;
    private Playlist playlist;

    public ChooseFromCreatedPlaylists() {
        this.listOfPlaylists = new ArrayList<>(); //creates list of playlists
        this.sc = new Scanner(System.in);
        this.playlist = null;
    }

    public ArrayList<Playlist> getListOfPlaylists() {
        return listOfPlaylists;
    }
    public void setListOfPlaylists(ArrayList<Playlist> listOfPlaylists) {
        this.listOfPlaylists = listOfPlaylists;
    }

    //adds new playlist to list
    public void addNewPlaylistInList(Playlist addPlaylist) {
        this.listOfPlaylists.add(addPlaylist);
    }

    public void removesPlaylistInList(Playlist removePlaylist) {
        this.listOfPlaylists.remove(removePlaylist);
    }


    public Playlist choosePlaylist() {
        boolean exit = false;
        System.out.println("********** what you want to do with playlists: **********");
        while(! exit) {

            System.out.println("0. return to main menu \n1. delete the playlist \n2. select the playlist, start playing \n3. show all playlists of list \n\n");
            System.out.println("enter the number: ");
            exit = sc.hasNextInt();

            if(exit) {
                int value = sc.nextInt();
                sc.nextLine();
                switch (value) {
                    case 0: {
                        System.out.println("selected exit to main menu");
                        return null; // exit
                    }
                    case 1: {
                        if(listOfPlaylistsIsEmpty()) {
                            System.out.println("*************** delete the playlist ***************");
                            removesPlaylist();
                        } else {
                            System.out.println("playlists of list is empty ...");
                        }
                        exit = false;
                        break;
                    }
                    case 2: {
                        if(listOfPlaylistsIsEmpty()) {
                            System.out.println("*************** select the playlist, start playing ***************");
                            selectThePlaylist();
                            exit = true;
                        } else {
                            System.out.println("playlists of list is empty ...");
                            exit = false;
                        }
                        break;
                    }
                    case 3: {
                        if(listOfPlaylistsIsEmpty() ) {
                            System.out.println("*************** show all playlists of list ***************");
                            showAllPlaylists();
                        } else {
                            System.out.println("playlists of list is empty ...");
                        }
                        exit = false;
                        break;
                    }
                    default: {
                            System.out.println("wrong value, range 0 - 3");
                            exit = false;
                            break;
                    }
                }
            } else {
                System.out.println("wrong value, please again ... ");
                sc.nextLine();
            }
        }
        return playlist;
    }
    private boolean listOfPlaylistsIsEmpty() {
        if( ! listOfPlaylists.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void selectThePlaylist() {
        showAllPlaylists();
        boolean exit = false;
        int sizeList = listOfPlaylists.size();

        while( ! exit) {
            System.out.println("select playlist, enter number: ");
            exit = sc.hasNextInt();
            if(exit) {
                int choiceOfValue = sc.nextInt();
                sc.nextLine();
                choiceOfValue--;
                if(choiceOfValue >= 0 && choiceOfValue < sizeList) {

                    playlist = listOfPlaylists.get(choiceOfValue);
                    System.out.println("choosing a playlist called : " + playlist.getNamePlaylist());
                    exit = true;
                } else {
                    System.out.println("wrong value, no the playlist in list ...");
                    exit = false;
                }

            } else {
                System.out.println("wrong value, please again ...");
                sc.nextLine();
            }
        }
    }

    private void removesPlaylist() {
        showAllPlaylists();
        int sizeList = listOfPlaylists.size();
        boolean exit = false;

        while(! exit) {
            System.out.println("enter the number: ");
            exit = sc.hasNextInt();
            if(exit) {
                int numberOfPlaylistToRemoved = sc.nextInt();
                sc.nextLine();
                numberOfPlaylistToRemoved--;
                if(numberOfPlaylistToRemoved >= 0 && numberOfPlaylistToRemoved < sizeList ) {
                    System.out.println("***************\ndelete playlist: " + listOfPlaylists.get(numberOfPlaylistToRemoved).getNamePlaylist() + "\n***************");
                    removesPlaylistInList(listOfPlaylists.get(numberOfPlaylistToRemoved)); //removes the playlist that has been selected
                    exit = true;
                } else {
                    System.out.println("wrong value, no the playlist in list ...");
                    exit = false;
                }
            } else {
                System.out.println("wrong value, please again ...");
                sc.nextLine();
            }
        }
    }
    private void showAllPlaylists() {

        for(int i = 0; i < listOfPlaylists.size(); i++) {
            playlist = listOfPlaylists.get(i);
            System.out.println( (i+1) + ". Playlist: " + playlist.getNamePlaylist() );
            System.out.println("\n*******************************");
            playlist.printListOfPlaylist();
            System.out.println("*******************************\n");
        }
        System.out.println("\n\n");
    }
}
