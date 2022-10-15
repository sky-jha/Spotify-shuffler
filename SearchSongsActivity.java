public class SearchSongsActivity extends AppCompatActivity {

   private String AUTH_TOKEN;

   public static SpotifyService spotifyService;

   private boolean isSingleLight;

   int position;

   @Override

   protected void onCreate(@Nullable Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_search_song);

       AUTH_TOKEN = getIntent().getStringExtra(SpotifyLoginActivity.AUTH_TOKEN);

       setServiceAPI();

       Fragment mFragment = null;

       mFragment = new SearchFragment();

       FragmentManager fragmentManager = getSupportFragmentManager();

       fragmentManager.beginTransaction()

               .replace(R.id.fragment_container, mFragment).commit();

   }

   private void setServiceAPI(){

       Log.d("PlayMusic", "Setting Spotify API Service");

       SpotifyApi api = new SpotifyApi();

       api.setAccessToken(AUTH_TOKEN);

       spotifyService = api.getService();

   }

}
Add one listener class like below:

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
