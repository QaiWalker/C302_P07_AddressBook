package android.myapplicationdev.com.c302_p07_addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> contacts;
    private Context context;
    private TextView tvName;
    private TextView tvContact;


    public ContactAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        contacts = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);


        tvName = (TextView) rowView.findViewById(R.id.textViewName);
        tvContact = (TextView) rowView.findViewById(R.id.textViewNumber);

        Contact currentContact = contacts.get(position);
        tvName.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
        tvContact.setText(currentContact.getMobileNumber());

        return rowView;
    }
}