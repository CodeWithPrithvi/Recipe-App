
package com.prithviraj.recipeapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;



public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<Meal> meals ;

    public CategoryAdapter(List<Meal> meals, RecyclerViewInterface recyclerViewInterface) {
        this.meals = meals;
        this.recyclerViewInterface= recyclerViewInterface;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitems, parent, false);
        return new CategoryViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Meal meal = meals.get(position);
        holder.textView.setText(meal.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}



class CategoryViewHolder extends RecyclerView.ViewHolder{

    LinearLayout linearLayout;
    ImageView imageView;
    TextView textView;
    public CategoryViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.item);
        imageView = itemView.findViewById(R.id.foodimage2);
        textView = itemView.findViewById(R.id.fooddescription2);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null)
                {
                    int pos= getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }

                }
            }
        });
    }
}


