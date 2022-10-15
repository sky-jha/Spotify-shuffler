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
