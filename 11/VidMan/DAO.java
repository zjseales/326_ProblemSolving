
package VidMan;

import java.util.ArrayList;
import java.util.Collection;

/** DAO.java
 *  COSC326 - Etude 11
 * 
 *  The data access object, contains a list of
 *  video file paths.
 *@author Zac Seales - 6687905
 */
public class DAO {
    
    /* A videos ArrayList. Stores file-paths. */
    private static Collection<String> videos
            = new ArrayList<>();
    
    /** Adds a video to the list.
     * @param vidPath - the new video file-path.
     */
    public void saveVideo(String vidPath){
        videos.add(vidPath);
    }
    
   /** Removes the video-path argument from the list.
     * @param vidPath - the video being removed.
     */
    public void removeVideo(String vidPath){
        videos.remove(vidPath);
    }
    
   /** Returns the list of video file-paths.
     * @return - the array list.
     */
    public Collection<String> getVideos(){
        return videos;
    }
    
}
