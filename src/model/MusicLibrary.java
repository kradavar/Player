
package model;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MusicLibrary {

	private Map<String, Song> songLibraryMap = new HashMap<String, Song>();
	private Map<String, Playlist> playlistLibraryMap = new HashMap<String, Playlist>();
	private HashSet<String> artists;
	private HashSet<String> albums;
	private HashSet<String> genres;
	private HashSet<String> directories;
	private HashSet<String> songTitles;
	private File libraryFile;
	private Clip clip;
	private Song currentSong;
	
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
	
	public void addSong(String genre, String artist, String album, String title, String directory){
		Song newSong = new Song(genre, artist, album, title, directory);
		songLibraryMap.put(title, newSong);
		if(artists.contains(artist) == false){
			artists.add(artist);
		}
		if (albums.contains(album) == false){
			albums.add(album);
		}
		if (genres.contains(genre) == false){
			genres.add(genre);
		}
		if (songTitles.contains(title) == false){
			songTitles.add(title);
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

	public void saveLibrary() throws IOException{
		FileWriter libraryFileWriter = new FileWriter(libraryFile, false);
		BufferedWriter libraryWriter = new BufferedWriter(libraryFileWriter);
		
		libraryWriter.write(directories.size()+ "\n");
		for(String s : directories){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(genres.size()+ "\n");
		for(String s : genres){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(artists.size()+ "\n");
		for(String s : artists){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(albums.size()+ "\n");
		for(String s : albums){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(playlistLibraryMap.size() + "\n");
		for(Map.Entry<String, Playlist> entry: playlistLibraryMap.entrySet()){
			libraryWriter.write(entry.getValue().getTitle() + "\n");			
		}
		libraryWriter.write(songLibraryMap.size() + "\n");
		for(Map.Entry<String,Song> entry : songLibraryMap.entrySet()){
			Song write = entry.getValue();
			libraryWriter.write(write.getArtist() +","+write.getAlbum()+","+write.getTitle()+","+write.getGenre()+","+write.getDirectory()+"\n");
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
}