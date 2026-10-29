package com.arijit.rise.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.arijit.rise.R;
import com.google.android.material.card.MaterialCardView;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DayViewHolder> {
    private final List<LocalDate> dates;
    private int selectedPosition;

    public CalendarAdapter(List<LocalDate> dates, int selectedPosition) {
        this.dates = dates;
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_day, parent, false);

        return new DayViewHolder(view);
    }


    @Override
    public void onBindViewHolder(
            @NonNull DayViewHolder holder,
            int position) {

        LocalDate date = dates.get(position);

        holder.dayName.setText(
                date.getDayOfWeek()
                        .getDisplayName(
                                TextStyle.SHORT,
                                Locale.ENGLISH
                        ).toUpperCase()
        );

        holder.dayNumber.setText(
                String.valueOf(date.getDayOfMonth())
        );

        boolean selected = position == selectedPosition;

        if (selected) {
            holder.dayCard.setStrokeColor(
                    Color.parseColor("#FFA000")
            );

            holder.dayCard.setStrokeWidth(5);

            holder.dayName.setTextColor(
                    Color.parseColor("#FFA000")
            );

            holder.dayNumber.setTextColor(
                    Color.WHITE
            );

            holder.dayIndicator.setBackgroundTintList(
                    ColorStateList.valueOf(
                            Color.parseColor("#FFA000")
                    )
            );

            holder.glow.setVisibility(View.VISIBLE);

        } else {

            holder.dayCard.setStrokeColor(
                    Color.parseColor("#2D241F")
            );

            holder.dayCard.setStrokeWidth(2);

            holder.dayName.setTextColor(
                    Color.parseColor("#9B8D83")
            );

            holder.dayNumber.setTextColor(
                    Color.parseColor("#8C8078")
            );

            holder.dayIndicator.setBackgroundTintList(
                    ColorStateList.valueOf(
                            Color.parseColor("#241D18")
                    )
            );

            holder.glow.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {

            int oldPosition = selectedPosition;
            selectedPosition = position;

            notifyItemChanged(oldPosition);
            notifyItemChanged(selectedPosition);

        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView dayCard;
        TextView dayName;
        TextView dayNumber;
        View dayIndicator;
        View glow;

        DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayCard = itemView.findViewById(R.id.day_card);
            dayName = itemView.findViewById(R.id.day_name);
            dayNumber = itemView.findViewById(R.id.day_number);
            dayIndicator = itemView.findViewById(R.id.day_indicator);
            glow = itemView.findViewById(R.id.glow);
        }
    }
}
