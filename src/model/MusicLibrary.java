
package model;

import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class MusicLibrary {

	public Map<String, Song> songLibraryMap = new HashMap<String, Song>();
	public Map<String, Playlist> playlistLibraryMap = new HashMap<String, Playlist>();
	public HashSet<String> artists;
	public HashSet<String> albums;
	public HashSet<String> genres;
	public HashSet<String> directories;
	public HashSet<String> songTitles;
	public File libraryFile;
	public Clip clip;
	public Song currentSong;
    public List<Song> songList;

    public Song getCurrentSong(){
		return currentSong;
	}
	public Map<String, Song> getSongLibrary(){
		return songLibraryMap;
	}
	public HashSet<String> getArtists(){
		return artists;
	}
	public HashSet<String> getSongTitles(){
		return songTitles;
	}
	public HashSet<Playlist> getPlaylists(){
		HashSet<Playlist> returnSet = new HashSet<Playlist>(playlistLibraryMap.values());
		return returnSet;
	}
	

	public MusicLibrary(){
		artists = new HashSet<String>();
		albums = new HashSet<String>();
		genres = new HashSet<String>();
		directories = new HashSet<String>();
		songTitles = new HashSet<String>();
		libraryFile = new File("Library.txt");
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
		}
	}
	
	public Song getNextSong(Song curSong){
		Song nextSong ;
		String[] tempArray = new String[songTitles.size()];
		int i = 0;
		for(String entry : songTitles){
			tempArray[i] = entry;
			i++;
		}
		Arrays.sort(tempArray);
		int index = Arrays.asList(tempArray).indexOf(curSong.getTitle());
		if(index == songTitles.size() - 1){
			index = -1;
		}
		String newSongTitle = tempArray[index + 1];
		nextSong = songLibraryMap.get(newSongTitle);
		return nextSong;
	}
	public void setCurrentSong(Song curSong){
		currentSong = curSong;
	}
	public void addPlaylist(Playlist playlist){
		playlistLibraryMap.put(playlist.getTitle(), playlist);
	}
	public void addDirectory(String directory){
		directories.add(directory);
	}
	public void addArtist(String artist){
		artists.add(artist);
	}
	public void addGenre(String genre){
		genres.add(genre);
	}
	public void addAlbum(String album){
		albums.add(album);
	}
	/*public void addSong(String CSVSong){
		Song newSong = new Song(CSVSong);
		songLibraryMap.put(newSong.getTitle(), newSong);
		if(artists.contains(newSong.getArtist()) == false){
			artists.add(newSong.getArtist());
		}
		if (albums.contains(newSong.getAlbum()) == false){
			albums.add(newSong.getAlbum());
		}
		if (genres.contains(newSong.getGenre()) == false){
			genres.add(newSong.getGenre());
		}
		if (songTitles.contains(newSong.getTitle()) == false){
			songTitles.add(newSong.getTitle());
		}
	}*/
	public void addSong(Song newSong){
		songLibraryMap.put(newSong.getTitle(), newSong);
		if(artists.contains(newSong.getArtist()) == false){
			artists.add(newSong.getArtist());
		}
		if (albums.contains(newSong.getAlbum()) == false){
			albums.add(newSong.getAlbum());
		}
		if (genres.contains(newSong.getGenre()) == false){
			genres.add(newSong.getGenre());
		}
		if (songTitles.contains(newSong.getTitle()) == false){
			songTitles.add(newSong.getTitle());
		}
	}
	public void saveLibrary() throws IOException {
		FileWriter libraryFileWriter = new FileWriter(libraryFile, false);
		BufferedWriter libraryWriter = new BufferedWriter(libraryFileWriter);

		libraryWriter.write(directories.size() + "\n");
		for (String s : directories) {
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(genres.size() + "\n");
		for (String s : genres) {
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(artists.size() + "\n");
		for (String s : artists) {
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(albums.size() + "\n");
		for (String s : albums) {
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(playlistLibraryMap.size() + "\n");
		for (Map.Entry<String, Playlist> entry : playlistLibraryMap.entrySet()) {
			libraryWriter.write(entry.getValue().getTitle() + "\n");
		}
		libraryWriter.write(songLibraryMap.size() + "\n");
		for (Map.Entry<String, Song> entry : songLibraryMap.entrySet()) {
			Song write = entry.getValue();
			libraryWriter.write(write.getArtist() + "," + write.getAlbum() + "," + write.getTitle() + "," + write.getGenre() + "," + write.getDirectory() + "\n");
		}

		libraryWriter.close();
		libraryFileWriter.close();
	}


	public static MusicLibrary parseLibrary(String libraryFileLocation) throws IOException{
		MusicLibrary newLib = new MusicLibrary();
		File libraryFile = new File(libraryFileLocation);
		FileReader libReader = new FileReader(libraryFile);
		BufferedReader libraryReader = new BufferedReader(libReader);
		String curLine = libraryReader.readLine();
		if(curLine != null && curLine.length() > 0){
			System.out.println(curLine);
			int readLength = Integer.parseInt(curLine);
			for(int i = 0; i < readLength; i++){
				newLib.addDirectory(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addGenre(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addArtist(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addAlbum(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				Playlist newPlaylist = new Playlist(libraryReader.readLine());
				newLib.addPlaylist(newPlaylist);
			}
			/*readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				Song newSong = new Song(libraryReader.readLine());
				newLib.addSong(newSong);
			}*/

		}
		return newLib;
	}
	
	public Song getSong(String title){
		Song newSong =  songLibraryMap.get(title);
		return newSong;
	}
	
	public void updateSets(){
		artists.clear();
		albums.clear();
		directories.clear();
		songTitles.clear();
		genres.clear();
		for(Map.Entry<String,Song> entry : songLibraryMap.entrySet()){
			Song write = entry.getValue();
			artists.add(write.getArtist());
			albums.add(write.getAlbum());
			directories.add(write.getAlbum());
			songTitles.add(write.getTitle());
			genres.add(write.getGenre());
		}
	}
	
	public void removeSong(Song removeSong){
		songLibraryMap.remove(removeSong.getTitle());
		updateSets();
	}
	public void removeSong(String title){
		songLibraryMap.remove(title);
		updateSets();
	}

	public void playSong(Song songToPlay){
		try {
			clip.stop();
			clip.close();
			File playSongFile = new File(songToPlay.getDirectory());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(playSongFile);
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	public void stopSong(){
		try{

		clip.stop();
		clip.close();
		}
		catch(Exception e){
			
		}
	}

	public HashSet<String> getAlbums() {
		return albums;
	}

	public HashSet<String> getGenres() {
		return genres;
	}

}