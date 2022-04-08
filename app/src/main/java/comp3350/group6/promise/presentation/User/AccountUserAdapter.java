package comp3350.group6.promise.presentation.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.AccountUser;

public class AccountUserAdapter extends RecyclerView.Adapter<AccountUserAdapter.ViewHolder> {

    private List<AccountUser> accountUserList;
    private OnUserClickListener onUserClickListener;
    private int itemLayout = R.layout.user_list_item;

    public AccountUserAdapter(Context context, List<AccountUser> accountUserList, OnUserClickListener onUserClickListener) {
        this.accountUserList = accountUserList;
        this.onUserClickListener = onUserClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view, this.onUserClickListener);
    }

    @Override
    public void onBindViewHolder(AccountUserAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        AccountUser accountUser = accountUserList.get(position);

        viewHolder.nameView.setText(accountUser.getUserName());
        viewHolder.emailView.setText(accountUser.getEmail());
        viewHolder.avatarLetter.setText(Character.toString(accountUser.getUserName().charAt(0)).toUpperCase());
    }

    @Override
    public int getItemCount() {
        if (accountUserList != null) {
            return accountUserList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout container;
        private TextView nameView;
        private TextView emailView;
        private TextView avatarLetter;
        private ImageView avatar;
        private OnUserClickListener clickListener;

        public ViewHolder(View itemView, OnUserClickListener onUserClickListener) { // constructor
            super(itemView);

            this.container = itemView.findViewById(R.id.user_container);
            this.nameView = itemView.findViewById(R.id.user_name);
            this.emailView = itemView.findViewById(R.id.user_email);
            this.avatar = itemView.findViewById(R.id.avatar);
            this.avatarLetter = itemView.findViewById(R.id.avatar_letter);

            this.clickListener = onUserClickListener;

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onUserClick(getAbsoluteAdapterPosition());
        }

    }

    public interface OnUserClickListener {
        void onUserClick(int position);
    }
}
