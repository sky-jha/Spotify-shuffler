public class SpotifyLoginActivity extends AppCompatActivity {

   private static final String TAG = "Spotify " + SpotifyLoginActivity.class.getSimpleName();

   private static final int REQUEST_CODE = 1337;

   public static final String AUTH_TOKEN = "AUTH_TOKEN";

   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

setContentView(R.layout.activity_spotify_login);Button mLoginButton = (Button)findViewById(R.id.login_button);

       mLoginButton.setOnClickListener(mListener);

   }

   private void openLoginWindow() {

       AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, TOKEN,REDIRECT_URL);

       builder.setScopes(new String[]{ "streaming"});

       AuthorizationRequest request = builder.build();

       AuthorizationClient.openLoginActivity(this,REQUEST_CODE,request);

   }

   @Override

   protected void onActivityResult(int requestCode, final int resultCode, Intent data) {

       super.onActivityResult(requestCode, resultCode, data);

       if(requestCode == REQUEST_CODE)

       {

           final AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);

           switch (response.getType()) {

               // Response was successful and contains auth token

               case TOKEN:

                   Log.e(TAG,"Auth token: " + response.getAccessToken());

                   Intent intent = new Intent(SpotifyLoginActivity.this,

                           SearchSongsActivity.class);

                   intent.putExtra(AUTH_TOKEN, response.getAccessToken());

                   startActivity(intent);

                   destroy();

                   break;

               // Auth flow returned an error

               case ERROR:

                   Log.e(TAG,"Auth error: " + response.getError());

                   break;

               // Most likely auth flow was cancelled

               default:

                   Log.d(TAG,"Auth result: " + response.getType());

           }

       }

   }


   View.OnClickListener mListener = new View.OnClickListener(){

       @Override

       public void onClick(View view) {

           switch (view.getId()){

               case R.id.login_button:

                   openLoginWindow();

                   break;

           }

       }

   };

   public void destroy(){

       SpotifyLoginActivity.this.finish();

   }

}
