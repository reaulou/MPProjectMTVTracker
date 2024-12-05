package com.example.mpprojectmtvtracker;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.mpprojectmtvtracker.DAO.UserDao;
import com.example.mpprojectmtvtracker.DTO.MovieDto;
import com.example.mpprojectmtvtracker.Entity.Movie;
import com.example.mpprojectmtvtracker.Entity.User;
import com.example.mpprojectmtvtracker.Network.MovieResponse;
import com.example.mpprojectmtvtracker.Network.RetrofitInstance;
import com.example.mpprojectmtvtracker.Network.TMDBApiService;
import com.example.mpprojectmtvtracker.Service.TMDBService;
import com.example.mpprojectmtvtracker.ViewModel.MovieViewModel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        TMDBApiService tmdbApiService = RetrofitInstance.getApiService();
//        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYWJiYjlhODZkZDc1Y2Q4NGM4MDM4ZjI5Y2VlNGIxMiIsIm5iZiI6MTczMzMyODYyNi43MzUwMDAxLCJzdWIiOiI2NzUwN2VmMjM1NWRiYzBiMTVkN2IyZjIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.I180H8CfolN62cbkgPWiQu-a7XHy7zLsO26CreoI7Qk";
//        // retrofit
//        MutableLiveData<List<MovieDto>> responseLiveData = new MutableLiveData<List<MovieDto>>();
//        tmdbApiService.getPopularMovies("Bearer " + accessToken, "Jack+Reacher").enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<MovieDto> movieDtos = response.body().getResults();
//
//                    responseLiveData.postValue(movieDtos);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        TMDBService serviceTMDB = new TMDBService();
        serviceTMDB.searchMovieByName("Jack+Reacher").observe(this, new Observer<List<MovieDto>>() {
            @Override
            public void onChanged(List<MovieDto> movieDtos) {
                for(MovieDto movieDto : movieDtos){
                    System.out.println(movieDto.getId());
                    System.out.println("TMDBService: " + movieDto.getTitle());
                }
            }
        });

        // view model setup
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        Movie mov1 = new Movie("Interstellar", "Space Movie", "watched");
        //movieViewModel.deleteAllMovies();
        movieViewModel.insert(mov1);

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> models) {
                for(Movie movie : models) {
                    System.out.println(movie.getId());
                    System.out.println(movie.getMovieName());
                    System.out.println(movie.getMovieDescription());
                }
            }
        });


        // HTTP Request
        //ctx = this;
        //HttpAsyncClass task = new HttpAsyncClass();
        //task.execute();



//        // create/get DB
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "MTVTracerDB").build();
//
//        UserDao userDao = db.userDao();
//        // insert user
//        User user1 = new User();
//        user1.firstName = "jack";
//        user1.lastName = "testo111";
//        System.out.println("debug juan777d90");
//
//
//        try {
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    // Insert Data
//                    userDao.insertAll(user1);
//                }
//            });
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        // get all
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        List<User> users = new ArrayList<>();
//        Future<List<User>> result = executor.submit(new Callable<List<User>>() {
//            @Override
//            public List<User> call() throws Exception{
//                // get
//                return userDao.getAll();
//            }
//        });
//
//        try {
//            Thread.sleep(3000);
//            users = result.get();
//        } catch (Exception exception) {
//            //handle exception
//        }
//        System.out.println("debug juan999d90");
//        for(User user : users){
//            System.out.println(user.uid);
//            System.out.println(user.firstName);
//            System.out.println(user.lastName);
//        }
    }

    // read stream
    private String readStream(InputStream is){
        try{
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int i = is.read();

            while(i != -1){
                buffer.write(i);
                i = is.read();
            }
            return buffer.toString();
        } catch (Exception e) {
            return "error response 6969";
        }

    }

    // async task
    private class HttpAsyncClass extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            try {
                URL url = new URL("https://itunes.apple.com/search?term=jack+johnson");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                //System.out.println("result 69: " + result);
                urlConnection.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.v("server response: ", result);
        }
    }
}