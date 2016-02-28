package butterknife.transitapp.StopActivityRelated;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.transitapp.MainActivity;
import butterknife.transitapp.Model.Stop;
import butterknife.transitapp.R;

/**
 * Created by Trevor on 2/28/2016.
 */
public class StopRecyclerAdapter extends RecyclerView.Adapter<StopRecyclerAdapter.ViewHolder> {

    private Stop stopData;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case

        CardView busCardView;
        TextView routeNumberText;
        TextView routeDirText;
        TextView routeNameText;
        TextView nextTimesText;


        public ViewHolder(View v) {
            super(v);

            busCardView = (CardView) itemView.findViewById(R.id.bus_card_view);
            routeNumberText = (TextView) busCardView.findViewById(R.id.route_number);
            routeDirText = (TextView) busCardView.findViewById(R.id.route_direction);
            routeNameText = (TextView) busCardView.findViewById(R.id.route_name);
            nextTimesText = (TextView) busCardView.findViewById(R.id.next_times);

            busCardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Clicking Works", Snackbar.LENGTH_LONG).show();
            /*if (v != nextTimesText) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("busNumber", busNumberText.getText());
                intent.putExtra("stopName", stopNameText.getText());
                intent.putExtra("routeName", routeNameText.getText());
                intent.putExtra("nextTimes", nextTimesText.getText());
                v.getContext().startActivity(intent);
            } else {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("busNumber", busNumberText.getText());
                intent.putExtra("stopName", stopNameText.getText());
                intent.putExtra("routeName", routeNameText.getText());
                intent.putExtra("nextTimes", nextTimesText.getText());
                v.getContext().startActivity(intent);
            }*/
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StopRecyclerAdapter(Stop stopData) {
        this.stopData = stopData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StopRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                     int viewType) {

        final ViewHolder vh;

        if (viewType == 'N') {
            // create a new bus_cardview
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_nightbus, viewGroup, false);
            // set the view's size, margins, paddings and layout parameters
            vh = new ViewHolder(v);
        } else if (viewType == 'C') {
            // create a new train_cardview
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_shuttle, viewGroup, false);
            // set the view's size, margins, paddings and layout parameters
            vh = new ViewHolder(v);
        } else {
            // create a new train_cardview
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_bus, viewGroup, false);
            // set the view's size, margins, paddings and layout parameters
            vh = new ViewHolder(v);
        }

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.routeNumberText.setText(stopData.getBuses().get(position).getRouteNo());
        holder.routeDirText.setText(stopData.getBuses().get(position).getDirection());
        holder.routeNameText.setText(stopData.getBuses().get(position).getSchedules().get(0).getRouteName());
        holder.nextTimesText.setText(stopData.getNextTimes(stopData.getBuses().get(position).getRouteNo()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return stopData.getBuses().size();
    }

    @Override
    public int getItemViewType(int position) {
        return stopData.getBuses().get(position).getGetRouteNo().charAt(0);
    }

}
