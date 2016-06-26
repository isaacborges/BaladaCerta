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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import unlv.erc.partiubalada.R;
import unlv.erc.partiubalada.model.Party;

public class PartyCreateAcitivity extends AppCompatActivity {
    public static final String CHOOSE_FROM_GALLERY = "Escolher banner";
    public static final String CANCEL = "Cancelar";
    private static int RESULT_LOAD_IMAGE = 1;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private DatabaseReference mDatabase;
    private Party party;
    private ImageView partyBanner;
    private EditText editTextPartyName;
    private EditText editTextLocation;
    private EditText editTextPartyLatitude;
    private EditText editTextPartyLongitude;
    private EditText editTextPartyPrice;
    private EditText editTextPartyInitialTime;
    private EditText editTextPartyEndTime;
    private String partyName;
    private String partyLocation;
    private String partyLatitude;
    private String partyLongitude;
    private String partyPrice;
    private String partyInitialTime;
    private String partyEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_create_acitivity);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://project-8420821685282639830.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        party = new Party();

        startComponents();
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
        StorageReference partyImagesRef = storageRef.child("images/" + partyName);
        UploadTask uploadTask = partyImagesRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("Upload", "it was not possible to upload the image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                party.setPartyImage(downloadUrl);

                Log.i("Upload", "Success");
            }
        });
    }

    private String chooseImageFromGallery(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

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

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                } else if (options[item].equals(CANCEL)) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onPartiesButtonClicked(View view) {
        Intent intent = new Intent(PartyCreateAcitivity.this, PartyCRUDActivity.class);
        startActivity(intent);
    }

    public void onPartiesClicked(View view) {
        Intent intent = new Intent(PartyCreateAcitivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onSendPartyClicked(View view) {
        getEditTextInformations();

        sendPartyToFirebase();

        Toast.makeText(PartyCreateAcitivity.this, "Festa adicionada", Toast.LENGTH_LONG).show();
    }

    private void sendPartyToFirebase() {
        String partyId = mDatabase.child("Parties").push().getKey();
        Log.i("Creating id", partyId);
        party.setIdParty(partyId);
        setPartyInformations();

        Map<String, Object> partyValues = party.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Parties/" + partyId, partyValues);

        mDatabase.updateChildren(childUpdates);
    }

    @NonNull
    private void setPartyInformations() {
        party.setPartyName(partyName);
        party.setLocality(partyLocation);
        party.setLatitude(partyLatitude);
        party.setLongitude(partyLongitude);
        party.setPrice(partyPrice);
        party.setStartTime(partyInitialTime);
        party.setEndTime(partyEndTime);
    }

    private void getEditTextInformations() {
        partyName = editTextPartyName.getText().toString();
        partyLocation = editTextLocation.getText().toString();
        partyLatitude = editTextPartyLatitude.getText().toString();
        partyLongitude = editTextPartyLongitude.getText().toString();
        partyPrice = editTextPartyPrice.getText().toString();
        partyInitialTime = editTextPartyInitialTime.getText().toString();
        partyEndTime = editTextPartyEndTime.getText().toString();
    }

    private void startComponents() {
        editTextPartyName = (EditText) findViewById(R.id.editTextPartyName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextPartyLatitude = (EditText) findViewById(R.id.editTextPartyLatitude);
        editTextPartyLongitude = (EditText) findViewById(R.id.editTextPartyLongitude);
        editTextPartyPrice = (EditText) findViewById(R.id.editTextPartyPrice);
        editTextPartyInitialTime = (EditText) findViewById(R.id.editTextPartyInitialTime);
        editTextPartyEndTime = (EditText) findViewById(R.id.editTextPartyEndTime);
    }
}
