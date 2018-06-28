package android.myapplicationdev.com.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class UpdateContact extends AppCompatActivity {

    private EditText etUpdateLastName;
    private EditText etUpdateFastName;
    private EditText etUpdateMobileNum;
    private Button btnUpdate;
    private  Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
    }
    @Override
    protected void onResume() {
        super.onResume();
        etUpdateFastName = (EditText)findViewById(R.id.etFirstNameUpdate);
        etUpdateLastName = (EditText)findViewById(R.id.etLastNameUpdate);
        etUpdateMobileNum = (EditText)findViewById(R.id.etMobileUpdate);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        btnDelete = (Button)findViewById(R.id.buttonDelete);

        Intent IDRecieve = getIntent();
        String updateName = IDRecieve.getStringExtra("firstName");
        String updateLastName = IDRecieve.getStringExtra("lastName");
        String updateMobile = IDRecieve.getStringExtra("mobile");

        Log.i("Name",updateName);

        etUpdateFastName.setText(updateName);
        etUpdateLastName.setText(updateLastName);
        etUpdateMobileNum.setText(updateMobile);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:8080/C302_CloudAddressBook/deleteItem.php";
                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener);
                Intent IDRecieve3 = getIntent();
                String contactId = IDRecieve3.getStringExtra("contactId");
                request.setMethod("POST");
                request.addData("id",contactId);
                request.execute();
                Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                Intent IDRecieve2 = getIntent();
                String contactId = IDRecieve2.getStringExtra("contactId");
//                int valueContactId = Integer.parseInt(contactId);
                Log.i("Contactid",contactId);

                String url = "http://10.0.2.2:8080/C302_CloudAddressBook/updateContactsById.php?";
                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("id",contactId);
                request.addData("FirstName", etUpdateFastName.getText().toString());
                request.addData("LastName", etUpdateLastName.getText().toString());
                request.addData("Mobile", etUpdateMobileNum.getText().toString());
                request.execute();
                Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                startActivity(intent);
                // Code for step 1 end
            }
        });
    }

    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
}
