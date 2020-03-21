package com.anc.ancprime.views.activities;

import android.content.Intent;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.HttpException;




/**
 * Created by User on 12/26/2019.
 */
public class BaseActivity extends AppCompatActivity {


    private Snackbar snackbar;




    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    protected void setImageWithGlide(ImageView imageView, File file, int width, int height) {
        Glide.with(this)
                .load(file)
                .apply(new RequestOptions().override(width, height))
                .into(imageView);
    }




    public void showSnackBarWhenNetworkNotAvailable(View parentLayout) {
        snackbar = Snackbar.make(parentLayout, "Internet not available", Snackbar.LENGTH_INDEFINITE)
                .setAction("Settings", (View view) -> {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                });
        snackbar.show();
    }




    public void hideSnackBarWhenNetAvailable() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }




    public void showErrorToast(Throwable error) {
        if (error instanceof HttpException) {
            switch (((HttpException) error).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    Toast.makeText(this, "Unauthorised user.", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    Toast.makeText(this, "Password didn't match.", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    Toast.makeText(this, "Internal server error", Toast.LENGTH_SHORT).show();
                    break;
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    Toast.makeText(this, "Bad request", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}
