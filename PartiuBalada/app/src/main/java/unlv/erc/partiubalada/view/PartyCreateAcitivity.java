package unlv.erc.partiubalada.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

import unlv.erc.partiubalada.R;

public class PartyCreateAcitivity extends AppCompatActivity {
    public static final String CHOOSE_FROM_GALLERY = "Escolher banner";
    public static final String CANCEL = "Cancelar";
    private static int RESULT_LOAD_IMAGE = 1;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private ImageView partyBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_create_acitivity);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://project-8420821685282639830.appspot.com");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            String picturePath = chooseImageFromGallery(data);

            uploadImageOnFirebase(picturePath);
        } else {
            Log.e("ERROR", "It wasn't possible to get the image");
        }
    }

    private void uploadImageOnFirebase(String picturePath) {
        Uri file = Uri.fromFile(new File(picturePath));
        StorageReference partyImagesRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = partyImagesRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.e("Upload", "it was not possible to upload the image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.i("Upload","Success");
            }
        });
    }

    private String chooseImageFromGallery(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);

        cursor.close();

        Bitmap bmp = null;
        try {
            bmp = getBitmapFromUri(selectedImage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        partyBanner = (ImageView) findViewById(R.id.partyBanner);
        partyBanner.setVisibility(ImageView.VISIBLE);
        partyBanner.setImageBitmap(bmp);
        return picturePath;
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public void onChooseBannerClicked(View view) {
        final CharSequence[] options = {CHOOSE_FROM_GALLERY, CANCEL};

        AlertDialog.Builder builder = new AlertDialog.Builder(PartyCreateAcitivity.this);
        builder.setTitle("Escolher Banner da Festa");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(CHOOSE_FROM_GALLERY)) {

                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                } else if (options[item].equals(CANCEL)) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}