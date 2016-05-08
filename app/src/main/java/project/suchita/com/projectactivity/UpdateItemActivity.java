package project.suchita.com.projectactivity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class UpdateItemActivity extends AppCompatActivity {

    ListView lvItem;
    List<Feed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvItem = (ListView) findViewById(R.id.lvItem);

    }

    @Override
    protected void onResume() {
        super.onResume();
        feeds = FeedOrm.getFeedList(this);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_row, feeds);
        lvItem.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }



    class CustomAdapter extends ArrayAdapter {
        List<Feed> feeds;
        FeedViewHolder feedViewHolder;
        Context context;

        public CustomAdapter(Context context, int resource, List<Feed> feeds) {
            super(context, resource);
            this.feeds = feeds;
            this.context = context;
        }

        @Override
        public int getCount() {
            return feeds.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

                // inflate the layout
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_row, parent, false);

                // well set up the ViewHolder
                feedViewHolder = new FeedViewHolder();
                feedViewHolder.itemName = (TextView) convertView.findViewById(R.id.itemname);
                feedViewHolder.description = (TextView) convertView.findViewById(R.id.description);
                feedViewHolder.price = (TextView) convertView.findViewById(R.id.price);

                // store the holder with the view.
                convertView.setTag(feedViewHolder);

            } else {
                // we've just avoided calling findViewById() on resource everytime
                // just use the viewHolder
                feedViewHolder = (FeedViewHolder) convertView.getTag();
            }

            Feed feed = feeds.get(position);
            feedViewHolder.itemName.setText(feed.getItem_name());
            feedViewHolder.description.setText(feed.getDescription());
            feedViewHolder.price.setText("Rs. " + feed.getPrice());

            return convertView;
        }


        class FeedViewHolder {
            TextView itemName, description, price, unit;
        }
    }
}
