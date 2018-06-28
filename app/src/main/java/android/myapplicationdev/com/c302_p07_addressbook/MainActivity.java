package android.myapplicationdev.com.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvContacts;
    ArrayList<Contact> alContacts;
    ContactAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContacts = (ListView)findViewById(R.id.listViewContacts);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View lView, final int pos, long id) {
                Intent intent = new Intent(MainActivity.this, UpdateContact.class);
                Contact selectedContact = alContacts.get(pos);
                String contactId = selectedContact.getId();
                String firstName = selectedContact.getFirstName();
                String lastName = selectedContact.getLastName();
                String mobileNum = selectedContact.getMobileNumber();
                intent.putExtra("contactId", contactId);
                intent.putExtra("lastName", lastName);
                intent.putExtra("firstName", firstName);
                intent.putExtra("mobile", mobileNum);
                Log.i("mobile",mobileNum);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        alContacts = new ArrayList<Contact>();
        ca = new ContactAdapter(this,R.layout.row, alContacts);
        lvContacts.setAdapter(ca);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2:8080/C302_CloudAddressBook/getContacts.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("POST");
        request.execute();
        // Code for step 1 end
    }
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){
                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            String firstName = jsonObj.getString("firstname");
                            String lastname = jsonObj.getString("lastname");
                            String mobileNumber = jsonObj.getString("mobile");
                            String id = jsonObj.getString("id");

                            alContacts.add(new Contact(id,firstName,lastname,mobileNumber));
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    ca.notifyDataSetChanged();

                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.addContact){
            Intent intent = new Intent(getBaseContext(),CreateContact.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
