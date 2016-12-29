package com.example.shrukul.farmour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by shrukul on 20/1/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        TextView amount;
        ImageView type;
        RatingBar rating;
        private static Context mc;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            try {
                personName = (TextView) itemView.findViewById(R.id.recent_item_name);
            } catch(Exception E){
                E.printStackTrace();
            }
//            personAge = (TextView) itemView.findViewById(R.id.recent_item_number);
            personPhoto = (ImageView) itemView.findViewById(R.id.recent_item_pic);
            amount = (TextView) itemView.findViewById(R.id.amt);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            mc = itemView.getContext();
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Person person = persons.get(i);

            Intent it = new Intent(mc, Seller.class);
            it.putExtra("name", person.getName());
            it.putExtra("phone", person.getPhone());
            it.putExtra("price", person.getAmount());
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) mc, (View) personPhoto, "profile");
            mc.startActivity(it, options.toBundle());
        }
    }

    private static List<Person> persons;

    RVAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recentslist_item_layout, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
//        personViewHolder.personAge.setText(persons.get(i).phone);
        personViewHolder.rating.setRating(2 + new Random().nextInt(3));
        personViewHolder.amount.setText("₹ " + persons.get(i).amount);
//        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        personViewHolder.personPhoto.setImageResource(R.drawable.profile);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
