package androidsamples.java.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

  public static final String rtdb_url = "https://tic-tac-toe-97658-default-rtdb.asia-southeast1.firebasedatabase.app/";

  private static final String TAG = "MainActivity";
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FirebaseApp.initializeApp(this);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    if (navHostFragment != null) {
      navController = navHostFragment.getNavController();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_logout) {
      Log.d(TAG, "logout clicked");
      // Handle log out
      FirebaseAuth.getInstance().signOut();
      Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
      if (navController != null) {
        navController.navigate(R.id.loginFragment);
      } else {
        Log.e(TAG, "NavController is null. Cannot navigate to login.");
      }
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}