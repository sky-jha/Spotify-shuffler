public class SearchPager {

   private static SearchPager searchPager;

   private SpotifyService spotifyService = SearchSongsActivity.spotifyService;

   public interface CompleteListener {

       void onComplete(List<Track> items);

       void onError(Throwable error);

   }

public static SearchPager getInstance(Context context){

   if(searchPager == null){

       searchPager = new SearchPager();

   }

   return searchPager;

}

public void getTracksFromSearch(String query, CompleteListener listener){

   getData(query, listener);

}

private void getData(String query, final CompleteListener listener){

   spotifyService.searchTracks(query, new SpotifyCallback<TracksPager>() {

       @Override

       public void failure(SpotifyError spotifyError) {

           listener.onError(spotifyError);

       }

       @Override

       public void success(TracksPager tracksPager, Response response) {

           listener.onComplete(tracksPager.tracks.items);

       }

   });

}

}
